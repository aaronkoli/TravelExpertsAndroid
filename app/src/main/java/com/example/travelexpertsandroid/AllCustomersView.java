package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
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


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(AllCustomersView.this);
        String url = "http://192.168.1.198:8080/Workshop7MicahREST_war_exploded/api/customers/getallcustomers";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvError.setText(response);
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("customers");
                            ArrayList<Customer> list = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                Customer c = null;
                                c.setCustomerId(jo.getInt("id"));
                                c.setCustFirstName(jo.getString("custFirstName"));
                                c.setCustLastName(jo.getString("custLastName"));
                                c.setEtCustAddress(jo.getString("custAddress"));
                                c.setEtCustCity(jo.getString("custCity"));
                                c.setEtCustProv(jo.getString("custProv"));
                                c.setEtCustPostal(jo.getString("custPostal"));
                                c.setEtCustCountry(jo.getString("custCountry"));
                                c.setEtCustHomePhone(jo.getString("custHomePhone"));
                                c.setEtCustBusPhone(jo.getString("custBusPhone"));
                                c.setEtCustEmail(jo.getString("custEmail"));
                                c.setEtAgentId(jo.getInt("agentId"));

                                list.add(c);
                            }
                            Toast to = new Toast(AllCustomersView.this);
                            to.makeText(AllCustomersView.this, list.toString(), Toast.LENGTH_LONG).show();
                            ArrayAdapter<Customer> adapter = new ArrayAdapter<>(
                                    AllCustomersView.this, android.R.layout.simple_list_item_1, list );
                            lvCustomers.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvError.setText(error.toString());
                Toast toast = new Toast(AllCustomersView.this);
                toast.makeText(AllCustomersView.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}