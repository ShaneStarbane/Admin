package com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class CustomerInfo extends AppCompatActivity {
    ImageButton ibtnName,ibtnEmail,ibtnContact,ibtnCancellations;
    EditText etCustDetailsName,etCustDetailsEmail,etCustDetailsContact,etCustDetailsCancellations;
    DatabaseReference custDBF;
    StorageReference ref;
    TextView tvCDName,tvCDEmail,tvCDContact,tvCDCancellations,tvImageUrl;
    ImageView ivCustDetailImage;
    private Uri imgUri;
    public static  final int Request_Code = 1234;
    ProgressDialog progressDialog;
    Button btnDeleteCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ibtnName = (ImageButton) findViewById(R.id.ibtnName);
        ibtnEmail = (ImageButton) findViewById(R.id.ibtnEmail);
        ibtnContact = (ImageButton) findViewById(R.id.ibtnContact);
        ibtnCancellations = (ImageButton) findViewById(R.id.ibtnCancellations);
        etCustDetailsName = (EditText) findViewById(R.id.etCustDetailName);
        etCustDetailsEmail = (EditText) findViewById(R.id.etCustDetailEmail);
        etCustDetailsContact = (EditText) findViewById(R.id.etCustDetailContact);
        etCustDetailsCancellations = (EditText) findViewById(R.id.etCustDetailCancellations);
        tvCDName = (TextView) findViewById(R.id.tvCDName);
        tvCDEmail = (TextView) findViewById(R.id.tvCDEmail);
        tvCDContact = (TextView) findViewById(R.id.tvCDContact);
        tvCDCancellations = (TextView) findViewById(R.id.tvCDCancellations);
        tvImageUrl = (TextView) findViewById(R.id.tvImageUrl);
        ivCustDetailImage = (ImageView) findViewById(R.id.ivCustDetailImage);
        btnDeleteCustomer = (Button) findViewById(R.id.btnDeleteCustomer);
        ref = FirebaseStorage.getInstance().getReference("Customer_images/");
        progressDialog = new ProgressDialog(this);

        Intent thisIntent = getIntent();
        String custID = thisIntent.getStringExtra("custID");
        custDBF  = FirebaseDatabase.getInstance().getReference("Customers").child(custID);
        custDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("custUsername").getValue() != null) {
                    String name = dataSnapshot.child("custUsername").getValue().toString();
                    String email = dataSnapshot.child("custEmail").getValue().toString();
                    String contact = dataSnapshot.child("custContact").getValue().toString();
                    String cancellations = dataSnapshot.child("timesCancelled").getValue().toString();
                    String imageURL = dataSnapshot.child("custImageUrl").getValue().toString();

                    tvCDName.setText(name);
                    tvCDEmail.setText(email);
                    tvCDContact.setText(contact);
                    tvCDCancellations.setText(cancellations);
                    tvImageUrl.setText(imageURL);
                    etCustDetailsName.setText(name);
                    etCustDetailsEmail.setText(email);
                    etCustDetailsContact.setText(contact);
                    etCustDetailsCancellations.setText(cancellations);
                    Glide.with(getApplicationContext()).load(imageURL).into(ivCustDetailImage);
                }else{
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ibtnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                //Grab needed Strings
                final String oldName = tvCDName.getText().toString();
                final String newName = etCustDetailsName.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldName + " - - - > " + newName + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custUsername", newName);
                        custDBF.updateChildren(nameMap);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                etCustDetailsName.setText(oldName);
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                //Grab needed Strings
                final String oldEmail = tvCDEmail.getText().toString();
                final String newEmail = etCustDetailsEmail.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldEmail + " - - - > " + newEmail + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custEmail", newEmail);
                        custDBF.updateChildren(nameMap);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etCustDetailsEmail.setText(oldEmail);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                //Grab needed Strings
                final String oldContact = tvCDContact.getText().toString();
                final String newContact = etCustDetailsContact.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldContact + " - - - > " + newContact + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custContact", newContact);
                        custDBF.updateChildren(nameMap);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etCustDetailsEmail.setText(oldContact);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnCancellations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                //Grab needed Strings
                final String oldCancellations = tvCDCancellations.getText().toString();
                final String newCancellations = etCustDetailsCancellations.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldCancellations + " - - - > " + newCancellations + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custContact", newCancellations);
                        custDBF.updateChildren(nameMap);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etCustDetailsEmail.setText(oldCancellations);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                // set title
                builder.setTitle("Delete Driver?");
                String custName = etCustDetailsName.getText().toString();
                // set dialog message
                builder.setMessage("Delete "+custName+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        custDBF.removeValue();
                        CustomerInfo.this.finish();
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
                custDBF.removeValue();
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
                ivCustDetailImage.setImageBitmap(bm);
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerInfo.this);
                //Grab needed Strings
                final String oldUrl = tvImageUrl.getText().toString();
//                final String newUrl = etCustDetailsCancellations.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage("Change profile picture.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        final ProgressDialog dialog2 = new ProgressDialog(CustomerInfo.this);
                        dialog2.setMessage("Uploading Image. . . ");
                        dialog2.show();

                        ref.child(tvCDName.getText().toString()).putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                dialog2.dismiss();
                                final String newUrl = taskSnapshot.getDownloadUrl().toString();
                                Map nameMap = new HashMap();
                                nameMap.put("custImageUrl", newUrl);
                                custDBF.updateChildren(nameMap);

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
                       custDBF.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               String imageURL = dataSnapshot.child("custImageUrl").getValue().toString();
                               Glide.with(getApplicationContext()).load(imageURL).into(ivCustDetailImage);

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

}
