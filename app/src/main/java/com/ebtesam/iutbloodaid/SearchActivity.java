package com.ebtesam.iutbloodaid;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    String email,password;
    String bg[];
    String bldgrp[]={"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};;
    private Spinner bgroup;
    Button searchbtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bg=getResources().getStringArray(R.array.blood_groups);

        bgroup=findViewById(R.id.bg);
        searchbtn=findViewById(R.id.searchbtn);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bldgrp);
        bgroup.setAdapter(arrayAdapter);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bgroup.setAdapter(arrayAdapter);


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgroup!=null) {
                    Intent resultIntent = new Intent(SearchActivity.this, ResultActivity.class);
                    resultIntent.putExtra("bloodgp", bgroup.getSelectedItem().toString());
                    startActivity(resultIntent);
                } else {
                    Toast.makeText(SearchActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    /*private void search(){
        Query q=mDatabase.orderByChild("name");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Intent resultIntent = new Intent(SearchActivity.this, ResultActivity.class);
                    resultIntent.putExtra("bloodgp", bgroup.getSelectedItem().toString());
                    startActivity(resultIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
