package com.example.calculatorlock.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.calculatorlock.R;
import com.example.calculatorlock.databinding.ActivityPasswordSetBinding;
import com.example.calculatorlock.model.PreKey;

public class PasswordSetActivity extends AppCompatActivity {

    AlertDialog password_info;
    View dialogView;
    AlertDialog.Builder builder;
    Button got_it;

    AlertDialog conform_pass;
    View conform_dialogView;
    AlertDialog.Builder conform_dialog_builder;
    LinearLayout reset , ok;

    ActivityPasswordSetBinding passwordSetBinding;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //setContentView(R.layout.activity_password_set);
        passwordSetBinding = DataBindingUtil.setContentView(this, R.layout.activity_password_set);
        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);

        dialogView = getLayoutInflater().inflate(R.layout.set_password_info_dialog,null);
        builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        got_it = dialogView.findViewById(R.id.got_it);

        password_info = builder.create();
        password_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        password_info.setCancelable(false);

        conform_dialogView = getLayoutInflater().inflate(R.layout.confirm_password_dialog,null);
        conform_dialog_builder = new AlertDialog.Builder(this);
        conform_dialog_builder.setView(conform_dialogView);

        reset = conform_dialogView.findViewById(R.id.reset_pass);
        ok = conform_dialogView.findViewById(R.id.conform_pass);
        conform_pass = conform_dialog_builder.create();
        conform_pass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        conform_pass.setCancelable(false);

        if(!sharedPreferences.getBoolean(PreKey.Password_Set,false))
            password_info.show();

        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_info.dismiss();
            }
        });

        passwordSetBinding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!sharedPreferences.getBoolean(PreKey.Password_Set,false))
                    conform_pass.show();
                else
                    startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
                sharedPreferences.edit().putBoolean(PreKey.Password_Set,true).apply();
                startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
            }
        });

    }
}