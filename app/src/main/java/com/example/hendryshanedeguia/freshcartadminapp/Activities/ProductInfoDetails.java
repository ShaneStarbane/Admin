package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts.CustomerInfo;
import com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts.DriverInfo;
import com.example.hendryshanedeguia.freshcartadminapp.Models.ProductInfo;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductInfoDetails extends AppCompatActivity {
    ImageButton ibtnProdName,ibtnProdDes,ibtnProdPrice,ibtProdStock;
    EditText etProdDetailsName,etProdDetailsDes,etProdDetailsPrice,etProdDetailsStock;
    DatabaseReference prodDBF;
    StorageReference ref;
    TextView tvPDName,tvPDDes,tvPDPrice,tvPDStock,tvPDImageUrl;
    ImageView ivProdDetailImage;
    private Uri imgUri;
    public static  final int Request_Code = 1234;
    ProgressDialog progressDialog;
    Button btnDeleteProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        final String prodID = thisIntent.getStringExtra("prodID");
        final String forDBF = thisIntent.getStringExtra("categoryForDatabase");
        final String forSRF = thisIntent.getStringExtra("categoryForStorage");

        ibtnProdName = (ImageButton) findViewById(R.id.ibtnProdName);
        ibtnProdDes = (ImageButton) findViewById(R.id.ibtnProdDes);
        ibtnProdPrice = (ImageButton) findViewById(R.id.ibtnProdPrice);
        ibtProdStock = (ImageButton) findViewById(R.id.ibtnProdStock);
        etProdDetailsName = (EditText) findViewById(R.id.etProdDetailName);
        etProdDetailsDes = (EditText) findViewById(R.id.etProdDetailDes);
        etProdDetailsPrice = (EditText) findViewById(R.id.etProdDetailPrice);
        etProdDetailsStock = (EditText) findViewById(R.id.etProdDetailStock);
        tvPDName = (TextView) findViewById(R.id.tvPDName);
        tvPDDes = (TextView) findViewById(R.id.tvPDDes);
        tvPDPrice = (TextView) findViewById(R.id.tvPDPrice);
        tvPDStock = (TextView) findViewById(R.id.tvPDStock);
        tvPDImageUrl = (TextView) findViewById(R.id.tvPDImageUrl);
        ivProdDetailImage = (ImageView) findViewById(R.id.ivProdDetailImage);
        btnDeleteProduct = (Button) findViewById(R.id.btnDeleteProduct);
        ref = FirebaseStorage.getInstance().getReference("Product_images/").child(forSRF);
        progressDialog = new ProgressDialog(this);


        prodDBF  = FirebaseDatabase.getInstance().getReference("Products").child(forDBF).child(prodID);
        prodDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("prodName").getValue() != null){
                    String name = dataSnapshot.child("prodName").getValue().toString();
                    String price = dataSnapshot.child("prodPrice").getValue().toString();
                    String des = dataSnapshot.child("prodDes").getValue().toString();
                    String stock = dataSnapshot.child("prodStock").getValue().toString();
                    String imageURL = dataSnapshot.child("imageURL").getValue().toString();

                    tvPDName.setText(name);
                    tvPDDes.setText(des);
                    tvPDPrice.setText(price);
                    tvPDStock.setText(stock);
                    tvPDImageUrl.setText(imageURL);
                    etProdDetailsName.setText(name);
                    etProdDetailsDes.setText(des);
                    etProdDetailsPrice.setText(price);
                    etProdDetailsStock.setText(stock);
                    Glide.with(getApplicationContext()).load(imageURL).into(ivProdDetailImage);

                }
                else{

                    return;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ibtnProdName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                //Grab needed Strings
                final String oldName = tvPDName.getText().toString();
                final String newName = etProdDetailsName.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldName + " - - - > " + newName + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("prodNname", newName);
                        prodDBF.updateChildren(nameMap);
                        ProductInfoDetails.this.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etProdDetailsName.setText(oldName);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnProdDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                //Grab needed Strings
                final String oldDes = tvPDDes.getText().toString();
                final String newDes = etProdDetailsDes.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldDes + " - - - > " + newDes + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("prodDes", newDes);
                        prodDBF.updateChildren(nameMap);
                        ProductInfoDetails.this.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etProdDetailsDes.setText(oldDes);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnProdPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                //Grab needed Strings
                final String oldPrice = tvPDPrice.getText().toString();
                final String newPrice = etProdDetailsPrice.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog oldPrice
                builder.setMessage(oldPrice + " - - - > " + newPrice + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("prodPrice", newPrice);
                        prodDBF.updateChildren(nameMap);
                        ProductInfoDetails.this.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etProdDetailsPrice.setText(oldPrice);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtProdStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                //Grab needed Strings
                final String oldStock = tvPDStock.getText().toString();
                final String newStock = etProdDetailsStock.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog oldPrice
                builder.setMessage(oldStock + " - - - > " + newStock + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("prodStock", newStock);
                        prodDBF.updateChildren(nameMap);


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etProdDetailsPrice.setText(oldStock);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                // set title
                builder.setTitle("Delete Product?");
                String prodName = etProdDetailsName.getText().toString();
                // set dialog message
                builder.setMessage("Delete "+prodName+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        prodDBF.removeValue();
                        Intent thisIntent = getIntent();
                        String forDBF = thisIntent.getStringExtra("categoryForDatabase");
                        String forSRF = thisIntent.getStringExtra("categoryForStorage");
                        Intent nextPhase = new Intent(getApplicationContext(),Report_Inventory.class);
                        nextPhase.putExtra("categoryForDatabase",forDBF);
                        nextPhase.putExtra("categoryForStorage",forSRF);
                        startActivity(nextPhase);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
                prodDBF.removeValue();
            }
        });
    }
    public void imageButton_Click(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
    }
    @SuppressWarnings("VisibleForTests")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request_Code && resultCode == RESULT_OK &&  data != null && data.getData() != null) {
            imgUri = data.getData();
            try{

                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                ivProdDetailImage.setImageBitmap(bm);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoDetails.this);
                //Grab needed Strings
                final String oldUrl = tvPDImageUrl.getText().toString();
//                final String newUrl = etCustDetailsCancellations.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage("Change product picture.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        final ProgressDialog dialog2 = new ProgressDialog(ProductInfoDetails.this);
                        dialog2.setMessage("Uploading Image. . . ");
                        dialog2.show();
                        ref.child(tvPDName.getText().toString()).putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                dialog2.dismiss();
                                final String newUrl = taskSnapshot.getDownloadUrl().toString();
                                Map nameMap = new HashMap();
                                nameMap.put("imageURL", newUrl);
                                prodDBF.updateChildren(nameMap);
                                ProductInfoDetails.this.finish();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                double progress =(100* taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                dialog2.setMessage("Uploading Image  " + (int)progress+"%");
                            }
                        });

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       prodDBF.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String imageURL = dataSnapshot.child("imageURL").getValue().toString();
                                Glide.with(getApplicationContext()).load(imageURL).into(ivProdDetailImage);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent thisIntent = getIntent();
             String forDBF = thisIntent.getStringExtra("categoryForDatabase");
             String forSRF = thisIntent.getStringExtra("categoryForStorage");
            Intent nextPhase = new Intent(getApplicationContext(),Report_Inventory.class);
            nextPhase.putExtra("categoryForDatabase",forDBF);
            nextPhase.putExtra("categoryForStorage",forSRF);
            startActivity(nextPhase);



        }
        return super.onKeyDown(keyCode, event);
    }

}
