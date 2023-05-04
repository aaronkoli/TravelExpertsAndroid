package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CustomerForm extends AppCompatActivity {

    EditText etCustomerId, etCustFirstName, etCustLastName,
            etCustAddress, etCustCity, etCustProv, etCustPostal, etCustCountry,
            etCustHomePhone, etCustBusPhone, etCustEmail, etAgentId;
    Button btnSave, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        etCustomerId = findViewById(R.id.etCustomerId);
        etCustFirstName = findViewById(R.id.etCustFirstName);
        etCustLastName = findViewById(R.id.etCustLastName);
        etCustAddress = findViewById(R.id.etCustAddress);
        etCustCity = findViewById(R.id.etCustCity);
        etCustProv = findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustCountry = findViewById(R.id.etCustCountry);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone = findViewById(R.id.etCustBusPhone);
        etCustEmail = findViewById(R.id.etCustEmail);
        etAgentId = findViewById(R.id.etAgentId);

        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
    }
}