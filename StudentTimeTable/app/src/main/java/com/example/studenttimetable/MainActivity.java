package com.example.studenttimetable;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spDay, spSubject;
    Button btnAdd;
    TextView tvSchedule;

    // Data for spinners
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] subjects = {"Android Dev", "Cloud Computing", "Python", "Maths", "Lab"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spDay = findViewById(R.id.spDay);
        spSubject = findViewById(R.id.spSubject);
        btnAdd = findViewById(R.id.btnAdd);
        tvSchedule = findViewById(R.id.tvSchedule);

        // Set Adapter for Day Spinner
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        spDay.setAdapter(dayAdapter);

        // Set Adapter for Subject Spinner
        ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spSubject.setAdapter(subAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = spDay.getSelectedItem().toString();
                String subject = spSubject.getSelectedItem().toString();

                // Append to the text view
                String entry = day + " : " + subject + "\n";
                tvSchedule.append(entry);
            }
        });
    }
}