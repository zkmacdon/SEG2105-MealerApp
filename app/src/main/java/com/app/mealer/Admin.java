package com.app.mealer;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

public class Admin extends AppCompatActivity {
    private FirebaseAuth mauth;
    private Button logout_btn, complaint_btn;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        logout_btn = findViewById(R.id.logOutButton);
        complaint_btn = findViewById(R.id.complaintButton);
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("user");
        logout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){logout();}
        });
        complaint_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Admin.this, ComplaintsList.class));
            }
        });
    }




    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Admin.this,LoginForm.class));
        finish();
    }
}