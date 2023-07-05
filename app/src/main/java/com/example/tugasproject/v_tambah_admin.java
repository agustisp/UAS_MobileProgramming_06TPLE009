package com.example.tugasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class v_tambah_admin extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonSimpan;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vtambah_admin);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSimpan = findViewById(R.id.buttonSimpan);
        databaseHelper = new DatabaseHelper(this);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Insert admin data into database
                long result = insertAdmin(username, password);

                if (result != -1) {
                    Toast.makeText(v_tambah_admin.this, "Admin has been added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v_tambah_admin.this, "Failed to add admin", Toast.LENGTH_SHORT).show();
                }

                // Return to v_kelola_admin page
                finish();
            }
        });
    }

    private long insertAdmin(String username, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("admin", null, values);
        db.close();
        return result;
    }
}