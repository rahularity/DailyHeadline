package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity1 extends AppCompatActivity {

    private EditText emailBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        emailBox = (EditText)findViewById(R.id.email);

        Button next = (Button)findViewById(R.id.emailNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailBox.getText().toString();
                startActivity(new Intent(SignUpActivity1.this, SignUpActivity2.class).putExtra("email",email));
            }
        });
    }
}
