package io.github.mhrafi66.iutbloodaid;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton findDonor,donorProfile,regDonor;
    public static String dacc;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference donorReference, databaseRef;
    private FirebaseAuth mAuth;
    private String donorVal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        findDonor=findViewById(R.id.findDonor);
        donorProfile=findViewById(R.id.donorProfile);
        regDonor=findViewById(R.id.regDonor);


        findDonor.setOnClickListener(this);
        donorProfile.setOnClickListener(this);
        regDonor.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.findDonor:
                Intent intent = new Intent(ChooseActivity.this,SearchActivity.class);
                startActivity(intent);

                break;

            case R.id.donorProfile:

            Intent profileIntent=new Intent(ChooseActivity.this,ProfileActivity.class);
            startActivity(profileIntent);
                break;

            case R.id.regDonor:
              //  Toast.makeText(ChooseActivity.this,"And crash!",Toast.LENGTH_LONG).show();






                    Intent intent2 = new Intent(ChooseActivity.this, DonorReg.class);
                    startActivity(intent2);





                break;



        }



    }
}
