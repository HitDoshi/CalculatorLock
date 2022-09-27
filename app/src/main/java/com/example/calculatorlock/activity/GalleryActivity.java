package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.calculatorlock.R;
import com.example.calculatorlock.adapter.FolderListGridViewAdapter;
import com.example.calculatorlock.adapter.GalleryImgAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Bitmap> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.folder_list_recyclerView);

        File directory = new File("/sdcard/Hit/");
        File[] files = directory.listFiles();

        if(files.length>0) {
            arrayList.clear();

            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(files[i].getName()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                        stringBuffer.append("\n");
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                byte[] bytes = Base64.decode(String.valueOf(stringBuffer), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                arrayList.add(bitmap);
                stringBuffer.delete(0, stringBuffer.length());

            }
        }

        GalleryImgAdapter folderListGridViewAdapter = new GalleryImgAdapter(this,
                arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(folderListGridViewAdapter);

    }
}