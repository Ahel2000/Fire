package com.example.socialapp.daos;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.socialapp.models.Post;
import com.example.socialapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PostDao {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseAuth mauth;
    public CollectionReference postCollection=db.collection("posts");
    public void addPost(String text){
        //db=FirebaseFirestore.getInstance();
        mauth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=mauth.getCurrentUser();
        UserDao userDao=new UserDao();
        assert currentUser != null;
        User user = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getPhotoUrl().toString());
        postCollection=db.collection("posts");
        long currentTime = System.currentTimeMillis();
        Post post = new Post(text, user, currentTime);

        db.collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error adding document", e);
                    }
                });
    }

    public  Task<DocumentSnapshot> getPostById(String postId){
        return postCollection.document(postId).get();
    }
}
