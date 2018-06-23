package com.example.ujjwalsmahapatra.recyclerviewwithdatabasefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {
    MyHelper mh;
    SQLiteDatabase db;

    public MyDatabase(Context c)
    {
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
public void deleteStudent(int id)
{
    db.delete("student","_id=?",new String[]{""+id});
}
public void updateStudent(int sno, String sname,String sub){
        ContentValues cv=new ContentValues();
        cv.put("sname",sname);
        cv.put("sub",sub);
        db.update("student",cv,"_id=?",new String[]{"+sno"});
}
public Cursor queryStudent()
{
Cursor c=db.query("student",null,null,null,null,null,null);
return c;
}
public void close()
{
    if(db!=null)
        db.close();
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
