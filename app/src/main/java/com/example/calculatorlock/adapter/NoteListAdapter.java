package com.example.calculatorlock.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.example.calculatorlock.activity.GalleryActivity;
import com.example.calculatorlock.activity.NoteActivity;
import com.example.calculatorlock.model.PreKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    ArrayList<File> files = new ArrayList<>();
    Activity activity;

    public NoteListAdapter(Activity activity , ArrayList<File> files) {
        this.activity = activity;
        this.files = files;
    }

    @NonNull
    @Override
    public NoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.folder_list_gridview, parent, false);
        NoteListAdapter.ViewHolder viewHolder = new NoteListAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            File note = new File("/sdcard/Hide/Notes");
            File[] files = note.listFiles();
            File readFrom = new File(note, files[position].getName());
            byte[] content = new byte[(int) readFrom.length()];

            FileInputStream stream = null;
            try {
                stream = new FileInputStream(readFrom);
                stream.read(content);
                String decrypt_text = decryptMsg(new String(content),generateKey());

                holder.note.setText(decrypt_text);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | InvalidAlgorithmParameterException | IOException e) {
                e.printStackTrace();
            }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,note,date ;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_OfNote);
            this.note = (TextView) itemView.findViewById(R.id.note);
            this.date = (TextView) itemView.findViewById(R.id.dateOfImg);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.list_item);
        }
    }

    public String decryptMsg(String cipherText, SecretKey secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] decode = Base64.decode(cipherText, Base64.NO_WRAP);
        String decryptString = new String(cipher.doFinal(decode), "UTF-8");
        return decryptString;
    }

    public static SecretKey generateKey()
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        SecretKeySpec secret;
        secret = new SecretKeySpec(PreKey.Hide_key.getBytes(), "AES");
        return  secret;
    }
}
