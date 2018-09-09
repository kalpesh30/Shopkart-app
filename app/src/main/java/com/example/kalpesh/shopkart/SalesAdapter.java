package com.example.kalpesh.shopkart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesViewHolder> {

    ArrayList<Sales> buyers;
    Context context;

    SalesAdapter(Context context, ArrayList<Sales> buyers){
        this.context = context ;
        this.buyers = buyers;
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()) ;
        View view = inflater.inflate(R.layout.sales_list,viewGroup,false) ;
        return  new SalesViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder salesViewHolder, int i) {
        Sales buyer = buyers.get(i);
        salesViewHolder.invoice.setText("Invoice Number : " + buyer.getInvoice()) ;
        salesViewHolder.model.setText("Model : " + buyer.getModel());
        salesViewHolder.username.setText("Username : " + buyer.getUsername());
        salesViewHolder.quantity.setText("Quantity : " + String.valueOf(buyer.getQuantity())) ;
    }

    @Override
    public int getItemCount() {
        return buyers.size();
    }

    public class SalesViewHolder extends RecyclerView.ViewHolder {

        TextView invoice, model, quantity,username ;
        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);
            invoice = itemView.findViewById(R.id.tv_inv_no);
            model = itemView.findViewById(R.id.tv_model);
            quantity = itemView.findViewById(R.id.tv_qty);
            username = itemView.findViewById(R.id.tv_usrname);
        }
    }
}
