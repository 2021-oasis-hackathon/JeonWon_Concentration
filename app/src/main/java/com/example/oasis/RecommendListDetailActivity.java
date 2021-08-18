package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecommendListDetailActivity extends AppCompatActivity {

    private static final String TAG = "RecommendListDetailActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    RecyclerView recyclerView;
    BlogListActivityAdapter blogListActivityAdapter;
    RecyclerView.LayoutManager layoutManager;

    private List<BlogMain> blogMainList = new ArrayList<>();

    public String setTitle, setLocation, setTime, setImage;

    private TextView title, location, time;
    private ImageView image;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list_detail);

        setTitle = RecommendListActivity.setTitle;
        setLocation = RecommendListActivity.setLocation;
        setTime = RecommendListActivity.setTime;
        setImage = RecommendListActivity.setImage;

        title = (TextView) findViewById(R.id.title);
        location = (TextView) findViewById(R.id.location);
        time = (TextView) findViewById(R.id.time);
        image = (ImageView) findViewById(R.id.image);

        progress = (ProgressBar) findViewById(R.id.progress);

        title.setText(setTitle);
        location.setText(setLocation);
        time.setText(setTime);
        image.setImageBitmap(StringToBitMap(setImage));



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(RecommendListDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        blogListActivityAdapter = new BlogListActivityAdapter(blogMainList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object obj = v.getTag();
                if(obj != null){
                    final int position = (int) obj;
                    Intent intent = new Intent(RecommendListDetailActivity.this, BlogListDetailActivity.class);
                    intent.putExtra("key", blogMainList.get(position).getKey());
                    intent.putExtra("childKey", blogMainList.get(position).getChildKey());
                    intent.putExtra("likeKey", blogMainList.get(position).getLikeKey());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(blogListActivityAdapter);


    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        myRefBlog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);
                    if (setTitle.equals(blogMain.getTitle())) {
                        blogMainList.add(blogMain);
                        blogListActivityAdapter.notifyDataSetChanged();
                    }




                }
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}