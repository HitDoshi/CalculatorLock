package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.calculatorlock.R;
import com.example.calculatorlock.model.PreKey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Add_NewNoteActivity extends AppCompatActivity {

    ClipboardManager cpl_board;
    EditText title_OfNote , Note;
    ImageView tick;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_add_new_note);

        cpl_board = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        title_OfNote = findViewById(R.id.title_OfNote);
        Note = findViewById(R.id.note);
        tick = (ImageView) findViewById(R.id.tick);
        toolbar = findViewById(R.id.toolbar);

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (title_OfNote.getText().toString()!=null && Note.getText().toString()!=null) {
                    File wallpaperDirectory = new File("/sdcard/Hide/Notes/");
// have the object build the directory structure, if needed.
                    wallpaperDirectory.mkdirs();
                    try {
                        String note = title_OfNote.getText().toString() + "\n" +
                                Note.getText().toString();
                        try {
                            String encrypt_text=encryptMsg(note,generateKey());
                            FileOutputStream writer = new FileOutputStream(new File(wallpaperDirectory,
                                    "notes" + ".txt"));
                            writer.write(Integer.parseInt(encrypt_text));
                            writer.close();
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    if (title_OfNote.getText().toString()==null)
                        title_OfNote.setError("Write title of note");
                    else
                        Note.setText("Write your secret note");
                }
            }
        });

    }

    public String encryptMsg(String message, SecretKey secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException,
            IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.encodeToString(cipherText, Base64.NO_WRAP);
    }

    public static SecretKey generateKey()
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        SecretKeySpec secret;
        secret = new SecretKeySpec(PreKey.Hide_key.getBytes(), "AES");
        return  secret;
    }
}