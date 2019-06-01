package com.lexkane.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "people_table";

    private static final String TABLE_NAME = "NOTES";
    private static final String COL1 = "id";
    private static final String COL2 = "title";
    private static final String COL3 = "note";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public boolean addData(String title, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, note);

        Log.d(TAG, "addData: Adding " + title + ", " + note +" to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result == -1 ? false : true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
