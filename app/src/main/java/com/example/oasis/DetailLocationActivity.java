package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailLocationActivity extends AppCompatActivity {

    private static final String TAG = "DetailLocationActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    private RecyclerView recyclerView;
    private DetailLocationActivityAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public List<BlogMain> blogMainList = new ArrayList<>();
    private List<BlogMain> filterList = new ArrayList<>();

    private TextView title;
    private ImageView search;
    private EditText searchEditText;

    private String getTitle;

    private RelativeLayout hashTagMap, hashTagFoodStore, hashTagHealing, hashTagOutside;
    private TextView hashTagMapText, hashTagFoodStoreText, hashTagHealingText, hashTagOutsideText;

    private ProgressBar progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        Intent intent = getIntent();
        getTitle = intent.getStringExtra("location");

        title = (TextView) findViewById(R.id.title);
        Log.d(TAG, getTitle);
        title.setText(getTitle);

        progress = (ProgressBar) findViewById(R.id.progress);
        search = (ImageView) findViewById(R.id.search);
        search.setColorFilter(Color.parseColor("#42C458"));

        searchEditText = (EditText) findViewById(R.id.searchEditText);

        hashTagMap = (RelativeLayout) findViewById(R.id.hashTagMap);
        hashTagFoodStore = (RelativeLayout) findViewById(R.id.hashTagFoodStore);
        hashTagHealing = (RelativeLayout) findViewById(R.id.hashTagHealing);
        hashTagOutside = (RelativeLayout) findViewById(R.id.hashTagOutside);

        hashTagMapText = (TextView) findViewById(R.id.hashTagMapText);
        hashTagFoodStoreText = (TextView) findViewById(R.id.hashTagFoodStoreText);
        hashTagHealingText = (TextView) findViewById(R.id.hashTagHealingText);
        hashTagOutsideText = (TextView) findViewById(R.id.hashTagOutsideText);



        hashTagMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initTagBackground();
                hashTagMap.setBackgroundResource(R.drawable.hashtag_click_design);
                hashTagMapText.setTextColor(Color.parseColor("#ffffff"));


                filterList.clear();
                for (BlogMain blog : blogMainList) {
                    filterList.add(blog);
                }

                mAdapter = new DetailLocationActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);

            }
        });

        hashTagFoodStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initTagBackground();
                hashTagFoodStore.setBackgroundResource(R.drawable.hashtag_click_design);
                hashTagFoodStoreText.setTextColor(Color.parseColor("#ffffff"));

                filterList.clear();
                for (BlogMain blog : blogMainList) {
                    if (blog.getHashTag().equals("#맛집")) {
                        filterList.add(blog);
                    }
                }

                mAdapter = new DetailLocationActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
            }
        });

        hashTagHealing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initTagBackground();
                hashTagHealing.setBackgroundResource(R.drawable.hashtag_click_design);
                hashTagHealingText.setTextColor(Color.parseColor("#ffffff"));


                filterList.clear();
                for (BlogMain blog : blogMainList) {
                    if (blog.getHashTag().equals("#힐링")) {
                        filterList.add(blog);
                    }
                }

                mAdapter = new DetailLocationActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
            }
        });

        hashTagOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initTagBackground();
                hashTagOutside.setBackgroundResource(R.drawable.hashtag_click_design);
                hashTagOutsideText.setTextColor(Color.parseColor("#ffffff"));

                filterList.clear();
                for (BlogMain blog : blogMainList) {
                    if (blog.getHashTag().equals("#야외")) {
                        filterList.add(blog);
                    }
                }

                mAdapter = new DetailLocationActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initTagBackground();
                String tag = searchEditText.getText().toString();

                if (tag.getBytes().length <= 0 ) {
                    return;
                }

                filterList.clear();
                for (BlogMain blog : blogMainList) {
                    String str = blog.getHashTag().substring(1);
                    if (str.equals(tag)) {
                        filterList.add(blog);
                    }
                }

                mAdapter = new DetailLocationActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
                searchEditText.setText("");
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.detailLocationActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DetailLocationActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DetailLocationActivityAdapter(blogMainList);
        recyclerView.setAdapter(mAdapter);

    }

    public void initTagBackground() {
        hashTagMap.setBackgroundResource(R.drawable.hashtag_design);
        hashTagFoodStore.setBackgroundResource(R.drawable.hashtag_design);
        hashTagHealing.setBackgroundResource(R.drawable.hashtag_design);
        hashTagOutside.setBackgroundResource(R.drawable.hashtag_design);

        // android:textColor="#ACA8A8"

        hashTagMapText.setTextColor(Color.parseColor("#ACA8A8"));
        hashTagFoodStoreText.setTextColor(Color.parseColor("#ACA8A8"));
        hashTagHealingText.setTextColor(Color.parseColor("#ACA8A8"));
        hashTagOutsideText.setTextColor(Color.parseColor("#ACA8A8"));

    }


    @Override
    public void onStart() {
        super.onStart();
        myRefBlog.child("전주시").child("blog").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);
                    blogMainList.add(blogMain);
                    mAdapter.notifyDataSetChanged();
                }

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}