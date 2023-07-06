package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class v_tambah_barangmasuk extends AppCompatActivity {
    EditText nama_barangmasuk, merk_barang, jumlah_barang, harga_barang, penanggung_jawab;
    Button btnSimpan;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barangmasuk);

        nama_barangmasuk = findViewById(R.id.nama_barangmasuk);
        merk_barang = findViewById(R.id.merk_barang);
        jumlah_barang = findViewById(R.id.harga_barang);
        harga_barang = findViewById(R.id.harga_barang);
        penanggung_jawab = findViewById(R.id.penanggung_jawab);
        btnSimpan = findViewById(R.id.buttonSimpan);
        databaseHelper = new DatabaseHelper(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaBarangMasuk = nama_barangmasuk.getText().toString().trim();
                String merkBarangMasuk = merk_barang.getText().toString().trim();
                int jumlahBarangMasuk = Integer.parseInt(jumlah_barang.getText().toString().trim());
                int hargaBarangMasuk = Integer.parseInt(harga_barang.getText().toString().trim());
                String penanggungJawabMasuk = penanggung_jawab.getText().toString().trim();

                long result = databaseHelper.insertBarangMasuk(namaBarangMasuk, merkBarangMasuk, jumlahBarangMasuk, hargaBarangMasuk, penanggungJawabMasuk);

                if (result != -1) {
                    Toast.makeText(v_tambah_barangmasuk.this, "Incoming goods has been added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v_tambah_barangmasuk.this, "Failed to add incoming goods", Toast.LENGTH_SHORT).show();
                }

                // Return to previous page
                finish();
            }
        });
    }
}