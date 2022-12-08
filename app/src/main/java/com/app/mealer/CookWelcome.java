package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class




CookWelcome extends AppCompatActivity {
    //private FirebaseAuth mauth;
    private Button logout_btn;
    private Button meals_btn;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_welcome);
        logout_btn=findViewById(R.id.logOutButton);
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("user");

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        meals_btn = findViewById(R.id.mealsButton);
        meals_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), MealsActivity.class);
                i.putExtra("user", userName);
                startActivity(i);

            }
          }
        );

    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(CookWelcome.this,LoginForm.class));
        finish();
    }
    }
