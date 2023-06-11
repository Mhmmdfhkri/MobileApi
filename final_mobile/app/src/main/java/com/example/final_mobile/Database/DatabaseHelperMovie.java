package com.example.final_mobile.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_mobile.Model.FavoriteItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperMovie extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_VOTE_AVERAGE = "vote_average";
    private static final String COLUMN_POSTER_PATH = "poster_path";
    private static final String COLUMN_TYPE = "type"; // Tambahkan kolom tipe

    public DatabaseHelperMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_VOTE_AVERAGE + " REAL, " +
                COLUMN_POSTER_PATH + " TEXT, " +
                COLUMN_TYPE + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addFavorite(String title, double voteAverage, String posterPath, String type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_VOTE_AVERAGE, voteAverage);
        values.put(COLUMN_POSTER_PATH, posterPath);
        values.put(COLUMN_TYPE, type);
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public void removeFavorite(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FAVORITES, COLUMN_TITLE + "=?", new String[]{title});
        db.close();
    }

    public void addFavoriteMovie(String title, double voteAverage, String posterPath) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_VOTE_AVERAGE, voteAverage);
        values.put(COLUMN_POSTER_PATH, posterPath);
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public void addFavoriteTelevision(String title, double voteAverage, String posterPath) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_VOTE_AVERAGE, voteAverage);
        values.put(COLUMN_POSTER_PATH, posterPath);
        db.insert(TABLE_FAVORITES, null, values);
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

    @SuppressLint("Range")
    public List<FavoriteItem> getFavoriteMovies() {
        return getFavoritesByType("Movie");
    }

    @SuppressLint("Range")
    public List<FavoriteItem> getFavoriteTelevision() {
        return getFavoritesByType("Television");
    }

    private List<FavoriteItem> getFavoritesByType(String type) {
        List<FavoriteItem> favoriteList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_TYPE + "=?";
        String[] selectionArgs = {type};
        Cursor cursor = db.query(TABLE_FAVORITES, null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                double voteAverage = cursor.getDouble(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
                String posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
                String itemType = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));

                FavoriteItem favoriteItem = new FavoriteItem(id, title, voteAverage, posterPath);
                favoriteList.add(favoriteItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return favoriteList;
    }
}
