package com.example.opdconsultations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opdconsultations.appointment_activities.AppointmentInfo;
import com.example.opdconsultations.data.Appointment;
import com.example.opdconsultations.data.Physician;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference hospitalListref = db.collection("ListOfHostpitals");
    CollectionReference physicianref = db.collection("ListOfPhysicians");
    DocumentReference finalPhysicians;
    View mylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getAppointmentDetails();

    }
    public void getAppointmentDetails() {

        for (int i = 1; i <= 7; i++) {
            String filePath="Hospital"+i;
            finalPhysicians = hospitalListref.document(filePath);
            finalPhysicians.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String id=documentSnapshot.getString("id");
                    String name = documentSnapshot.getString("name");
                    String address = documentSnapshot.getString("address");
                    String rating = documentSnapshot.getString("rating");
                    String distance = documentSnapshot.getString("distance");
                    makeView(id,name,distance,rating,address);
                }
            });
        }
    }

    @SuppressLint("ResourceType")
    public void makeView(String ids,String name,String distance,String rating,String address)
    {
        int id=Integer.parseInt(ids);
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 15);
       LinearLayout deflayout = (LinearLayout) findViewById(R.id.linearLay);
       mylayout = getLayoutInflater().inflate(R.layout.appointment_data, null);
        mylayout.setId(id);
        View view_ = new View(this);
        view_.setLayoutParams(layoutParams);
        deflayout.addView(mylayout);
        deflayout.addView(view_);
        TextView header=mylayout.findViewById(R.id.header_tv);
        TextView dist=mylayout.findViewById(R.id.distance_tv);
        TextView rat=mylayout.findViewById(R.id.rating_tv);
        TextView adress=mylayout.findViewById(R.id.address_tv);
        header.setText(name);
        dist.setText(distance+" Km");
        rat.setText(rating);
        adress.setText(address);

        mylayout.findViewById(R.id.moreinfo).setId(id+10);

    }

    public void onClickMoreInfo(View v)
    {
            int id=v.getId();
            int finalid=id-10;
           // Toast.makeText(this, Integer.toString(finalid), Toast.LENGTH_SHORT).show();
            Intent startAppointmentInfo=new Intent(AppointmentActivity.this, AppointmentInfo.class);
            startAppointmentInfo.putExtra("KEYID",finalid);
            startActivity(startAppointmentInfo);
    }

}

