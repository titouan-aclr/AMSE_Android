package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "liste";

    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";
    public static final String ANNEE = "annee";

    // Database Information
    static final String DB_NAME = "LEBONCOIN.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + PRICE + " TEXT, "
            + IMAGE + " TEXT, "
            + ANNEE + " TEXT);";


    public DBHelper(Context context) {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
