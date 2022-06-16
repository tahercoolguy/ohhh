package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.SavedAddressDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Saved_Address_DM_Adapter extends RecyclerView.Adapter<Saved_Address_DM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;

    public Saved_Address_DM_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custum_saved_addres,parent,false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info data= arrayList.get(position);
        holder.address.setText(data.getStreet()+","+data.getFloor_no()+","+data.getAreaname()+","+data.getGovernatesname());
//        holder.home.setText(savedAddressDM.getHome());
//        holder.home_img.setImageResource(savedAddressDM.getHome_img());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder{

        TextView address,home;
         ImageView home_img;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.address_txt);
            home=(TextView) itemView.findViewById(R.id.home_txt);
            home_img=(ImageView) itemView.findViewById(R.id.home_img);

        }
    }
}
