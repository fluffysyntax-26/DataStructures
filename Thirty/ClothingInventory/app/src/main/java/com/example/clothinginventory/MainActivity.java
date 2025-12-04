package com.example.clothinginventory;

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

    EditText etItem, etSize, etQty;
    Button btnAdd, btnView;
    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> stockList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        etSize = findViewById(R.id.etSize);
        etQty = findViewById(R.id.etQty);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this);
        stockList = new ArrayList<>();

        // Connect the list data to the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stockList);
        listView.setAdapter(adapter);

        // Add Item Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                String size = etSize.getText().toString();
                String qty = etQty.getText().toString();

                boolean inserted = dbHelper.insertItem(item, size, qty);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                    etItem.setText("");
                    etSize.setText("");
                    etQty.setText("");
                }
            }
        });

        // View Low Stock Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getLowStockItems();
                stockList.clear(); // Clear list before showing new results

                if (cursor.getCount() == 0) {
                    stockList.add("No low stock items found.");
                } else {
                    while (cursor.moveToNext()) {
                        String line = "Item: " + cursor.getString(1) +
                                "\nSize: " + cursor.getString(2) +
                                "\nQty: " + cursor.getString(3);
                        stockList.add(line);
                    }
                }
                adapter.notifyDataSetChanged(); // Refresh the ListView
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ClothingDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, item, size, quantity (stored as integer)
            db.execSQL("create table clothes(id integer primary key autoincrement, item text, size text, quantity integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists clothes");
            onCreate(db);
        }

        public boolean insertItem(String item, String size, String qty) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("item", item);
            contentValues.put("size", size);
            contentValues.put("quantity", Integer.parseInt(qty)); // Store as Integer for comparison
            long result = db.insert("clothes", null, contentValues);
            return result != -1;
        }

        public Cursor getLowStockItems() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Query for items where quantity is 5 or less
            return db.rawQuery("select * from clothes where quantity <= 5", null);
        }
    }
}