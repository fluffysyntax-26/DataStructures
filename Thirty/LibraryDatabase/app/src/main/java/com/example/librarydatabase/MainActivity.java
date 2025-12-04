package com.example.librarydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etAuthor;
    CheckBox cbAvailable;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        cbAvailable = findViewById(R.id.cbAvailable);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Book Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String author = etAuthor.getText().toString();
                // Store '1' for available, '0' for not available
                int status = cbAvailable.isChecked() ? 1 : 0;

                boolean inserted = dbHelper.insertBook(title, author, status);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etAuthor.setText("");
                }
            }
        });

        // View Available Books Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getAvailableBooks();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No available books found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("ID: ").append(cursor.getString(0)).append("\n");
                    buffer.append("Title: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Author: ").append(cursor.getString(2)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Simple Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "LibraryDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create table with columns: id, title, author, status
            db.execSQL("create table books(id integer primary key autoincrement, title text, author text, status integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists books");
            onCreate(db);
        }

        public boolean insertBook(String title, String author, int status) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("author", author);
            contentValues.put("status", status);
            long result = db.insert("books", null, contentValues);
            return result != -1;
        }

        public Cursor getAvailableBooks() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Query: Select * from books where status = 1
            return db.rawQuery("select * from books where status=1", null);
        }
    }
}