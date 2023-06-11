package com.example.final_mobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperTelevision extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "television.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_TITLE = "title";

    public DatabaseHelperTelevision(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableFavorites = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                COLUMN_TITLE + " TEXT PRIMARY KEY)";
        db.execSQL(createTableFavorites);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public boolean isFavorite(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, new String[]{COLUMN_TITLE},
                COLUMN_TITLE + "=?", new String[]{title},
                null, null, null);

        boolean isFavorite = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isFavorite;
    }

    public void addFavorite(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public void removeFavorite(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_TITLE + "=?", new String[]{title});
        db.close();
    }
}
