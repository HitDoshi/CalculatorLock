package com.example.calculatorlock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatorlock.R;

import java.util.ArrayList;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ViewHolder> {

    ArrayList<Integer> icon = new ArrayList<>();
    ArrayList<String> folderName = new ArrayList<>();
    ArrayList<String> folderTitle = new ArrayList<>();

    public FolderListAdapter(ArrayList<Integer> icon, ArrayList<String> folderName, ArrayList<String> folderTitle) {
        this.icon = icon;
        this.folderName = folderName;
        this.folderTitle = folderTitle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.folder_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderListAdapter.ViewHolder holder, int position) {

        holder.icon.setImageResource(icon.get(position));
        holder.folder_name_textView.setText(folderName.get(position));
        holder.folder_work_title_textView.setText(folderTitle.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return icon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView folder_name_textView, folder_work_title_textView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.folder_name_textView = (TextView) itemView.findViewById(R.id.folder_name);
            this.folder_work_title_textView = (TextView) itemView.findViewById(R.id.folder_work_title);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.list_item);
        }
    }
}
