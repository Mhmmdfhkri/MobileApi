package com.example.final_mobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseFavorite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_POSTER_PATH = "poster_path";
    private static final String COLUMN_RELEASE_DATE = "release_date";
    private static final String COLUMN_VOTE_AVERAGE = "vote_average";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_BACKDROP_PATH = "backdrop_path";

    public DatabaseFavorite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_POSTER_PATH + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT, " +
                COLUMN_VOTE_AVERAGE + " REAL, " +
                COLUMN_OVERVIEW + " TEXT, " +
                COLUMN_BACKDROP_PATH + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addFavorite(String title, String posterPath, String releaseDate, double voteAverage,
                            String overview, String backdropPath) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_POSTER_PATH, posterPath);
        values.put(COLUMN_RELEASE_DATE, releaseDate);
        values.put(COLUMN_VOTE_AVERAGE, voteAverage);
        values.put(COLUMN_OVERVIEW, overview);
        values.put(COLUMN_BACKDROP_PATH, backdropPath);
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

