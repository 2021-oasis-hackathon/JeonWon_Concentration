package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DetailLocationListActivity extends AppCompatActivity {

    private RelativeLayout page, jeonJu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_location);

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

        jeonJu = (RelativeLayout) findViewById(R.id.jeonJu);
        jeonJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailLocationListActivity.this, DetailLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}