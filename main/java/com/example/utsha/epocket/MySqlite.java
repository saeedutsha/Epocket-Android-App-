package com.example.utsha.epocket;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySqlite extends SQLiteOpenHelper {

        private Context context;
        private static final String DATABASE_NAME = "mydatabaseuts";
        private static final String TABLE_NAME = "mytableuts";
        private static final String COLUMN1 = "SERIAL";
        private static final String COLUMN2 = "TYPE";
        private static final String COLUMN3 = "DATE";
        private static final String COLUMN4 = "AMOUNT";
        private static final String COLUMN5 = "DESCRIPTION";
        private int n=0;


        public MySqlite(Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String quary;
            quary = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN1 + " INTEGER PRIMARY KEY, " + COLUMN2 + " INTEGER, " + COLUMN3 + " TEXT, " + COLUMN4 + " TEXT, " + COLUMN5 + " TEXT " + ")";
            db.execSQL(quary);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public void addToTable(SQLiteDatabase sqLiteDatabase,int type, String date, String amount, String description) {
            int n = getCount()+1;
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN1,n );
            contentValues.put(COLUMN2, type);
            contentValues.put(COLUMN3, date);
            contentValues.put(COLUMN4, amount);
            contentValues.put(COLUMN5, description);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
           // Toast.makeText(context,success+"",Toast.LENGTH_SHORT).show();

        }

    public Cursor displaydata() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

    public void deleteAllData(){
            n=0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

    public int deleteAnyData(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME," SERIAL = ? ",new String[] {id});
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


    }
