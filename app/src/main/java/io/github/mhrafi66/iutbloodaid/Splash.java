package io.github.mhrafi66.iutbloodaid;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                FirebaseUser user=mAuth.getCurrentUser();
                if (user != null && user.isEmailVerified()) {
                    Intent loggedinIntent=new Intent(Splash.this,ChooseActivity.class);
                    startActivity(loggedinIntent);
                    Splash.this.finish();
                }
                else {
                    Intent loginIntent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(loginIntent);
                    Splash.this.finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}
