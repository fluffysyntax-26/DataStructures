package com.example.foodorders;

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

    EditText etOrderId, etFoodItem, etStatus;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOrderId = findViewById(R.id.etOrderId);
        etFoodItem = findViewById(R.id.etFoodItem);
        etStatus = findViewById(R.id.etStatus);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Order Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderId = etOrderId.getText().toString();
                String item = etFoodItem.getText().toString();
                String status = etStatus.getText().toString();

                boolean inserted = dbHelper.insertOrder(orderId, item, status);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Order Added", Toast.LENGTH_SHORT).show();
                    etOrderId.setText("");
                    etFoodItem.setText("");
                    etStatus.setText("");
                }
            }
        });

        // View Orders Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getOrders();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No orders found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Order ID: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Item: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Status: ").append(cursor.getString(3)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "OrderDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id (primary key), order_id_input, food_item, status
            // Note: We keep a separate auto-inc ID for sorting, and store user's Order ID as text/int
            db.execSQL("create table orders(id integer primary key autoincrement, order_id_input text, food_item text, status text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists orders");
            onCreate(db);
        }

        public boolean insertOrder(String orderId, String item, String status) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("order_id_input", orderId);
            contentValues.put("food_item", item);
            contentValues.put("status", status);
            long result = db.insert("orders", null, contentValues);
            return result != -1;
        }

        public Cursor getOrders() {
            SQLiteDatabase db = this.getReadableDatabase();
            // Order by id DESC to show latest added first
            return db.rawQuery("select * from orders order by id desc", null);
        }
    }
}