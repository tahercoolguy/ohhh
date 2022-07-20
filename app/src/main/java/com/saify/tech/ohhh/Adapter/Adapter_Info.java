package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Adapter_Info extends RecyclerView.Adapter<Adapter_Info .ViewHolder>{


    private Context context;
    private ArrayList<Info> arrayList;
    private Info selected;
    User user;


    public Adapter_Info (Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Info .ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_view_order_recycleview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Info .ViewHolder vh = new Adapter_Info .ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Info .ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Info .ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);
        Info data= arrayList.get(position);
        viewHolder.name.setText(data.getProduct_name());
        viewHolder.price.setText(data.getProduct_price());

    }

    public Info getSelected() {
        return selected;
    }

    public void setSelected(Info selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView size,name,price;
//        private ImageView companyIcon,img;

        public ViewHolder(View itemView) {
            super(itemView);
            size = (TextView) itemView.findViewById(R.id.size);
            name = (TextView) itemView.findViewById(R.id.name);
            price= (TextView) itemView.findViewById(R.id.priceTV);

            //
        }
    }


}
