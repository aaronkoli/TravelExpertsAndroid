package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllCustomersView extends AppCompatActivity {
    ListView lvCustomers;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers_view);

        tvError = findViewById(R.id.tvError);
        lvCustomers = findViewById(R.id.lvCustomers);

        ArrayList<Customer> list = new ArrayList<>();
        ArrayAdapter<Customer> adapter = new ArrayAdapter<>(
                AllCustomersView.this, android.R.layout.simple_list_item_1 ,list );


        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(AllCustomersView.this);
        String url = "http://192.168.1.198:8080/Workshop7MicahREST_war_exploded/api/customers/getallcustomers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = "{\"customers\":" + response + "}";
                        // Display the first 500 characters of the response string.
                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("customers");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                int customerId = (jo.getInt("id"));
                                String custFirstName = (jo.getString("custFirstName"));
                                String custLastName = (jo.getString("custLastName"));
                                String custAddress = (jo.getString("custAddress"));
                                String custCity = (jo.getString("custCity"));
                                String custProv = (jo.getString("custProv"));
                                String custPostal = (jo.getString("custPostal"));
                                String custCountry = (jo.getString("custCountry"));
                                String custHomePhone = (jo.getString("custHomePhone"));
                                String custBusPhone = (jo.getString("custBusPhone"));
                                String custEmail = (jo.getString("custEmail"));
                                int agentId = (jo.getInt("agentId"));

                                Customer c = new Customer(customerId, custFirstName, custLastName, custAddress,
                                        custCity, custProv, custPostal, custCountry, custHomePhone, custBusPhone,
                                        custEmail, agentId);

                                list.add(c);
                            }
                            lvCustomers.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = new Toast(AllCustomersView.this);
                toast.makeText(AllCustomersView.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Customer c = (Customer) lvCustomers.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                intent.putExtra("Customer", (Parcelable) c);
                intent.putExtra("hasData", true);

                startActivity(intent);
            }
        });

    }




}