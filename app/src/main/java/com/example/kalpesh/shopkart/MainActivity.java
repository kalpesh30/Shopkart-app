package com.example.kalpesh.shopkart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://bitnami-39xfosdmxa.appspot.com/get-items";
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("intent is ",getIntent().toString()) ;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Intent intent = getIntent();
        if(getIntent() == null)
        Log.v("Null is there ","null ");
        String fetchUrl = getUrl() ;
        StringRequest request = new StringRequest(fetchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Response", response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                ArrayList<Phone> phones = new ArrayList<>(Arrays.asList(gson.fromJson(response, Phone[].class)));
                renderPhone(phones);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Couldn't get list from the net", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN) ;
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK) ;
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op_search:
                searchActivity();
                break;
            default:
        }
        return true;
    }

    public void searchActivity() {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    public void renderPhone(ArrayList<Phone> phones) {

        ListAdapter adapter = new ListAdapter(getApplicationContext(), phones);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public String getUrl(){
        Intent intent = getIntent();
        String fetchURL = BASE_URL ;
        String str = intent.getStringExtra("SearchIntent") ;
        if(str == "Searc" ){
            Bundle searchDetails = intent.getExtras();
            String manufacturer = searchDetails.getString("Manufacturer") ;
            String model = searchDetails.getString("Model");
            String min = searchDetails.getString("Min") ;
            String max = searchDetails.getString("Max") ;
            fetchURL = fetchURL + "?" ;
            if(manufacturer != null)
                fetchURL = fetchURL + "&manufacturer=" + manufacturer;
            if(model != null)
                fetchURL = fetchURL + "&model=" + model;
            if(Integer.parseInt(min) != -1 )
                fetchURL = fetchURL + "&min-price=" + String.valueOf(min);
            if(Integer.parseInt(max) != -1)
                fetchURL = fetchURL + "&max-price=" + String.valueOf(min);
        }
        Log.v("Fetch Url-->",fetchURL) ;
        return fetchURL;

    }

}
