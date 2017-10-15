package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ListView;

import com.example.hendryshanedeguia.freshcartadminapp.Adapters.NewOrdersAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Adapters.SalesAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sales extends AppCompatActivity {
    public DatabaseReference ordersDBF;
    private List<OrderMainDetailsModel> orderList;
    public ListView lv;
    private SalesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        lv = (ListView) findViewById(R.id.lvSales);
        orderList =new ArrayList<>();
        ordersDBF = FirebaseDatabase.getInstance().getReference("Orders").child("All Orders");
        ordersDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OrderMainDetailsModel oi = snapshot.getValue(OrderMainDetailsModel.class);
                    if(TextUtils.equals(snapshot.child("orderStatus").getValue().toString(),"Completed")){
                        orderList.add(oi);
                    }
                    lv.setAdapter(null);

                    adapter = new SalesAdapter(Sales.this, R.layout.sales_layout, orderList);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
