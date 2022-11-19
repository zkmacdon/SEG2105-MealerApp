package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MealsActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private Button meal_adder_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        meal_adder_btn = findViewById(R.id.mealsButton2);
        meal_adder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MealsActivity.this, MealFormActivity.class));
            }
        });
    }
}