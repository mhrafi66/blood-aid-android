package io.github.mhrafi66.iutbloodaid;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorReg extends AppCompatActivity {
    String bg[];
    EditText sidText, nameText, contactText;
    ImageButton img;
    Spinner spinner;
    String name, sid, phone, bldgrp;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference donorReference;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_reg);
        bg= getResources().getStringArray(R.array.blood_groups);

        spinner = findViewById(R.id.bg);
        sidText = findViewById(R.id.sid);
        nameText = findViewById(R.id.name);
        contactText = findViewById(R.id.phone);
        img = findViewById(R.id.imageButton);


        /*spinner.setOnClickListener(this);
        sidText.setOnClickListener(this);
        nameText.setOnClickListener(this);
        contactText.setOnClickListener(this);
        img.setOnClickListener(this);*/

        insertInDonorDatabase();

    }


    public void storeInDatabase() {
        mAuth = FirebaseAuth.getInstance();
        donorReference = FirebaseDatabase.getInstance().getReference().child("Donors");
        String userId = user.getUid();
        String email = user.getEmail();
        String name = nameText.getText().toString();
        String phone = contactText.getText().toString();
        String bloodgp = spinner.getSelectedItem().toString();
        String sid = sidText.getText().toString();
        String status = "unapproved";
        String elig="not eligible";


        Donor donor = new Donor(userId, email, name, sid, bloodgp, phone, status,elig);
        donorReference.child(bloodgp).push().setValue(donor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(DonorReg.this, "Your application for Donor Registration has been received.", Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(DonorReg.this, WaitApproval.class);
                startActivity(intent2);

            }
        });
    }

    public void insertInDonorDatabase() {
        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imageButton) {
                    name = nameText.getText().toString();
                    sid = sidText.getText().toString();
                    phone = contactText.getText().toString();
                    bldgrp = spinner.getSelectedItem().toString();

                    if (name.isEmpty()) {
                        nameText.setError("Enter your name");
                        nameText.requestFocus();
                        return;
                    }
                    if (sid.isEmpty()) {
                        sidText.setError("Enter your IUT student ID");
                        sidText.requestFocus();
                        return;
                    }
                    if (phone.isEmpty()) {
                        contactText.setError("Enter your contact number");
                        contactText.requestFocus();
                        return;
                    }




                }
                String userId = user.getUid();
                String email=user.getEmail();
                String name = nameText.getText().toString();
                String phone = contactText.getText().toString();
                String bloodgp = spinner.getSelectedItem().toString();
                String sid = sidText.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty() && !bloodgp.isEmpty() && !sid.isEmpty()) {
                    storeInDatabase();
                } else {
                    Toast.makeText(DonorReg.this, "All fields are mandatory. Make sure that you have entered all the required details.", Toast.LENGTH_LONG).show();
                }

            }



        });

    }




}
