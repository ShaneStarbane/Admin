package com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class DriverInfo extends AppCompatActivity {
    ImageButton ibtnDriverName,ibtnDriverEmail,ibtnDriverContact;
    EditText etDriverDetailsName,etDriverDetailsEmail,etDriverDetailsContact;
    DatabaseReference driverDBF;
    StorageReference ref;
    TextView tvDDName,tvDDEmail,tvDDContact,tvDDImageUrl;
    ImageView ivDriverDetailImage;
    private Uri imgUri;
    public static  final int Request_Code = 1234;
    ProgressDialog progressDialog;
    Button btnDeleteDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ibtnDriverName = (ImageButton) findViewById(R.id.ibtnDriverName);
        ibtnDriverEmail = (ImageButton) findViewById(R.id.ibtnDriverEmail);
        ibtnDriverContact = (ImageButton) findViewById(R.id.ibtnDriverContact);
        etDriverDetailsName = (EditText) findViewById(R.id.etDriverDetailName);
        etDriverDetailsEmail = (EditText) findViewById(R.id.etDriverDetailEmail);
        etDriverDetailsContact = (EditText) findViewById(R.id.etDriverDetailContact);
        tvDDName = (TextView) findViewById(R.id.tvDDName);
        tvDDEmail = (TextView) findViewById(R.id.tvDDEmail);
        tvDDContact = (TextView) findViewById(R.id.tvDDContact);
        tvDDImageUrl = (TextView) findViewById(R.id.tvDDImageUrl);
        ivDriverDetailImage = (ImageView) findViewById(R.id.ivDriverDetailImage);
        btnDeleteDriver = (Button) findViewById(R.id.btnDeleteDriver);
        ref = FirebaseStorage.getInstance().getReference("Driver_images/");
        progressDialog = new ProgressDialog(this);

        Intent thisIntent = getIntent();
        String driverID = thisIntent.getStringExtra("driverID");
        driverDBF  = FirebaseDatabase.getInstance().getReference("Drivers").child(driverID);
        driverDBF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("driverUsername").getValue() != null) {
                    String name = dataSnapshot.child("driverUsername").getValue().toString();
                    String email = dataSnapshot.child("driverEmail").getValue().toString();
                    String contact = dataSnapshot.child("driverContact").getValue().toString();
                    String imageURL = dataSnapshot.child("driverImageUrl").getValue().toString();

                    tvDDName.setText(name);
                    tvDDEmail.setText(email);
                    tvDDContact.setText(contact);
                    tvDDImageUrl.setText(imageURL);
                    etDriverDetailsName.setText(name);
                    etDriverDetailsEmail.setText(email);
                    etDriverDetailsContact.setText(contact);
                    Glide.with(getApplicationContext()).load(imageURL).into(ivDriverDetailImage);
                }else{
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ibtnDriverName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DriverInfo.this);
                //Grab needed Strings
                final String oldName = tvDDName.getText().toString();
                final String newName = etDriverDetailsName.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldName + " - - - > " + newName + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custUsername", newName);
                        driverDBF.updateChildren(nameMap);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etDriverDetailsName.setText(oldName);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnDriverEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DriverInfo.this);
                //Grab needed Strings
                final String oldEmail = tvDDEmail.getText().toString();
                final String newEmail = etDriverDetailsEmail.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldEmail + " - - - > " + newEmail + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custUsername", newEmail);
                        driverDBF.updateChildren(nameMap);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etDriverDetailsEmail.setText(oldEmail);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        ibtnDriverContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DriverInfo.this);
                //Grab needed Strings
                final String oldContact = tvDDContact.getText().toString();
                final String newContact = etDriverDetailsContact.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage(oldContact + " - - - > " + newContact + "");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Map nameMap = new HashMap();
                        nameMap.put("custUsername", newContact);
                        driverDBF.updateChildren(nameMap);
                        DriverInfo.this.finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        etDriverDetailsContact.setText(oldContact);
                        dialog.cancel();
                    }
                });
                // create alert dialog
                AlertDialog alertDialog = builder.create();
                // show it
                alertDialog.show();
            }
        });
        btnDeleteDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DriverInfo.this);
                // set title
                builder.setTitle("Delete Driver?");
                String driverName = etDriverDetailsName.getText().toString();
                // set dialog message
                builder.setMessage("Delete "+driverName+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        driverDBF.removeValue();
                        DriverInfo.this.finish();

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
                driverDBF.removeValue();
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
                ivDriverDetailImage.setImageBitmap(bm);
                AlertDialog.Builder builder = new AlertDialog.Builder(DriverInfo.this);
                //Grab needed Strings
                final String oldUrl = tvDDImageUrl.getText().toString();
//                final String newUrl = etCustDetailsCancellations.getText().toString();
                // set title
                builder.setTitle("Commit Changes?");

                // set dialog message
                builder.setMessage("Change profile picture.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        final ProgressDialog dialog2 = new ProgressDialog(DriverInfo.this);
                        dialog2.setMessage("Uploading Image. . . ");
                        dialog2.show();

                        ref.child(tvDDName.getText().toString()).putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                dialog2.dismiss();
                                final String newUrl = taskSnapshot.getDownloadUrl().toString();
                                Map nameMap = new HashMap();
                                nameMap.put("driverImageUrl", newUrl);
                                driverDBF.updateChildren(nameMap);
                                DriverInfo.this.finish();
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
                        driverDBF.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String imageURL = dataSnapshot.child("custImageUrl").getValue().toString();
                                Glide.with(getApplicationContext()).load(imageURL).into(ivDriverDetailImage);


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
