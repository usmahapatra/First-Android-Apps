package com.example.ujjwalsmahapatra.databasefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {
    private  MyHelper mh;//for ddl commands
    private  SQLiteDatabase sdb;//for dml commands

    //constructor to initialize my helper variable

    public  MyDatabase(Context c) {

        //to change version .. modify the last parameter (from 1 to 2)
        mh = new MyHelper(c, "techpalle.db", null, 1/*2*/);
    }

    //step 6:open database connection:
    public void open()
    {
        sdb=mh.getWritableDatabase();
    }
    //step7: DML Commands- insert,update,delete,query

    public void insertStudent(String sname,String sub){

        ContentValues cv=new ContentValues();//like bundle
        cv.put("sname",sname);//column name , value
        cv.put("sub",sub);

        //let us insert

        sdb.insert("student",null,cv);

    }

    public Cursor queryStudent(){
        Cursor c= sdb.query("student",null,null,null,null,null,null);

        //we are returning student table data

        return c;
    }
//closew database connection:
    public void close()
    {
        if(sdb!=null)
            sdb.close();//if we don't close it will lead to memory leak
    }

        //extend the inner helper class
    public class MyHelper extends SQLiteOpenHelper{


        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);


        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //table design is to be done only once:
            sqLiteDatabase.execSQL("create table student(_id integer primary key, sname text, sub text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //to upgrade the version:
            /*if(i1==2){
                sqLiteDatabase.execSQL("create table jobs(_id integer primary key, jd text);");
                sqLiteDatabase.execSQL("alter table student add column comp text;");*/
                //sqLiteDatabase.execSQL("drop table  if exists"+Resume);

            //}

        }
    }

}
