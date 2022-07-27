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
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter_Rate2 extends RecyclerView.Adapter<Adapter_Rate2.ViewHolder> {


    private Context context;
    private ArrayList<Data> arrayList;
    private Info selected;
    User user;


    int selectedPosition = 0;

    public Adapter_Rate2(Context context, ArrayList<Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Rate2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_rate2, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Rate2.ViewHolder vh = new Adapter_Rate2.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Rate2.ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Rate2.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);
        Data data = arrayList.get(position);
        if(data.getName()!=null){
        viewHolder.name.setText(data.getName());}
//        viewHolder.Datetime.setText(data.getCreated_date());
        viewHolder.rating_starTxt.setText(data.getProduct_rating());
        if(data.getComments()!=null){
        viewHolder.comment.setText(data.getComments());}


        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sf.parse(data.getCreated_date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sf.applyPattern("dd/MM/yyyy");
        String newDate=sf.format(date);
        viewHolder.Datetime.setText(newDate);


    }

    public Info getSelected() {
        return selected;
    }

    public void setSelected(Info selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,Datetime, rating_starTxt, comment;
        private ImageView star;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameTV);
            Datetime= (TextView) itemView.findViewById(R.id.DateandTimeTV);
            rating_starTxt = (TextView) itemView.findViewById(R.id.rating_starTxt);
            comment = (TextView) itemView.findViewById(R.id.CommentTV);

        }
    }


}
