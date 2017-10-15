package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Adapters.NewOrdersAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Adapters.OrderItemsAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderInnerDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewOrderDetails extends AppCompatActivity {
    TextView tvNewOrderDetailsID,tvNewOrderDetailsBill,tvNewOrderDetailsCustUsername,tvNewOrderDetailsCashOnHand;
    DatabaseReference ordersDBF,innerOrdersDBF;
    ListView lvNewOrderDetails;
    private List<OrderInnerDetailsModel> orderList;
    OrderItemsAdapter adapter;
    Button btnAssign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        lvNewOrderDetails = (ListView) findViewById(R.id.lvNewOrderDetails);
        tvNewOrderDetailsID = (TextView) findViewById(R.id.tvNewOrderDetailsID);
        tvNewOrderDetailsBill = (TextView) findViewById(R.id.tvNewOrderDetailsBill);
        tvNewOrderDetailsCustUsername = (TextView) findViewById(R.id.tvNewOrderDetailsCustUsername);
        tvNewOrderDetailsCashOnHand = (TextView) findViewById(R.id.tvNewOrderDetailsCashOnHand);
        Intent thisIntent = getIntent();
        orderList = new ArrayList<>();
        String orderID = thisIntent.getStringExtra("orderID");
        ordersDBF = FirebaseDatabase.getInstance().getReference("Orders").child("All Orders").child(orderID);
        innerOrdersDBF = FirebaseDatabase.getInstance().getReference("Orders").child("All Orders").child(orderID).child("order(s)");
        btnAssign = (Button)findViewById(R.id.btnAssignDriver);
        ordersDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String orderID = dataSnapshot.child("orderID").getValue().toString();
                String orderBill = dataSnapshot.child("orderBill").getValue().toString();
                String cashOnHand = dataSnapshot.child("cashOnHand").getValue().toString();
                String custUsername = dataSnapshot.child("custUsername").getValue().toString();

                tvNewOrderDetailsID.setText(orderID);
                tvNewOrderDetailsBill.setText(orderBill);
                tvNewOrderDetailsCustUsername.setText(custUsername);
                tvNewOrderDetailsCashOnHand.setText(cashOnHand);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        innerOrdersDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderInnerDetailsModel oi = snapshot.getValue(OrderInnerDetailsModel.class);
                        orderList.add(oi);
                }
                adapter = new OrderItemsAdapter(NewOrderDetails.this, R.layout.order_list_custom_layout, orderList);
                lvNewOrderDetails.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thisIntent = getIntent();
                String orderID = thisIntent.getStringExtra("orderID");
                Intent nextPhase = new Intent(getApplicationContext(),DriversAvailable.class);
                nextPhase.putExtra("orderID",orderID);
                startActivity(nextPhase);
            }
        });






    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){


            Intent back = new Intent(getApplicationContext(),NewOrders.class);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
