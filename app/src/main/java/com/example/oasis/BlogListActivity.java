package com.example.oasis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Collections;
import java.util.Comparator;
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

    private ImageView pen, filterImage;
    private RelativeLayout writeBlog, filter;

    private TextView title;

    private ProgressBar progress;

    private LinearLayout filterView;
    private Button like, date, filterTitle;

  
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
                SharedPreferences sf = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);

                String userNickName = sf.getString("nickName", null);

                if (userNickName == null) {
                    Toast.makeText(getActivity(), "로그인이 필요한 기능입니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getActivity(), BlogSelectedLocationActivity.class);
                startActivity(intent);
                /*
                Intent intent = new Intent(getActivity(), BlogWriteActivity.class);
                startActivity(intent);
                */
            }
        });

        filterView = (LinearLayout) v.findViewById(R.id.filterView);

        like = (Button) v.findViewById(R.id.like);
        date = (Button) v.findViewById(R.id.date);
        filterTitle = (Button) v.findViewById(R.id.filterTitle);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterView.setVisibility(View.GONE);
                Collections.sort(blogMainList, new NoDescCompare());
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
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterView.setVisibility(View.GONE);
                Collections.sort(blogMainList, new DateAscCompare());
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
            }
        });

        filterTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterView.setVisibility(View.GONE);
                Collections.sort(blogMainList, new NameAscCompare());
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
            }
        });


        filterImage = (ImageView) v.findViewById(R.id.filterImage);
        filterImage.setColorFilter(Color.parseColor("#ffffff"));
        filter = (RelativeLayout) v.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterView.setVisibility(View.VISIBLE);
            }
        });

        title = (TextView) v.findViewById(R.id.title);
        title.setText(HomeActivity.selectLocation + " RLOG");

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

        String location = "";
        if (HomeActivity.selectLocation.equals("전라북도")) {
            location = "전라북도";
            for(int i = 0; i < HomeActivity.jbLocation.size(); i++) {
               // Log.d(TAG, HomeActivity.jbLocation.get(i));
            }
        } else if (HomeActivity.selectLocation.equals("전라남도")) {
            location = "전라남도";
            for(int i = 0; i < HomeActivity.jnLocation.size(); i++) {
                //Log.d(TAG, HomeActivity.jnLocation.get(i));
            }
        } else {
            location = "광주";
            Log.d(TAG, "광주");
        }
        String finalLocation = location;
        myRefBlog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogMainList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BlogMain blogMain = snapshot1.getValue(BlogMain.class);

                    if (blogMain.getLocation1().equals(finalLocation)) {

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
    // 날짜 오름차순

    static class DateAscCompare implements Comparator<BlogMain> {
        @Override
        public int compare(BlogMain arg0, BlogMain arg1) {
            return arg0.getDate().compareTo(arg1.getDate());
        }

    }

    // 이름 오름차순

    static class NameAscCompare implements Comparator<BlogMain> {
        @Override
        public int compare(BlogMain arg0, BlogMain arg1) {
            return arg0.getTitle().compareTo(arg1.getTitle());
        }

    }

    // 이름 내림차순
    static class NameDescCompare implements Comparator<BlogMain> {
        @Override
        public int compare(BlogMain arg0, BlogMain arg1) {
            return arg1.getTitle().compareTo(arg0.getTitle());
        }
    }



    // No 오름차순

    static class NoAscCompare implements Comparator<BlogMain> {
        @Override
        public int compare(BlogMain arg0, BlogMain arg1) {
            int i  = Integer.parseInt(arg0.getLike());
            return Integer.parseInt(arg0.getLike()) < Integer.parseInt(arg1.getLike()) ? -1 : Integer.parseInt(arg0.getLike()) > Integer.parseInt(arg1.getLike()) ? 1:0;
        }
    }



    // No 내림차순

    static class NoDescCompare implements Comparator<BlogMain> {
        @Override
        public int compare(BlogMain arg0, BlogMain arg1) {
            return Integer.parseInt(arg0.getLike()) > Integer.parseInt(arg1.getLike()) ? -1 : Integer.parseInt(arg0.getLike()) < Integer.parseInt(arg1.getLike()) ? 1:0;
        }
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
