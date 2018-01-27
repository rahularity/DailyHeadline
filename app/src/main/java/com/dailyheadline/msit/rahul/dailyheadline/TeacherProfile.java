package com.dailyheadline.msit.rahul.dailyheadline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        Button teacherprofile = (Button)findViewById(R.id.teacherprofile);
        teacherprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //back to teacher page
            }
        });
    }
}
