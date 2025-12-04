package com.example.parkinglot;

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

    EditText etVehicleNo, etTime;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etVehicleNo = findViewById(R.id.etVehicleNo);
        etTime = findViewById(R.id.etTime);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Vehicle Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleNo = etVehicleNo.getText().toString();
                String time = etTime.getText().toString();

                boolean inserted = dbHelper.insertVehicle(vehicleNo, time);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Vehicle Parked", Toast.LENGTH_SHORT).show();
                    etVehicleNo.setText("");
                    etTime.setText("");
                }
            }
        });

        // View Parked Vehicles Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getVehicles();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No vehicles parked.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Vehicle No: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Entry Time: ").append(cursor.getString(2)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ParkingDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, vehicle_no, entry_time
            db.execSQL("create table parking(id integer primary key autoincrement, vehicle_no text, entry_time text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists parking");
            onCreate(db);
        }

        public boolean insertVehicle(String vehicleNo, String time) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("vehicle_no", vehicleNo);
            contentValues.put("entry_time", time);
            long result = db.insert("parking", null, contentValues);
            return result != -1;
        }

        public Cursor getVehicles() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from parking", null);
        }
    }
}