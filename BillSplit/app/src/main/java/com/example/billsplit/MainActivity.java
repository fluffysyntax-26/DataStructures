package com.example.billsplit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTotalBill, etPeople;
    Button btnSplit;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTotalBill = findViewById(R.id.etTotalBill);
        etPeople = findViewById(R.id.etPeople);
        btnSplit = findViewById(R.id.btnSplit);
        tvResult = findViewById(R.id.tvResult);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String billStr = etTotalBill.getText().toString();
                String peopleStr = etPeople.getText().toString();

                // Basic validation to prevent crashes
                if (billStr.isEmpty() || peopleStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                }

                double totalBill = Double.parseDouble(billStr);
                int people = Integer.parseInt(peopleStr);

                if (people > 0) {
                    double share = totalBill / people;
                    tvResult.setText("Per Person: " + String.format("%.2f", share));
                } else {
                    Toast.makeText(MainActivity.this, "People cannot be 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}