package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Adapters.NewOrdersAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewOrders extends AppCompatActivity {
    public DatabaseReference ordersDBF;
    private List<OrderMainDetailsModel> orderList;
    public ListView lv;
    private NewOrdersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        lv = (ListView) findViewById(R.id.lvNewOrders);
        orderList =new ArrayList<>();
        ordersDBF = FirebaseDatabase.getInstance().getReference("Orders").child("All Orders");
        ordersDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderMainDetailsModel oi = snapshot.getValue(OrderMainDetailsModel.class);
                    if(TextUtils.equals(snapshot.child("orderStatus").getValue().toString(),"Pending")){
                        orderList.add(oi);
                    }
                    lv.setAdapter(null);

                    adapter = new NewOrdersAdapter(NewOrders.this, R.layout.new_orders_layout, orderList);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvOrderID = (TextView)  view.findViewById(R.id.tvNewOrderID);
                String orderID = tvOrderID.getText().toString();

                Intent nextPhase = new Intent(getApplicationContext(),NewOrderDetails.class);
                nextPhase.putExtra("orderID",orderID);
                startActivity(nextPhase);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent thisIntent = getIntent();
            Intent back = new Intent(getApplicationContext(),Orders.class);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
