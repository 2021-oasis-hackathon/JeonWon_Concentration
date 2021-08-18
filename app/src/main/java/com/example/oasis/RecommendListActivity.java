package com.example.oasis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import java.util.concurrent.ThreadLocalRandom;

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

    private TextView title, location, nullData;
    private TextView cafe, festival, foodStore;

    public static String setTitle, setLocation, setTime, setImage;

    String[] locationList = {"전주시", "정읍시", "군산시", "부안시", "고창시", "임실군", "광주", "여수시", "순천시", "완도시", "목포시", "보성시", "해남시" };
    String randomLocation;

    public static int i = 0;

    private View v;
    public RecommendListActivity() {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_recommend_list, container, false);

        nullData = (TextView) v.findViewById(R.id.nullData);
        location = (TextView) v.findViewById(R.id.location);
        title = (TextView) v.findViewById(R.id.title);

        if (i == 0) {
            randomLocation = "전주시";
            i++;
        } else {
            int randomNum = (int) (Math.random() * 13);
            randomLocation = locationList[randomNum];
        }




<<<<<<< HEAD
=======
        int randomNum = (int) (Math.random() * 13);
        randomLocation = locationList[randomNum];
        Log.d(TAG, "");
>>>>>>> 9744137843350af1bc19e86c563d86c604149db2
        location.setText(randomLocation);


        recyclerView = (RecyclerView) v.findViewById(R.id.recommendListActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

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
                cafe.setTextColor(Color.parseColor("#9acd32"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("카페")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            setTitle = filterList.get(position).getContent();
                            setLocation = filterList.get(position).getLocation();
                            setTime = filterList.get(position).getTime();
                            setImage = filterList.get(position).getImage();
                            Intent intent = new Intent(getActivity(), RecommendListDetailActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);

            }
        });

        festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("축제, 전시회");
                initTextBackgroundColor();
                festival.setTextColor(Color.parseColor("#9acd32"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("축제")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            setTitle = filterList.get(position).getContent();
                            setLocation = filterList.get(position).getLocation();
                            setTime = filterList.get(position).getTime();
                            setImage = filterList.get(position).getImage();
                            Intent intent = new Intent(getActivity(), RecommendListDetailActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
       });

        foodStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("맛집");
                initTextBackgroundColor();
                foodStore.setTextColor(Color.parseColor("#9acd32"));
                filterList.clear();
                for (Recommend recommend : recommendList) {
                    if(recommend.getTitle().equals("맛집")) {
                        filterList.add(recommend);
                    }
                }

                mAdapter = new RecommendListActivityAdapter(filterList , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object obj = v.getTag();
                        if (obj != null) {
                            final int position = (int) obj;
                            setTitle = filterList.get(position).getContent();
                            setLocation = filterList.get(position).getLocation();
                            setTime = filterList.get(position).getTime();
                            setImage = filterList.get(position).getImage();
                            Intent intent = new Intent(getActivity(), RecommendListDetailActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
        });

        progress = (ProgressBar) v.findViewById(R.id.progress);

        myRefBlog.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recommendList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Recommend recommend = snapshot1.getValue(Recommend.class);
                    if (recommend.getLocation2().equals(randomLocation)) {
                        recommendList.add(recommend);
                    }
                }

                if (recommendList.size() == 0) {
                    nullData.setVisibility(View.VISIBLE);
                    nullData.setSingleLine(true);    // 한줄로 표시하기
                    nullData.setEllipsize(TextUtils.TruncateAt.MARQUEE); // 흐르게 만들기
                    nullData.setSelected(true);

                    title.setText("");
                    cafe.setTextColor(Color.parseColor("#000000"));
                } else {
                    title.setText("카페");
                    cafe.setTextColor(Color.parseColor("#9acd32"));
                    filterList.clear();
                    for (Recommend recommend : recommendList) {
                        if(recommend.getTitle().equals("카페")) {
                            filterList.add(recommend);
                        }
                    }

                    mAdapter = new RecommendListActivityAdapter(filterList , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Object obj = v.getTag();
                            if (obj != null) {
                                final int position = (int) obj;
                                setTitle = filterList.get(position).getContent();
                                setLocation = filterList.get(position).getLocation();
                                setTime = filterList.get(position).getTime();
                                setImage = filterList.get(position).getImage();
                                Intent intent = new Intent(getActivity(), RecommendListDetailActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                }



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

    }

    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}

//title, content, image, time, location

/*
               Bitmap bitmap1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.f1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.f2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.f3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.f4);


        myRefBlog.push().setValue(
                new Recommend("축제", "왕과의 산책", BitmapToString(bitmap1), "2021.07.01. (목) ~ 2021.09.30. (목)", "전주 경기전", "전라북도", "전주시" ));

        myRefBlog.push().setValue(
                new Recommend("축제", "전주 비바체", BitmapToString(bitmap2), "2021.05.22. (토) ~ 2021.10.02. (토)", "한국소리문화의전당", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("축제", "전주 문화재 야행", BitmapToString(bitmap3), "2021.10.08. (금) ~ 2021.10.10. (일)", "경기전, 전주한옥마을", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("축제", "정원 문화 박람회", BitmapToString(bitmap4), "2021.09.09. (목) ~ 2021.09.11. (토)", "전주종합경기장", "전라북도", "전주시"  ));

        Bitmap bitmap11 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s1);
        Bitmap bitmap22 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s2);
        Bitmap bitmap33 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s3);
        Bitmap bitmap44 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.s4);


        myRefBlog.push().setValue(
                new Recommend("맛집", "피스비 (양식)", BitmapToString(bitmap11), "매일 11:30 - 21:00", "전라북도 전주시 덕진구 권삼득로 319-1", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("맛집", "통집", BitmapToString(bitmap22), "매일 10:00 – 02:00 일요일 17:00 – 02:00", "전북 전주시 덕진구 삼송1길 35", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("맛집", "서양주택(양식)", BitmapToString(bitmap33), "매일 11:00 – 22:00", "전라북도 전주시 완산구 전주객사2길 40", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("맛집", "경양카츠", BitmapToString(bitmap44), "매일 11:30 - 21:00", "전북 전주시 완산구 전주객사4길 47 1층 경양카츠", "전라북도", "전주시"  ));

        Bitmap bitmap111 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p1);
        Bitmap bitmap222 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p2);
        Bitmap bitmap333 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p3);
        Bitmap bitmap444 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.p5);


        myRefBlog.push().setValue(
                new Recommend("카페", "NOAH", BitmapToString(bitmap111), "매일 09:30 ~ 23:59 연중무휴", "전북 전주시 덕진구 편운로 25", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("카페", "그날의 온도", BitmapToString(bitmap222), "평일 11:00 ~ 20:30", "전북 전주시 덕진구 원동로 45", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("카페", "BLEU", BitmapToString(bitmap333), "매일 11:00 ~ 22:00", "전북 전주시 완산구 태진로 58-8", "전라북도", "전주시"  ));

        myRefBlog.push().setValue(
                new Recommend("카페", "빈타이", BitmapToString(bitmap444), "매일 12:00 ~ 23:00", "전북 전주시 덕진구 명륜3길 10-4", "전라북도", "전주시"  ));

*/
