package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.example.calculatorlock.R;

public class Add_NewNoteActivity extends AppCompatActivity {

    ClipboardManager cpl_board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_add_new_note);

        cpl_board = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    }
}