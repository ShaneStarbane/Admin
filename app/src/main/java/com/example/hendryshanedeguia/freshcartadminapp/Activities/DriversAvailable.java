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

import com.example.hendryshanedeguia.freshcartadminapp.Adapters.DriversAvailableAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Adapters.NewOrdersAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.DriverModel;
import com.example.hendryshanedeguia.freshcartadminapp.Models.OrderMainDetailsModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DriversAvailable extends AppCompatActivity {
    public DatabaseReference driversDBF;
    private List<DriverModel> driverList;
    public ListView lv;
    private DriversAvailableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_available);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        lv = (ListView) findViewById(R.id.lvDriversAvailble);
        driverList =new ArrayList<>();
        driversDBF = FirebaseDatabase.getInstance().getReference("Drivers");
        driversDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DriverModel DM = snapshot.getValue(DriverModel.class);
                    if(TextUtils.equals(snapshot.child("status").getValue().toString(),"Available")){

                        driverList.add(DM);
                    }
                    lv.setAdapter(null);

                    adapter = new DriversAvailableAdapter(DriversAvailable.this, R.layout.drivers_available_customlayout, driverList);
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
                Intent thisIntent = getIntent();
                String orderID = thisIntent.getStringExtra("orderID");

                TextView tvDriverID = (TextView)  view.findViewById(R.id.tvDriversAvailableID);
                TextView tvDriverName = (TextView)  view.findViewById(R.id.tvDriversAvailableName);

                String driverID = tvDriverID.getText().toString();
                String driverName = tvDriverName.getText().toString();

                Intent nextPhase = new Intent(getApplicationContext(),AssignDriverToOrder.class);
                nextPhase.putExtra("driverID",driverID);
                nextPhase.putExtra("driverName",driverName);
                nextPhase.putExtra("orderID",orderID);

                startActivity(nextPhase);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){


            Intent back = new Intent(getApplicationContext(),NewOrderDetails.class);

            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
