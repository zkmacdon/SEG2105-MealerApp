package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SuspendedCook extends AppCompatActivity {
    private FirebaseAuth mauth;
    private FirebaseFirestore fstore;
    private Button logout_btn;
    private String datesus;

    private EditText num_days_suspended; // can be taken from a new Suspensions table in Firebase
    // also, maybe edittext could just be a textview? I don't know how that works
    /* TODO: create a method that instantiates suspension table,
        calls it to find out num days that the cook suspended for */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_cook);
        mauth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        logout_btn = findViewById(R.id.logOutButton2);
        datesus=getdatesus();
        num_days_suspended=findViewById(R.id.suspendedTimeText);
        num_days_suspended.setText(datesus);
        logout_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){logout();}
        });
    }
    private String getdatesus(){
        FirebaseUser currentUser=mauth.getCurrentUser();
        if(currentUser==null){
            return null;
        }
        else {
            String a=currentUser.getUid();
            final String[] result = new String[1];
            DocumentReference df=fstore.collection("Users").document(a);
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    com.google.firebase.Timestamp time= documentSnapshot.getTimestamp("datesus");

                    SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String date=sdf.format(new Date(time.getSeconds()*1000));
                    result[0] =date;
                }
            });
            return result[0];
        }

    };

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(SuspendedCook.this, LoginForm.class));
        finish();
    }
}