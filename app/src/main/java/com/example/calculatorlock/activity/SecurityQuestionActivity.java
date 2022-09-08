package com.example.calculatorlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.calculatorlock.R;
import com.example.calculatorlock.model.PreKey;
import com.example.calculatorlock.model.SecurityQueModel;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class SecurityQuestionActivity extends AppCompatActivity {

    Drawable upArrow;
    Button next;
    Spinner spinner;
    EditText answer;

    boolean isFirstTime;
    String security_Que , security_Ans , email = null;
    List<SecurityQueModel> securityQueDB = new ArrayList<SecurityQueModel>();
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);

        getSupportActionBar().setTitle("Security Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        upArrow = this.getDrawable(R.drawable.arrow_left);
//      upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);

        spinner = findViewById(R.id.spinner);
        next = findViewById(R.id.next);
        answer = findViewById(R.id.answer);

        Paper.init(this);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("What was your childhood nickname?");
        arrayList.add("What is your favorite team?");
        arrayList.add("What is your favorite movie?");
        arrayList.add("What is your favorite sport?");
        arrayList.add("In what city were you born?");
        arrayList.add("Who is your best friend?");

        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(PreKey.firstTime_Lunch,true);

        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        if(!isFirstTime)
        {
            setValue();
        }

//        for(int i=0;i<arrayList.size();i++)
//        {
//            if(security_Que.equals(arrayList.get(i)))
//            {
//                spinner.setSelection(i);
//            }
//        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                security_Que = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                security_Ans = answer.getText().toString().trim();
                securityQueDB = new ArrayList<>();

                if(security_Ans.isEmpty())
                {
                    answer.setError("Please enter your answer");
                }
                else {
                    securityQueDB.add(new SecurityQueModel(security_Que, security_Ans, email));
                    Paper.book().write(PreKey.SecurityDB, securityQueDB);

                    if(isFirstTime)
                        startActivity(new Intent(getApplicationContext(), PasswordRecoveryActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });

    }

    private void setValue() {
        securityQueDB = Paper.book().read(PreKey.SecurityDB);

        if(securityQueDB!=null && securityQueDB.size()!=0)
        {
            security_Que = securityQueDB.get(0).getSecurity_que();
            security_Ans = securityQueDB.get(0).getSecurity_ans();
            email = securityQueDB.get(0).getRecovery_email();
            securityQueDB.clear();
        }
        int spinnerPosition = arrayAdapter.getPosition(security_Que);
        spinner.setSelection(spinnerPosition);
        answer.setText(security_Ans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}