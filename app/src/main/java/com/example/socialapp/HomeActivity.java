package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.socialapp.daos.PostDao;
import com.example.socialapp.models.Post;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;

public class HomeActivity extends AppCompatActivity {

    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,CreatePostActivity.class);
                startActivity(intent);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        PostDao postDao = new PostDao();
         CollectionReference postsCollections = postDao.postCollection;
        Query query = postsCollections.orderBy("currentTime", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Post> recyclerViewOptions = new FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post.class).build();

        adapter = new PostAdapter(recyclerViewOptions,getApplicationContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}