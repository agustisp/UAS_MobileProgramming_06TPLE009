package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;
    Button btnkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnkembali = (Button) findViewById(R.id.btnkembali);
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BukaActDua = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(BukaActDua);
            }
        });

        listView = (ListView) findViewById(R.id.mataKuliah);
        String[] values = new String[]{
                "Algoritma Informatika", "Struktur Data", "Pemrograman Web 1",
                "Pemrograman 2", "Mobile Programming", "Kecerdasan Buatan",
                "Sistem Informasi Manajemen", "Komputer Grafik I", "Fisika Dasar 1"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, values);

        listView.setAdapter(adapter);
    }
}