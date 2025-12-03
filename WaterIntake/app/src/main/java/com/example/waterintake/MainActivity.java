package com.example.waterintake;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWater;
    Button btnAdd;
    ProgressBar progressBar;
    TextView tvStatus;

    int totalIntake = 0;
    final int GOAL = 2000; // Fixed Goal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWater = findViewById(R.id.etWater);
        btnAdd = findViewById(R.id.btnAdd);
        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etWater.getText().toString();

                if (!input.isEmpty()) {
                    int currentAmount = Integer.parseInt(input);
                    totalIntake += currentAmount;

                    // Calculate Percentage
                    int percentage = (totalIntake * 100) / GOAL;

                    // Update UI
                    progressBar.setProgress(percentage);
                    tvStatus.setText(percentage + "% Completed");

                    // Clear input field for next entry
                    etWater.setText("");
                }
            }
        });
    }
}