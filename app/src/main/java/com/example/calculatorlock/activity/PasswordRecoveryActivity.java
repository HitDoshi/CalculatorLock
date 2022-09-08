package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.calculatorlock.R;
import com.example.calculatorlock.model.PreKey;
import com.example.calculatorlock.model.SecurityQueModel;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class PasswordRecoveryActivity extends AppCompatActivity {

    Drawable upArrow;
    EditText recovery_email;
    Button save;
    String email , security_Que, security_Ans;

    List<SecurityQueModel> securityQueDB = new ArrayList<>();
    SharedPreferences sharedPreferences;
    boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        getSupportActionBar().setTitle("Password Recovery Email");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        upArrow = this.getDrawable(R.drawable.arrow_left);
//      upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        save = findViewById(R.id.save);
        recovery_email = findViewById(R.id.recovery_email);

        Paper.init(this);

        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(PreKey.firstTime_Lunch,true);

        securityQueDB = Paper.book().read(PreKey.SecurityDB);
        security_Que = securityQueDB.get(0).getSecurity_que();
        security_Ans = securityQueDB.get(0).getSecurity_ans();

        if(!isFirstTime)
        {
            setValue();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = recovery_email.getText().toString().trim();
                securityQueDB.clear();
                securityQueDB = new ArrayList<>();

                if(email.isEmpty())
                {
                    recovery_email.setError("Please enter your answer");
                }
                else {
                    sharedPreferences.edit().putBoolean(PreKey.firstTime_Lunch, false).apply();
                    securityQueDB.add(new SecurityQueModel(security_Que, security_Ans, email));
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
    }

    private void setValue() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(securityQueDB!=null && securityQueDB.size()!=0)
        {
            email = securityQueDB.get(0).getRecovery_email();
            securityQueDB.clear();
        }
    }
}