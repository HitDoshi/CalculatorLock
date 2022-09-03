package com.example.calculatorlock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorlock.R;
import com.example.calculatorlock.activity.PasswordSetActivity;
import com.example.calculatorlock.model.PreKey;

public class WelcomeActivity extends AppCompatActivity {

    Button next;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);

        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean(PreKey.firstTime_Lunch,false).apply();
                startActivity(new Intent(getApplicationContext(), PasswordSetActivity.class));
            }
        });
    }
}