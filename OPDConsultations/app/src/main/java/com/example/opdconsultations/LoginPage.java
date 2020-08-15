package com.example.opdconsultations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    EditText ed_email,ed_password;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginup);
        FirebaseApp.initializeApp(this);

        ed_email=findViewById(R.id.email_edittext);
        ed_password=findViewById(R.id.pasword_edittext);
        progressBar=findViewById(R.id.progress_bar1);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance();



    }
    public void UserLogin() {

        String email_login=ed_email.getText().toString();
        String pass_login=ed_password.getText().toString();

        if(email_login.isEmpty())
        {
            ed_email.setError("Please fill Email");
            ed_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_login).matches())
        {
            ed_email.setError("Please fill Valid Email");
            ed_email.requestFocus();
            return;
        }
        if(pass_login.isEmpty())
        {
            ed_password.setError("Please fill password");
            ed_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email_login,pass_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent=new Intent(LoginPage.this,MainPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(LoginPage.this,MainPage.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_button:
                UserLogin();
                break;
            case R.id.sign_up_button:
                finish();
                startActivity(new Intent(this, SignUpPage.class));
                break;
        }

    }
}
