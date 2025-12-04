package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link variables to XML IDs
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        // 2. Set Listeners (Logic)

        // ADDITION
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(etNum1.getText().toString());
                double n2 = Double.parseDouble(etNum2.getText().toString());
                tvResult.setText("Result: " + (n1 + n2));
            }
        });

        // SUBTRACTION
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(etNum1.getText().toString());
                double n2 = Double.parseDouble(etNum2.getText().toString());
                tvResult.setText("Result: " + (n1 - n2));
            }
        });

        // MULTIPLICATION
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(etNum1.getText().toString());
                double n2 = Double.parseDouble(etNum2.getText().toString());
                tvResult.setText("Result: " + (n1 * n2));
            }
        });

        // DIVISION
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(etNum1.getText().toString());
                double n2 = Double.parseDouble(etNum2.getText().toString());
                tvResult.setText("Result: " + (n1 / n2));
            }
        });
    }
}