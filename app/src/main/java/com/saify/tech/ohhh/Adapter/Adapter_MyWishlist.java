package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.InfoMyWIshlist;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_MyWishlist extends RecyclerView.Adapter<Adapter_MyWishlist.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<InfoMyWIshlist> arrayList;

    public Adapter_MyWishlist(Context context, ArrayList<InfoMyWIshlist> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Adapter_MyWishlist.Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_mywishlist, parent, false);
        return new Adapter_MyWishlist.Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_MyWishlist.Programming_AdapterViewHolder holder, int position) {
        InfoMyWIshlist info= arrayList.get(position);


        if(!info.getImage().equalsIgnoreCase(""))
        {
            Picasso.with(context).load(info.getImage()).into(holder.img);
        }

        holder.text.setText(info.getShop_name());

//        holder.time_img.setImageResource(R.drawable.ic_time);

//        holder.shop_cake_Rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ((MainActivity)context).addFragment(new Deep_and_Deep_1_Fragment(),false);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        ImageView img;

        RelativeLayout heart;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text1);
            img  = (ImageView) itemView.findViewById(R.id.img1);
            heart = (RelativeLayout) itemView.findViewById(R.id.heart1);


        }
    }


}
