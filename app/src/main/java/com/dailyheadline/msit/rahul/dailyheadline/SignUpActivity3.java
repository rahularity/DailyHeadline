package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Objects;

public class SignUpActivity3 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profession_name, R.layout.dropdown_look);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Button next = (Button)findViewById(R.id.professionNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profession = spinner.getSelectedItem().toString();
                if(Objects.equals(profession, "Student"))
                startActivity(new Intent(SignUpActivity3.this, SignUpActivity4Student.class));
                else
                    startActivity(new Intent(SignUpActivity3.this, SignUpActivity4Teacher.class));
            }
        });
    }

}
