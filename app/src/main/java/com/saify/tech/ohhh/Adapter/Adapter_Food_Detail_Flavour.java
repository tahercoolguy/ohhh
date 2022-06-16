package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.Size;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Adapter_Food_Detail_Flavour extends RecyclerView.Adapter<Adapter_Food_Detail_Flavour.ViewHolder> {


    private Context context;
    private ArrayList<Info> arrayList;
    private Size selected;
    User user;



    int selectedPosition = 0;

    public Adapter_Food_Detail_Flavour(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Food_Detail_Flavour.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_size_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Food_Detail_Flavour.ViewHolder vh = new Adapter_Food_Detail_Flavour.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Food_Detail_Flavour.ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Food_Detail_Flavour.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);

         Info data=arrayList.get(position);
        if(data!=null ) {
            viewHolder.size.setText(data.getFlavour());
//        viewHolder.price.setText(data.getPrice());
             }
        else{
            viewHolder.radioButton.setVisibility(View.GONE);
               }
    }

    public Size getSelected() {
        return selected;
    }

    public void setSelected(Size selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView size,price;
        private RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            radioButton =   itemView.findViewById(R.id.radioButton);
            size = itemView.findViewById(R.id.size);
//            price = itemView.findViewById(R.id.price);



        }
    }


}
