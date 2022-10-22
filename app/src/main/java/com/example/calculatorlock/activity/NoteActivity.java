package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Toolbar;

import com.example.calculatorlock.R;
import com.example.calculatorlock.adapter.GalleryImgAdapter;
import com.example.calculatorlock.adapter.NoteListAdapter;
import com.example.calculatorlock.model.PreKey;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NoteActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ArrayList<File> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_note);

        floatingActionButton = findViewById(R.id.Floating_Button);
        recyclerView = findViewById(R.id.list_noteRecyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Add_NewNoteActivity.class));
            }
        });

        File note = new File("/sdcard/Hide/Notes/");
        File[] files = note.listFiles();

        assert files != null;
        arrayList.addAll(Arrays.asList(files));

        NoteListAdapter noteListAdapter = new NoteListAdapter(this, arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);

    }
}