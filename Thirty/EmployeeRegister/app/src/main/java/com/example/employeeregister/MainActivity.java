package com.example.employeeregister;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etEmpId, etName, etSalary;
    Button btnSave, btnView;
    ListView listView;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmpId = findViewById(R.id.etEmpId);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        DB = new DBHelper(this);

        // Save Button Logic
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etEmpId.getText().toString();
                String name = etName.getText().toString();
                String salary = etSalary.getText().toString();

                if (id.isEmpty() || name.isEmpty() || salary.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkInsert = DB.insertEmployee(id, name, salary);
                    if (checkInsert) {
                        Toast.makeText(MainActivity.this, "Employee Saved", Toast.LENGTH_SHORT).show();
                        // Clear inputs
                        etEmpId.setText("");
                        etName.setText("");
                        etSalary.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Error: ID might already exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // View Button Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getAllEmployees();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Register is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<String> list = new ArrayList<>();
                while (res.moveToNext()) {
                    // Format: ID - Name - Rs. Salary
                    String record = "ID: " + res.getString(0) + "\n" +
                            "Name: " + res.getString(1) + "\n" +
                            "Salary: Rs. " + res.getString(2);
                    list.add(record);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
        });
    }
}