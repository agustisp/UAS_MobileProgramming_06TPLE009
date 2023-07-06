package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {
    Button btnkembali, btnTambahBarangMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnTambahBarangMasuk = (Button) findViewById(R.id.btnTambahBarangMasuk);
        btnkembali = (Button) findViewById(R.id.btnkembali);

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
}