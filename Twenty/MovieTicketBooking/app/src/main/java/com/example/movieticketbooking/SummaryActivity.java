package com.example.movieticketbooking;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);

        // Get data from Intent
        String movie = getIntent().getStringExtra("movie");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String seats = getIntent().getStringExtra("seats");

        // Display
        String result = "Movie: " + movie + "\n\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Seats: " + seats;

        tvSummary.setText(result);
    }
}