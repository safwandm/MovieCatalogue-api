package com.example.tgs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tgs.db.MovieDatabaseContract.MovieColumns;

import static com.example.tgs.db.MovieDatabaseContract.MovieColumns.TABLE_NAME;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION = 1;

    private static final String DATABASE_NAME = "dbmovie";

    private static final String SQL_CREATE_TABLE = String.format("CREATE TABLE %s" +
                    " (%s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL)",
            TABLE_NAME,
//            MovieColumns._ID,
            MovieColumns.ID,
            MovieColumns.TITLE,
            MovieColumns.OVERVIEW,
            MovieColumns.RELEASE_DATE,
            MovieColumns.VOTE_AVERAGE,
            MovieColumns.POSTER_PATH_STRING,
            MovieColumns.BACKDROP_PATH_STRING
    );

    MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

}
