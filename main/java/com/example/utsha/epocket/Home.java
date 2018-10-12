package com.example.utsha.epocket;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.graphics.PorterDuff;
        import android.media.MediaPlayer;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.animation.AlphaAnimation;
        import android.view.animation.Animation;
        import android.widget.Button;
        import android.widget.MediaController;
        import android.widget.Toast;

        import static com.example.utsha.epocket.R.drawable.buttonshape;

//import static com.example.utsha.epocket.Input.mysqlite;

public class Home extends AppCompatActivity {
    public MySqlite mysqlite;
    Button Use_Wallet,usage;
    MediaPlayer mp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.my_icon);
        setContentView(R.layout.activity_home);
        mysqlite = new MySqlite(this);
        mp=MediaPlayer.create(this,R.raw.sample);

    }


    public void usewallet(View view)
    {
        mp.start();
        Intent i = new Intent(Home.this, Input.class);
        startActivity(i);
    }

    public void usagehistory(View view)
    {
        mp.start();
        Intent i = new Intent(Home.this, Output.class);
        startActivity(i);

    }

    public void exitMethod(View view){
        mp.start();
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View v = layoutInflater.inflate(R.layout.custom_alert,null);
        final Button B1= (Button)v.findViewById(R.id.button8);
        final Button B2= (Button)v.findViewById(R.id.button9);


        /*builder.setTitle("WARNING !!");
        builder.setMessage("Are you sure want to exit ?? ");
        builder.setNegativeButton("NO",null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        */
        builder.setView(v);
        final AlertDialog alertDialog = builder.create();
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                finish();
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    public void clearalldata(View view){
        mp.start();
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View v = layoutInflater.inflate(R.layout.custom_alert2,null);
        final Button B1= (Button)v.findViewById(R.id.button8);
        final Button B2= (Button)v.findViewById(R.id.button9);

        builder.setView(v);
        final AlertDialog alertDialog = builder.create();

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                SharedPreferences sp=getSharedPreferences("pocket", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                int income=sp.getInt("income",0);
                int expense=sp.getInt("expense", 0);
                int balance=sp.getInt("balance", 0);
                if(income==0 && expense==0 && balance==0){
                    Toast.makeText(getApplicationContext(),"NO DATA FOUND", Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putInt("income",0);
                    editor.apply();
                    editor.putInt("expense",0);
                    editor.apply();
                    editor.putInt("balance",0);
                    editor.apply();
                    mysqlite.deleteAllData();
                }
                alertDialog.dismiss();
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();


       /* builder.setTitle("WARNING !!");
        builder.setMessage("Are you sure want to delete all data ?? ");
        builder.setNegativeButton("NO",null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences sp=getSharedPreferences("pocket", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                int income=sp.getInt("income",0);
                int expense=sp.getInt("expense", 0);
                int balance=sp.getInt("balance", 0);
                if(income==0 && expense==0 && balance==0){
                    Toast.makeText(getApplicationContext(),"NO DATA FOUND", Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putInt("income",0);
                    editor.apply();
                    editor.putInt("expense",0);
                    editor.apply();
                    editor.putInt("balance",0);
                    editor.apply();
                    mysqlite.deleteAllData();
                }
            }
        });*/

    }

    public void showus(View view){
        mp.start();
        Intent i = new Intent(Home.this, AboutUs.class);
        startActivity(i);
    }



}
