package com.example.tugasproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    // Initializing db
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    // Initializing table (admin)
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ID = "id_admin";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Initializing table (barang_masuk)
    public static final String TABLE_MASUK = "barang_masuk";
    public static final String COLUMN_ID_BARANGMASUK = "id_barang_masuk";
    public static final String COLUMN_NAMA_BARANG = "nama_barangmasuk";
    public static final String COLUMN_MERK_BARANG = "merk_barang";
    public static final String COLUMN_JUMLAH_BARANG = "jumlah_barang";
    public static final String COLUMN_HARGA_BARANG = "harga_barang";
    public static final String COLUMN_PENANGGUNG_JAWAB_MASUK = "penanggung_jawab_masuk";


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

        // Create the barang_masuk table
        String createBarangMasukTableQuery = "CREATE TABLE " + TABLE_MASUK + "("
                + COLUMN_ID_BARANGMASUK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAMA_BARANG + " TEXT, "
                + COLUMN_MERK_BARANG + " TEXT, "
                + COLUMN_JUMLAH_BARANG + " TEXT, "
                + COLUMN_HARGA_BARANG + " REAL, "
                + COLUMN_PENANGGUNG_JAWAB_MASUK + " TEXT)";
        db.execSQL(createBarangMasukTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MASUK);
        // Create the table again
        onCreate(db);
    }

    public long insertBarangMasuk(String nama, String merk, int jumlah, int harga, String penanggungJawab) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA_BARANG, nama);
        values.put(COLUMN_MERK_BARANG, merk);
        values.put(COLUMN_JUMLAH_BARANG, jumlah);
        values.put(COLUMN_HARGA_BARANG, harga);
        values.put(COLUMN_PENANGGUNG_JAWAB_MASUK, penanggungJawab);
        long id = db.insert(TABLE_MASUK, null, values);
        db.close();
        return id;
    }
}
