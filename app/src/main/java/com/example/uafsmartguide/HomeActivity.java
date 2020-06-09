package com.example.uafsmartguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class HomeActivity extends AppCompatActivity implements LocationListener {
ImageView imageView;
EditText lat,lon,desc,name;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private StorageReference mStorageRef;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    private String[] array;
Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        spin();

    }

    void init()
    {
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
        desc = findViewById(R.id.description);
        imageView = findViewById(R.id.addimg);
        type = findViewById(R.id.typespin);
        name = findViewById(R.id.placename);
    }

    void spin(){
        array = getResources().getStringArray(R.array.catagory);
        final ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.catagory,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    public void selectimg(View view) {

        openGallery();
       // showDialog(HomeActivity.this,"Choose Option","");

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }





    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                dispatchTakePictureIntent();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                openGallery();
                dialog.dismiss();
            }
        });
        builder.show();
    }
    void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
        else if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

    }

    public void submit(View view) {

uploadPicAndData(view);

    }



    void uploadPicAndData(View view)
    {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        final LottieAnimationView lottieAnimationView = findViewById(R.id.upload_login);
        lottieAnimationView.setVisibility(View.VISIBLE);
       final StorageReference riversRef = mStorageRef.child("images/"+name.getText().toString()+".jpg");
        if (imageUri != null) {
            riversRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                           riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                    String url = uri.toString();
                                   PositionObject obj = new PositionObject(lat.getText().toString(),lon.getText().toString()
                                           ,desc.getText().toString(),url,name.getText().toString(),type.getSelectedItem().toString());
                                   FirebaseDatabase database = FirebaseDatabase.getInstance();
                                   final DatabaseReference myRef = database.getReference();
                                   myRef.child("Locations").child(type.getSelectedItem().toString()).child(name.getText().toString()).setValue(obj);
                                   lottieAnimationView.setVisibility(View.GONE);
                                   lat.setText("");
                                   lon.setText("");
                                   name.setText("");
                                   desc.setText("");
                                   imageView.setImageResource(R.drawable.admin_main_img);
                                   Toast.makeText(HomeActivity.this, "Successfully uploaded.", Toast.LENGTH_SHORT).show();



                               }
                           });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            lottieAnimationView.setVisibility(View.GONE);
                        }
                    });
        }
        else
        {

            PositionObject obj = new PositionObject(lat.getText().toString(),lon.getText().toString()
                    ,desc.getText().toString(),"https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Fdownload.jpg?alt=media&token=286ad0b2-5ee2-434b-9178-688be0f68717",name.getText().toString(),type.getSelectedItem().toString());
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference();
            myRef.child("Locations").child(type.getSelectedItem().toString()).child(name.getText().toString()).setValue(obj);
            lottieAnimationView.setVisibility(View.GONE);
            lat.setText("");
            lon.setText("");
            name.setText("");
            desc.setText("");
            imageView.setImageResource(R.drawable.admin_main_img);

            Toast.makeText(HomeActivity.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();



        }

    }

    public void getlocation(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
