package com.example.calculatorlock.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.calculatorlock.R;
import com.example.calculatorlock.adapter.FolderListAdapter;
import com.example.calculatorlock.adapter.FolderListGridViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;

    Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView bar , view_change;
    public NavigationView navigationView;
    FloatingActionButton floatingActionButton;

    RecyclerView recyclerView;
    ArrayList<Integer> icon = new ArrayList<>();
    ArrayList<String> folderName = new ArrayList<>();
    ArrayList<String> folderTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        bar = findViewById(R.id.bar);
        view_change = findViewById(R.id.view_change);

        navigationView = findViewById(R.id.navigation);
        recyclerView = findViewById(R.id.folder_list_recyclerView);
        floatingActionButton = findViewById(R.id.Floating_Button);

        addItemData();

        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

//        FolderListAdapter folderListAdapter = new FolderListAdapter(icon,folderName,folderTitle);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(folderListAdapter);


        FolderListGridViewAdapter folderListGridViewAdapter = new FolderListGridViewAdapter(this,
                icon,folderName,folderTitle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(folderListGridViewAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),
                        PICK_IMAGE_MULTIPLE);
            }
        });
    }

    private void addItemData(){
        Collections.addAll(icon,
                R.drawable.gallery,R.drawable.video,R.drawable.music_audio,R.drawable.note,
                R.drawable.folder,R.drawable.document,R.drawable.security_safe,R.drawable.trash,
                R.drawable.disguise_icon,R.drawable.browser
                );

        Collections.addAll(folderName,"Gallery","Video","Audio","Note",
                "File Manager","Document","App Lock","Recycler Bin",
                "Disguise Icon","Browser");

        Collections.addAll(folderTitle,"Hide Photos","Hide Videos","Hide Audios","Your Notes",
                "Categorised Docs","Hidden Documents","Hide App","Deleted Hidden File",
                "App Icon","Search Url");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Log.d("LOG_TAG", "Selected Images : " + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}