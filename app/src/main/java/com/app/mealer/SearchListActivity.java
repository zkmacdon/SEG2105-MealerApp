package com.app.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchListActivity extends AppCompatActivity {
    private FirebaseFirestore fstore;
    private ListView searchListView;
    private ArrayList<Searchresult> searchList;
    private String searchcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        Bundle extras = getIntent().getExtras();
        searchcontent = extras.getString("searchcontent");
        Log.d("hey",searchcontent);
        searchListView=findViewById(R.id.searchlist);
        fstore=FirebaseFirestore.getInstance();
        searchList=new ArrayList<>();
        onGetSearches();
    }

    private void onGetSearches() {
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
                        Searchresult m=new Searchresult(cookid,mname,mprice,mavailable,mdes);
                        if(cookid.contains(searchcontent)||mdes.contains(searchcontent)||mname.contains(searchcontent)&&mavailable)
                        {searchList.add(m);}
                    }
                    if(searchList.isEmpty()){
                        Toast.makeText(SearchListActivity.this,"No meal found",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }
                    SearchAdapter adapter=new SearchAdapter(SearchListActivity.this,searchList);
                    searchListView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(SearchListActivity.this,"No meal found in database",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SearchListActivity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}