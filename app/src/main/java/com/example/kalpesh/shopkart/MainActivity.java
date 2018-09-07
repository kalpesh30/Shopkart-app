package com.example.kalpesh.shopkart;

import android.content.Intent;
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        StringRequest request = new StringRequest(BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("REs", response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                ArrayList<Phone> phones = new ArrayList<Phone>(Arrays.asList(gson.fromJson(response, Phone[].class)));
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
        intent.setFlags()
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

}
