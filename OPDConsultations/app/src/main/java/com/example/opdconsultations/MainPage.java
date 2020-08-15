package com.example.opdconsultations;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView btnAPP=findViewById(R.id.btnAPP);
        ImageView btnREP=findViewById(R.id.btnREP);
        ImageView btnPAST=findViewById(R.id.btnPAST);
        ImageView btnPHY=findViewById(R.id.btnPHY);
        ImageView btnEMG=findViewById(R.id.btnEMG);
        ImageView btnABT=findViewById(R.id.btnABT);


        btnAPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnAPP is tapped now!");
                startActivity(new Intent(MainPage.this,AppointmentActivity.class));

            }
        });
        btnREP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnREP is tapped now!");
                startActivity(new Intent(MainPage.this,Reports.class));
            }
        });
        btnPAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnPAST is tapped now!");
            }
        });
        btnPHY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnPHY is tapped now!");
                startActivity(new Intent(MainPage.this,PhysicianActivity.class));
            }
        });
        btnEMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnEMG is tapped now!");
                startActivity(new Intent(MainPage.this,Emergency.class));
            }
        });
        btnABT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i( "OPD Consultations", "btnABT is tapped now!");
                startActivity(new Intent(MainPage.this,AboutApp.class));

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"developeremail@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainPage.this,SettingPage.class));
        }
        if (id == R.id.signout) {
             FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainPage.this, LoginPage.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
