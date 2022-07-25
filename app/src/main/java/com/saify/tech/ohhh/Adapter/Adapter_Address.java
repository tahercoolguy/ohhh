package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.SaveAddressActivity;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.ShopsResult;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Address extends RecyclerView.Adapter<Adapter_Address.ViewHolder>{


    private Context context;
    private ArrayList<Info> arrayList;
    private Info selected;
    User user;



    int selectedPosition = 0;

    public Adapter_Address(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Address.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Address.ViewHolder vh = new Adapter_Address.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Address.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    private void setDetails(Adapter_Address.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);
        Info data= arrayList.get(position);
        viewHolder.address.setText(data.getGovernate()+","+data.getArea()+","+data.getBlock()+","+data.getStreet()+","+data.getBuilding_no()+","+data.getFloor_no()+","+data.getGovernatesname());

        viewHolder.change_Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SaveAddressActivity.class);
                intent.putExtra("name", data.getName());
                intent.putExtra("email", data.getEmail());
                intent.putExtra("code", data.getCountrycode());
                intent.putExtra("mobileNo", data.getMobile_no());
                intent.putExtra("governate", data.getGovernate());
                intent.putExtra("area", data.getArea());
                intent.putExtra("building", data.getBuilding_no());
                intent.putExtra("block", data.getBlock());
                intent.putExtra("street", data.getStreet());
                intent.putExtra("floor",data.getFloor_no());
                intent.putExtra("AddressId",data.getId());
                 context.startActivity(intent);            }
        });
    }

    public Info getSelected() {
        return selected;
    }

    public void setSelected(Info selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView address,change_Txt;
//        private ImageView companyIcon,img;

        public ViewHolder(View itemView) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.address_text);
            change_Txt = (TextView) itemView.findViewById(R.id.change_Txt);

            //
        }
    }



}
