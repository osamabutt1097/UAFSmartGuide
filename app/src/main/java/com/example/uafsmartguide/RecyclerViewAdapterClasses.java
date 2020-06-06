package com.example.uafsmartguide;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uafsmartguide.LocationAttributes;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterClasses extends RecyclerView.Adapter<RecyclerViewAdapterClasses.ViewHolder>{



    ArrayList<LocationAttributes> attr = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapterClasses(Context mContext)
    {
        this.mContext = mContext;

    }


    public RecyclerViewAdapterClasses(ArrayList<LocationAttributes> attr, Context mContext) {

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


                Toast.makeText(mContext, attr.get(position).getName(), Toast.LENGTH_SHORT).show();

                /////                       /////////////

                Context context = view.getContext();
                Intent intent = new Intent(context, ShowLocation.class);
                intent.putExtra("subjectname",attr.get(position).getName());
                context.startActivity(intent);
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
