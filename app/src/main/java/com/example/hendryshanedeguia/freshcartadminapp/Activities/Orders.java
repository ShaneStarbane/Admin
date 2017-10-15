package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.hendryshanedeguia.freshcartadminapp.R;

public class Orders extends AppCompatActivity {
        Button btnNewOrders,btnValidatedOrders,btnCancelledOrders,btnDeliveredOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        btnNewOrders = (Button) findViewById(R.id.btnNewOrders);
        btnValidatedOrders = (Button) findViewById(R.id.btnValidatedOrders);
        btnCancelledOrders = (Button) findViewById(R.id.btnCancelledOrders);
        btnDeliveredOrders = (Button) findViewById(R.id.btnDeliveredOrders);

        btnNewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),NewOrders.class);
                startActivity(nextPhase);
            }
        });
        btnCancelledOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),CancelledOrders.class);
                startActivity(nextPhase);
            }
        });
        btnDeliveredOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),CompletedOrders.class);
                startActivity(nextPhase);
            }
        });
        btnValidatedOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),ValidatedOrders.class);
                startActivity(nextPhase);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){


            Intent back = new Intent(getApplicationContext(),MainActivity.class);

            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
