package com.example.uafsmartguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailLocation extends AppCompatActivity {

    private TextView name,details;
    private ImageView headerImage;
    private Button showLocation;
    private String nameBundle,detailsBundle,ImageURL,lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        name = findViewById(R.id.seenewsletter_subject);
        details = findViewById(R.id.seenewsletter_message);
        headerImage = findViewById(R.id.seenewsletter_pic);
        showLocation = findViewById(R.id.openMaps);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            nameBundle =(String) b.get("name");
            detailsBundle = (String) b.get("description");
            ImageURL = (String) b.get("imageURl");
            lat = (String) b.get("lat");
            lon = (String) b.get("lon");

            name.setText(nameBundle);
            details.setText(detailsBundle);
            Glide.with(this).load(ImageURL).into(headerImage);
        }
        else
        {
            Toast.makeText(this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
        }


        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*     Uri gmmIntentUri = Uri.parse("google.navigation:q=31.434006,73.020467");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                */
                Toast.makeText(DetailLocation.this, "Working in progress!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
