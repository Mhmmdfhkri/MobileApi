package com.example.final_mobile.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperMovie extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";

    public DatabaseHelperMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addFavorite(String title) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public void removeFavorite(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_TITLE + "=?", new String[]{title});
        db.close();
    }

    public boolean isFavorite(String title) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_TITLE + "=?";
        String[] selectionArgs = {title};
        Cursor cursor = db.query(TABLE_FAVORITES, null, selection, selectionArgs, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFavorite;
    }
}

