package com.example.hendryshanedeguia.freshcartadminapp.Activities.Accounts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hendryshanedeguia.freshcartadminapp.Models.CustomerModel;
import com.example.hendryshanedeguia.freshcartadminapp.Models.DriverModel;
import com.example.hendryshanedeguia.freshcartadminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewDriver extends AppCompatActivity {
    EditText etDriverUsername, etDriverEmail, etDriverPassword, etDriverContact;
    Button btnAddDriver, btnSelectDriverImage;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ImageView ivImage;
    private Uri imgUri;
    public static final int Request_Code = 1234;
    ProgressDialog progressDialog;
    String driver_email, driver_password, driver_contact, driver_Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_driver);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        etDriverUsername = (EditText) findViewById(R.id.etDriverUsername);
        etDriverEmail = (EditText) findViewById(R.id.etDriverEmail);
        etDriverPassword = (EditText) findViewById(R.id.etDriverPassword);
        etDriverContact = (EditText) findViewById(R.id.etDriverContact);
        btnAddDriver = (Button) findViewById(R.id.btnAddDriver);
        btnSelectDriverImage = (Button) findViewById(R.id.btnSelectDriverImage);
        ivImage = (ImageView) findViewById(R.id.ivDriverImage);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Drivers");
        storageReference = FirebaseStorage.getInstance().getReference("Driver_images/");
    }

    @SuppressWarnings("VisibleForTests")
    public void btnAddDriver_Click(View v) {
        driver_email = etDriverEmail.getText().toString().trim();
        driver_password = etDriverPassword.getText().toString().trim();
        driver_contact = etDriverContact.getText().toString().trim();
        driver_Username = etDriverUsername.getText().toString().trim();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(driver_email);

        if (!matcher.matches()) {
            Toast.makeText(getApplicationContext(), "Not a valid email address.( Only Alphanumeric , _ , . , and - characters are allowed). ", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(driver_email)) {
            Toast.makeText(getApplicationContext(), "Email should not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(driver_password)) {
            Toast.makeText(getApplicationContext(), "Password should not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(driver_contact)) {
            Toast.makeText(getApplicationContext(), "Contact number should not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(driver_Username)) {
            Toast.makeText(getApplicationContext(), "Username number should not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (imgUri == null) {
            Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_LONG).show();
            return;

        }
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Registering user . . . ");
        dialog.show();
        String photoName = etDriverUsername.getText().toString();
        StorageReference ref = storageReference.child(photoName);
        StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                dialog.dismiss();

                final String driver_email = etDriverEmail.getText().toString().trim();
                String driver_password = etDriverPassword.getText().toString().trim();
                final String driver_contact = etDriverContact.getText().toString().trim();
                final String driver_Username = etDriverUsername.getText().toString().trim();
                final String imageURL = taskSnapshot.getDownloadUrl().toString();

                firebaseAuth.createUserWithEmailAndPassword(driver_email, driver_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser fbu = FirebaseAuth.getInstance().getCurrentUser();
                            String uID = fbu.getUid();
                            DriverModel DriverInfo = new DriverModel(driver_email, driver_contact, "null", imageURL, driver_Username, uID, "0","false");
                            databaseReference.child(uID).setValue(DriverInfo);
                            etDriverUsername.setText("");
                            etDriverPassword.setText("");
                            etDriverEmail.setText("");
                            etDriverContact.setText("");
                            ivImage.setImageBitmap(null);
                            Toast.makeText(getApplicationContext(), "Registration success!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                dialog.setMessage("Adding Driver. . . " + (int) progress + "%");
            }
        });

    }
    public void btnSelectDriverImage_Click(View v){
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
                ivImage.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

