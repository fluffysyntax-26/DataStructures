package com.example.agecategory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Selected Date (DOB)
                int birthDay = datePicker.getDayOfMonth();
                int birthMonth = datePicker.getMonth();
                int birthYear = datePicker.getYear();

                // Get Current Date
                Calendar today = Calendar.getInstance();
                int currentDay = today.get(Calendar.DAY_OF_MONTH);
                int currentMonth = today.get(Calendar.MONTH);
                int currentYear = today.get(Calendar.YEAR);

                // Calculate Age
                int age = currentYear - birthYear;

                // Adjust if birthday hasn't happened yet this year
                if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                    age--;
                }

                // Determine Category
                String category;
                if (age < 18) {
                    category = "Minor";
                } else if (age >= 60) {
                    category = "Senior Citizen";
                } else {
                    category = "Adult";
                }

                // Show Result
                tvResult.setText("Age: " + age + "\nCategory: " + category);
            }
        });
    }
}