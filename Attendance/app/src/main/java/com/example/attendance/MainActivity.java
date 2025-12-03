package com.example.attendance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTotal, etAttended;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTotal = findViewById(R.id.etTotal);
        etAttended = findViewById(R.id.etAttended);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStr = etTotal.getText().toString();
                String attendedStr = etAttended.getText().toString();

                if (totalStr.isEmpty() || attendedStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                }

                double total = Double.parseDouble(totalStr);
                double attended = Double.parseDouble(attendedStr);

                if (attended > total) {
                    tvResult.setText("Invalid Input: Attended > Total");
                    return;
                }

                if (total == 0) {
                    tvResult.setText("Total classes cannot be 0");
                    return;
                }

                // Calculate Percentage
                double percentage = (attended / total) * 100;

                String status;
                String colorHex; // Only used conceptually, we set color code directly below

                if (percentage >= 75) {
                    status = "Safe / Eligible";
                    tvResult.setTextColor(0xFF00AA00); // Green
                } else if (percentage >= 65) {
                    status = "Warning / Condonation";
                    tvResult.setTextColor(0xFFFFA500); // Orange
                } else {
                    status = "Debarred / Shortage";
                    tvResult.setTextColor(0xFFFF0000); // Red
                }

                String output = String.format("Percentage: %.2f%%\nStatus: %s", percentage, status);
                tvResult.setText(output);
            }
        });
    }
}