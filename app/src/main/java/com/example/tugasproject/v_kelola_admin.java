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
    import android.widget.Toast;
    import java.util.ArrayList;
    import java.util.List;
    public class v_kelola_admin extends AppCompatActivity {
        private ListView listViewAdmin;
        private Button buttonTambahAdmin;
        private List<String> adminList;
        private DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_v_kelola_admin);

            listViewAdmin = findViewById(R.id.listViewAdmin);
            buttonTambahAdmin = findViewById(R.id.buttonTambahAdmin);
            dbHelper = new DatabaseHelper(this);

            adminList = new ArrayList<>();
            loadData();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adminList);
            listViewAdmin.setAdapter(adapter);

            listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedAdmin = adminList.get(position);
                    Toast.makeText(v_kelola_admin.this, "Selected admin: " + selectedAdmin, Toast.LENGTH_SHORT).show();
                }
            });

            buttonTambahAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(v_kelola_admin.this, v_tambah_admin.class);
                    startActivity(intent);
                }
            });
        }

        private void loadData() {
            adminList.clear();
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM admin", null);

            if (cursor.moveToFirst()) {
                do {
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    adminList.add("* " + username);
                } while (cursor.moveToNext());
            }

            cursor.close();
            dbHelper.close();
        }
    }