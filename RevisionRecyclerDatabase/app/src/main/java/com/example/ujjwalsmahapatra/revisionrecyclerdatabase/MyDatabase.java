package com.example.ujjwalsmahapatra.revisionrecyclerdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase {
    public MyDatabase(Context c) {
        mh=new MyHelper(c,"contacts.db",null,1);
    }

    public void open()
    {
        db=mh.getWritableDatabase();
    }

    public void insert(String name,String number)
    {
        Log.d("hahaha",name+number);
        ContentValues cv=new ContentValues();
        cv.put("cname",name);
        cv.put("cnumber",number);
        db.insert("contacts",null,cv);
    }

    public void update(int cno,String name, String number)
    {
        ContentValues cv = new ContentValues();
        cv.put("cname",name);
        cv.put("cnumber",number);
        db.update("contacts",cv,"_id=?",new String[] {""+cno});
    }

    public void delete(int cno){
        db.delete("contacts","_id=?",new String[] {""+cno});
    }

    public Cursor queryContacts(){
        Cursor c=db.query("contacts",null,null,null,null,null,null);

        return c;
    }

    public void close()
    {
        if(db!=null)
        {
            db.close();
        }
    }
    SQLiteDatabase db;
    //I forgot below lines.. all the lines:
    MyHelper mh;


    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contacts(_id integer primary key, cname text, cnumber text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
