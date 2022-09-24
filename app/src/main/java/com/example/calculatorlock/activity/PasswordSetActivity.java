package com.example.calculatorlock.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.calculatorlock.R;
import com.example.calculatorlock.databinding.ActivityPasswordSetBinding;
import com.example.calculatorlock.model.PreKey;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordSetActivity extends AppCompatActivity {

    AlertDialog password_info;
    View dialogView;
    AlertDialog.Builder builder;
    Button got_it;

    AlertDialog conform_pass;
    View conform_dialogView;
    AlertDialog.Builder conform_dialog_builder;
    LinearLayout reset , ok;

    ActivityPasswordSetBinding passwordSetBinding;

    SharedPreferences sharedPreferences;

    double num=1 , num1 , num2 , total='\0' , temp='\0' ;
    ArrayList<String> number = new ArrayList<>();
    String symbol = null;
    String pn = null;

    //check only number or not
    String regex = "[0-9]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //setContentView(R.layout.activity_password_set);
        passwordSetBinding = DataBindingUtil.setContentView(this, R.layout.activity_password_set);
        sharedPreferences = getSharedPreferences(PreKey.preference_name,MODE_PRIVATE);

        dialogView = getLayoutInflater().inflate(R.layout.set_password_info_dialog,null);
        builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        got_it = dialogView.findViewById(R.id.got_it);

        password_info = builder.create();
        password_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        password_info.setCancelable(false);

        conform_dialogView = getLayoutInflater().inflate(R.layout.confirm_password_dialog,null);
        conform_dialog_builder = new AlertDialog.Builder(this);
        conform_dialog_builder.setView(conform_dialogView);

        reset = conform_dialogView.findViewById(R.id.reset_pass);
        ok = conform_dialogView.findViewById(R.id.conform_pass);
        conform_pass = conform_dialog_builder.create();
        conform_pass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        conform_pass.setCancelable(false);

        if(!sharedPreferences.getBoolean(PreKey.Password_Set,false))
            //password_info.show();

        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_info.dismiss();
            }
        });

        passwordSetBinding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(total + "");

                if(passwordSetBinding.numberListTextview.getText().toString().equals("1"))
                {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
                number.clear();
                passwordSetBinding.calculateAnsTextview.setText("");


                if(total-((int)(total))==0){
                    passwordSetBinding.numberListTextview.setText("= " + (int)total);
                }else{
                    passwordSetBinding.numberListTextview.setText(total+"");
                }
                temp = total;
                addData(String.valueOf(total));

//                if(!sharedPreferences.getBoolean(PreKey.Password_Set,false)) {
//                    if(m.matches())
//                        conform_pass.show();
//                }
//                else {
//                    if (sharedPreferences.getString(PreKey.Password,"1234").equals(total+"")) {
//                        startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
//                    }
//                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conform_pass.dismiss();
                sharedPreferences.edit().putBoolean(PreKey.Password_Set,true).apply();
                startActivity(new Intent(getApplicationContext(), SecurityQuestionActivity.class));
            }
        });

        passwordSetBinding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null; //above fun calling

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("1");
            }
        });

        passwordSetBinding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("2");
            }
        });

        passwordSetBinding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("3");
            }
        });

        passwordSetBinding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("4");
            }
        });

        passwordSetBinding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("5");
            }
        });

        passwordSetBinding.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("6");
            }
        });

        passwordSetBinding.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("7");
            }
        });

        passwordSetBinding.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("8");
            }
        });

        passwordSetBinding.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("9");
            }
        });

        passwordSetBinding.zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("0");
            }
        });

        passwordSetBinding.doubleZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(temp!='\0')
                {
                    number.clear();
                    temp = '\0';
                }
                addData("00");
            }
        });

        passwordSetBinding.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = null;

                if(!number.get(number.size()-1).contains(".")) {
                    if (temp != '\0') {
                        number.clear();
                        temp = '\0';
                    }
                    addData(".");
                }
            }
        });

        passwordSetBinding.percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "%" ;
                addData("%");
            }
        });

        passwordSetBinding.addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "+" ;
                temp = '\0';

                if (number.size()=='\0') {
                    passwordSetBinding.numberListTextview.setText("+");
                    pn = "+";
                }
                else
                    addData("+");
            }
        });

        passwordSetBinding.subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "-" ;
                temp = '\0';

                if (number.size()=='\0') {
                    passwordSetBinding.numberListTextview.setText("-");
                    pn = "-";
                }
                else
                    addData("-");
            }
        });

        passwordSetBinding.multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "*" ;
                temp = '\0';

                if(number.size()!='\0')
                    addData("*");
            }
        });

        passwordSetBinding.division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                symbol = "/" ;
                temp = '\0';

                if(number.size()!='\0')
                    addData("/");
            }
        });

        passwordSetBinding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = number.size();
                String full = null;

                if (size!=0)
                {
                    full = number.get(size-1);

                    if(full.length()!=1)
                    {
                        full = full.substring(0,full.length()-1);
                        Log.d("Remove",full);
                        number.set(size-1,full);
                        passwordSetBinding.numberListTextview.setText(full);
                    }
                    else{
                        number.remove(size-1);
                    }
                }
                else
                {
                    pn = null;
                    passwordSetBinding.numberListTextview.setText("");
                    passwordSetBinding.calculateAnsTextview.setText("");
                }

                if(size>0)
                {
                    calculate();
                }

                if (number.size()==0){
                    passwordSetBinding.numberListTextview.setText("");
                }
            }
        });

        passwordSetBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.clear();
                passwordSetBinding.numberListTextview.setText("");
                passwordSetBinding.calculateAnsTextview.setText("");
                total = '\0';
                temp = 0;
                pn = null;
            }
        });

    }

    private void addData(String s) {

        int size = number.size();
        int p = 0;
        if(symbol==null)
        {
            if(size!=0 && num==1) {
                String a = number.get(size-1);
                a+=(s);
                number.set(size-1, a);
            }else if(size!=0 && num==0)
            {
                number.add(s);
                num = 1;
            }
            else {

                if(pn!=null)
                {
                    s = pn + s;
                }
                number.add(s);
                pn = null ;
            }
        }
        else
        {
//            if(temp!=0)
//            {
//                number.add(temp+"");
//                temp=0;
//            }

            if(number.size()>0) {
                if (number.get(size - 1).equals("+") || number.get(size - 1).equals("-") ||
                        number.get(size - 1).equals("*") || number.get(size - 1).equals("/")) {
                    number.set(size - 1, s);
                    p=1;
                }
            }

            if(p==0)
                number.add(s);


            num = 0;
        }

        calculate();

    }

    public void calculate(){

        String list = null;

        for (int i=0;i<number.size();i++){

            if(i==0) {
                list = (number.get(i));
            }
            else {
                list+=(number.get(i));
            }
        }

        for (int i=0;i<number.size();i++){

            if(i==0) {
                num1 = Double.parseDouble(number.get(i));
                passwordSetBinding.calculateAnsTextview.setText("");
            }

            if(number.get(i).equals("+")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 + num2;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }
                else{
                    // 2 + write
                    total = num1 = num1 + 0;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }
            }

            if(number.get(i).equals("-")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 - num2;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }else{
                    // 2 - write = 2-0 calculate
                    total = num1 = num1 - 0;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }
            }

            if(number.get(i).equals("*")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 * num2;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }else{
                    total = num1 = num1 * 1;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }
            }

            if(number.get(i).equals("/")){

                if (i+1<number.size())
                {
                    num2 = Double.parseDouble(number.get(i+1));
                    total = num1 = num1 / num2;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }else{
                    total = num1 = num1/1;
                    passwordSetBinding.calculateAnsTextview.setText("= " + total);
                }
            }
        }

        if(list == null){
            passwordSetBinding.calculateAnsTextview.setText("");
            total=0;
        }

        if(total-((int)(total))==0){
            passwordSetBinding.calculateAnsTextview.setText("= " + (int)total);
        }

        passwordSetBinding.numberListTextview.setText(list);
    }
}