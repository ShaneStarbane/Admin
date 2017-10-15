package com.example.hendryshanedeguia.freshcartadminapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hendryshanedeguia.freshcartadminapp.Models.ProductInfo;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NewProduct extends AppCompatActivity {
    Button btnUpload, btnImage,btnShow;
    EditText etProdName, etProdPrice, etProdDes,etProdStock;
    Spinner spinnerCategory;
    ImageView ivProdImage;
    private StorageReference mStoragRef;
    private DatabaseReference mdatabaseRef;
    private Uri imgUri;
    public static  final String Storage_Path ="Product_images/";
    public static  final String Database_Path ="Products";
    public static  final int Request_Code = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        etProdName =  (EditText) findViewById(R.id.etProdName);
        etProdPrice =  (EditText) findViewById(R.id.etPrice);
        etProdDes =  (EditText) findViewById(R.id.etDescription);
        etProdStock = (EditText) findViewById(R.id.etProdStock);
        spinnerCategory =  (Spinner) findViewById(R.id.spinnerCategory);
        ivProdImage = (ImageView)findViewById(R.id.ivProdImage);
        btnUpload =  (Button) findViewById(R.id.btnAddProduct);
        btnImage = (Button) findViewById(R.id.btnSelectImage);

        mStoragRef = FirebaseStorage.getInstance().getReference(Storage_Path);
        mdatabaseRef =  FirebaseDatabase.getInstance().getReference(Database_Path);

    }
    public void btnImage_Click(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request_Code && resultCode == RESULT_OK &&  data != null && data.getData() != null) {
            imgUri = data.getData();
            try{
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                ivProdImage.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("VisibleForTests")
    public void btnAddNewProduct_Click(View v){
        if(imgUri !=null){
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading Image");
            dialog.show();
            final String category = spinnerCategory.getSelectedItem().toString();
            String photoName = etProdName.getText().toString();
            StorageReference ref = mStoragRef.child(category+"/").child(photoName);
            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    dialog.dismiss();
                    String uploadID = mdatabaseRef.push().getKey();
                    String prodName = etProdName.getText().toString();
                    String prodPrice = etProdPrice.getText().toString();
                    String prodDes = etProdDes.getText().toString();
                    String prodStock = etProdStock.getText().toString();
                    String imageURL = taskSnapshot.getDownloadUrl().toString();
                    String prodCategory = spinnerCategory.getSelectedItem().toString();


                    ProductInfo PU =  new ProductInfo(prodName,prodPrice,prodDes,imageURL,uploadID,prodCategory,prodStock);

                    mdatabaseRef.child(category).child(uploadID).setValue(PU);
                    etProdName.setText("");
                    etProdPrice.setText("");
                    etProdDes.setText("");
                    etProdStock.setText("");

                }
            })
                    .addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100* taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploading " + (int)progress+"%");
                        }
                    });
        }
        else{
            Toast.makeText(getApplicationContext(),"Please select an Image",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent thisIntent = getIntent();
            String forDBF = thisIntent.getStringExtra("categoryForDatabase");
            String forSRF = thisIntent.getStringExtra("categoryForStorage");
            Intent back = new Intent(getApplicationContext(),Report_Inventory.class);
            back.putExtra("categoryForDatabase",forDBF);
            back.putExtra("categoryForStorage",forSRF);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }

}
