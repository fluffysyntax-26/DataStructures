package com.example.eventdecoration;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spTheme;
    EditText etQuantity;
    DatePicker datePicker;
    Button btnOrder;
    TextView tvSummary;

    String[] themes = {"Birthday Party", "Wedding", "Corporate Event", "Festival"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTheme = findViewById(R.id.spTheme);
        etQuantity = findViewById(R.id.etQuantity);
        datePicker = findViewById(R.id.datePicker);
        btnOrder = findViewById(R.id.btnOrder);
        tvSummary = findViewById(R.id.tvSummary);

        // Set Adapter for Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, themes);
        spTheme.setAdapter(adapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qtyStr = etQuantity.getText().toString();

                if (qtyStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                String theme = spTheme.getSelectedItem().toString();

                // Get Date
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month is 0-indexed
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;

                // Display Summary
                String summary = "Order Confirmed!\n\n" +
                        "Theme: " + theme + "\n" +
                        "Quantity: " + qtyStr + "\n" +
                        "Date: " + date;

                tvSummary.setText(summary);
            }
        });
    }
}