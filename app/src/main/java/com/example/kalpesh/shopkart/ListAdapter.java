package com.example.kalpesh.shopkart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;
    ArrayList<Phone> phones;

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
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        Phone P = phones.get(i) ;
        listViewHolder.model.setText(P.getModel());
        listViewHolder.manufacturer.setText(P.getManufacturer());
        listViewHolder.price.setText("Rs. " + P.getPrice().toString());
        Glide.with(context)
                .load(P.getImage())
                .apply(new RequestOptions().override(160,250))
                .into(listViewHolder.phone) ;
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
}
