package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts.CustomerInfo;
import com.example.hendryshanedeguia.freshcartadminapp.Adapters.ProductsAdapter;
import com.example.hendryshanedeguia.freshcartadminapp.Models.ProductInfo;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class Report_Inventory extends AppCompatActivity {
    DatabaseReference productsDBF;
    ListView lvProducts;
    List<ProductInfo> products;
    ProductsAdapter adapter;
    Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__inventory);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        final String forDBF = thisIntent.getStringExtra("categoryForDatabase");
        final String forSRF = thisIntent.getStringExtra("categoryForStorage");
        productsDBF = getInstance().getReference("Products").child(forDBF);
        products =  new ArrayList<>();
        lvProducts = (ListView) findViewById(R.id.listViewProducts);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        productsDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    ProductInfo PI = snapshot.getValue(ProductInfo.class);
                    products.add(PI);
                }

                adapter = new ProductsAdapter(Report_Inventory.this,R.layout.products_layout,products);
                lvProducts.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase = new Intent(getApplicationContext(),NewProduct.class);
                startActivity(nextPhase);
            }
        });

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvProdID = (TextView)  view.findViewById(R.id.tvProdID);
                String prodID = tvProdID.getText().toString();

                Intent nextPhase = new Intent(getApplicationContext(),ProductInfoDetails.class);
                nextPhase.putExtra("prodID",prodID);
                nextPhase.putExtra("categoryForDatabase",forDBF);
                nextPhase.putExtra("categoryForStorage",forSRF);
                startActivity(nextPhase);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){


            Intent back = new Intent(getApplicationContext(),CategoryInventory.class);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
}
