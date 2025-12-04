package com.example.playerscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etTeam, etScore;
    Button btnAdd, btnView;
    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> scoreList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etTeam = findViewById(R.id.etTeam);
        etScore = findViewById(R.id.etScore);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
        scoreList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
        listView.setAdapter(adapter);

        // Add Player Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String team = etTeam.getText().toString();
                String score = etScore.getText().toString();

                boolean inserted = dbHelper.insertScore(name, team, score);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Score Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etTeam.setText("");
                    etScore.setText("");
                }
            }
        });

        // View Top 10 Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getTopScorers();
                scoreList.clear();

                if (cursor.getCount() == 0) {
                    scoreList.add("No scores recorded.");
                } else {
                    while (cursor.moveToNext()) {
                        String line = "Player: " + cursor.getString(1) +
                                "\nTeam: " + cursor.getString(2) +
                                "\nScore: " + cursor.getInt(3);
                        scoreList.add(line);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ScoreDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, team, score (as integer for sorting)
            db.execSQL("create table players(id integer primary key autoincrement, name text, team text, score integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists players");
            onCreate(db);
        }

        public boolean insertScore(String name, String team, String score) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("team", team);
            contentValues.put("score", Integer.parseInt(score)); // Store as int
            long result = db.insert("players", null, contentValues);
            return result != -1;
        }

        public Cursor getTopScorers() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Query for top 10 highest scorers
            return db.rawQuery("select * from players order by score desc limit 10", null);
        }
    }
}