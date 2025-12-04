package com.example.complainregister;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone;
    Spinner spIssue;
    Button btnSubmit;
    TextView tvResult;

    // Issue Types
    String[] issues = {"Network Failure", "Billing Error", "Product Damage", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        spIssue = findViewById(R.id.spIssue);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, issues);
        spIssue.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();

                if(name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Generate Random ID (1000 to 9999)
                Random random = new Random();
                int complaintId = random.nextInt(9000) + 1000;

                String result = "Complaint Registered Successfully!\n" +
                        "Name: " + name + "\n" +
                        "Issue: " + spIssue.getSelectedItem().toString() + "\n" +
                        "Complaint ID: CMP-" + complaintId;

                tvResult.setText(result);
            }
        });
    }
}