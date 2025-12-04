package com.example.emi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etPrincipal, etRate, etTenure;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrincipal = findViewById(R.id.etPrincipal);
        etRate = findViewById(R.id.etRate);
        etTenure = findViewById(R.id.etTenure);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get inputs
                double principal = Double.parseDouble(etPrincipal.getText().toString());
                double rate = Double.parseDouble(etRate.getText().toString());
                int months = Integer.parseInt(etTenure.getText().toString());

                // Pass to next activity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("principal", principal);
                intent.putExtra("rate", rate);
                intent.putExtra("months", months);
                startActivity(intent);
            }
        });
    }
}