package com.app.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MealFormActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private EditText name_txt, description_txt;
    private TextView price_txt;
    private Switch meal_offered;
    private Button add_meal_btn;
    private boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_form);

        mauth = FirebaseAuth.getInstance();
        add_meal_btn = findViewById(R.id.mealsButton3);
        price_txt = findViewById(R.id.meal_price);
        name_txt = findViewById(R.id.meal_name);
        description_txt = findViewById(R.id.meal_description);
        meal_offered = findViewById(R.id.meal_offered_switch);

        meal_offered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meal_offered.isChecked()) {
                    isChecked = true;
                }
            }
        });

        add_meal_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addMeal(isChecked);
            }
        });



    }

    private void addMeal(boolean checkedStatus){}

}