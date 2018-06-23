package com.example.ujjwalsmahapatra.databaseplain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {

    public class MyDatabasehelper extends SQLiteOpenHelper{
        public MyDatabasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, "Employee.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
