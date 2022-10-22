package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.example.calculatorlock.R;
import com.example.calculatorlock.adapter.GalleryImgAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Bitmap> arrayList = new ArrayList<Bitmap>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.folder_list_recyclerView);

        File image = new File("/sdcard/Hide/Image");
        File imageDetail = new File("/sdcard/ImageDetail/");
        File[] files = image.listFiles();

        if(files!=null) {
            arrayList.clear();

            for (File file : files) {

                File readFrom = new File(image,file.getName());
                byte[] content = new byte[(int)readFrom.length()];

                try{

                    FileInputStream stream = new FileInputStream(readFrom);
                    stream.read(content);

                    byte[] bytes = Base64.decode((new String(content)), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    arrayList.add(bitmap);

                }catch (Exception e){

                }

            }
//                Log.d("Files", "FileName:" + file.getName());
//                StringBuilder stringBuffer = new StringBuilder();
//                try {
//                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getName()));
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        stringBuffer.append(line);
//                        stringBuffer.append("\n");
//                        Log.d("File", String.valueOf(stringBuffer));
//                    }
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////
////                byte[] bytes = Base64.decode(String.valueOf(stringBuffer), Base64.DEFAULT);
////                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
////                arrayList.add(bitmap);
////                stringBuffer.delete(0, stringBuffer.length());
//                arrayList.add(stringBuffer);
//                stringBuffer.delete(0, stringBuffer.length());
//            }
        }

        GalleryImgAdapter folderListGridViewAdapter = new GalleryImgAdapter(this,
                arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(folderListGridViewAdapter);

    }
}