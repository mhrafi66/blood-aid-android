package com.ebtesam.iutbloodaid;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WaitApproval extends AppCompatActivity implements View.OnClickListener {
    String email,password;
    ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_approval);

        imgButton=findViewById(R.id.okay);

        imgButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.okay){

            Intent intent = new Intent(getApplicationContext(),ChooseActivity.class);
            startActivity(intent);

        }
    }
}
