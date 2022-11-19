package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComplaintsList extends AppCompatActivity {
    private FirebaseFirestore fstore;
    private Button getcomplain;
    private ArrayList complains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        fstore=FirebaseFirestore.getInstance();
        getcomplain=findViewById(R.id.getcomplain);
        getcomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnGetComplain();
            }
        });
    }
    private void OnGetComplain(){

    }
}