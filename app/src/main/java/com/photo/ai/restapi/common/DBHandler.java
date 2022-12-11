package com.photo.ai.restapi.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Recent.db";
    // Channel table name
    public static final String TABLE_RECENT = "table_recent";
    public static final String RECENT_ID = "recent_id";
    public static final String RECENT_TITLE = "recent_title";
    public static final String RECENT_HEIGHT = "recent_height";
    public static final String RECENT_AFTER = "recent_after";
    public static final String RECENT_BEFORE = "recent_before";
    public static final String RECENT_PRESET = "recent_preset";
    public static final String RECENT_PREMIUM = "recent_premium";

    private String CREATE_RECENT_TABLE = "CREATE TABLE " + TABLE_RECENT + "("
            + RECENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RECENT_TITLE + " TEXT,"
            + RECENT_HEIGHT + " TEXT,"
            + RECENT_AFTER + " TEXT,"
            + RECENT_BEFORE + " TEXT,"
            + RECENT_PRESET + " TEXT,"
            + RECENT_PREMIUM + " TEXT " + ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECENT_TABLE);
    }

    public boolean checkRecent(String title) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select *  FROM table_recent WHERE recent_title = ?;", new String[] { title });
        if (c.getCount() > 0) {
            return true;
        }
        return false;
    }

    public void addRecent(String title,String height, String after, String before, String preset, String premium) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECENT_TITLE, title);
        contentValues.put(RECENT_HEIGHT, height);
        contentValues.put(RECENT_AFTER, after);
        contentValues.put(RECENT_BEFORE, before);
        contentValues.put(RECENT_PRESET, preset);
        contentValues.put(RECENT_PREMIUM, premium);
        db.insert(TABLE_RECENT, null, contentValues);
        db.close();
    }

//    public List<Recent> getRecentData() {
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        List<Recent> recentArrayList = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_RECENT + " ORDER BY " + RECENT_ID + " DESC";
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                recentArrayList.add(new Recent(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
//            } while (cursor.moveToNext());
//        }
//        return recentArrayList;
//    }

    public void removeRecent(String title) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_RECENT, RECENT_TITLE + "=?", new String[]{title});
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_RECENT_TABLE);
        onCreate(db);
    }

}
