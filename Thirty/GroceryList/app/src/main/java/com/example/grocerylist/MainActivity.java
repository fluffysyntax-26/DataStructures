package com.example.grocerylist;

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

    EditText etItem, etQty;
    Button btnAdd, btnView;
    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> shoppingList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        etQty = findViewById(R.id.etQty);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
        shoppingList = new ArrayList<>();

        // Simple adapter to display text in the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoppingList);
        listView.setAdapter(adapter);

        // Add Item Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                String qty = etQty.getText().toString();

                boolean inserted = dbHelper.insertItem(item, qty);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                    etItem.setText("");
                    etQty.setText("");
                }
            }
        });

        // View List Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getAllItems();
                shoppingList.clear(); // Clear old list before adding new data

                if (cursor.getCount() == 0) {
                    shoppingList.add("No items in list");
                } else {
                    while (cursor.moveToNext()) {
                        String line = "Item: " + cursor.getString(1) + " - Qty: " + cursor.getString(2);
                        shoppingList.add(line);
                    }
                }
                adapter.notifyDataSetChanged(); // Refresh the ListView
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "GroceryDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, quantity
            db.execSQL("create table items(id integer primary key autoincrement, name text, quantity text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists items");
            onCreate(db);
        }

        public boolean insertItem(String name, String quantity) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("quantity", quantity);
            long result = db.insert("items", null, contentValues);
            return result != -1;
        }

        public Cursor getAllItems() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from items", null);
        }
    }
}