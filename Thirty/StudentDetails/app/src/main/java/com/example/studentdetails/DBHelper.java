package com.example.studentdetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "StudentDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Table Query
        db.execSQL("create table students(name TEXT, reg TEXT, dept TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists students");
    }

    // Method to Insert Data
    public boolean insertData(String name, String reg, String dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("reg", reg);
        contentValues.put("dept", dept);
        long result = db.insert("students", null, contentValues);
        return result != -1; // returns true if insert successful
    }

    // Method to Get All Data
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from students", null);
    }
}