package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class AddWorkActivity extends AppCompatActivity {

    private EditText topic,subject,info,links;
    private Spinner sem,branch;
    private Button done;
    private FirebaseDatabase database;
    private DatabaseReference collegeRef,semRef,teacherRef;
    private FirebaseAuth mAuth;
    private String college,uid;
    private String topic_val,subject_val,info_val,links_val,sem_val,branch_val,sem_branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        topic = (EditText)findViewById(R.id.topic);
        subject = (EditText)findViewById(R.id.subject);
        info = (EditText)findViewById(R.id.info);
        links = (EditText)findViewById(R.id.links);
        sem = (Spinner)findViewById(R.id.sem);
        branch = (Spinner)findViewById(R.id.branch);
        done = (Button)findViewById(R.id.done);

        college = "MSIT";
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        collegeRef = database.getReference().child(college);



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });

    }

    private void startPosting() {
        topic_val = topic.getText().toString().trim();
        subject_val = subject.getText().toString().trim();
        info_val = info.getText().toString().trim();
        links_val = links.getText().toString().trim();
        //sem_val = sem.getSelectedItem().toString();
        //branch_val = branch.getSelectedItem().toString();
        //sem_branch = sem_val + branch_val;
        String sem_branch = "6CSE1";

        if(!TextUtils.isEmpty(topic_val)){
            semRef = database.getReference().child(college).child("posts").child(sem_branch);
            DatabaseReference newPost =  semRef.push();
            teacherRef = database.getReference().child(college).child("posts").child("teacherPosts").child(uid).child(newPost.getKey());

            PostTheWork(newPost);
            PostTheWork(teacherRef);

        }

    }

    private void PostTheWork(DatabaseReference newPost) {
        newPost.child("topic").setValue(topic_val);
        newPost.child("subject").setValue(subject_val);
        newPost.child("info").setValue(info_val);
        newPost.child("links").setValue(links_val);
        newPost.child("teacherName").setValue(mAuth.getCurrentUser().getDisplayName());
        //newPost.child("sem").setValue(sem_val);
        //newPost.child("branch").setValue(branch_val);
        newPost.child("uid").setValue(uid);
        newPost.child("timeStamp").setValue(ServerValue.TIMESTAMP);
        startActivity(new Intent(AddWorkActivity.this,MainActivity.class));

    }
}
