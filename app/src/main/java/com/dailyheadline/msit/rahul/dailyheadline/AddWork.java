package com.dailyheadline.msit.rahul.dailyheadline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        final Spinner sem = (Spinner) findViewById(R.id.sem);
        ArrayAdapter<CharSequence> SemAdapter = ArrayAdapter.createFromResource(this,
                R.array.sem, R.layout.dropdown_look);
        SemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(SemAdapter);

        final Spinner branch = (Spinner) findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.branch, R.layout.dropdown_look);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);
    }
}
