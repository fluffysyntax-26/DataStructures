package com.example.emi;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView tvEmiResult;
    ListView listViewBreakup;
    ArrayList<String> breakupList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvEmiResult = findViewById(R.id.tvEmiResult);
        listViewBreakup = findViewById(R.id.listViewBreakup);

        // Get Data
        double P = getIntent().getDoubleExtra("principal", 0);
        double R = getIntent().getDoubleExtra("rate", 0);
        int N = getIntent().getIntExtra("months", 0);

        // Calculate Monthly Rate
        double r = R / 12 / 100;

        // EMI Formula: [P x r x (1+r)^n] / [(1+r)^n-1]
        double emi = (P * r * Math.pow(1 + r, N)) / (Math.pow(1 + r, N) - 1);

        tvEmiResult.setText("Monthly EMI: " + String.format("%.2f", emi));

        // Calculate Breakdown (Amortization)
        double outstandingPrincipal = P;

        for (int i = 1; i <= N; i++) {
            double interest = outstandingPrincipal * r;
            double principalComponent = emi - interest;
            outstandingPrincipal -= principalComponent;

            // Handle last month precision issues
            if (outstandingPrincipal < 0) outstandingPrincipal = 0;

            String entry = "Month " + i +
                    "\nPrincipal: " + String.format("%.2f", principalComponent) +
                    " | Interest: " + String.format("%.2f", interest) +
                    "\nBalance: " + String.format("%.2f", outstandingPrincipal);

            breakupList.add(entry);
        }

        // Show in List
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, breakupList);
        listViewBreakup.setAdapter(adapter);
    }
}