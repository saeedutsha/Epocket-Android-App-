package com.example.utsha.epocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//import static com.example.utsha.epocket.Input.mysqlite;

public class Output extends AppCompatActivity {
    TextView t1,t2,t3;
    ListView mylist;
    public MySqlite mysqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.my_icon);
        setContentView(R.layout.activity_output);
        mysqlite = new MySqlite(this);
        mylist = (ListView)findViewById(R.id.listview);

        Cursor cursor = mysqlite.displaydata();
        int len=mysqlite.getCount();
        if(len!=0) {
            cursor.moveToFirst();


            int n = 0;
            String[] no = new String[cursor.getCount()];
            int[] type = new int[cursor.getCount()];
            String[] date = new String[cursor.getCount()];
            String[] amount = new String[cursor.getCount()];
            String[] category = new String[cursor.getCount()];


            do {
                Log.d("app debug", cursor.getString(0).toString());
                no[n] = cursor.getString(0).toString();
                type[n] = cursor.getInt(1);
                date[n] = cursor.getString(2).toString();
                amount[n] = cursor.getString(3).toString();
                category[n] = cursor.getString(4).toString();
               // Toast.makeText(getApplicationContext(),Integer.toString(type[n]), Toast.LENGTH_LONG).show();
                n = n + 1;

            } while (cursor.moveToNext());
            CustomAdapter customAdapter = new CustomAdapter(this, no, type, date, amount, category);
            mylist.setAdapter(customAdapter);
        }
            t1 = (TextView) findViewById(R.id.textview2);
            t2 = (TextView) findViewById(R.id.textview4);
            t3 = (TextView) findViewById(R.id.textview6);
            SharedPreferences sp = getSharedPreferences("pocket", Context.MODE_PRIVATE);
            int income = sp.getInt("income", 0);
            t1.setText(Integer.toString(income));
        int expense = sp.getInt("expense", 0);
            t2.setText(Integer.toString(expense));
            int balance = sp.getInt("balance", 0);
            t3.setText(Integer.toString(balance));

    }

    /*public void viewdata(String data) {
        T.setText(data);
    }*/
}
