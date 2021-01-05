package com.example.socialapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.socialapp.models.Post;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends FirestoreRecyclerAdapter<Post, PostAdapter.ViewHolder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param applicationContext
     */
    public PostAdapter(@NonNull FirestoreRecyclerOptions<Post> options, Context applicationContext) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Post model) {
        holder.postText.setText(model.getText());
        holder.userText.setText(model.getUser().getDisplayName());
        Glide.with(holder.userImage.getContext()).load(model.getUser().getDisplayUrl()).circleCrop().into(holder.userImage);
        //holder.likeCount.text = model.likedBy.size.toString()
        holder.createdAt.setText(Utils.getTimeAgo(model.getCurrentTime()));
    }




    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView postText;
        public TextView userText;
        public TextView likeCount;
        public ImageView userImage;
        public ImageView likeButton;
        public TextView createdAt;

        public ViewHolder(View itemView) {
            super(itemView);

            postText = itemView.findViewById(R.id.postTitle);
            userText = itemView.findViewById(R.id.userName);
            likeCount = itemView.findViewById(R.id.likeCount);
            userImage = itemView.findViewById(R.id.userImage);
            likeButton = itemView.findViewById(R.id.likeButton);
            createdAt= itemView.findViewById(R.id.createdAt);
        }
    }
}
