package com.dailyheadline.msit.rahul.dailyheadline;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private FirebaseAuth mAuth;
    private String uid;
    private Query node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //--------------------------------Layout Manager and Recycler View-----------------------------//

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view) ;
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        //--------------------------------------------------------------------------------------------//

        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddWorkActivity.class));
            }
        });


        //---------------------------Recycler Adapter--------------------------------------------------------------------------------------------------------

        FirebaseRecyclerAdapter<PostDisplayModel,PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostDisplayModel, PostViewHolder>(
                PostDisplayModel.class,
                R.layout.card,
                PostViewHolder.class,
                node
        ) {
            @Override
            protected void onBindViewHolder(PostViewHolder holder, int position, PostDisplayModel model) {

            }

            @Override
            public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }


        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                return true;
            case R.id.log_out:
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,WelcomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


    }
}
