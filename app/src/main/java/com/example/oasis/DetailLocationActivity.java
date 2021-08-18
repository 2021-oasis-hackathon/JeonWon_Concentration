package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DetailLocationActivity extends AppCompatActivity {

    private static final String TAG = "DetailLocationActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    private RecyclerView recyclerView;
    private DetailLocationActivityAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView2;
    private RecommendListBannerAdapter mAdapter2;
    LinearLayoutManager layoutManager2;
    private List<String> bannerList = new ArrayList<>();

    public List<BlogMain> blogMainList = new ArrayList<>();
    private List<BlogMain> filterList = new ArrayList<>();

    private TextView title;
    private ImageView search;
    private EditText searchEditText;

    private String getTitle;

    private RelativeLayout hashTagMap, hashTagFoodStore, hashTagHealing, hashTagOutside;
    private TextView hashTagMapText, hashTagFoodStoreText, hashTagHealingText, hashTagOutsideText;

    public static String setTitle, setLocation, setTime, setImage;

    private ProgressBar progress;

    Timer timer;
    TimerTask timerTask;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        Intent intent = getIntent();
        getTitle = intent.getStringExtra("location");

        title = (TextView) findViewById(R.id.title);
        title.setText(getTitle);

        progress = (ProgressBar) findViewById(R.id.progress);
        search = (ImageView) findViewById(R.id.search);
        search.setColorFilter(Color.parseColor("#9acd32"));

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

                mAdapter = new DetailLocationActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                            intent.putExtra("key", filterList.get(position).getKey());
                            intent.putExtra("childKey", filterList.get(position).getChildKey());
                            intent.putExtra("likeKey", filterList.get(position).getLikeKey());
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
                //mAdapter = new DetailLocationActivityAdapter(filterList);
                //recyclerView.setAdapter(mAdapter);

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

                mAdapter = new DetailLocationActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                            intent.putExtra("key", filterList.get(position).getKey());
                            intent.putExtra("childKey", filterList.get(position).getChildKey());
                            intent.putExtra("likeKey", filterList.get(position).getLikeKey());
                            startActivity(intent);
                        }
                    }
                });
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

                mAdapter = new DetailLocationActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                            intent.putExtra("key", filterList.get(position).getKey());
                            intent.putExtra("childKey", filterList.get(position).getChildKey());
                            intent.putExtra("likeKey", filterList.get(position).getLikeKey());
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
                //mAdapter = new DetailLocationActivityAdapter(filterList);
                //recyclerView.setAdapter(mAdapter);
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

                mAdapter = new DetailLocationActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                            intent.putExtra("key", filterList.get(position).getKey());
                            intent.putExtra("childKey", filterList.get(position).getChildKey());
                            intent.putExtra("likeKey", filterList.get(position).getLikeKey());
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
                //mAdapter = new DetailLocationActivityAdapter(filterList);
                //recyclerView.setAdapter(mAdapter);
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

                mAdapter = new DetailLocationActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                            intent.putExtra("key", filterList.get(position).getKey());
                            intent.putExtra("childKey", filterList.get(position).getChildKey());
                            intent.putExtra("likeKey", filterList.get(position).getLikeKey());
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
                //mAdapter = new DetailLocationActivityAdapter(filterList);
                //recyclerView.setAdapter(mAdapter);
                searchEditText.setText("");
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.detailLocationActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DetailLocationActivity.this);
        recyclerView.setLayoutManager(layoutManager);

       // mAdapter = new DetailLocationActivityAdapter(blogMainList);
        mAdapter = new DetailLocationActivityAdapter(blogMainList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object obj = v.getTag();
                if (obj != null) {
                    final int position = (int) obj;
                    Intent intent = new Intent(DetailLocationActivity.this, BlogListDetailActivity.class);
                    intent.putExtra("key", blogMainList.get(position).getKey());
                    intent.putExtra("childKey", blogMainList.get(position).getChildKey());
                    intent.putExtra("likeKey", blogMainList.get(position).getLikeKey());
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(mAdapter);



        //배너

        bannerList.add(BitmapToString(BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.banner1)));
        bannerList.add(BitmapToString(BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.banner2)));
        bannerList.add(BitmapToString(BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.banner3)));
        bannerList.add(BitmapToString(BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.banner4)));

        recyclerView2 = (RecyclerView) findViewById(R.id.recommendBannerRecyclerView);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        mAdapter2 = new RecommendListBannerAdapter(bannerList);
        recyclerView2.setAdapter(mAdapter2);

        if(bannerList != null) {
            position = Integer.MAX_VALUE / 2;
            recyclerView2.scrollToPosition(position);
        }

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView2);
        recyclerView2.smoothScrollBy(5,0);

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoAScrollBanner();
                } else if (newState == 0) {
                    position = layoutManager2.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });
    }

    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    @Override
    public void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopAutoAScrollBanner();
    }


    private void stopAutoAScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManager2.findFirstCompletelyVisibleItemPosition();
        }
    }

    private void runAutoScrollBanner() {
        if(timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        recyclerView2.scrollToPosition(position);
                        recyclerView2.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        recyclerView2.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 3000, 3000);
        }

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
        myRefBlog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);
                    String boolString = blogMain.getLocation2();
                    if (HomeActivity.selectLocation.equals("광주")) {
                        boolString = blogMain.getLocation1();
                    }
                    if (getTitle.equals(boolString)) {
                        blogMainList.add(blogMain);
                        mAdapter.notifyDataSetChanged();
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