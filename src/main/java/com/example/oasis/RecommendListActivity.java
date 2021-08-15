package com.example.oasis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RecommendListActivity extends Fragment {

    private static final String TAG = "RecommendActivity";

    private RecyclerView recyclerView;
    private RecommendListBannerAdapter mAdapter;
    LinearLayoutManager layoutManager;
    private List<String> bannerList = new ArrayList<>();

    private RecyclerView recyclerView2;
    private RecommendListActivityAdapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager2;
    private List<Recommend> recommendList = new ArrayList<>();

    Timer timer;
    TimerTask timerTask;
    int position;


    private View v;
    public RecommendListActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_recommend_list, container, false);

        for(int i = 0; i < 10; i++) {
            bannerList.add("BANNER" + (i + 1));
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recommendBannerRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecommendListBannerAdapter(bannerList);
        recyclerView.setAdapter(mAdapter);

        if(bannerList != null) {
            position = Integer.MAX_VALUE / 2;
            recyclerView.scrollToPosition(position);
        }

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.smoothScrollBy(5,0);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    stopAutoAScrollBanner();
                } else if (newState == 0) {
                    position = layoutManager.findFirstCompletelyVisibleItemPosition();
                    runAutoScrollBanner();
                }
            }
        });

        Bitmap[] bitmapArray = new Bitmap[10];
        bitmapArray[0] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j1);
        bitmapArray[1] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j2);
        bitmapArray[2] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j3);
        bitmapArray[3] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j1);
        bitmapArray[4] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j2);
        bitmapArray[5] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j3);
        bitmapArray[6] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j1);
        bitmapArray[7] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j2);
        bitmapArray[8] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j3);
        bitmapArray[9] =  BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.j1);


        for (int i = 0; i < 10; i++) {
            recommendList.add(new Recommend("전주" + (i + 1), "전주" + (i + 1) + "-내용", bitmapArray[i], String.valueOf((i + 1) * 10)));
        }



        recyclerView2 = (RecyclerView) v.findViewById(R.id.recommendActivityRecyclerView);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);

        mAdapter2 = new RecommendListActivityAdapter(recommendList);
        recyclerView2.setAdapter(mAdapter2);

        return v;

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
            position = layoutManager.findFirstCompletelyVisibleItemPosition();
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
                        recyclerView.scrollToPosition(position);
                        recyclerView.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        recyclerView.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }

    }

}
