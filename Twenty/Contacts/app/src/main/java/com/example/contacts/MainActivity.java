package com.example.contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTV;
    TextView tvDetails;

    // Data Source: Name -> Details
    HashMap<String, String> contactMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTV = findViewById(R.id.autoCompleteTV);
        tvDetails = findViewById(R.id.tvDetails);

        // 1. Prepare Data
        contactMap.put("Alice", "Mobile: 9876543210\nCity: New York");
        contactMap.put("Bob", "Mobile: 1234567890\nCity: London");
        contactMap.put("Charlie", "Mobile: 5556667777\nCity: Paris");
        contactMap.put("David", "Mobile: 9988776655\nCity: Tokyo");
        contactMap.put("Eve", "Mobile: 1122334455\nCity: Berlin");

        // Convert Keys (Names) to an Array for the Adapter
        String[] names = contactMap.keySet().toArray(new String[0]);

        // 2. Set Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, names);
        autoCompleteTV.setAdapter(adapter);

        // 3. Handle Selection
        autoCompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected name
                String selectedName = (String) parent.getItemAtPosition(position);

                // Fetch details from HashMap
                String details = contactMap.get(selectedName);

                // Display
                tvDetails.setText("Contact Details:\n\n" + details);
            }
        });
    }
}