package com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Adapters.DriversAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.DriverModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Drivers extends AppCompatActivity {
    DatabaseReference AccountsDBF;
    ListView lvDrivers;
    List<DriverModel> listDrivers;
    DriversAdapter adapter;
    Button btnAddNewDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        AccountsDBF = FirebaseDatabase.getInstance().getReference("Drivers");
        lvDrivers =(ListView) findViewById(R.id.lvDrivers);
        btnAddNewDriver = (Button) findViewById(R.id.btnAddNewDriver);
        listDrivers =  new ArrayList<>();
        adapter = new DriversAdapter(Drivers.this,R.layout.drivers_accounts,listDrivers);

        AccountsDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    DriverModel CM = snapshot.getValue(DriverModel.class);
                    listDrivers.add(CM);
                }

                adapter = new DriversAdapter(Drivers.this,R.layout.drivers_accounts,listDrivers);
                lvDrivers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),NewDriver.class);
                startActivity(nextPhase);
            }
        });
        lvDrivers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvDriverID = (TextView)  view.findViewById(R.id.tvDriverID);
                String driverID = tvDriverID.getText().toString();

                Intent nextPhase = new Intent(getApplicationContext(),DriverInfo.class);
                nextPhase.putExtra("driverID",driverID);
                startActivity(nextPhase);
            }
        });
    }
}
