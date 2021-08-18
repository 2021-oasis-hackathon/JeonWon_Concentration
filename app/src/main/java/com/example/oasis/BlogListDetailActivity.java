package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogListDetailActivity extends AppCompatActivity {

    private static final String TAG = "BlogListDetailActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<BlogCourse> blogCourseList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    String userNickName;
    String key;
    String childKey;
    String likeKey;
    String nickName;

    private TextView title, likeCount, content, hashTag, location;
    private ImageView like;

    List<String> likeUserList = new ArrayList<>();

    String likeKey2;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list_detail);

        title = (TextView) findViewById(R.id.title);
        likeCount = (TextView) findViewById(R.id.likeCount);
        content = (TextView) findViewById(R.id.content);
        hashTag = (TextView) findViewById(R.id.hashTag);
        location = (TextView) findViewById(R.id.location);

        like = (ImageView) findViewById(R.id.like);
        like.setEnabled(true);
        like.setClickable(true);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.blogListDetailActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new BlogListDetailActivityAdapter(blogCourseList);
        recyclerView.setAdapter(mAdapter);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userNickName.equals(nickName)) {
                    return;
                }


                for (String user : likeUserList) {
                    if (user.equals(userNickName)) {
                        count++;
                        break;
                    }
                }



                if (count == 0) {
                    like.setImageResource(R.drawable.like);
                    myRefBlog.child(key).child(likeKey).child(likeKey2).setValue(userNickName);
                    // myRefBlog.child("전주시").child("blog").child(key).child(likeKey).child(likeKey2).setValue(userNickName);
                    count = 0;
                } else {
                    like.setImageResource(R.drawable.nolike);
                    myRefBlog.child(key).child(likeKey).child(likeKey2).removeValue();
                    //             myRefBlog.child("전주시").child("blog").child(key).child(likeKey).child(likeKey2).removeValue();
                    count = 0;
                }

            }
        });

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);

        userNickName = sf.getString("nickName", null);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        childKey = intent.getStringExtra("childKey");
        likeKey = intent.getStringExtra("likeKey");

        //  likeKey2 = myRefBlog.child("전주시").child("blog").child(key).child(likeKey).push().getKey();
        likeKey2 = myRefBlog.child(key).child(likeKey).push().getKey();


    }

    @Override
    public void onStart() {
        super.onStart();

        myRefBlog.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BlogMain blogMain = snapshot.getValue(BlogMain.class);

                title.setText(blogMain.getTitle());
                content.setText(blogMain.getContent());
                likeCount.setText(blogMain.getLike());
                hashTag.setText(blogMain.getHashTag());
                nickName = blogMain.getNickName();
                location.setText("(" + blogMain.getLocation1() + " " + blogMain.getLocation2() + ")");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        blogCourseList.clear();
        myRefBlog.child(key).child(childKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BlogCourse blogCourse = dataSnapshot.getValue(BlogCourse.class);
                    blogCourseList.add(blogCourse);
                    Log.d(TAG, String.valueOf(dataSnapshot.getKey()));
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRefBlog.child(key).child(likeKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                likeUserList.clear();
                System.out.println(snapshot.getChildrenCount());
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d(TAG, "one");
                    String likeUser = snapshot1.getValue(String.class);
                    likeUserList.add(likeUser);

                    if (userNickName.equals(likeUser)) {
                        like.setImageResource(R.drawable.like);
                        likeKey2 = snapshot1.getKey();
                        Log.d(TAG, snapshot1.getKey());
                    }
                }
                myRefBlog.child(key).child("like").setValue(String.valueOf(likeUserList.size() - 1));
                likeCount.setText(String.valueOf(likeUserList.size() - 1));


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*

        @Override
    public void onStart() {
        super.onStart();

        myRefBlog.child("전주시").child("blog").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BlogMain blogMain = snapshot.getValue(BlogMain.class);

                title.setText(blogMain.getTitle());
                content.setText(blogMain.getContent());
                likeCount.setText(blogMain.getLike());
                hashTag.setText(blogMain.getHashTag());
                nickName = blogMain.getNickName();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        blogCourseList.clear();
        myRefBlog.child("전주시").child("blog").child(key).child(childKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BlogCourse blogCourse = dataSnapshot.getValue(BlogCourse.class);
                    blogCourseList.add(blogCourse);
                    Log.d(TAG, String.valueOf(dataSnapshot.getKey()));
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRefBlog.child("전주시").child("blog").child(key).child(likeKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                likeUserList.clear();
                System.out.println(snapshot.getChildrenCount());
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d(TAG, "one");
                    String likeUser = snapshot1.getValue(String.class);
                    likeUserList.add(likeUser);

                    if (userNickName.equals(likeUser)) {
                        like.setImageResource(R.drawable.like);
                        likeKey2 = snapshot1.getKey();
                        Log.d(TAG, snapshot1.getKey());
                    }
                }
                myRefBlog.child("전주시").child("blog").child(key).child("like").setValue(String.valueOf(likeUserList.size() - 1));
                likeCount.setText(String.valueOf(likeUserList.size() - 1));


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

     */
}