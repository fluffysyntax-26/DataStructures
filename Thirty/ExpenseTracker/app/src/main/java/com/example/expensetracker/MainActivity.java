package com.example.expensetracker;

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

    EditText etAmount, etCategory, etDate;
    Button btnAdd, btnView;
    TextView tvResult;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etCategory = findViewById(R.id.etCategory);
        etDate = findViewById(R.id.etDate);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        dbHelper = new DBHelper(this);

        // Add Expense Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = etAmount.getText().toString();
                String category = etCategory.getText().toString();
                String date = etDate.getText().toString();

                boolean inserted = dbHelper.insertExpense(amount, category, date);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Expense Added", Toast.LENGTH_SHORT).show();
                    etAmount.setText("");
                    etCategory.setText("");
                    etDate.setText("");
                }
            }
        });

        // View Expenses Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getExpenses();
                StringBuilder buffer = new StringBuilder();

                if (cursor.getCount() == 0) {
                    tvResult.setText("No expenses found.");
                    return;
                }

                while (cursor.moveToNext()) {
                    buffer.append("Date: ").append(cursor.getString(3)).append("\n");
                    buffer.append("Category: ").append(cursor.getString(2)).append("\n");
                    buffer.append("Amount: ").append(cursor.getString(1)).append("\n\n");
                }
                tvResult.setText(buffer.toString());
            }
        });
    }

    // Inner Class for Database Helper
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "ExpenseDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Table with columns: id, amount, category, date
            db.execSQL("create table expenses(id integer primary key autoincrement, amount text, category text, date text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists expenses");
            onCreate(db);
        }

        public boolean insertExpense(String amount, String category, String date) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("amount", amount);
            contentValues.put("category", category);
            contentValues.put("date", date);
            long result = db.insert("expenses", null, contentValues);
            return result != -1;
        }

        public Cursor getExpenses() {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.rawQuery("select * from expenses", null);
        }
    }
}