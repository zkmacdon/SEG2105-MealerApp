package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SuspendedCook extends AppCompatActivity {
    private FirebaseAuth mauth;
    private Button logout_btn;
    private EditText num_days_suspended; // can be taken from a new Suspensions table in Firebase
    // also, maybe edittext could just be a textview? I don't know how that works
    /* TODO: create a method that instantiates suspension table,
        calls it to find out num days that the cook suspended for */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_cook);
        logout_btn = findViewById(R.id.logOutButton2);
        logout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){logout();}
        });
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(SuspendedCook.this, LoginForm.class));
        finish();
    }
}