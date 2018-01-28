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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity4Student extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Spinner semester, branch, college;
    private EditText fullName, class_serial_no;
    String fullName_val, college_val, semester_val, branch_val, profession_val, classSerial_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity4_student);

        //-----------------------spinners setup---------------------------------------------------------//

        semester = (Spinner) findViewById(R.id.semester);
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.year, R.layout.dropdown_look);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(yearAdapter);

        branch = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(this,
                R.array.branch, R.layout.dropdown_look);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(branchAdapter);

        college = (Spinner)findViewById(R.id.college);
        ArrayAdapter<CharSequence> collegeAdapter = ArrayAdapter.createFromResource(this,
                R.array.college_name, R.layout.dropdown_look);
        collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        college.setAdapter(collegeAdapter);

        //-----------------------spinner setup----------------------------------------------------------//

        fullName = (EditText)findViewById(R.id.full_name);
        class_serial_no = (EditText)findViewById(R.id.class_serial_no);

        mAuth = FirebaseAuth.getInstance();

        Button studentRegister = (Button)findViewById(R.id.studentRegister);
        studentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //after registration send the students to the main activity
                completeRegistration();
            }
        });

    }

    private void completeRegistration() {

        //---------------getting values from every info column----------------------------------------//
        fullName_val = fullName.getText().toString().trim();
        classSerial_val = class_serial_no.getText().toString().trim();
        semester_val = semester.getSelectedItem().toString();
        branch_val = branch.getSelectedItem().toString();
        college_val = college.getSelectedItem().toString();
        profession_val = getIntent().getStringExtra("profession");
        //--------------------------------------------------------------------------------------------//

        if(TextUtils.isEmpty(fullName_val)){
            Toast.makeText(SignUpActivity4Student.this, "Please write your name", Toast.LENGTH_SHORT).show();
        }else{
            String uid = mAuth.getCurrentUser().getUid();
            String email = mAuth.getCurrentUser().getEmail();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
            userRef.child("fullName").setValue(fullName_val);
            userRef.child("profession").setValue(profession_val);

            DatabaseReference mainUserRef = FirebaseDatabase.getInstance().getReference()
                    .child(college_val).child("users").child(profession_val).child(uid);

                //saving personal data in students section
                mainUserRef.child("email").setValue(email);
                mainUserRef.child("fullName").setValue(fullName_val);
                mainUserRef.child("classSerialNo").setValue(classSerial_val);
                mainUserRef.child("semester").setValue(semester_val);
                mainUserRef.child("branch").setValue(branch_val);
                mainUserRef.child("college").setValue(college_val);
                mainUserRef.child("profession").setValue(profession_val);

            Intent intent = new Intent(SignUpActivity4Student.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
