package com.app.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private Button logout_btn;
    private Button msgs_btn;
    private Button search_btn;
    private EditText search;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("user");
        logout_btn=findViewById(R.id.logOutButton);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        search = findViewById(R.id.search_text);
        msgs_btn = findViewById(R.id.messagesButton);
        search_btn = findViewById(R.id.searchButton);
        search_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Search(search.getText().toString());
            }
        });
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(WelcomeActivity.this,LoginForm.class));
        finish();
    }

    private void Search(String searchContent){

        Intent i = new Intent(getApplicationContext(), SearchListActivity.class);
        i.putExtra("searchcontent", searchContent);
        i.putExtra("user",userName);
        startActivity(i);
    }

}