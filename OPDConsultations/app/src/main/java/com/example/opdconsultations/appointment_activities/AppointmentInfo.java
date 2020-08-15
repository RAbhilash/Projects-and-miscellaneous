package com.example.opdconsultations.appointment_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opdconsultations.PaymentActivity;
import com.example.opdconsultations.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppointmentInfo extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference hospitalListref = db.collection("ListOfHostpitals");
    CollectionReference physicianref = db.collection("ListOfPhysicians");
    DocumentReference finalPhysicians;
    View mylayout;
    int idToCheck;
    Button checkout;

    CheckBox radio,neuro,skins,entS;
    int totalBill;
    String bill="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_info);


        Bundle bundle = getIntent().getExtras();
       idToCheck = bundle.getInt("KEYID");
        getAppointmentDetails();




    }
    public void getAppointmentDetails() {

        for (int i = 1; i <= 7; i++) {
            final String filePath="Hospital"+i;
            finalPhysicians = hospitalListref.document(filePath);
            finalPhysicians.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    final String id = documentSnapshot.getString("id");

                    if (Integer.parseInt(id) == idToCheck) {
                        DocumentReference finalref = hospitalListref.document(filePath).collection("services").document("Departments");
                        finalref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                    String description = documentSnapshot.getString("description");
                                                                    boolean ent = documentSnapshot.getBoolean("ENT");
                                                                    boolean neurology = documentSnapshot.getBoolean("Neurology");
                                                                    boolean radiology = documentSnapshot.getBoolean("Radiology");
                                                                    boolean skin = documentSnapshot.getBoolean("Skin");

                                                                    makeView(id,description,ent,neurology,radiology,skin);
                                                                }
                                                            }
                        );
                    }

                }
            });
        }
    }

    @SuppressLint("ResourceType")
    public void makeView(String ids,String desc,Boolean ent,Boolean neurology,Boolean radiology,Boolean skin)
    {
        int id=Integer.parseInt(ids);
        RelativeLayout.LayoutParams layoutParams = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 15);
        LinearLayout deflayout = (LinearLayout) findViewById(R.id.linearLayout);
        mylayout = getLayoutInflater().inflate(R.layout.appointment_info, null);
        mylayout.setId(id+30);
        View view_ = new View(this);
        view_.setLayoutParams(layoutParams);
        deflayout.addView(mylayout);
        deflayout.addView(view_);


        TextView description=mylayout.findViewById(R.id.show_hosp_desc);
        description.setText(desc);

        entS=mylayout.findViewById(R.id.first_service_chk);
        if(ent)
        {
            entS.setVisibility(View.VISIBLE);
        }
        else
            entS.setVisibility(View.INVISIBLE);
       neuro=mylayout.findViewById(R.id.Third_service_chk);
        if(neurology)
        {
            neuro.setVisibility(View.VISIBLE);
        }
        else
            neuro.setVisibility(View.INVISIBLE);
        radio=mylayout.findViewById(R.id.second_service_chk);
        if(radiology)
        {
            radio.setVisibility(View.VISIBLE);
        }
        else
            radio.setVisibility(View.INVISIBLE);
        skins=mylayout.findViewById(R.id.fourth_service_chk);
        if(skin)
        {
            skins.setVisibility(View.VISIBLE);
        }
        else
            skins.setVisibility(View.INVISIBLE);

        checkout = mylayout.findViewById(R.id.checkout_bt);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(entS.isChecked()) {
                    totalBill = totalBill + 30000;
                    bill=bill+"ENT"+"\n";
                }
                if(radio.isChecked()) {
                    totalBill = totalBill + 50000;
                    bill=bill+"RADIOLOGY"+"\n";
                }
                if(neuro.isChecked()) {
                    totalBill = totalBill + 100000;
                    bill=bill+"NEUROLOGY"+"\n";
                }

                if(skins.isChecked()) {
                    totalBill = totalBill + 40000;
                    bill=bill+"SKIN TREATMENT"+"\n";
                }

                Intent newInt=new Intent(AppointmentInfo.this, PaymentActivity.class);
                newInt.putExtra("BILL",Integer.toString(totalBill));
                newInt.putExtra("bill",bill);
                startActivity(newInt);
            }
        });

    }
}
