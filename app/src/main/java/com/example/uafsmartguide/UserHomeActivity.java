package com.example.uafsmartguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

    }

    private void init_recyclerview()
    {




        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
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
