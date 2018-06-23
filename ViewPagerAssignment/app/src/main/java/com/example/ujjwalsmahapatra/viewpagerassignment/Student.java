package com.example.ujjwalsmahapatra.viewpagerassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Student {

    MyHelper myHelper;
    SQLiteDatabase sdb;
    public Student(Context context) {
        myHelper=new MyHelper(context,"techpalle.db",null,1);
    }

    public void open()
    {
        sdb=myHelper.getWritableDatabase();
    }

    public void insertstudent(String name,String subject)
    {
        ContentValues cv=new ContentValues();
        cv.put("sname",name);
        cv.put("subject",subject);
        sdb.insert("stu_det",null,cv);//inserted a row
    }

    public Cursor querystudent()
    {
        Cursor c=sdb.query("stu_det",null,null,null,null,null,null);
        return c;
    }
    public void close()
    {
        if(sdb!=null)
            sdb.close();
    }
    public class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table stu_det(_id integer primary key,sname text,subject text);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
