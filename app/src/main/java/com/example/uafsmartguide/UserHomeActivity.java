package com.example.uafsmartguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<LocationAttributes> classes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        init();
        init_data();
        init_recyclerview();
    }



    void init()
    {
        recyclerView = findViewById(R.id.locationRecyclerView);
    }



    void init_data()
    {

        LocationAttributes a = new LocationAttributes("","","","Cafe","","https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Ffoodie.jpg?alt=media&token=3a2f809d-aaed-455d-b40e-4eb1d9ed046a");
        LocationAttributes b = new LocationAttributes("","","","Hostel","","https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Fhostel.jpg?alt=media&token=da5dc1b7-6211-412f-b196-9da0cf1a0a54");
        LocationAttributes c = new LocationAttributes("","","","Department","","https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Flogo.jpg?alt=media&token=843786d4-66f0-4384-994e-728eab3e5e88");
        LocationAttributes d = new LocationAttributes("","","","Mosque","","https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Fmosque.jpg?alt=media&token=6b398903-cb45-4aa7-8f11-063f23673908");
        LocationAttributes e = new LocationAttributes("","","","Lecture Theatre Room","","https://firebasestorage.googleapis.com/v0/b/uaf-smart-guide.appspot.com/o/FrontPics%2Fltr.jpg?alt=media&token=4c26038e-ec95-4e21-bee6-20f5eb7f58cf");
        //LocationAttributes f = new LocationAttributes("","","","Signout","","");




        classes.add(a);
        classes.add(b);
        classes.add(c);
        classes.add(d);
        classes.add(e);
       // classes.add(f);


//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://uaf-smart-guide.firebaseio.com/Locations/Cafe");
//
//        //Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                classes.clear();
//                for (DataSnapshot children : dataSnapshot.getChildren()) {
//                    LocationAttributes attr = children.getValue(LocationAttributes.class);
//                    classes.add(attr);
//                    //add you mediaItem to list that you provided
//                }
//                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
//                init_recyclerview();
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
    }

    private void init_recyclerview()
    {




//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutmanager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutmanager);
        RecyclerViewAdapterClasses adapter = new RecyclerViewAdapterClasses(classes,this);
        recyclerView.setAdapter(adapter);

        ////////////////
//        if(classes.size() == 0)
//        {
//            imageView.setVisibility(View.VISIBLE);
//            textView.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            imageView.setVisibility(View.GONE);
//            textView.setVisibility(View.GONE);
//
//        }


    }



}
