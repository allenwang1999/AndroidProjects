package com.example.intern.movieapp.mvp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final String TABLE_NAME = "favortiesTable";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Title";
    private static final String COL_3 = "originalTitle";
    private static final String COL_4 = "smallImagePath";
    private static final String COL_5 = "bigImagePath";
    private static final String COL_6 = "averageVote";
    private static final String COL_7 = "releaseDate";

    private static final int DATABASE_VERSION = 1;

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT NOT NULL,"
                + COL_3 + " TEXT NOT NULL,"
                + COL_4 + " TEXT NOT NULL,"
                + COL_5 + " TEXT NOT NULL,"
                + COL_5 + " TEXT NOT NULL,"
                + COL_6 + " TEXT NOT NULL,"
                + COL_7 + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String title, String oTitle,
                              String smallImagePath, String bigImagePath,
                              String averageVote, String releaseDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, oTitle);
        contentValues.put(COL_4, smallImagePath);
        contentValues.put(COL_5, bigImagePath);
        contentValues.put(COL_6, averageVote);
        contentValues.put(COL_7, releaseDate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    //TODO Finish creating database
    //Use https://developer.android.com/training/basics/intents/result to get a result of clicking favorite button from
    //DetailIntent
}
