package com.example.customerpurchasehistory;

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

    EditText etName, etProduct, etAmount;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etProduct = findViewById(R.id.etProduct);
        etAmount = findViewById(R.id.etAmount);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Purchase Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String product = etProduct.getText().toString();
                String amount = etAmount.getText().toString();

                boolean inserted = dbHelper.insertPurchase(name, product, amount);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Purchase Added", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etProduct.setText("");
                    etAmount.setText("");
                }
            }
        });

        // View History Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getAllPurchases();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No purchases found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("ID: ").append(cursor.getString(0)).append("\n");
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Product: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Amount: ").append(cursor.getString(3)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ShopDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, name, product, amount
            db.execSQL("create table purchases(id integer primary key autoincrement, name text, product text, amount text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists purchases");
            onCreate(db);
        }

        public boolean insertPurchase(String name, String product, String amount) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("product", product);
            contentValues.put("amount", amount);
            long result = db.insert("purchases", null, contentValues);
            return result != -1;
        }

        public Cursor getAllPurchases() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from purchases", null);
        }
    }
}