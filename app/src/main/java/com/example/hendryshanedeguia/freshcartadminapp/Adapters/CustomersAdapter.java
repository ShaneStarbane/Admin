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

import com.example.hendryshanedeguia.freshcartadminapp.Models.CustomerModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;

import java.util.List;

/**
 * Created by HendryShanedeGuia on 06/10/2017.
 */

public class CustomersAdapter extends ArrayAdapter {
    private Activity context;
    private int resource;
    private List<CustomerModel> listCustomers;
    public CustomersAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listCustomers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();


        View v = inflater.inflate(resource,null);
        TextView tvCustomerID = (TextView) v.findViewById(R.id.tvCustomerAccID);
        TextView tvCustomerUsername = (TextView) v.findViewById(R.id.tvCustomerAccUsername);
        TextView tvCustomerEmail = (TextView) v.findViewById(R.id.tvCustomerAccEmail);
        TextView tvCustomerContact = (TextView) v.findViewById(R.id.tvCustomerAccContact);
        TextView tvCustomerTimesCancelled = (TextView) v.findViewById(R.id.tvCustomerAccTimesCancelled);


        tvCustomerID.setText(listCustomers.get(position).getCustID());
        tvCustomerUsername.setText(listCustomers.get(position).getCustUsername());
        tvCustomerEmail.setText(listCustomers.get(position).getCustEmail());
        tvCustomerContact.setText(listCustomers.get(position).getCustContact());
        tvCustomerTimesCancelled.setText(listCustomers.get(position).getTimesCancelled());


        return v;


    }
}
