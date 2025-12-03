package com.example.fuelprojector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etDistance, etMileage;
    RadioGroup rgFuel;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDistance = findViewById(R.id.etDistance);
        etMileage = findViewById(R.id.etMileage);
        rgFuel = findViewById(R.id.rgFuel);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String distStr = etDistance.getText().toString();
                String milStr = etMileage.getText().toString();

                if (distStr.isEmpty() || milStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
                    return;
                }

                double distance = Double.parseDouble(distStr);
                double mileage = Double.parseDouble(milStr);
                double price = 0;

                // Check which RadioButton is selected
                int selectedId = rgFuel.getCheckedRadioButtonId();

                if (selectedId == R.id.rbPetrol) {
                    price = 100; // Hardcoded Price for Petrol
                } else if (selectedId == R.id.rbDiesel) {
                    price = 90;  // Hardcoded Price for Diesel
                } else {
                    Toast.makeText(MainActivity.this, "Select Fuel Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Formula: (Distance / Mileage) * Price per Liter
                double cost = (distance / mileage) * price;

                tvResult.setText("Estimated Cost: Rs. " + String.format("%.2f", cost));
            }
        });
    }
}