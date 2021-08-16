package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailLocationActivity extends AppCompatActivity {

    private static final String TAG = "DetailLocationActivity";

    private TextView title;
    private ImageView search;

    private String getTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        Intent intent = getIntent();
        getTitle = intent.getStringExtra("title");

        title = (TextView) findViewById(R.id.title);
        title.setText(getTitle);


        search = (ImageView) findViewById(R.id.search);
        search.setColorFilter(Color.parseColor("#42C458"));


    }
}