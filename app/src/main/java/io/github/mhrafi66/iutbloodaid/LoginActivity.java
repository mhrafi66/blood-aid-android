package io.github.mhrafi66.iutbloodaid;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private EditText emailEditText,passwordEditText;
    private Button forgotPassword;
    private ImageButton logIn,signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgotPass);
        logIn = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signupButton);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        logIn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.loginButton:
                userLogIn();
                break;

            case R.id.forgotPass:
                final String email=emailEditText.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,"Password reset instructions sent. Please check your e-mail.",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(LoginActivity.this,"Enter a valid e-mail address.",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;

            case R.id.signupButton:
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                break;


        }



    }

    private void userLogIn() {
        String email=emailEditText.getText().toString().trim();
        String password=passwordEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Enter an Email Address");
            emailEditText.requestFocus();
            return;
        }
        if(!email.endsWith("@iut-dhaka.edu")){
            emailEditText.setError("Invalid E-mail Address");
            emailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            Intent mainIntent = new Intent(LoginActivity.this, ChooseActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Please verify your account",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid E-mail/Password combination", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }



    }
}
