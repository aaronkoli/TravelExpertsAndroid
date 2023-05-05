package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllCustomersView extends AppCompatActivity {
    ListView lvCustomers;
    ArrayAdapter<Customer> adapter;
    Customer selection;
    String apiUrl = "http://localhost:8080/Workshop7MicahREST_war_exploded/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers_view);

        lvCustomers = findViewById(R.id.lvCustomers);
    }
}