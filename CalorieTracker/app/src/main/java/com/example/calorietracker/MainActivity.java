package com.example.calorietracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etDuration, etCalories;
    Button btnAdd;
    TextView tvTotal, tvLog;

    int totalCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDuration = findViewById(R.id.etDuration);
        etCalories = findViewById(R.id.etCalories);
        btnAdd = findViewById(R.id.btnAdd);
        tvTotal = findViewById(R.id.tvTotal);
        tvLog = findViewById(R.id.tvLog);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String duration = etDuration.getText().toString();
                String caloriesStr = etCalories.getText().toString();

                if (!name.isEmpty() && !caloriesStr.isEmpty()) {
                    // Update Total
                    int currentCals = Integer.parseInt(caloriesStr);
                    totalCalories += currentCals;
                    tvTotal.setText("Total Calories: " + totalCalories);

                    // Update Log
                    String entry = name + " - " + duration + " mins - " + currentCals + " cal\n";
                    tvLog.append(entry);

                    // Clear inputs
                    etName.setText("");
                    etDuration.setText("");
                    etCalories.setText("");
                }
            }
        });
    }
}