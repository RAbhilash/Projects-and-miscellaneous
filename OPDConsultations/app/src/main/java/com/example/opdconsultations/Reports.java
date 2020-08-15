package com.example.opdconsultations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Reports extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference reportsref = db.collection("ListOfReports");
    DocumentReference finalreports;
    View mylayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        getReports();
    }
        public void getReports() {

            for (int i = 1; i <=6; i++) {
                final String filePath="Report"+i;
                finalreports = reportsref.document(filePath);
                finalreports.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String id=documentSnapshot.getString("id");
                         String name = documentSnapshot.getString("name");
                        String type = documentSnapshot.getString("type");
                        makeView(id,name,type);
                    }
                }
                );
            }

    }

        @SuppressLint("ResourceType")
        public void makeView(String ids,String name,String type)
        {
            int id=Integer.parseInt(ids);
            LinearLayout.LayoutParams layoutParams = new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 15);
            LinearLayout deflayout = (LinearLayout) findViewById(R.id.report_linear_layout);
            mylayout = getLayoutInflater().inflate(R.layout.reports, null);
            mylayout.setId(id);
            View view_ = new View(this);
            view_.setLayoutParams(layoutParams);
            deflayout.addView(mylayout);
            deflayout.addView(view_);

            TextView nameH=mylayout.findViewById(R.id.show_name);
            TextView typeH=mylayout.findViewById(R.id.show_type);

            nameH.setText(name);
            typeH.setText(type);
        }
}
