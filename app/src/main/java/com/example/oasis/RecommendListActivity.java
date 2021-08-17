package com.example.oasis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

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

public class RecommendListActivity extends Fragment {

    private static final String TAG = "RecommendActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recommend> recommendList = new ArrayList<>();
    private List<Recommend> filterList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("recommend");

    private ProgressBar progress;

    private TextView title;
    private TextView cafe, festival, foodStore;


    private View v;
    public RecommendListActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_recommend_list, container, false);

        title = (TextView) v.findViewById(R.id.title);
        title.setText("카페");

        recyclerView = (RecyclerView) v.findViewById(R.id.recommendListActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecommendListActivityAdapter(recommendList);
        recyclerView.setAdapter(mAdapter);

        cafe = (TextView) v.findViewById(R.id.cafe);
        festival = (TextView) v.findViewById(R.id.festival);
        foodStore = (TextView) v.findViewById(R.id.foodStore);

        cafe.setEnabled(true);
        cafe.setClickable(true);

        festival.setEnabled(true);
        festival.setClickable(true);

        foodStore.setEnabled(true);
        foodStore.setClickable(true);

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("카페");
                initTextBackgroundColor();
                cafe.setTextColor(Color.parseColor("#42C458"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("카페")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);

            }
        });

        festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("축제, 전시회");
                initTextBackgroundColor();
                festival.setTextColor(Color.parseColor("#42C458"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("축제")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
            }
       });

        foodStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("맛집");
                initTextBackgroundColor();
                foodStore.setTextColor(Color.parseColor("#42C458"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("맛집")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
            }
        });

        progress = (ProgressBar) v.findViewById(R.id.progress);

//        Bitmap bitmap1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.j1);
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.j2);
//        Bitmap bitmap3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.j3);
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("축제", "축제1", BitmapToString(bitmap1)));
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("축제", "축제2", BitmapToString(bitmap2)));
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("축제", "축제3", BitmapToString(bitmap3)));
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("맛집", "맛집1", BitmapToString(bitmap2)));
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("맛집", "맛집2", BitmapToString(bitmap3)));
//
//        myRefBlog.child("전주시").child("recommend").push().setValue(
//                new Recommend("맛집", "맛집3", BitmapToString(bitmap1)));



        return v;

    }

    private void initTextBackgroundColor() {
        cafe.setTextColor(Color.parseColor("#000000"));
        festival.setTextColor(Color.parseColor("#000000"));
        foodStore.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onStart() {
        super.onStart();
        //recommendList.clear();
        myRefBlog.child("전주시").child("recommend").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recommendList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Recommend recommend = snapshot1.getValue(Recommend.class);
                    recommendList.add(recommend);
                }
                //mAdapter.notifyDataSetChanged();


                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("카페")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList);
                recyclerView.setAdapter(mAdapter);
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
}
