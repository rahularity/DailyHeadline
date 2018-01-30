package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity4Teacher extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Spinner college;
    private EditText fullName;
    private String fullName_val, college_val, profession_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity4_teacher);

        college = (Spinner)findViewById(R.id.college);
        ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(this,
                R.array.college_name, R.layout.dropdown_look);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        college.setAdapter(collegeAdapter);

        fullName = (EditText)findViewById(R.id.full_name);

        mAuth = FirebaseAuth.getInstance();

        Button teacherRegister = (Button)findViewById(R.id.teacherRegister);
        teacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeRegistration();
            }
        });
    }

    private void completeRegistration() {
        fullName_val = fullName.getText().toString().trim();
        college_val = college.getSelectedItem().toString();
        profession_val = getIntent().getStringExtra("profession");

        if(TextUtils.isEmpty(fullName_val)){
            Toast.makeText(SignUpActivity4Teacher.this, "Please write your name", Toast.LENGTH_SHORT).show();
        }else{
            String uid = mAuth.getCurrentUser().getUid();
            String email = mAuth.getCurrentUser().getEmail();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
            userRef.child("fullName").setValue(fullName_val);
            userRef.child("profession").setValue(profession_val);

            DatabaseReference mainUserRef = FirebaseDatabase.getInstance().getReference()
                    .child(college_val).child("users").child(profession_val).child(uid);

            mainUserRef.child("email").setValue(email);
            mainUserRef.child("fullName").setValue(fullName_val);
            mainUserRef.child("college").setValue(college_val);
            mainUserRef.child("profession").setValue(profession_val);

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(fullName_val).build();
            mAuth.getCurrentUser().updateProfile(profileUpdates);

            Intent intent = new Intent(SignUpActivity4Teacher.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }
}
