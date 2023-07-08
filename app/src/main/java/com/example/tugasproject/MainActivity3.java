package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    Button btnkembali, btnTambahBarangMasuk;
    ListView listViewBarangMasuk;
    List<String> barangMasukList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnTambahBarangMasuk = (Button) findViewById(R.id.btnTambahBarangMasuk);
        btnkembali = (Button) findViewById(R.id.btnkembali);
        listViewBarangMasuk = findViewById(R.id.listViewBarangMasuk);
        databaseHelper = new DatabaseHelper(this);
        barangMasukList = new ArrayList<>();
        loadData();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barangMasukList);
        listViewBarangMasuk.setAdapter(adapter);

        listViewBarangMasuk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedAdmin = barangMasukList.get(position);
                Toast.makeText(MainActivity3.this, "Selected admin: " + selectedAdmin, Toast.LENGTH_SHORT).show();
            }
        });

        btnTambahBarangMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, v_tambah_barangmasuk.class);
                startActivity(intent);
            }
        });
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BukaActDua = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(BukaActDua);
            }
        });

    }

    private void loadData() {
        barangMasukList.clear();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM barang_masuk", null);

        if (cursor.moveToFirst()) {
            do {
                String nama_barangmasuk = cursor.getString(cursor.getColumnIndex("nama_barangmasuk"));
                String merk_barang = cursor.getString(cursor.getColumnIndex("merk_barang"));
                String jumlah_barang = cursor.getString(cursor.getColumnIndex("jumlah_barang"));
                String harga_barang = cursor.getString(cursor.getColumnIndex("harga_barang"));
                String penanggung_jawab_masuk = cursor.getString(cursor.getColumnIndex("penanggung_jawab_masuk"));
                barangMasukList.add("* " + nama_barangmasuk + " - " + merk_barang + " - " + jumlah_barang + " - " + harga_barang + " - " + penanggung_jawab_masuk);
            } while (cursor.moveToNext());
        }

        cursor.close();
        databaseHelper.close();
    }
}