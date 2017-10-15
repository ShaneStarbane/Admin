package com.example.hendryshanedeguia.freshcartadminapp.Adapters;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Models.ProductInfo;
import com.example.hendryshanedeguia.freshcartadminapp.R;

import java.util.List;


/**
 * Created by HendryShanedeGuia on 10/10/2017.
 */

public class ProductsAdapter extends ArrayAdapter {
    private Activity context;
    private int resource;
    private List<ProductInfo> listProducts;
    public ProductsAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listProducts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
       TextView tvID = (TextView) v.findViewById(R.id.tvProdID);
        TextView tvName = (TextView) v.findViewById(R.id.tvProdName);
        TextView tvPrice = (TextView) v.findViewById(R.id.tvProdPrice);
        TextView tvStock = (TextView) v.findViewById(R.id.tvProdStock);

        tvID.setText(listProducts.get(position).getProdID());
        tvName.setText(listProducts.get(position).getProdName());
        tvPrice.setText(listProducts.get(position).getProdPrice());
        tvStock.setText(listProducts.get(position).getProdStock());
        return v;
    }


}
