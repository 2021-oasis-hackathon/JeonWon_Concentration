package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BlogSelectedLocationActivity extends AppCompatActivity {

    private static final String TAG = "BlogSelectedLocationActivity";

    private RelativeLayout page;
    private TextView title;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_selected_location);

        title = (TextView) findViewById(R.id.title);
        title.setText(HomeActivity.selectLocation + " 어느 지역의 블로그를 작성하시겠습니까?");

        page = (RelativeLayout) findViewById(R.id.page);
        page.setEnabled(true);
        page.setClickable(true);
        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

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
    }
}