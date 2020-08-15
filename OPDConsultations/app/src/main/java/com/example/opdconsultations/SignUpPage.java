package com.example.opdconsultations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {

    EditText mEmail,mUserId,mPhonenum,mPassword;
    ProgressBar pbar;
    private FirebaseAuth mAuth;
    Button mSignUp,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        mSignUp=findViewById(R.id.signup_button);
        login=findViewById(R.id.loginup_button);
        mEmail = findViewById(R.id.email2_edittext);
        pbar=findViewById(R.id.progress_bar);
        mSignUp.setOnClickListener(this);
        login.setOnClickListener(this);

        mPassword = findViewById(R.id.pasword2_edittext);


        mAuth = FirebaseAuth.getInstance();

    }

    public void UserRegister()
    {
        String email=mEmail.getText().toString().trim();
//        String userid=mUserId.getText().toString().trim();
//        String phonenum=mPhonenum.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        // text to show erroe messgaes
        if(email.isEmpty())
        {
            mEmail.setError("Email is Required");
            mEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mEmail.setError("Enter a valid email");
            mEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            mPassword.setError("Password is Required");
            mPassword.requestFocus();
            return;
        }
        if(password.length()<4)
        {
            mPassword.setError("Password length should be greater than 4");
            mPassword.requestFocus();
            return;
        }
//        if(userid.isEmpty())
//        {
//            mUserId.setError("userId is Required");
//            mUserId.requestFocus();
//            return;
//        }
//        if(phonenum.isEmpty())
//        {
//            mPhonenum.setError("phonenumber is Required");
//            mPhonenum.requestFocus();
//            return;
//        }
        pbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pbar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {

                    finish();

                    Intent intent=new Intent(SignUpPage.this,MainPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                if(task.getException() instanceof FirebaseAuthUserCollisionException)
                {
                    Toast.makeText(getApplicationContext(),"You Are Already REgistered",Toast.LENGTH_LONG).show();

                }
                else
                {
                    String es=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),es,Toast.LENGTH_LONG).show();
                }
            }});
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.loginup_button:
                finish();
                startActivity(new Intent(this,MainPage.class));
                break;
            case R.id.signup_button:
                UserRegister();
                break;
        }

    }
}