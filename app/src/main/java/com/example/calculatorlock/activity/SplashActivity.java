package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.calculatorlock.R;
import com.example.calculatorlock.model.PreKey;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    boolean isFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(PreKey.firstTime_Lunch,true);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2s = 2000ms
                if(isFirstTime)
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        }, 2000);
    }
}