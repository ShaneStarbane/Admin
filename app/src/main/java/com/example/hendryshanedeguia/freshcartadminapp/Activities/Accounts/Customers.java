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

import com.example.hendryshanedeguia.freshcartadminapp.Activities.NewOrderDetails;
import com.example.hendryshanedeguia.freshcartadminapp.Adapters.CustomersAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.CustomerModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Customers extends AppCompatActivity {
    DatabaseReference AccountsDBF;
    ListView lvCustomers;
    List<CustomerModel> listCustomers;
    CustomersAdapter adapter;
    Button btnAddNewCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        AccountsDBF = FirebaseDatabase.getInstance().getReference("Customers");
        lvCustomers =(ListView) findViewById(R.id.lvCustomers);
        btnAddNewCustomer = (Button) findViewById(R.id.btnAddCust);
        listCustomers =  new ArrayList<>();
        adapter = new CustomersAdapter(Customers.this,R.layout.customer_accounts,listCustomers);


        AccountsDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                lvCustomers.invalidateViews();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    CustomerModel CM = snapshot.getValue(CustomerModel.class);
                    listCustomers.add(CM);
                }

                adapter = new CustomersAdapter(Customers.this,R.layout.customer_accounts,listCustomers);
                lvCustomers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),NewCustomer.class);
                startActivity(nextPhase);
            }
        });
        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvCustID = (TextView)  view.findViewById(R.id.tvCustomerAccID);
                String custID = tvCustID.getText().toString();

                Intent nextPhase = new Intent(getApplicationContext(),CustomerInfo.class);
                nextPhase.putExtra("custID",custID);
                startActivity(nextPhase);
            }
        });




    }
}
