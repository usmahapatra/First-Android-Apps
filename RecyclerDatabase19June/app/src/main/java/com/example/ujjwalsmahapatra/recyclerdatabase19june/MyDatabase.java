package com.example.ujjwalsmahapatra.recyclerdatabase19june;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {
    MyHelper mh;
    SQLiteDatabase db;

    public MyDatabase(Context c) {
        mh=new MyHelper(c,"techpalle.db",null,1);
    }

    public void open()
    {
        db=mh.getWritableDatabase();//open database connection
    }

    public void insertStudent(String sname,String sub){
        ContentValues cv=new ContentValues();
        cv.put("sname",sname);
        cv.put("sub",sub);
        db.insert("student",null,cv);
    }
    public void delete(int id)
    {
        db.delete("student","_id=?",new String[]{""+id});
    }

    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table student(_id integer primary key,sname text,sub text);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
