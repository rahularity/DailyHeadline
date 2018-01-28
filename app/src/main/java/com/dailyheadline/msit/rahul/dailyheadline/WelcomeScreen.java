package com.dailyheadline.msit.rahul.dailyheadline;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mAuth = FirebaseAuth.getInstance();



       // Toast.makeText(WelcomeScreen.this,"rahul pandey",Toast.LENGTH_LONG).show();
       // Toast.makeText(WelcomeScreen.this,mAuth.getCurrentUser().getUid().toString(),Toast.LENGTH_LONG).show();



        Button signUp = (Button) findViewById(R.id.sign_up);
        Button login = (Button) findViewById(R.id.login);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this,SignUpActivity1.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this,LoginActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        if(mAuth.getCurrentUser() != null) {

            String uid = mAuth.getCurrentUser().getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        startActivity(new Intent(WelcomeScreen.this, MainActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(WelcomeScreen.this,SignUpActivity3.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
