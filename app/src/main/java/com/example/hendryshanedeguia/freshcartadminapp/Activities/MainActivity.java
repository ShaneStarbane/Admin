package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts.Accounts;
import com.example.hendryshanedeguia.freshcartadminapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnAccounts,btnProducts,btnOrders,btnSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        btnAccounts = (Button) findViewById(R.id.btnAccounts);
        btnProducts = (Button) findViewById(R.id.btnStocks);
        btnOrders = (Button) findViewById(R.id.btnOrders);
        btnSales = (Button) findViewById(R.id.btnSales);



        btnAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accounts = new Intent(getApplicationContext(),Accounts.class);
                startActivity(accounts);
            }
        });
        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventory = new Intent(getApplicationContext(),CategoryInventory.class);
                startActivity(inventory);
            }
        });
        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventory = new Intent(getApplicationContext(),Orders.class);
                startActivity(inventory);
            }
        });
        btnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventory = new Intent(getApplicationContext(),Sales.class);
                startActivity(inventory);
            }
        });
    }
}
