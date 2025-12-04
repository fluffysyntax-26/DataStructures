package com.example.electricitybill;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etUnits;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etUnits = findViewById(R.id.etUnits);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // 2. Calculation Logic
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etUnits.getText().toString();

                if (!input.isEmpty()) {
                    int units = Integer.parseInt(input);
                    double billAmount = 0;

                    // Slab Logic
                    if (units <= 100) {
                        // First 100 units charge
                        billAmount = units * 2.0;
                    }
                    else if (units <= 200) {
                        // First 100 @ 2.0 + Remaining @ 3.0
                        billAmount = (100 * 2.0) + ((units - 100) * 3.0);
                    }
                    else {
                        // First 100 @ 2.0 + Next 100 @ 3.0 + Remaining @ 5.0
                        billAmount = (100 * 2.0) + (100 * 3.0) + ((units - 200) * 5.0);
                    }

                    tvResult.setText("Total Bill: ₹ " + billAmount);
                }
            }
        });
    }
}