package com.example.groceryorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox cbMilk, cbSugar, cbBread;
    Button btnBill;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML to Java
        cbMilk = findViewById(R.id.cbMilk);
        cbSugar = findViewById(R.id.cbSugar);
        cbBread = findViewById(R.id.cbBread);
        btnBill = findViewById(R.id.btnBill);
        tvResult = findViewById(R.id.tvResult);

        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = 0;
                StringBuilder items = new StringBuilder("Selected Items:\n");

                // Check which boxes are selected
                if (cbMilk.isChecked()) {
                    total += 50;
                    items.append("Milk: 50\n");
                }
                if (cbSugar.isChecked()) {
                    total += 40;
                    items.append("Sugar: 40\n");
                }
                if (cbBread.isChecked()) {
                    total += 30;
                    items.append("Bread: 30\n");
                }

                // Calculate GST (18%)
                double gst = total * 0.18;
                double grandTotal = total + gst;

                // Display Result
                String result = items.toString() +
                        "\nTotal: Rs. " + total +
                        "\nGST (18%): Rs. " + String.format("%.2f", gst) +
                        "\n-----------------" +
                        "\nGrand Total: Rs. " + String.format("%.2f", grandTotal);

                tvResult.setText(result);
            }
        });
    }
}