package com.example.aadhar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAadhar, etDob;
    Button btnCheck;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etName = findViewById(R.id.etName);
        etAadhar = findViewById(R.id.etAadhar);
        etDob = findViewById(R.id.etDob);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);

        // 2. Button Logic
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String aadhar = etAadhar.getText().toString();
                String dob = etDob.getText().toString(); // Format: DD/MM/YYYY

                // Basic Validation to prevent crash
                if(dob.contains("/")) {
                    // Extract Year: split "12/05/2000" -> gets "2000"
                    String[] parts = dob.split("/");
                    int birthYear = Integer.parseInt(parts[2]);

                    // Get Current Year dynamically
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int age = currentYear - birthYear;

                    if (age >= 18) {
                        tvResult.setText("Hello " + name + ",\nYou are ELIGIBLE to Vote.\nAge: " + age);
                        tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    } else {
                        tvResult.setText("Hello " + name + ",\nYou are NOT Eligible.\nAge: " + age);
                        tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }
                } else {
                    tvResult.setText("Please enter DOB as DD/MM/YYYY");
                }
            }
        });
    }
}