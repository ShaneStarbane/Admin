package com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hendryshanedeguia.freshcartadminapp.R;

public class Accounts extends AppCompatActivity {

    Button btnCustomers,btnDrivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_accounts);
        btnCustomers=(Button) findViewById(R.id.btnCustomerAccs);
        btnDrivers = (Button) findViewById(R.id.btnDriverAccs);

        btnCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountSelecter = new Intent(getApplicationContext(),Customers.class);
                startActivity(accountSelecter);
            }
        });
        btnDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountSelecter = new Intent(getApplicationContext(),Drivers.class);
                startActivity(accountSelecter);
            }
        });




    }
}
