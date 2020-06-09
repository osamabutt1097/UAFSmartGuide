package com.example.uafsmartguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterLocation extends RecyclerView.Adapter<RecyclerViewAdapterLocation.ViewHolder>{



    ArrayList<LocationAttributes> attr = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapterLocation(Context mContext)
    {
        this.mContext = mContext;

    }


    public RecyclerViewAdapterLocation(ArrayList<LocationAttributes> attr, Context mContext) {

        this.mContext = mContext;
        this.attr = attr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_cardslayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        holder.classname.setText(attr.get(position).getName());

        Glide.with(mContext).load(attr.get(position).getUrl()).into(holder.imageView);
        holder.cardView.setClickable(true);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //////                   /////////////////

                /*Click Listner for the card i.e clicked*/


                Context context = view.getContext();
                Intent intent = new Intent(context, DetailLocation.class);
                intent.putExtra("name",attr.get(position).getName());
                intent.putExtra("description",attr.get(position).getDesc());
                intent.putExtra("imageURl",attr.get(position).getUrl());
                intent.putExtra("lat",attr.get(position).getLat());
                intent.putExtra("lon",attr.get(position).getLon());
                context.startActivity(intent);



                Toast.makeText(mContext, attr.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return attr.size();
    }

    public class   ViewHolder extends RecyclerView.ViewHolder{
        TextView classname;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.class_img);
            classname = itemView.findViewById(R.id.class_name_card);
            cardView = itemView.findViewById(R.id.classcard);
        }
    }

}
