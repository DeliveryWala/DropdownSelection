package com.example.arif.dropdownselection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arif on 24/09/16.
 */
public class MyDBHelper {
    private final Context m_context;


    public static final String DATABASE_NAME = "medicaldatabase";
    public static final String TABLE_NAME = "medical";
    public static final int version = 5;
    public static final String SID = "_id";
    public static final String DR_NAME = "Name";
    public static final String REGION = "Region";
    public static final String DISEASE = "Disease";
    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + SID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DR_NAME + " TEXT , " + REGION + " TEXT , " + DISEASE + " TEXT );";
    private static final String TAG = "SignupAdapter";
    DatabaseHelper helper;
    ContentValues cValues;
    SQLiteDatabase db;

    public MyDBHelper(Context ctx) {
        this.m_context = ctx;
        helper = new DatabaseHelper(m_context);
    }

    public void insertEntry(String dr, String region, String disease) {
        cValues = new ContentValues();
        db = helper.getWritableDatabase();
        cValues.put(DR_NAME, dr);
        cValues.put(REGION, region);
        cValues.put(DISEASE, disease);
        Message.message(m_context, "Inserted Successfully");
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        Context context;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, version);
            this.context = context;
            Message.message(context, "Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Message.message(context, "onCreate called");
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "onUpgrade called");
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context, "" + e);


            }
        }
    }

    //---opens the database---
    public MyDBHelper open() throws SQLException {
        db = helper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        if (db != null)
            db.close();
        if (helper != null)
            helper.close();

    }

    public List<Information> checkInfo(String disease, String region) {

        db = helper.getWritableDatabase();
        List<Information> data = new ArrayList<>();
        Information current = new Information();
        Cursor cursor = db.rawQuery("SELECT " + DR_NAME + ", " + REGION + ", "
                + DISEASE + " FROM " + TABLE_NAME + " where " + DISEASE + " = '"
                + disease + "' AND " + REGION + " = '" + region + "';", null);
        if (cursor.moveToFirst()) {
            do {

                current.setDrname(cursor.getString(0));
                current.setRegion(cursor.getString(1));
                current.setDisease(cursor.getString(2));
                data.add(current);
            } while (cursor.moveToNext());
            Message.message(m_context, " " + data.toString());
            Log.d(" "," "+ data.get(0));
            Log.d(" "," "+ data.get(1));
            Log.d(" "," "+ data.get(2));
            Log.d(" "," "+ data.get(3));
            Log.d(" "," "+ data.get(4));
            Object[] array=data.toArray();
            for(int i=0;i<array.length;i++)
            {
                Log.d("Arif","Abc"+array[i]);
            }
        }

        cursor.close();
        return data;

    }

    public List<Information> showAll() {

        db = helper.getWritableDatabase();
        List<Information> data = new ArrayList<>();
        Information current = new Information();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                current.setDrname(cursor.getString(0));
                current.setRegion(cursor.getString(1));
                current.setDisease(cursor.getString(2));
                data.add(current);
            } while (cursor.moveToNext());

            Message.message(m_context, "" + data.toString());
            cursor.close();

        }
        return data;
    }
}