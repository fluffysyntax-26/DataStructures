package com.example.studentdetails;

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

    EditText etName, etReg, etDept;
    Button btnInsert, btnView;
    ListView listView;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etReg = findViewById(R.id.etReg);
        etDept = findViewById(R.id.etDept);
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        DB = new DBHelper(this);

        // Insert Button Logic
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String reg = etReg.getText().toString();
                String dept = etDept.getText().toString();

                if(name.isEmpty() || reg.isEmpty() || dept.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean checkInsert = DB.insertData(name, reg, dept);
                if (checkInsert)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
            }
        });

        // View Button Logic
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<String> list = new ArrayList<>();
                while (res.moveToNext()) {
                    // Combine data into one string per row
                    String buffer = "Name: " + res.getString(0) + "\n" +
                            "Reg: " + res.getString(1) + "\n" +
                            "Dept: " + res.getString(2);
                    list.add(buffer);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
        });
    }
}