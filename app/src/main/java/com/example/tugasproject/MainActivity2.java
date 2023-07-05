package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {
    Button Keluar, btnList, btnKelolaAdmin;
    AlertDialog.Builder builder;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Keluar = findViewById(R.id.Keluar);
        btnList = (Button) findViewById(R.id.btnList);
        btnKelolaAdmin = (Button) findViewById(R.id.btnKelolaAdmin);
        builder = new AlertDialog.Builder(this);

        btnKelolaAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, v_kelola_admin.class);
                startActivity(intent);
                finish();
            }
        });

        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Attention!!!")
                        .setMessage("Aapakah Anda Yakin?")
                        .setCancelable(true)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });
    }
}