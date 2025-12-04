package com.example.unitconversion;

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

    EditText etValue;
    Spinner spConversion;
    Button btnConvert;
    TextView tvResult;

    // Conversion Options
    String[] options = {"Km to m", "m to Km", "Kg to g", "g to Kg", "cm to mm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValue = findViewById(R.id.etValue);
        spConversion = findViewById(R.id.spConversion);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spConversion.setAdapter(adapter);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etValue.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(input);
                double result = 0;
                String type = spConversion.getSelectedItem().toString();

                // Simple logic
                if (type.equals("Km to m")) {
                    result = value * 1000;
                } else if (type.equals("m to Km")) {
                    result = value / 1000;
                } else if (type.equals("Kg to g")) {
                    result = value * 1000;
                } else if (type.equals("g to Kg")) {
                    result = value / 1000;
                } else if (type.equals("cm to mm")) {
                    result = value * 10;
                }

                tvResult.setText("Result: " + result);
            }
        });
    }
}