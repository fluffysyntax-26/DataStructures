package com.example.courseselection;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRollNo, etSection;
    Spinner spinnerCourses;
    Button btnSubmit;
    TextView tvResult;

    String[] electives = {"Cloud Computing", "Data Science", "Cyber Security", "IoT", "Blockchain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etSection = findViewById(R.id.etSection);
        spinnerCourses = findViewById(R.id.spinnerCourses);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);

        // 2. Set Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                electives
        );
        spinnerCourses.setAdapter(adapter);

        // 3. Button Logic
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Inputs
                String name = etName.getText().toString();
                String roll = etRollNo.getText().toString();
                String sec = etSection.getText().toString();
                String course = spinnerCourses.getSelectedItem().toString();

                // Display Result
                String output = "Confirmed:\n" +
                        "Name: " + name + "\n" +
                        "Roll: " + roll + "\n" +
                        "Sec: " + sec + "\n" +
                        "Course: " + course;

                tvResult.setText(output);
            }
        });
    }
}