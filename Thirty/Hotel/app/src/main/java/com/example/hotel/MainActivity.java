package com.example.hotel;

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

    EditText etName, etRoomType, etDays;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etRoomType = findViewById(R.id.etRoomType);
        etDays = findViewById(R.id.etDays);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Check In Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String type = etRoomType.getText().toString();
                String days = etDays.getText().toString();

                boolean inserted = dbHelper.insertGuest(name, type, days);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Guest Checked In", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etRoomType.setText("");
                    etDays.setText("");
                }
            }
        });

        // View Occupied Rooms Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getGuests();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No rooms occupied.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Guest: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Room Type: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Days: ").append(cursor.getString(3)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "HotelDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, room_type, days
            db.execSQL("create table guests(id integer primary key autoincrement, name text, room_type text, days text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists guests");
            onCreate(db);
        }

        public boolean insertGuest(String name, String type, String days) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("room_type", type);
            contentValues.put("days", days);
            long result = db.insert("guests", null, contentValues);
            return result != -1;
        }

        public Cursor getGuests() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from guests", null);
        }
    }
}