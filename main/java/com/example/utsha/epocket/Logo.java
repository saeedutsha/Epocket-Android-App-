package com.example.utsha.epocket;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class Logo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.my_icon);
        setContentView(R.layout.activity_logo);

        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();

                }finally{


                    nextActivity();


                }}} ;

        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void nextActivity(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

}
