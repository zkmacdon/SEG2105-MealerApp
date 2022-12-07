package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import java.util.ArrayList;

public class ComplaintsList extends AppCompatActivity {
    private FirebaseFirestore fstore;
    private ListView complaintsListView;
    private ArrayList<Complaint> complaintsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        complaintsListView = findViewById(R.id.complaints_list_view);
        fstore=FirebaseFirestore.getInstance();

        OnGetComplaint();

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
