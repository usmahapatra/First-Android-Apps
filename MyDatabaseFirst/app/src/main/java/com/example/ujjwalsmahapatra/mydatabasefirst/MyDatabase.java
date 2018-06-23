package com.example.ujjwalsmahapatra.mydatabasefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabase {
    MyDatabaseHelper mh;
    SQLiteDatabase db;
    public static final String TABLE_NAME="Registration";
    private static final String CREATE_COMMAND="create table "+TABLE_NAME+"("+"_id INTEGER PRIMARY KEY, sname TEXT,sub TEXT "+");";
    public class MyDatabaseHelper extends SQLiteOpenHelper
    {

        public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    //Constructor
    public MyDatabase(Context c)
    {
        mh=new MyDatabaseHelper(c, "Reader.db", null, 1);
    }

    public void open()
    {
         db=mh.getWritableDatabase();
    }

    public void insert(String sname,String sub)
    {
        ContentValues cv=new ContentValues();//like bundle

        cv.put("sname",sname);//column name , value
        cv.put("sub",sub);
       Log.d("inserted","Data inserted");

        //let us insert

        db.insert("Registration",null,cv);
    }

    public Cursor query(){
        Cursor c= db.query("Registration",null,null,null,null,null,null);

        //we are returning student table data

        return c;
    }

    public void close()
    {
        if(db!=null)
            db.close();//if we don't close it will lead to memory leak
    }
}
