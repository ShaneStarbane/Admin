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

import com.example.hendryshanedeguia.freshcartadminapp.Models.CustomerModel;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;

import java.util.List;

/**
 * Created by HendryShanedeGuia on 11/10/2017.
 */

public class NewOrdersAdapter extends ArrayAdapter {
    private Activity context;
    private int resource;
    private List<OrderMainDetailsModel> listOrders;
    public NewOrdersAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listOrders = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tvID = (TextView) v.findViewById(R.id.tvNewOrderID);
        TextView tvBill = (TextView) v.findViewById(R.id.tvNewOrderBill);
        TextView tvCOH = (TextView) v.findViewById(R.id.tvNewOrderCashOnHand);
        TextView tvCustName = (TextView) v.findViewById(R.id.tvNewOrderCustUsername);

        tvID.setText(listOrders.get(position).getOrderID());
        tvBill.setText(listOrders.get(position).getOrderBill());
        tvCOH.setText(listOrders.get(position).getCashOnHand());
        tvCustName.setText(listOrders.get(position).getCustUsername());
        return v;
    }
}
