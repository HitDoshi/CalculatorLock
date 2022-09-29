package com.example.calculatorlock.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatorlock.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GalleryImgAdapter extends RecyclerView.Adapter<GalleryImgAdapter.ViewHolder> {

    ArrayList<Bitmap> image = new ArrayList<>();
    ArrayList<String> folderName = new ArrayList<>();
    ArrayList<String> folderTitle = new ArrayList<>();
    Activity activity;

    public GalleryImgAdapter(Activity activity, ArrayList<Bitmap> image) {
        this.activity=activity;
        this.image = image;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.gallery_list, parent, false);
        GalleryImgAdapter.ViewHolder viewHolder = new GalleryImgAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryImgAdapter.ViewHolder holder, int position) {

        holder.img.setImageBitmap(image.get(position));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.get(position).compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();
        long length = imageInByte.length;

//        holder.NameOfImg_textView.setText(folderName.get(position));
//        holder.dateOfImg_textView.setText(folderTitle.get(position));
        holder.sizeOfImg_textView.setText((length/1000)+" KB");

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView NameOfImg_textView, dateOfImg_textView , sizeOfImg_textView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.NameOfImg_textView = (TextView) itemView.findViewById(R.id.NameOfImg);
            this.dateOfImg_textView = (TextView) itemView.findViewById(R.id.dateOfImg);
            this.sizeOfImg_textView = (TextView) itemView.findViewById(R.id.sizeOfImg);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.list_item);
        }
    }

}
