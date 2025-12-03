package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etCategory, etAmount;
    Button btnAdd;
    TextView tvTotal, tvLog;

    // Global variable to hold the running total
    double currentTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etCategory = findViewById(R.id.etCategory);
        etAmount = findViewById(R.id.etAmount);
        btnAdd = findViewById(R.id.btnAdd);
        tvTotal = findViewById(R.id.tvTotal);
        tvLog = findViewById(R.id.tvLog);

        // 2. Button Logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat = etCategory.getText().toString();
                String amtStr = etAmount.getText().toString();

                // Basic validation to check if fields are not empty
                if (!cat.isEmpty() && !amtStr.isEmpty()) {

                    double amount = Double.parseDouble(amtStr);

                    // Update Global Total
                    currentTotal = currentTotal + amount;

                    // Update Total TextView
                    tvTotal.setText("Total: Rs. " + currentTotal);

                    // Append to the Log TextView (The receipt view)
                    // \n creates a new line
                    tvLog.append(cat + ": Rs. " + amount + "\n");

                    // Clear inputs for next entry
                    etCategory.setText("");
                    etAmount.setText("");
                }
            }
        });
    }
}