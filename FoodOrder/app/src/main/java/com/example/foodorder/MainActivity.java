package com.example.foodorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgCuisine;
    EditText etAddress;
    Button btnOrder;
    TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgCuisine = findViewById(R.id.rgCuisine);
        etAddress = findViewById(R.id.etAddress);
        btnOrder = findViewById(R.id.btnOrder);
        tvSummary = findViewById(R.id.tvSummary);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Selected Radio Button ID
                int selectedId = rgCuisine.getCheckedRadioButtonId();

                String address = etAddress.getText().toString();

                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Please select food", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Find the RadioButton View using the ID
                RadioButton selectedBtn = findViewById(selectedId);
                String foodItem = selectedBtn.getText().toString();

                String summary = "Order Successful!\n\n" +
                        "Item: " + foodItem + "\n" +
                        "Address: " + address;

                tvSummary.setText(summary);
            }
        });
    }
}