package com.example.employeeregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "CompanyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table with 3 columns: id, name, salary
        db.execSQL("create table employees(id TEXT primary key, name TEXT, salary TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists employees");
    }

    public boolean insertEmployee(String id, String name, String salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("salary", salary);

        long result = db.insert("employees", null, contentValues);
        return result != -1; // returns true if successful
    }

    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from employees", null);
    }
}