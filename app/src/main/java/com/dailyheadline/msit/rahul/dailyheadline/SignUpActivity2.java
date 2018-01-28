package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity2 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        mAuth = FirebaseAuth.getInstance();

        passwordBox = (EditText)findViewById(R.id.password) ;

        Button next = (Button)findViewById(R.id.passwordNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });
    }

    private void nextStep() {

        String email = getIntent().getStringExtra("email");
        String password = passwordBox.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUpActivity2.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity2.this, LoginActivity.class));
                            }

                            Toast.makeText(SignUpActivity2.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(SignUpActivity2.this, SignUpActivity3.class));
        finish();
    }

}
