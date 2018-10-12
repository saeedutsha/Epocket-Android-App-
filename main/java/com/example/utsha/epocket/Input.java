package com.example.utsha.epocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.utsha.epocket.R.layout.activity_input;

public class Input extends AppCompatActivity {


    EditText e,e2;
    public MySqlite mysqlite;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_input);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.my_icon);
        e=(EditText) findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        mysqlite = new MySqlite(this);
        mp=MediaPlayer.create(this,R.raw.sample);

    }

    public void ADD(View V) {
        if(e.length()==0 || e2.length()==0){
            Toast.makeText(getApplicationContext(),"Fields can not be empty", Toast.LENGTH_LONG).show();
        }
       else {
            mp.start();
            int num1;
            num1 = Integer.parseInt(e.getText().toString());
            Store p = new Store();
            p.amount = num1;
            p.type = "income";
            String K;
            String str = new String();
            str = e2.getText().toString();
            SharedPreferences sp = getSharedPreferences("pocket", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            int balance = sp.getInt("balance", 0);
            K = Integer.toString(balance);
            balance += num1;
            editor.putInt("balance", balance);
            p.description = str;
            editor.apply();
            int income = sp.getInt("income", 0);
            income += num1;
            editor.putInt("income", income);
            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
            editor.apply();


            mysqlite.addToTable(mysqlite.getWritableDatabase(),R.mipmap.input, date, e.getText().toString(), e2.getText().toString());
            e.setText("");
            e2.setText("");
        }

    }
    public void SUB(View V) {
        if(e.length()==0 || e2.length()==0){
            Toast.makeText(getApplicationContext(),"Fields can not be empty", Toast.LENGTH_LONG).show();
        }
        else {

            mp.start();
            int num1;
            num1 = Integer.parseInt(e.getText().toString());
            Store p = new Store();
            p.amount = num1;
            String K;
            p.type = "expense";
            K = new String();
            K = Integer.toString(num1);

            String str = new String();
            str = e2.getText().toString();
            SharedPreferences sp = getSharedPreferences("pocket", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            int balance = sp.getInt("balance", 0);

            balance -= num1;
            editor.putInt("balance", balance);
            editor.apply();
            K = Integer.toString(balance);
            K = str;
            //Toast.makeText(getApplicationContext(),K, Toast.LENGTH_LONG).show();
            p.description = K;
            int expense = sp.getInt("expense", 0);
            expense += num1;
            editor.putInt("expense", expense);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();

            mysqlite.addToTable(mysqlite.getWritableDatabase(),R.mipmap.output, date, e.getText().toString(), e2.getText().toString());
            e.setText("");
            e2.setText("");
        }
    }


    public void undo (View view)
    {
        mp.start();
        Cursor cursor = mysqlite.displaydata();
        int len=cursor.getCount();
       if(len!=0){
            cursor.moveToLast();
            int type=cursor.getInt(1);
       // Toast.makeText(getApplicationContext(),Integer.toString(type), Toast.LENGTH_LONG).show();
           SharedPreferences sp = getSharedPreferences("pocket", Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = sp.edit();
           int data=Integer.parseInt(cursor.getString(3));
           if(type==2130903042){
               editor.putInt("income", sp.getInt("income",0)-data);
               editor.apply();
               editor.putInt("balance", sp.getInt("balance",0)-data);
               editor.apply();
           }else{
               editor.putInt("expense", sp.getInt("expense",0)-data);
               editor.apply();
               editor.putInt("balance", sp.getInt("balance",0)+data);
               editor.apply();
           }

        }

            int deletechecker = mysqlite.deleteAnyData(String.valueOf(mysqlite.getCount()));
            if (deletechecker > 0){
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(this, "Not Deleted", Toast.LENGTH_SHORT).show();

    }


}
