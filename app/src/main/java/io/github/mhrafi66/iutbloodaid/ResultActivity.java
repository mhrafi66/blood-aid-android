package io.github.mhrafi66.iutbloodaid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private DatabaseReference donorReference;
    ArrayList<Donor> listOfDonors;

    private RecyclerView recyclerView;
    private EditText editText;
    private DonorAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listOfDonors = new ArrayList<Donor>();
       // editText = findViewById(R.id.editText);
        //editText.setText("");
        recyclerView = findViewById(R.id.result_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        String bgp = getIntent().getStringExtra("bloodgp");

        donorReference = FirebaseDatabase.getInstance().getReference().child("Donors").child(bgp);
        donorReference.keepSynced(true);
        donorReference
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Donor donor = snapshot.getValue(Donor.class);

                            String st=donor.getStatus();
                            String el=donor.getEligibility();
                            if("approved".equals(st) && "eligible".equals(el)) {
                                listOfDonors.add(donor);

                               // editText.setText(donor.getName() + " " + editText.getText());
                            }
                        }
                        recyclerView.setAdapter(new DonorAdapter(getApplicationContext(), listOfDonors));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


    }
}
