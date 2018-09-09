package com.example.kalpesh.shopkart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class SalesActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    public static final String BASE_URL = "https://bitnami-39xfosdmxa.appspot.com/getSalesRecords" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        recyclerView = findViewById(R.id.rc_sales) ;
        StringRequest request = new StringRequest(BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getSalesList(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),"Couldn't get data from the server.",Toast.LENGTH_LONG) ;
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request) ;
    }

    public void getSalesList(String response){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create() ;
        ArrayList<Sales> buyers = new ArrayList<>(Arrays.asList(gson.fromJson(response,Sales[].class))) ;
        renderSales(buyers);

    }

    public void renderSales(ArrayList<Sales> buyers) {
        SalesAdapter madapter = new SalesAdapter(this,buyers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(madapter);
        madapter.notifyDataSetChanged();

    }
}
