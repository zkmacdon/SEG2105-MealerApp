package com.app.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupForm extends AppCompatActivity {
    private FirebaseAuth mauth;
    private EditText fullname,phonenumber,email,password,address,cardnum,cardex,cardcvc;
    private CheckBox isCook;
    private Button signup_btn;
    private TextView login_text;
    private FirebaseFirestore fstore;

    private String isuser;
    private String iscook;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        mauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        email=findViewById(R.id.signup_mail);
        password=findViewById(R.id.signup_password);
        signup_btn=findViewById(R.id.signup_btn);
        login_text=findViewById(R.id.login_text);
        fullname=findViewById(R.id.signup_name);
        phonenumber=findViewById(R.id.signup_phone);
        address=findViewById(R.id.signup_address);
        cardcvc=findViewById(R.id.signup_cardcvc);
        cardnum=findViewById(R.id.signup_cardnumber);
        cardex=findViewById(R.id.signup_cardexpiredate);
        isCook=findViewById(R.id.signup_checkBox);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupForm.this, LoginForm.class));
            }
        });
        isCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCook.isChecked()){
                    iscook="1";
                }

            }
        });
    }
    private boolean valemail(String input){
        String emailRegex="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat=Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();


    }
    private void Register() {

        String user=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String name=fullname.getText().toString().trim();
        String phone=phonenumber.getText().toString().trim();
        String add=address.getText().toString().trim();
        String cardno=cardnum.getText().toString().trim();
        String cardcv=cardcvc.getText().toString().trim();
        String exdate=cardex.getText().toString().trim();
        try {
            Integer a=Integer.parseInt(phone);
        }catch(NumberFormatException e){
            phonenumber.setError("Invalid phone number");
        }
        if(!valemail(user)){
            email.setError("invalid email address");
        }
        if(pass.isEmpty()){
            password.setError("Password can not be empty");
        }
        if(name.isEmpty()){
            fullname.setError("Full name can not be empty");
        }
        if(add.isEmpty()){
            address.setError("Invalid address");
        }
        if(cardno.isEmpty()||cardno.length()!=16){
            cardnum.setError("invalid card number");

        }
        if(cardcv.isEmpty()|| cardcv.length()!=3){
            cardcvc.setError("invalid cvc");
        }
        if(exdate.isEmpty()){
            cardex.setError("invalid expire date");
        }

        else{
            mauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        FirebaseUser currentuser=mauth.getCurrentUser();

                        Toast.makeText(SignupForm.this,"User signed up successfully", Toast.LENGTH_SHORT).show();

                        String userid=currentuser.getUid();
                        DocumentReference df= fstore.collection("Users").document(userid);
                        Map<String,Object> userInfo=new HashMap<>();
                        userInfo.put("FullName",fullname.getText().toString());
                        userInfo.put("UserEmail",email.getText().toString());
                        userInfo.put("PhoneNumber",phonenumber.getText().toString());
                        userInfo.put("Address",address.getText().toString());
                        userInfo.put("Card number",cardnum.getText().toString());
                        userInfo.put("Card expire date", cardex.getText().toString());
                        userInfo.put("Card cvc", cardcvc.getText().toString());
                        if(iscook=="1"){
                            userInfo.put("isuser",null);
                            userInfo.put("iscook","1");
                        }
                        else{
                            userInfo.put("isuser","1");
                            userInfo.put("iscook",null);
                        }

                        df.set(userInfo);
                        startActivity(new Intent(getApplicationContext(),LoginForm.class));
                        finish();

                    }
                    else{
                        Toast.makeText(SignupForm.this, "Sign up Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }});

        }
    }
}