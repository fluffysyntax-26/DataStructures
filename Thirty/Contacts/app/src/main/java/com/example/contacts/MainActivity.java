package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Contact Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                boolean inserted = dbHelper.insertContact(name, phone);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Contact Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etPhone.setText("");
                }
            }
        });

        // View Contacts Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getContacts();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No contacts found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Phone: ").append(cursor.getString(2)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ContactDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, phone
            db.execSQL("create table contacts(id integer primary key autoincrement, name text, phone text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists contacts");
            onCreate(db);
        }

        public boolean insertContact(String name, String phone) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("phone", phone);
            long result = db.insert("contacts", null, contentValues);
            return result != -1;
        }

        public Cursor getContacts() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Query with ORDER BY name ASC for alphabetical sorting
            return db.rawQuery("select * from contacts order by name asc", null);
        }
    }
}