package com.example.oasis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogListActivity extends Fragment {

    private static final String TAG = "BlogListActivity";

    private View v;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    RecyclerView recyclerView;
    BlogListActivityAdapter blogListActivityAdapter;
    RecyclerView.LayoutManager layoutManager;

    public String selectLocation = HomeActivity.location();

    private List<BlogMain> blogMainList = new ArrayList<>();

    private ImageView pen;
    private RelativeLayout writeBlog;

    private TextView title;

    private ProgressBar progress;

  
    public BlogListActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_blog_list, container, false);
        
        pen = (ImageView) v.findViewById(R.id.pen);
        pen.setColorFilter(Color.parseColor("#ffffff"));
        writeBlog = (RelativeLayout) v.findViewById(R.id.writeBlog);
        writeBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BlogSelectedLocationActivity.class);
                startActivity(intent);

                /*
                Intent intent = new Intent(getActivity(), BlogWriteActivity.class);
                startActivity(intent);
                */
            }
        });

        title = (TextView) v.findViewById(R.id.title);
        title.setText(HomeActivity.selectLocation + " RLOG");
        Log.d(TAG, selectLocation + "!@!@!@");

        progress = (ProgressBar) v.findViewById(R.id.progress);

        recyclerView = (RecyclerView) v.findViewById(R.id.blogActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        blogListActivityAdapter = new BlogListActivityAdapter(blogMainList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object obj = v.getTag();
                if(obj != null){
                    final int position = (int) obj;
                    Intent intent = new Intent(getActivity(), BlogListDetailActivity.class);
                    intent.putExtra("key", blogMainList.get(position).getKey());
                    intent.putExtra("childKey", blogMainList.get(position).getChildKey());
                    intent.putExtra("likeKey", blogMainList.get(position).getLikeKey());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(blogListActivityAdapter);
        

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();

        if (HomeActivity.selectLocation.equals("전라북도")) {
            for(int i = 0; i < HomeActivity.jbLocation.size(); i++) {
                Log.d(TAG, HomeActivity.jbLocation.get(i));
            }
        } else if (HomeActivity.selectLocation.equals("전라남도")) {
            for(int i = 0; i < HomeActivity.jnLocation.size(); i++) {
                Log.d(TAG, HomeActivity.jnLocation.get(i));
            }
        } else {
            Log.d(TAG, "광주");
        }
        myRefBlog.child("전주시").child("blog").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);

                    blogMainList.add(blogMain);
                    blogListActivityAdapter.notifyDataSetChanged();



                }
                progress.setVisibility(View.GONE);

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

        if (HomeActivity.selectLocation.equals("전라북도")) {
            for(int i = 0; i < HomeActivity.jbLocation.size(); i++) {
                Log.d(TAG, HomeActivity.jbLocation.get(i));
            }
        } else if (HomeActivity.selectLocation.equals("전라남도")) {
            for(int i = 0; i < HomeActivity.jnLocation.size(); i++) {
                Log.d(TAG, HomeActivity.jnLocation.get(i));
            }
        } else {
            Log.d(TAG, "광주");
        }
        myRefBlog.child("전주시").child("blog").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);

                    blogMainList.add(blogMain);
                    blogListActivityAdapter.notifyDataSetChanged();



                }
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
     */
}
