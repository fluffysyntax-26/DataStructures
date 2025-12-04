package com.example.cakeorder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BillingActivity extends AppCompatActivity {

    TextView tvBillDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        tvBillDetails = findViewById(R.id.tvBillDetails);

        // 1. Get the Intent that started this activity
        Intent i = getIntent();

        // 2. Retrieve data using the keys we set in MainActivity
        String name = i.getStringExtra("name");
        String address = i.getStringExtra("address");
        String date = i.getStringExtra("date");
        String time = i.getStringExtra("time");
        String flavour = i.getStringExtra("flavour");

        // 3. Simple Price Calculation Logic
        int price = 0;
        if(flavour.contains("Chocolate")) price = 500;
        else if(flavour.contains("Vanilla")) price = 400;
        else if(flavour.contains("Red Velvet")) price = 600;

        // 4. Display Final Bill
        String bill = "Customer: " + name + "\n" +
                "Address: " + address + "\n" +
                "Date: " + date + " | Time: " + time + "\n" +
                "----------------------------\n" +
                "Item: " + flavour + "\n" +
                "Total Amount: Rs. " + price;

        tvBillDetails.setText(bill);
    }
}