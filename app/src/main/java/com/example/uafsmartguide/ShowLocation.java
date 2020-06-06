package com.example.uafsmartguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowLocation extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<LocationAttributes> classes = new ArrayList<>();

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        recyclerView = findViewById(R.id.show_location_id);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            type =(String) b.get("subjectname");
            init_data();
        }
        else
        {

        }
    }

    void init_data(){


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://uaf-smart-guide.firebaseio.com/Locations/" + type);
        //Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                classes.clear();
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    LocationAttributes attr = children.getValue(LocationAttributes.class);
                    classes.add(attr);
                    //add you mediaItem to list that you provided
                }
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
                init_recyclerview();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });

    }

    void init_recyclerview()
    {


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterClasses adapter = new RecyclerViewAdapterClasses(classes,this);
        recyclerView.setAdapter(adapter);
    }
}
