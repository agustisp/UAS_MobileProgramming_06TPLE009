    package com.example.tugasproject;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.os.Bundle;
    import android.text.method.HideReturnsTransformationMethod;
    import android.text.method.PasswordTransformationMethod;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.Toast;

    import java.util.Map;

    public class MainActivity extends AppCompatActivity {
        // Creating objects
        Button Login_button;
        EditText usernameEditText, passwordEditText;
        CheckBox showPasswordCheckBox;
        SharedPreferences sharedPreferences;
        DatabaseHelper databaseHelper;


        private static final String SHARED_PREF_NAME = "admin_login";
        private static final String ADMIN_USERNAME = "admin";
        private static final String ADMIN_PASSWORD = "admin";
        private static final String ADMIN_STATUS = "admin_status";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Login_button = (Button) findViewById(R.id.Login_button);
            usernameEditText = (EditText) findViewById(R.id.username);
            passwordEditText = (EditText) findViewById(R.id.passwordEditText);
            showPasswordCheckBox = (CheckBox) findViewById(R.id.showPasswordCheckBox);

            sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
            databaseHelper = new DatabaseHelper(this);

            Login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = usernameEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    int loginResult = isMatchingAdmin(username, password);

                    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                        // Set admin status to true in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(ADMIN_STATUS, true);
                        editor.apply();

                        // Login berhasil, lanjutkan ke halaman admin
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    } else if (loginResult == 1) {
                        // Login berhasil, lanjutkan ke halaman admin
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                    else {
                        // Login gagal, tampilkan pesan kesalahan
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        // Tampilkan password
                        passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        // Sembunyikan password
                        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });
        }
        private int isMatchingAdmin(String username, String password) {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String[] projection = {DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD};
            String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};

            Cursor cursor = db.query(
                    DatabaseHelper.TABLE_ADMIN,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            int result = 0;

            if (cursor != null && cursor.getCount() > 0) {
                result = 1; // Username dan password cocok
            } else {
                result = 2; // Username atau password tidak cocok
            }

            if (cursor != null) {
                cursor.close();
            }

            db.close();

            return result;
        }
    }
