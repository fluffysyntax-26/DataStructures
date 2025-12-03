package com.example.temperatureconvertor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etTemp;
    Button btnToF, btnToC;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etTemp = findViewById(R.id.etTemp);
        btnToF = findViewById(R.id.btnToF);
        btnToC = findViewById(R.id.btnToC);
        tvResult = findViewById(R.id.tvResult);

        // 2. Convert to Fahrenheit
        btnToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etTemp.getText().toString().isEmpty()) {
                    double c = Double.parseDouble(etTemp.getText().toString());
                    // Formula: (C * 1.8) + 32
                    double f = (c * 1.8) + 32;
                    tvResult.setText(String.format("%.2f °F", f));
                }
            }
        });

        // 3. Convert to Celsius
        btnToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etTemp.getText().toString().isEmpty()) {
                    double f = Double.parseDouble(etTemp.getText().toString());
                    // Formula: (F - 32) / 1.8
                    double c = (f - 32) / 1.8;
                    tvResult.setText(String.format("%.2f °C", c));
                }
            }
        });
    }
}