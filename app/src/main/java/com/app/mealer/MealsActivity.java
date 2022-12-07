package com.app.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MealsActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private FirebaseFirestore fstore;
    private ListView mealListView;
    private ArrayList<MealData> mealList;
    private Button meal_adder_btn;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("user");
        Log.d("hey",userName);
        meal_adder_btn = findViewById(R.id.mealsButton2);
        meal_adder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MealFormActivity.class);
                i.putExtra("user", userName);
                startActivity(i);
//                startActivity(new Intent(MealsActivity.this, MealFormActivity.class));
            }
        });
        mealListView=findViewById(R.id.listofmeal);
        fstore=FirebaseFirestore.getInstance();
        mealList=new ArrayList<>();
        onGetMeals();

    }
    private void onGetMeals(){
        fstore.collection("Meals").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:lst){
                        String cookid=d.get("CookID").toString();
                        String mdes=d.get("MealDescription").toString();
                        String mname=d.get("MealName").toString();
                        double mprice=Double.parseDouble(d.get("price").toString());
                        boolean mavailable= Boolean.parseBoolean(d.get("isOffered").toString());
                        MealData m=new MealData(cookid,mname,mprice,mavailable,mdes);
                        if(cookid.equals(userName)){mealList.add(m);}

                    }
                    MealAdapter adapter=new MealAdapter(MealsActivity.this,mealList);
                    mealListView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(MealsActivity.this,"No meal found in database",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MealsActivity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}