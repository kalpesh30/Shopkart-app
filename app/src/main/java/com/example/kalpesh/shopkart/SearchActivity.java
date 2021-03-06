package com.example.kalpesh.shopkart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private static EditText manufacturer;
    private static EditText model;
    private static EditText min;
    private static EditText max ;
    private static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        manufacturer = findViewById(R.id.et_manufacturer);
        model = findViewById(R.id.et_model) ;
        min = findViewById(R.id.et_min) ;
        max = findViewById(R.id.et_max) ;
        button = findViewById(R.id.search_button) ;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class) ;
                Bundle bundle = new Bundle() ;
                bundle.putString("Manufacturer",manufacturer.getText().toString());
                bundle.putString("Model",model.getText().toString());
                bundle.putString("Min",min.getText().toString());
                bundle.putString("Max",max.getText().toString());
                //intent.putExtra("SearchIntent","Searc");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // startActivity(intent);
    }
}
