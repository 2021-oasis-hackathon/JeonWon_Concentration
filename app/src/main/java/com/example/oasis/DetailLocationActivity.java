package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailLocationActivity extends AppCompatActivity {

    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);


        search = (ImageView) findViewById(R.id.search);

        search.setColorFilter(Color.parseColor("#42C458"));
    }
}