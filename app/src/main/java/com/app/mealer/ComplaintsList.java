package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ComplaintsList extends AppCompatActivity {
    private FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        fstore=FirebaseFirestore.getInstance();
    }
    private void OnGetComplain(){

    }
}