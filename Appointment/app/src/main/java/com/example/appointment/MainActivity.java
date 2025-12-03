package com.example.appointment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    TimePicker timePicker;
    Button btnConfirm;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvResult = findViewById(R.id.tvResult);

        // Optional: Set TimePicker to 24h view if preferred
        timePicker.setIs24HourView(true);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Date
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month starts from 0
                int year = datePicker.getYear();

                // Get Time
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // Format Message
                String date = day + "/" + month + "/" + year;
                String time = String.format("%02d:%02d", hour, minute);

                String message = "Appointment Confirmed!\nDate: " + date + "\nTime: " + time;

                tvResult.setText(message);
            }
        });
    }
}