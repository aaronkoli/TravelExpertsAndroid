package com.example.travelexpertsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String link = "http://192.168.100.131:8088/TravelExpertsRESTAPI_war_exploded/";
    ListView lvCustomers;
    Button btnCreate;

    ArrayAdapter<Customer> adapter;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCustomers = findViewById(R.id.lvCustomers);
        btnCreate = findViewById(R.id.btnCreate);

        queue = Volley.newRequestQueue(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                intent.putExtra("mode", "add");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), CustomerForm.class);
                intent.putExtra("customer", (Customer) lvCustomers.getAdapter().getItem(position));
                intent.putExtra("mode", "edit");
                startActivity(intent);
            }
        });
        Executors.newSingleThreadExecutor().execute(new loadCustomers());
    }

    private class loadCustomers implements Runnable {
        @Override
        public void run() {
            String url = link + "api/customers/getallcustomers";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "urf-8");
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    //ListViewCustomer listViewCustomer = new ListViewCustomer(getApplicationContext(), R.layout.activity_customer, customerList);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonCustomer = jsonArray.getJSONObject(i);
                            //JSONObject agtId = jsonCustomer.getJSONObject("agent");
                            //Log.d("log", agtId + "");

                            Customer customer = new Customer(
                                    jsonCustomer.getInt("id"),
                                    jsonCustomer.getString("custFirstName"),
                                    jsonCustomer.getString("custLastName"),
                                    jsonCustomer.getString("custAddress"),
                                    jsonCustomer.getString("custCity"),
                                    jsonCustomer.getString("custProv"),
                                    jsonCustomer.getString("custPostal"),
                                    jsonCustomer.getString("custCountry"),
                                    jsonCustomer.getString("custHomePhone"),
                                    jsonCustomer.getString("custBusPhone"),
                                    jsonCustomer.getString("custEmail"));
                                    //jsonCustomer.getInt("id"));

                            adapter.add(customer);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lvCustomers.setAdapter(adapter);
                        }
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("log", error.getMessage());
                }
            });
            queue.add(stringRequest);
        }
    }
}