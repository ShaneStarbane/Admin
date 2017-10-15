package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AssignDriverToOrder extends AppCompatActivity {
    DatabaseReference driverHistory,driverHistoryIndividual,ordersDatabase,driverHistoryChange;
    Button btnYes,btnNo;
    TextView tvAssignOrderID,tvAssignDriverID,tvHistory;
    String hasHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_driver_to_order);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        btnYes = (Button) findViewById(R.id.btnYes);
        btnNo = (Button) findViewById(R.id.btnNo);
        tvAssignDriverID = (TextView) findViewById(R.id.tvAssignDriverID);
        tvAssignOrderID = (TextView) findViewById(R.id.tvAssignOrderID);
        tvHistory = (TextView) findViewById(R.id.tvHistory);

        Intent thisIntent = getIntent();
        final String orderID = thisIntent.getStringExtra("orderID");
        final String driverID = thisIntent.getStringExtra("driverID");
        final String driverName = thisIntent.getStringExtra("driverName");
        tvAssignOrderID.setText(orderID);
        tvAssignDriverID.setText(driverID);
        driverHistory = FirebaseDatabase.getInstance().getReference("Drivers").child(driverID);
        driverHistoryChange = FirebaseDatabase.getInstance().getReference("Drivers").child(driverID);

        driverHistoryIndividual = FirebaseDatabase.getInstance().getReference("Drivers").child(driverID).child("order(s)");
        ordersDatabase = FirebaseDatabase.getInstance().getReference("Orders").child("All Orders").child(orderID);
        driverHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hasHistory = dataSnapshot.child("hasHistory").getValue().toString();
                tvHistory.setText(hasHistory);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),DoneAssigned.class);
                String uID = driverHistory.push().getKey();
                String history = tvHistory.getText().toString();
                if (TextUtils.equals(history,"false")){
                    Map firstOrder = new HashMap();
                    firstOrder.put("orderID",orderID);
                    firstOrder.put("orderEntryID",uID);
                    driverHistory.child("order(s)").child(uID).setValue(firstOrder);

                    Map updateOrderStatus = new HashMap();
                    updateOrderStatus.put("orderStatus","Validated");
                    ordersDatabase.updateChildren(updateOrderStatus);

                    Map addOrderDriver = new HashMap();
                    addOrderDriver.put("orderDriverUsername",driverName);
                    addOrderDriver.put("orderDriverID",driverID);
                    addOrderDriver.put("orderEntryID",uID);
                    ordersDatabase.updateChildren(addOrderDriver);

                    Map updateHistory = new HashMap();
                    updateHistory.put("hasHistory","true");
                    driverHistoryChange.updateChildren(updateHistory);

                    startActivity(nextPhase);


                }
                else{
                    Map order = new HashMap();
                    order.put("orderID",orderID);
                    order.put("orderEntryID",uID);
                    driverHistoryIndividual.child(uID).setValue(order);

                    Map updateOrderStatus = new HashMap();
                    updateOrderStatus.put("orderStatus","Validated");
                    ordersDatabase.updateChildren(updateOrderStatus);

                    Map addOrderDriver = new HashMap();
                    addOrderDriver.put("orderDriverUsername",driverName);
                    addOrderDriver.put("orderDriverID",driverID);
                    addOrderDriver.put("orderEntryID",uID);
                    ordersDatabase.updateChildren(addOrderDriver);
                    startActivity(nextPhase);
                }

            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){

            Intent thisIntent = getIntent();
            String orderID = thisIntent.getStringExtra("orderID");
            Intent back = new Intent(getApplicationContext(),DriversAvailable.class);
            back.putExtra("orderID",orderID);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
