package com.example.simpleinterestcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etPrincipal, etRate, etTime;
    Button btnSimple, btnCompound;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etPrincipal = findViewById(R.id.etPrincipal);
        etRate = findViewById(R.id.etRate);
        etTime = findViewById(R.id.etTime);
        btnSimple = findViewById(R.id.btnSimple);
        btnCompound = findViewById(R.id.btnCompound);
        tvResult = findViewById(R.id.tvResult);

        // 2. Logic for Simple Interest
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()) {
                    double p = Double.parseDouble(etPrincipal.getText().toString());
                    double r = Double.parseDouble(etRate.getText().toString());
                    double t = Double.parseDouble(etTime.getText().toString());

                    // Formula: SI = (P * R * T) / 100
                    double si = (p * r * t) / 100;
                    tvResult.setText("Simple Interest: " + si);
                }
            }
        });

        // 3. Logic for Compound Interest
        btnCompound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()) {
                    double p = Double.parseDouble(etPrincipal.getText().toString());
                    double r = Double.parseDouble(etRate.getText().toString());
                    double t = Double.parseDouble(etTime.getText().toString());

                    // Formula: CI = P * (1 + R/100)^T - P
                    double amount = p * Math.pow((1 + r / 100), t);
                    double ci = amount - p;

                    // String.format("%.2f", ci) limits decimals to 2 places
                    tvResult.setText(String.format("Compound Interest: %.2f", ci));
                }
            }
        });
    }

    // Helper method to ensure fields aren't empty (prevents crashing)
    private boolean checkInputs() {
        if(etPrincipal.getText().toString().isEmpty() ||
                etRate.getText().toString().isEmpty() ||
                etTime.getText().toString().isEmpty()) {
            tvResult.setText("Please enter all details");
            return false;
        }
        return true;
    }
}