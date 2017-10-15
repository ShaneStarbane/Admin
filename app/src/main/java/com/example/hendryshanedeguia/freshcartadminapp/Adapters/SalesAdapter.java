package com.example.hendryshanedeguia.freshcartadminapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderInnerDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;

import java.util.List;

/**
 * Created by HendryShanedeGuia on 12/10/2017.
 */

public class SalesAdapter extends ArrayAdapter {
    private Activity context;
    private int resource;
    private List<OrderMainDetailsModel> listSales;
    public SalesAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listSales = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();


        View v = inflater.inflate(resource,parent, false);
        TextView tvID = (TextView) v.findViewById(R.id.tvSalesOrderID);
        TextView tvGross = (TextView) v.findViewById(R.id.tvSalesOrderGross);
        TextView tvVAT = (TextView) v.findViewById(R.id.tvSalesOrderVAT);
        TextView tvDiscount = (TextView) v.findViewById(R.id.tvSalesOrderDiscount);
        TextView tvPromo = (TextView) v.findViewById(R.id.tvSalesOrderPromo);
        TextView tvBill = (TextView) v.findViewById(R.id.tvSalesOrderBill);


        tvID.setText(listSales.get(position).getOrderID());
        tvGross.setText(listSales.get(position).getOrderGross());
        tvVAT.setText(listSales.get(position).getOrderVAT());
        tvDiscount.setText(listSales.get(position).getDiscount());
        tvPromo.setText(listSales.get(position).getPromo());
        tvBill.setText(listSales.get(position).getOrderBill());

        return v;
    }
}
