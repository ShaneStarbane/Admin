package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.hendryshanedeguia.freshcartadminapp.R;

public class CategoryInventory extends AppCompatActivity {
    Button btnBakery,btnBeverages,btnBreakfast,btnBulk,btnCannedGoods,btnDairyAndEggs,btnDeli,btnFrozen,btnHousehold,btnInternational,btnMeatAndSeafood,btnPantry,btnPersonalCare,btnPets,btnProduce,btnSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_inventory);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btnBakery = (Button) findViewById(R.id.btnBakery);
        btnBeverages = (Button) findViewById(R.id.btnBeverages);
        btnBreakfast = (Button) findViewById(R.id.btnBreakfast);
        btnBulk = (Button) findViewById(R.id.btnBulk);
        btnBeverages = (Button) findViewById(R.id.btnBeverages);
        btnCannedGoods = (Button) findViewById(R.id.btnCannedGoods);
        btnDairyAndEggs = (Button) findViewById(R.id.btnDairyAndEggs);
        btnDeli = (Button) findViewById(R.id.btnDeli);
        btnFrozen = (Button) findViewById(R.id.btnFrozen);
        btnHousehold = (Button) findViewById(R.id.btnHousehold);
        btnInternational = (Button) findViewById(R.id.btnInternational);
        btnMeatAndSeafood = (Button) findViewById(R.id.btnMeatAndSeafood);
        btnPantry = (Button) findViewById(R.id.btnPantry);
        btnPersonalCare = (Button) findViewById(R.id.btnPersonalCare);
        btnPets = (Button) findViewById(R.id.btnPets);
        btnProduce = (Button) findViewById(R.id.btnProduce);
        btnSnacks = (Button) findViewById(R.id.btnSnacks);

        btnBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Bakery");
                nextPhase.putExtra("categoryForStorage","Bakery/");
                startActivity(nextPhase);

            }
        });
        btnBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Beverages");
                nextPhase.putExtra("categoryForStorage","Beverages/");
                startActivity(nextPhase);
            }
        });
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Breakfast");
                nextPhase.putExtra("categoryForStorage","Breakfast/");
                startActivity(nextPhase);
            }
        });
        btnCannedGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Canned Goods");
                nextPhase.putExtra("categoryForStorage","Canned_Goods/");
                startActivity(nextPhase);
            }
        });
        btnDairyAndEggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Dairy And Eggs");
                nextPhase.putExtra("categoryForStorage","Dairy_And_Eggs/");
                startActivity(nextPhase);
            }
        });
        btnDeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Deli");
                nextPhase.putExtra("categoryForStorage","Deli/");
                startActivity(nextPhase);
            }
        });
        btnFrozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Frozen");
                nextPhase.putExtra("categoryForStorage","Frozen/");
                startActivity(nextPhase);
            }
        });
        btnHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Household");
                nextPhase.putExtra("categoryForStorage","Household/");
                startActivity(nextPhase);
            }
        });
        btnInternational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","International");
                nextPhase.putExtra("categoryForStorage","International/");
                startActivity(nextPhase);
            }
        });
        btnMeatAndSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Meat And Seafood");
                nextPhase.putExtra("categoryForStorage","Meat_And_Seafood/");
                startActivity(nextPhase);
            }
        });
        btnPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Pantry");
                nextPhase.putExtra("categoryForStorage","Pantry/");
                startActivity(nextPhase);
            }
        });
        btnPersonalCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Personal Care");
                nextPhase.putExtra("categoryForStorage","Personal_Care/");
                startActivity(nextPhase);
            }
        });
        btnPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Pets");
                nextPhase.putExtra("categoryForStorage","Pets/");
                startActivity(nextPhase);
            }
        });
        btnProduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Produce");
                nextPhase.putExtra("categoryForStorage","Produce/");
                startActivity(nextPhase);
            }
        });
        btnSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPhase  = new Intent(getApplicationContext(),Report_Inventory.class);
                nextPhase.putExtra("categoryForDatabase","Snacks");
                nextPhase.putExtra("categoryForStorage","Snacks/");
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
