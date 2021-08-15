package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private static final String TAG = "CourseActivity";

    private RecyclerView recyclerView;
    private CourseListActivityAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static List<Course> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Bitmap[] bitmapArray = new Bitmap[10];
        bitmapArray[0] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j1);
        bitmapArray[1] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j2);
        bitmapArray[2] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j3);
        bitmapArray[3] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j1);
        bitmapArray[4] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j2);
        bitmapArray[5] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j3);
        bitmapArray[6] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j1);
        bitmapArray[7] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j2);
        bitmapArray[8] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j3);
        bitmapArray[9] =  BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.j1);


        for (int i = 0; i < 10; i++) {
            courseList.add(new Course("전주" + (i + 1), "전주" + (i + 1) + "-내용", bitmapArray[i]));
        }



        recyclerView = (RecyclerView) findViewById(R.id.courseListActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CourseListActivityAdapter(courseList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CourseListActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CourseListActivity.this, CourseActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}