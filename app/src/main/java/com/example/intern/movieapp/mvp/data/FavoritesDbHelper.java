package com.example.intern.movieapp.mvp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class FavoritesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final String TABLE_NAME = "favortiesTable";
    private static final String COL_1 = "_id";
    private static final String COL_2 = "originalTitle";
    private static final String COL_3 = "smallImagePath";
    private static final String COL_4 = "bigImagePath";
    private static final String COL_5 = "averageVote";
    private static final String COL_6 = "releaseDate";
    private static final String COL_7 = "summary";

    private static final int DATABASE_VERSION = 2;

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT NOT NULL, "
                + COL_3 + " TEXT NOT NULL, "
                + COL_4 + " TEXT NOT NULL, "
                + COL_5 + " TEXT NOT NULL, "
                + COL_6 + " TEXT NOT NULL, "
                + COL_7 + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String oTitle,
                              String smallImagePath, String bigImagePath,
                              String averageVote, String releaseDate, String summary) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(TAG, String.valueOf(db.isOpen()));
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, oTitle);
        contentValues.put(COL_3, smallImagePath);
        contentValues.put(COL_4, bigImagePath);
        contentValues.put(COL_5, averageVote);
        contentValues.put(COL_6, releaseDate);
        contentValues.put(COL_7, summary);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean deleteData(String oTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COL_2 + " = '" + oTitle + "'", null);
        return result != -1;
    }

    public boolean existsInDatabase(String oTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exists = false;
        Log.d(TAG, String.valueOf(db.isOpen()));
            Cursor cursor = getAllData();
            if(cursor != null) {
                cursor.moveToFirst();
                for(int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    if(cursor.getString(cursor.getColumnIndex(COL_2)).equals(oTitle)) {
                        exists = true;
                    }
                }
            }
        return exists;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    //TODO Finish creating database
    //Use https://developer.android.com/training/basics/intents/result to get a result of clicking favorite button from
    //DetailIntent
}
