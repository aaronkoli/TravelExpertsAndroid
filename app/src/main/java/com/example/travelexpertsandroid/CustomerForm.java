package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomerForm extends AppCompatActivity {

    EditText etCustomerId, etCustFirstName, etCustLastName,
            etCustAddress, etCustCity, etCustProv, etCustPostal, etCustCountry,
            etCustHomePhone, etCustBusPhone, etCustEmail, etAgentId;
    Button btnSave, btnDelete;
    RequestQueue queue;
    String mode;

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

        Intent intent = getIntent();
        queue = Volley.newRequestQueue(this);
        mode = intent.getStringExtra("mode");

        if(mode.equals("edit")){
            loadData(intent);
        }
    }

    private void loadData(Intent intent) {
        Customer customer = (Customer) intent.getSerializableExtra("customer");
        etCustomerId.setText(customer.getCustomerId());
        etCustFirstName.setText(customer.getCustFirstName());
        etCustLastName.setText(customer.getCustLastName());
        etCustAddress.setText(customer.getCustAddress());
        etCustCity.setText(customer.getCustCity());
        etCustProv.setText(customer.getCustProv());
        etCustPostal.setText(customer.getCustPostal());
        etCustCountry.setText(customer.getCustCountry());
        etCustHomePhone.setText(customer.getCustHomePhone());
        etCustBusPhone.setText(customer.getCustBusPhone());
        etCustEmail.setText(customer.getCustEmail());
        etAgentId.setText(customer.getAgentId());
    }
}