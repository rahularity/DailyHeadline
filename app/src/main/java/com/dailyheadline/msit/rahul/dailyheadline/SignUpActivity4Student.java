package com.dailyheadline.msit.rahul.dailyheadline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SignUpActivity4Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity4_student);

        final Spinner year = (Spinner) findViewById(R.id.year);
        ArrayAdapter<CharSequence> YearAdapter = ArrayAdapter.createFromResource(this,
                R.array.year, R.layout.dropdown_look);
        YearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(YearAdapter);

        final Spinner branch = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.branch, R.layout.dropdown_look);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);

        Button studentRegister = (Button)findViewById(R.id.studentRegister);
        studentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //after registration send the students to the main activity
            }
        });




    }
}
