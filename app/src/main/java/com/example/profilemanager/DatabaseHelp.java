package com.example.profilemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "timings.db";
    public static final String TABLE_NAME = "timing_table";
    public static final String COL1 = "id";
    public static final String COL2 = "starting_time";
    public static final String COL3 = "ending_time";

    public DatabaseHelp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "startting_time TEXT, ending_time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("create table "+ TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "startting_time TEXT, ending_time TEXT)");
    }

    public boolean insertTimings(String startTime, String endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, startTime);
        contentValues.put(COL3, endTime);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
}
