package com.example.currencyconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    Spinner spinnerCurrency;
    Button btnConvert;
    TextView tvResult;

    // Currency Options
    String[] currencies = {"USD", "EUR", "AED"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        spinnerCurrency.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etAmount.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                double inr = Double.parseDouble(input);
                double result = 0;
                String selectedCurrency = spinnerCurrency.getSelectedItem().toString();

                // Simple Logic for conversion rates (Approximate values)
                if (selectedCurrency.equals("USD")) {
                    result = inr / 83.0; // 1 USD = 83 INR
                } else if (selectedCurrency.equals("EUR")) {
                    result = inr / 90.0; // 1 EUR = 90 INR
                } else if (selectedCurrency.equals("AED")) {
                    result = inr / 22.5; // 1 AED = 22.5 INR
                }

                tvResult.setText(String.format("%.2f %s", result, selectedCurrency));
            }
        });
    }
}