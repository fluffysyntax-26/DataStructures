package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalc;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link views
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalc = findViewById(R.id.btnCalc);
        tvResult = findViewById(R.id.tvResult);

        // 2. Logic
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get inputs
                String wStr = etWeight.getText().toString();
                String hStr = etHeight.getText().toString();

                if (!wStr.isEmpty() && !hStr.isEmpty()) {
                    double weight = Double.parseDouble(wStr);
                    double height = Double.parseDouble(hStr);

                    // Calculation: BMI = kg / m*m
                    double bmi = weight / (height * height);

                    // Display result
                    tvResult.setText("Your BMI is: " + bmi);
                }
            }
        });
    }
}