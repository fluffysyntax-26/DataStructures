package com.example.skilltrack;

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

    EditText etName, etSkill, etEmail;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etSkill = findViewById(R.id.etSkill);
        etEmail = findViewById(R.id.etEmail);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Participant Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String skill = etSkill.getText().toString();
                String email = etEmail.getText().toString();

                boolean inserted = dbHelper.insertParticipant(name, skill, email);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Participant Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etSkill.setText("");
                    etEmail.setText("");
                }
            }
        });

        // View Sorted Participants Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getParticipants();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No participants found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Skill: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Email: ").append(cursor.getString(3)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ParticipantDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, skill, email
            db.execSQL("create table participants(id integer primary key autoincrement, name text, skill text, email text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists participants");
            onCreate(db);
        }

        public boolean insertParticipant(String name, String skill, String email) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("skill", skill);
            contentValues.put("email", email);
            long result = db.insert("participants", null, contentValues);
            return result != -1;
        }

        public Cursor getParticipants() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Order by skill to sort alphabetically by track
            return db.rawQuery("select * from participants order by skill asc", null);
        }
    }
}