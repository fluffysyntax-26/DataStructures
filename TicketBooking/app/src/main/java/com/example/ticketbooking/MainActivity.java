package com.example.ticketbooking;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spSource, spDest;
    EditText etPersons;
    Button btnBook;
    TextView tvResult;

    String[] cities = {"New York", "London", "Paris", "Tokyo"};
    int TICKET_PRICE = 500; // Fixed price for simplicity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spSource = findViewById(R.id.spSource);
        spDest = findViewById(R.id.spDest);
        etPersons = findViewById(R.id.etPersons);
        btnBook = findViewById(R.id.btnBook);
        tvResult = findViewById(R.id.tvResult);

        // Set Adapter for Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        spSource.setAdapter(adapter);
        spDest.setAdapter(adapter);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source = spSource.getSelectedItem().toString();
                String dest = spDest.getSelectedItem().toString();
                String personsStr = etPersons.getText().toString();

                if (personsStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter number of persons", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Logic: Check if Source and Destination are same
                if (source.equals(dest)) {
                    tvResult.setText("Source and Destination cannot be same!");
                    return;
                }

                int persons = Integer.parseInt(personsStr);
                int totalFare = persons * TICKET_PRICE;

                String result = "Ticket Details:\n" +
                        source + " -> " + dest + "\n" +
                        "Passengers: " + persons + "\n" +
                        "Total Fare: Rs. " + totalFare;

                tvResult.setText(result);
            }
        });
    }
}