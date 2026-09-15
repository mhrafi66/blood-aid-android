package com.ebtesam.iutbloodaid;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameText,emailEditText,passwordEditText,repasswordEditText;
    private ImageButton signUpButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameText=findViewById(R.id.name);
        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        repasswordEditText=findViewById(R.id.confirmpass);
        signUpButton=findViewById(R.id.signup);
        signUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signup){
            userSignUp();
        }

    }

    private void userSignUp() {
        final String name=nameText.getText().toString().trim();
        final String email=emailEditText.getText().toString().trim();
        final String donor="no";
        String password=passwordEditText.getText().toString().trim();
        String repass=repasswordEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Enter a valid Email Address");
            emailEditText.requestFocus();
            return;
        }
        if(password.equals(repass)==false){
            passwordEditText.setError("Passwords do not match");
            //Toast.makeText(SignUpActivity.this,password+"..."+repass,Toast.LENGTH_LONG).show();
            passwordEditText.requestFocus();
            return;
        }
        if(!email.endsWith("@iut-dhaka.edu")){
            emailEditText.setError("Enter your IUT Email Address");
            emailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Enter a valid Email Address");

            emailEditText.requestFocus();
            return;

        }
        if(password.isEmpty()){
            passwordEditText.setError("Enter your password");
            passwordEditText.requestFocus();
            return;
        }
        if(password.length()<6){
            passwordEditText.setError("Minimum length should be 6");
            passwordEditText.requestFocus();
            return;

        }

        else {
            mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Log.i("info","User created");
                    mAuth.getCurrentUser().sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Log.i("info","Verification");
                                Toast.makeText(SignUpActivity.this, "Verification Email Sent", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                        insertInDatabase(name,email,donor);

                        Intent chooseIntent = new Intent(SignUpActivity.this, LoginActivity.class);

                        startActivity(chooseIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Signup Failed, Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }



    public void insertInDatabase(String name,String email, String donor) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("donor",donor);
        mDatabase.setValue(hashMap);
    }
}
