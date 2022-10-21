package com.app.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupForm extends AppCompatActivity {
    private FirebaseAuth mauth;
    private EditText email,password;
    private Button signup_btn;
    private TextView login_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        mauth=FirebaseAuth.getInstance();
        email=findViewById(R.id.signup_mail);
        password=findViewById(R.id.signup_password);
        signup_btn=findViewById(R.id.signup_btn);
        login_text=findViewById(R.id.login_text);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupForm.this, LoginForm.class));
            }
        });
    }

    private void Register() {
        String user=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(user.isEmpty()){
            email.setError("Email can not be empty..");
        }
        if(pass.isEmpty()){
            password.setError("Password can not be empty");
        }
        else{
            mauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupForm.this,"User signed up successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupForm.this,WelcomeActivity.class));
                    }
                    else{
                        Toast.makeText(SignupForm.this, "Sign up Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}