package com.example.calculatorlock.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatorlock.R;
import com.example.calculatorlock.activity.GalleryActivity;

import java.util.ArrayList;

public class FolderListGridViewAdapter extends RecyclerView.Adapter<FolderListGridViewAdapter.ViewHolder>{

    ArrayList<Integer> icon = new ArrayList<>();
    ArrayList<String> folderName = new ArrayList<>();
    ArrayList<String> folderTitle = new ArrayList<>();
    Activity activity;

    public FolderListGridViewAdapter(Activity activity , ArrayList<Integer> icon,
                                     ArrayList<String> folderName, ArrayList<String> folderTitle) {
        this.icon = icon;
        this.folderName = folderName;
        this.folderTitle = folderTitle;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.folder_list_gridview, parent, false);
        FolderListGridViewAdapter.ViewHolder viewHolder = new FolderListGridViewAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderListGridViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.icon.setImageResource(icon.get(position));
        holder.folder_name_textView.setText(folderName.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                    activity.startActivity(new Intent(activity,GalleryActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return icon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView folder_name_textView ;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.folder_name_textView = (TextView) itemView.findViewById(R.id.folder_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.list_item);
        }
    }
}

