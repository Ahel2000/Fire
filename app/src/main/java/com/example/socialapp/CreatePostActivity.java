package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialapp.daos.PostDao;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        final PostDao postDao = new PostDao();
        final EditText postInput=(EditText)findViewById(R.id.postInput);
        Button postButton=(Button)findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input=postInput.getText().toString();
                if(!input.isEmpty()){
                    postDao.addPost(input);
                    CreatePostActivity.super.finish();
                }
            }
        });

    }
}