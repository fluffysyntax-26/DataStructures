package com.example.cakeorder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAddress, etDate, etTime;
    Spinner spinnerFlavour;
    Button btnOrder;

    String[] flavours = {"Chocolate (500)", "Vanilla (400)", "Red Velvet (600)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        spinnerFlavour = findViewById(R.id.spinnerFlavour);
        btnOrder = findViewById(R.id.btnOrder);

        // 2. Set Spinner Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                flavours
        );
        spinnerFlavour.setAdapter(adapter);

        // 3. Button Logic - Pass Data to Next Activity
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to go to BillingActivity
                Intent i = new Intent(MainActivity.this, BillingActivity.class);

                // Put data into the intent
                i.putExtra("name", etName.getText().toString());
                i.putExtra("address", etAddress.getText().toString());
                i.putExtra("date", etDate.getText().toString());
                i.putExtra("time", etTime.getText().toString());
                i.putExtra("flavour", spinnerFlavour.getSelectedItem().toString());

                // Start next activity
                startActivity(i);
            }
        });
    }
}