package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;

public class ComplaintsList extends AppCompatActivity {
    private FirebaseFirestore fstore;

    private ListView complaintsListView;
    private ArrayList<Complaint> complaintsList;

    private Button getcomplain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        complaintsListView = findViewById(R.id.complaints_list_view);
        fstore=FirebaseFirestore.getInstance();
        complaintsList=new ArrayList<>();


        OnGetComplain();

        getcomplain=findViewById(R.id.getcomplain);
        getcomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnGetComplain();
            }
        });
    }

    private void OnGetComplain(){
        fstore.collection("Complains").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            complaintsList.clear();
                            List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : lst) {
                                String a=d.get("cookName").toString();
                                String b=d.get("complaint").toString();
                                Log.d("hey",d.get("complaint").toString());
//                                Complaint c = d.toObject(Complaint.class);
                                Complaint c=new Complaint(a,b);
                                complaintsList.add(c);
                            }
                            ComplaintAdapter adapter = new ComplaintAdapter(ComplaintsList.this, complaintsList );
                            complaintsListView.setAdapter(adapter);
                        }
                        else{
                            Toast.makeText(ComplaintsList.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ComplaintsList.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });


        }
    }
