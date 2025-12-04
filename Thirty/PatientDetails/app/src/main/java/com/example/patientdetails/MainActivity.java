package com.example.patientdetails;

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

    EditText etName, etAge, etDiagnosis;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etDiagnosis = findViewById(R.id.etDiagnosis);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Patient Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                String diagnosis = etDiagnosis.getText().toString();

                boolean inserted = dbHelper.insertPatient(name, age, diagnosis);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Patient Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etAge.setText("");
                    etDiagnosis.setText("");
                }
            }
        });

        // View Patients Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getPatients();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No records found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Age: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Diagnosis: ").append(cursor.getString(3)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "HospitalDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, age, diagnosis
            db.execSQL("create table patients(id integer primary key autoincrement, name text, age text, diagnosis text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists patients");
            onCreate(db);
        }

        public boolean insertPatient(String name, String age, String diagnosis) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("age", age);
            contentValues.put("diagnosis", diagnosis);
            long result = db.insert("patients", null, contentValues);
            return result != -1;
        }

        public Cursor getPatients() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from patients", null);
        }
    }
}