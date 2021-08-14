package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private static final String TAG = "CourseActivity";

    private RecyclerView recyclerView;
    private CourseActivityAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Course> courseList = CourseListActivity.courseList;

    private TextView place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        place = (TextView) findViewById(R.id.place);
        place.setText(courseList.get(position).getPlace());


        recyclerView = (RecyclerView) findViewById(R.id.courseActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CourseActivityAdapter(courseList);
        recyclerView.setAdapter(mAdapter);

    }
}