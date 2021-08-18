package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";

    private ImageView back;
    private SwitchCompat loginState;
    private Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        loginState = (SwitchCompat) findViewById(R.id.loginState);

        back = (ImageView) findViewById(R.id.back);
        back.setEnabled(true);
        back.setClickable(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("loginState", loginState.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        logOutButton = (Button) findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}