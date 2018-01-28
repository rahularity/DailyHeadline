package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailbox;
    private EditText passwordbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button login = (Button)findViewById(R.id.loginButton);
        emailbox = (EditText)findViewById(R.id.email);
        passwordbox = (EditText)findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCheck();
            }
        });
    }

    private void loginCheck() {
        String email  = emailbox.getText().toString();
        String password = passwordbox.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);


                        }
                        else{
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {

        String uid = user.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(LoginActivity.this,SignUpActivity3.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
