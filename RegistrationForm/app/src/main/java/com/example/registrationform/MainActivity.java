package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declare Views
    EditText etName, etFather, etMother, etDob, etPhone;
    AutoCompleteTextView actvCity;
    RadioGroup rgGender;
    Button btnRegister;
    TextView tvResult;

    // Data for AutoComplete suggestions
    String[] cities = {"Delhi", "Mumbai", "Bangalore", "Chennai", "Kolkata", "Hyderabad"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Link Views
        etName = findViewById(R.id.etName);
        etFather = findViewById(R.id.etFather);
        etMother = findViewById(R.id.etMother);
        etDob = findViewById(R.id.etDob);
        etPhone = findViewById(R.id.etPhone);
        actvCity = findViewById(R.id.actvCity);
        rgGender = findViewById(R.id.rgGender);
        btnRegister = findViewById(R.id.btnRegister);
        tvResult = findViewById(R.id.tvResult);

        // 2. Setup AutoCompleteTextView Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.select_dialog_item, // Standard built-in layout
                cities
        );
        actvCity.setAdapter(adapter);

        // 3. Button Logic
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from EditTexts
                String name = etName.getText().toString();
                String father = etFather.getText().toString();
                String mother = etMother.getText().toString();
                String dob = etDob.getText().toString();
                String phone = etPhone.getText().toString();
                String city = actvCity.getText().toString();

                // Get Gender from RadioGroup
                String gender = "Not Selected";
                int selectedId = rgGender.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRb = findViewById(selectedId);
                    gender = selectedRb.getText().toString();
                }

                // Construct Result String
                String result = "Registration Details:\n" +
                        "Name: " + name + "\n" +
                        "Father: " + father + "\n" +
                        "Mother: " + mother + "\n" +
                        "DOB: " + dob + "\n" +
                        "Phone: " + phone + "\n" +
                        "City: " + city + "\n" +
                        "Gender: " + gender;

                // Display Result
                tvResult.setText(result);
            }
        });
    }
}