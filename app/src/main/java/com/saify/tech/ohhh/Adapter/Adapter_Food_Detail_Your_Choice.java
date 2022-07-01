package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.Addons;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Adapter_Food_Detail_Your_Choice extends RecyclerView.Adapter<Adapter_Food_Detail_Your_Choice.ViewHolder>{


    private Context context;
    private ArrayList<Addons> arrayList;
    private Addons selected;
    User user;



    int selectedPosition = 0;

    public Adapter_Food_Detail_Your_Choice(Context context, ArrayList<Addons> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Food_Detail_Your_Choice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_your_choice_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Food_Detail_Your_Choice.ViewHolder vh = new Adapter_Food_Detail_Your_Choice.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Food_Detail_Your_Choice.ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Food_Detail_Your_Choice.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);

        Addons data=arrayList.get(position);


            viewHolder.yourChoice.setText(data.getOption_group_name());
//        viewHolder.price.setText(data.getPrice());
        if(data.getItems()!=null) {
            Adapter_Food_Detail_Size_flavour dm = new Adapter_Food_Detail_Size_flavour(context, data.getItems());
            LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            viewHolder.SizeAndFlaverRecyclerView.setLayoutManager(l);
            viewHolder.SizeAndFlaverRecyclerView.setAdapter(dm);
        }

    }

    public Addons getSelected() {
        return selected;
    }

    public void setSelected(Addons selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView yourChoice;
        private RecyclerView SizeAndFlaverRecyclerView;


        public ViewHolder(View itemView) {
            super(itemView);

            yourChoice= itemView.findViewById(R.id.your_choiceTV);
            SizeAndFlaverRecyclerView = itemView.findViewById(R.id.sizeAndFlaver);



        }
    }



}
