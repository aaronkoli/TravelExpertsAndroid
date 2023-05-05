package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    String apiUrl = "http://localhost:8088/Workshop7MicahREST_war_exploded/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers_view);

        lvCustomers = findViewById(R.id.lvCustomers);



    }
    private ArrayList<Customer> getAllCustomers()
    {
        ArrayList<Customer> customers;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = apiUrl + "api/customers/getallcustomers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject resjo = new JSONObject(response);

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
}