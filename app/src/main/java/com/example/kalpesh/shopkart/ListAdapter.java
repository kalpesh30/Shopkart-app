package com.example.kalpesh.shopkart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    public static String BUY_URL= "https://bitnami-39xfosdmxa.appspot.com/buy?" ;
    Context context;
    ArrayList<Phone> phones;
    Dialog dialog;

    ListAdapter(Context context,ArrayList<Phone> phones){
        this.context = context ;
        this.phones = phones ;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()) ;
        View view = inflater.inflate(R.layout.list_item_view,viewGroup,false) ;
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        Phone P = phones.get(i) ;
        listViewHolder.model.setText(P.getModel());
        listViewHolder.manufacturer.setText(P.getManufacturer());
        listViewHolder.price.setText("Rs. " + P.getPrice().toString());
        Glide.with(context)
                .load(P.getImage())
                .apply(new RequestOptions().override(160,250))
                .into(listViewHolder.phone) ;
        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(listViewHolder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView phone;
        TextView model,manufacturer,price;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.iv_phn) ;
            model = itemView.findViewById(R.id.tv_model) ;
            manufacturer = itemView.findViewById(R.id.tv_manufaturer) ;
            price = itemView.findViewById(R.id.tv_price) ;
        }
    }

    public void setDialog(final ListViewHolder holder){
        dialog = new Dialog(context) ;
        dialog.setContentView(R.layout.buy_dialog);
        dialog.setTitle("Buying Dialog");


        // Setting the dialogbox elements
        final EditText username = dialog.findViewById (R.id.usrname) ;
        final EditText quantity = dialog.findViewById(R.id.quantity) ;
        final Button buy = dialog.findViewById(R.id.buy_btn);
        Button cancel = dialog.findViewById(R.id.cancel_btn) ;

       buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BUY_URL = BUY_URL + "model=" + holder.model.getText().toString() + "&username=" + username.getText().toString() + "&qty=" + quantity.getText().toString() ;
                Log.v("Buy url -> ",BUY_URL);
                StringRequest request = new StringRequest(BUY_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       /*GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        //Sales sales = gson.fromJson();*/
                       Log.v("The buyers -> ",response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Couldn't get JSON from the server",Toast.LENGTH_SHORT).show();
                    }
                }) ;

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               dismiss(dialog);
            }
        });

        dialog.show();
    }

    public void dismiss(Dialog dialog){
        dialog.dismiss();
    }

}
