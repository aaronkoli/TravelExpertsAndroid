package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class AllCustomersView extends AppCompatActivity {
    ListView lvCustomers;
    ArrayAdapter<Customer> adapter;
    Customer selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers_view);

        lvCustomers = findViewById(R.id.lvCustomers);
        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                intent.putExtra("customerId", adapter.getItem(position));
                startActivity(intent);
            }
        });

        loadData();
    }

    @Override
    protected void onStart()
    {
        lvCustomers.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        super.onStart();
        loadData();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadData();
    }

    private ArrayList<Customer> getAllCustomers()
    {
        ArrayList<Customer> customers = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://localhost:8088/Workshop7MicahREST_war_exploded/api/customers/getallcustomers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try
                    {
                        JSONObject resjo = new JSONObject(response);
                        Iterator<?> keys = resjo.keys();
                        while(keys.hasNext()) {
                            String key = (String) keys.next();
                            JSONObject jo = new JSONObject(((JSONObject) resjo.get(key)).toString());
                            int customerId = jo.getInt("customerId");
                            String custFirstName = jo.getString("custFirstName");
                            String custLastName = jo.getString("custLastName");
                            String custAddress = jo.getString("custAddress");
                            String custCity = jo.getString("custCity");
                            String custProv = jo.getString("custProv");
                            String custPostal = jo.getString("custPostal");
                            String custCountry = jo.getString("custCountry");
                            String custHomePhone = jo.getString("custHomePhone");
                            String custBusPhone = jo.getString("custBusPhone");
                            String custEmail = jo.getString("custEmail");
                            int agentId = jo.getInt("agentId");
                            customers.add(new Customer(customerId,
                                    custFirstName,
                                    custLastName,
                                    custAddress,
                                    custCity,
                                    custProv,
                                    custPostal,
                                    custCountry,
                                    custHomePhone,
                                    custBusPhone,
                                    custEmail,
                                    agentId));
                        }
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //do error stuff
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return customers;
    }

    private void loadData(){
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice,
                getAllCustomers());
        lvCustomers.setAdapter(adapter);
    }
}