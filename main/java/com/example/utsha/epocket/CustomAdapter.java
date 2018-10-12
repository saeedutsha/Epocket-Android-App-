package com.example.utsha.epocket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] no;
    int[] type;
    String[] date;
    String[] amount;
    String[] category;
    private static LayoutInflater inflater = null;



    public CustomAdapter(Context ct,String[] n,int[] t,String[] d,String[] a,String[] c){
        context=ct;
        no=n;
        type=t;
        date=d;
        amount=a;
        category=c;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    @Override
    public int getCount() {
        return no.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class MyHolder
    {
        TextView no;
        ImageView type;
        TextView date;
        TextView amount;
        TextView category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder myholder = new MyHolder();
        View myview;

        myview = inflater.inflate(R.layout.custom_layout,null);

        myholder.no = (TextView)myview.findViewById(R.id.textview1);
        myholder.type = (ImageView)myview.findViewById(R.id.imageview);
        myholder.date = (TextView)myview.findViewById(R.id.textview2);
        myholder.amount = (TextView)myview.findViewById(R.id.textview3);
        myholder.category = (TextView)myview.findViewById(R.id.textview4);

        //Toast.makeText(context,no[position],Toast.LENGTH_SHORT).show();
        myholder.no.setText(no[position]);
        myholder.type.setImageResource(type[position]);
        myholder.date.setText(date[position]);
        myholder.amount.setText(amount[position]);
        myholder.category.setText(category[position]);

        return myview;
    }
}


