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
import com.example.hendryshanedeguia.freshcartadminapp.Models.DriverModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;

import java.util.List;

/**
 * Created by HendryShanedeGuia on 10/10/2017.
 */

public class DriversAdapter extends ArrayAdapter {
    private Activity context;
    private int resource;
    private List<DriverModel> listDrivers;

    public DriversAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listDrivers = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource,null);
        TextView tvDriverID = (TextView) v.findViewById(R.id.tvDriverID);
        TextView tvDriverUsername = (TextView) v.findViewById(R.id.tvDriverUsername);
        TextView tvDriverEmail = (TextView) v.findViewById(R.id.tvDriverEmail);
        TextView tvDriverContact = (TextView) v.findViewById(R.id.tvDriverContact);

        tvDriverID.setText(listDrivers.get(position).getDriverID());
        tvDriverUsername.setText(listDrivers.get(position).getDriverUsername());
        tvDriverEmail.setText(listDrivers.get(position).getDriverEmail());
        tvDriverContact.setText(listDrivers.get(position).getDriverContact());

        return v;
    }
}
