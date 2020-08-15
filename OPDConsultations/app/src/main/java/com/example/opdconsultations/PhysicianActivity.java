package com.example.opdconsultations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PhysicianActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference physicianref = db.collection("ListOfPhysicians");
    DocumentReference finalPhysicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician);
        getPhysiciandetails();
    }

        public void getPhysiciandetails() {

            for (int i = 1; i <= 7; i++) {
                String filePath="Physician"+i;
                finalPhysicians = physicianref.document(filePath);
                finalPhysicians.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String id=documentSnapshot.getString("id");
                        String name = documentSnapshot.getString("name");
                        String degrees = documentSnapshot.getString("degrees");
                        String rating = documentSnapshot.getString("rating");
                        String pnum = documentSnapshot.getString("pnum");
                        String imgId=documentSnapshot.getString("imgid");
                        makeView(id,name,degrees,rating,pnum,imgId);
                    }
                });
            }
        }

        @SuppressLint("ResourceType")
        public void makeView(String ids,String name,String degrees,String rating,String phonenum,String imageId)
        {
            int id=Integer.parseInt(ids);
            RelativeLayout.LayoutParams layoutParams = new
                    RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 15);
            LinearLayout deflayout = (LinearLayout) findViewById(R.id.linearLay);
            View mylayout = getLayoutInflater().inflate(R.layout.appointment_data, null);
            mylayout.setId(id);
            View view_ = new View(this);
            view_.setLayoutParams(layoutParams);
            deflayout.addView(mylayout);
            deflayout.addView(view_);


            TextView header=mylayout.findViewById(R.id.header_tv);
            TextView degres=mylayout.findViewById(R.id.distance_tv);
            TextView rat=mylayout.findViewById(R.id.rating_tv);
            TextView pnum=mylayout.findViewById(R.id.address_tv);
            TextView moreinfo=mylayout.findViewById(R.id.moreinfo);
            moreinfo.setVisibility(View.GONE);
            header.setText(name);
            degres.setText(degrees);
            rat.setText(rating);
            pnum.setText(phonenum);


        }

    }
