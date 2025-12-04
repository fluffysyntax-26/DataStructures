package com.example.studentgradesaverage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etJava, etDS, etDBMS, etOS, etCN;
    Button btnCalc;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etJava = findViewById(R.id.etJava);
        etDS = findViewById(R.id.etDS);
        etDBMS = findViewById(R.id.etDBMS);
        etOS = findViewById(R.id.etOS);
        etCN = findViewById(R.id.etCN);
        btnCalc = findViewById(R.id.btnCalc);
        tvResult = findViewById(R.id.tvResult);

        // 2. Calculation Logic
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get inputs (Assuming user enters valid numbers)
                if(!etJava.getText().toString().isEmpty()) {

                    int m1 = Integer.parseInt(etJava.getText().toString());
                    int m2 = Integer.parseInt(etDS.getText().toString());
                    int m3 = Integer.parseInt(etDBMS.getText().toString());
                    int m4 = Integer.parseInt(etOS.getText().toString());
                    int m5 = Integer.parseInt(etCN.getText().toString());

                    // Calculate Average
                    int total = m1 + m2 + m3 + m4 + m5;
                    float avg = total / 5.0f;
                    String grade = "";

                    // Determine Grade
                    if (avg >= 90) grade = "O (Outstanding)";
                    else if (avg >= 80) grade = "A (Excellent)";
                    else if (avg >= 70) grade = "B (Good)";
                    else if (avg >= 60) grade = "C (Average)";
                    else grade = "Fail";

                    // Display
                    String resultText = "Total: " + total + "/500\n" +
                            "Average: " + avg + "%\n" +
                            "Grade: " + grade;

                    tvResult.setText(resultText);
                }
            }
        });
    }
}