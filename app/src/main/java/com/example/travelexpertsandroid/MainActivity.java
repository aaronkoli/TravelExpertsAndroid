package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lvCustomers;
    Button btnCreate;
    Button btnViewAll;

    ArrayAdapter<Customer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCustomers = findViewById(R.id.lvCustomers);
        btnCreate = findViewById(R.id.btnCreate);
        btnViewAll = findViewById(R.id.btnViewAll);

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                intent.putExtra("customerId", adapter.getItem(position));
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                startActivity(intent);
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), AllCustomersView.class);
                startActivity(intent);
            }
        });
    }

//    private void loadData() {
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSource.getAllCustomers());
//        lvCustomers.setAdapter(adapter);
//    }
}