package com.example.leapyear;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etYear;
    Button btnCheck;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etYear = findViewById(R.id.etYear);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etYear.getText().toString();

                if (!input.isEmpty()) {
                    int year = Integer.parseInt(input);
                    boolean isLeap = false;

                    // Leap Year Logic:
                    // Divisible by 4 AND (NOT divisible by 100 OR divisible by 400)
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        isLeap = true;
                    }

                    if (isLeap) {
                        tvResult.setText(year + " is a LEAP Year");
                        tvResult.setTextColor(0xFF00AA00); // Green color (optional)
                    } else {
                        tvResult.setText(year + " is NOT a Leap Year");
                        tvResult.setTextColor(0xFFFF0000); // Red color (optional)
                    }
                }
            }
        });
    }
}