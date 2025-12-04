package com.example.movieticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spMovie, spDate, spTime, spSeats;
    Button btnBook;

    // Data for spinners
    String[] movies = {"Avengers: Endgame", "Spider-Man", "Batman", "Inception"};
    String[] dates = {"10 Dec", "11 Dec", "12 Dec", "13 Dec"};
    String[] times = {"10:00 AM", "01:00 PM", "04:00 PM", "09:00 PM"};
    String[] seats = {"1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spMovie = findViewById(R.id.spinnerMovie);
        spDate = findViewById(R.id.spinnerDate);
        spTime = findViewById(R.id.spinnerTime);
        spSeats = findViewById(R.id.spinnerSeats);
        btnBook = findViewById(R.id.btnBook);

        // Setting Adapters
        setSpinnerAdapter(spMovie, movies);
        setSpinnerAdapter(spDate, dates);
        setSpinnerAdapter(spTime, times);
        setSpinnerAdapter(spSeats, seats);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                intent.putExtra("movie", spMovie.getSelectedItem().toString());
                intent.putExtra("date", spDate.getSelectedItem().toString());
                intent.putExtra("time", spTime.getSelectedItem().toString());
                intent.putExtra("seats", spSeats.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    // Helper method to set adapter simply
    private void setSpinnerAdapter(Spinner spinner, String[] data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adapter);
    }
}