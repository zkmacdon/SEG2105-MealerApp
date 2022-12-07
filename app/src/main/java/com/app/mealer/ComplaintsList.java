package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< HEAD
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
=======
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
>>>>>>> 41da3d6f98b002160e888264fcd003785544050e
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;

public class ComplaintsList extends AppCompatActivity {
    private FirebaseFirestore fstore;
<<<<<<< HEAD
    private ListView complaintsListView;
    private ArrayList<Complaint> complaintsList;

=======
    private Button getcomplain;
    private ArrayList complains;
>>>>>>> 41da3d6f98b002160e888264fcd003785544050e
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        complaintsListView = findViewById(R.id.complaints_list_view);
        fstore=FirebaseFirestore.getInstance();
<<<<<<< HEAD

        OnGetComplaint();
=======
        getcomplain=findViewById(R.id.getcomplain);
        getcomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnGetComplain();
            }
        });
    }
    private void OnGetComplain(){
>>>>>>> 41da3d6f98b002160e888264fcd003785544050e

    }
    private void OnGetComplaint(){
        fstore.collection("Complaints").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : lst) {
                            Complaint c = d.toObject(Complaint.class);
                            complaintsList.add(c);
                        }
                    }
                });

        }
    }
