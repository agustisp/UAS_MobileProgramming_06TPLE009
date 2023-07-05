package com.example.tugasproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    // Initializing table (admin)
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ID = "id_admin";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the admin table
        String createAdminTableQuery = "CREATE TABLE " + TABLE_ADMIN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createAdminTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        // Create the table again
        onCreate(db);
    }
}
