package com.example.kalpesh.shopkart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private static EditText manufacturer;
    private static EditText model;
    private static EditText min;
    private static  EditText max ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        manufacturer = findViewById(R.id.et_manufacturer);
        model = findViewById(R.id.et_model) ;
        min = findViewById(R.id.et_min) ;
        max = findViewById(R.id.et_max) ;
        Intent intent = new Intent(this,MainActivity.class) ;
        Bundle bundle = new Bundle() ;
        bundle.putString("Manufacturer",manufacturer.getText().toString());
        bundle.putString("Model",manufacturer.getText().toString());
        //bundle.putString("");

    }
}
