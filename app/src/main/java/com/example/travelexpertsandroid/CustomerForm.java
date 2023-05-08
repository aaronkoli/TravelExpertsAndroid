package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CustomerForm extends AppCompatActivity {

    EditText etCustomerId, etCustFirstName, etCustLastName,
            etCustAddress, etCustCity, etCustProv, etCustPostal, etCustCountry,
            etCustHomePhone, etCustBusPhone, etCustEmail, etAgentId;
    Button btnSave, btnDelete, btnHome;
    String editAdd = "add";
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
        btnHome = findViewById(R.id.btnHome);
        btnDelete = findViewById(R.id.btnDelete);
        Intent intent = getIntent();



        if(intent.getBooleanExtra("hasData", false) == true) {

            Customer c = intent.getParcelableExtra("Customer", Customer.class);

            etCustomerId.setText(c.getCustomerId() + "");
            etCustFirstName.setText(c.getCustFirstName());
            etCustLastName.setText(c.getCustLastName());
            etCustAddress.setText(c.getCustAddress());
            etCustCity.setText(c.getCustCity());
            etCustProv.setText(c.getCustProv());
            etCustPostal.setText(c.getCustPostal());
            etCustCountry.setText(c.getCustCountry());
            etCustHomePhone.setText(c.getCustHomePhone());
            etCustBusPhone.setText(c.getCustBusPhone());
            etCustEmail.setText(c.getCustEmail());
            etAgentId.setText(c.getAgentId() + "");

            editAdd = "edit";
            btnSave.setText("Edit");
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(CustomerForm.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(CustomerForm.this)
                        .setTitle("Delete Customer")
                        .setMessage("Do you really want to delete " + etCustFirstName.getText().toString() + " " +
                                etCustLastName.getText().toString() + "? This action cannot be undone.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                deleteCustomer();
                                finish();
                            }})
                        .setNegativeButton("No", null).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(CustomerForm.this)
                        .setTitle("Save Customer Data")
                        .setMessage("Are you sure you want to " + editAdd + " this customer?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(editAdd == "add") {
                                    addCustomer();
                                    finish();
                                } else if (editAdd == "edit") {
                                    editCustomer();
                                }
                            }})
                        .setNegativeButton("No", null).show();
            }
        });
    }

    private void editCustomer() {
        String url = "http://192.168.1.198:8080/Workshop7MicahREST_war_exploded/api/customers/putcustomer";

            JSONObject customer = new JSONObject();
            try {
                customer.put("id", Integer.parseInt(etCustomerId.getText().toString()));
                customer.put("custFirstName", etCustFirstName.getText().toString());
                customer.put("custLastName", etCustLastName.getText().toString());
                customer.put("custAddress", etCustAddress.getText().toString());
                customer.put("custCity", etCustCity.getText().toString());
                customer.put("custProv", etCustProv.getText().toString());
                customer.put("custPostal", etCustPostal.getText().toString());
                customer.put("custCountry", etCustCountry.getText().toString());
                customer.put("custHomePhone", etCustHomePhone.getText().toString());
                customer.put("custBusPhone", etCustBusPhone.getText().toString());
                customer.put("custEmail", etCustEmail.getText().toString());
                customer.put("agentId", Integer.parseInt(etAgentId.getText().toString()));
            }
            catch (JSONException e) {
                Toast toast = new Toast(CustomerForm.this);
                toast.makeText(CustomerForm.this, "Error updating customer: " + e, Toast.LENGTH_LONG).show();
            }

        // Request a jsonObject response from the provided URL.
       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, customer, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               Toast toasty = new Toast(CustomerForm.this);
               toasty.makeText(CustomerForm.this, "Customer updated successfully!", Toast.LENGTH_LONG).show();

               Intent returnHome = new Intent(getApplicationContext(), MainActivity.class);
               startActivity(returnHome);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast toast = new Toast(CustomerForm.this);
               toast.makeText(CustomerForm.this, "Error updating customer: " + error.toString(), Toast.LENGTH_LONG).show();
           }
       });
       RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
       requestQueue.add(jsonObjectRequest);
    }


    private void addCustomer() {
        String url = "http://192.168.1.198:8080/Workshop7MicahREST_war_exploded/api/customers/postcustomer";


        JSONObject customer = new JSONObject();
        try {
            customer.put("id", 0);
            customer.put("custFirstName", etCustFirstName.getText().toString());
            customer.put("custLastName", etCustLastName.getText().toString());
            customer.put("custAddress", etCustAddress.getText().toString());
            customer.put("custCity", etCustCity.getText().toString());
            customer.put("custProv", etCustProv.getText().toString());
            customer.put("custPostal", etCustPostal.getText().toString());
            customer.put("custCountry", etCustCountry.getText().toString());
            customer.put("custHomePhone", etCustHomePhone.getText().toString());
            customer.put("custBusPhone", etCustBusPhone.getText().toString());
            customer.put("custEmail", etCustEmail.getText().toString());
            customer.put("agentId", Integer.parseInt(etAgentId.getText().toString()));
        }
        catch (JSONException e) {
            Toast toast = new Toast(CustomerForm.this);
            toast.makeText(CustomerForm.this, "Error adding new customer: " + e, Toast.LENGTH_LONG).show();
        }

        // Request a jsonObject response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, customer, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast toasty = new Toast(CustomerForm.this);
                toasty.makeText(CustomerForm.this, "Customer added successfully!", Toast.LENGTH_LONG).show();

                Intent returnHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnHome);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = new Toast(CustomerForm.this);
                toast.makeText(CustomerForm.this, "Error adding customer: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void deleteCustomer() {
        RequestQueue queue = Volley.newRequestQueue(CustomerForm.this);
        String url = "http://192.168.1.198:8080/Workshop7MicahREST_war_exploded/api/customers/deletecustomer/" + etCustomerId.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toasty = new Toast(CustomerForm.this);
                        toasty.makeText(CustomerForm.this, "Customer deleted successfully!", Toast.LENGTH_LONG).show();

                        Intent returnHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(returnHome);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast toast = new Toast(CustomerForm.this);
                toast.makeText(CustomerForm.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}