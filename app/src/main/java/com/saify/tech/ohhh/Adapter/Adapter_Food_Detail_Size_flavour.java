package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.DataModel.Items;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.ParentChildDataModel;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Adapter_Food_Detail_Size_flavour extends RecyclerView.Adapter<Adapter_Food_Detail_Size_flavour.ViewHolder>{


    private Context context;
    private ArrayList<Items> arrayList;
    private Items selected;
    User user;



    int selectedPosition = 0;

    public Adapter_Food_Detail_Size_flavour(Context context, ArrayList<Items> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Food_Detail_Size_flavour.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_size_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Food_Detail_Size_flavour.ViewHolder vh = new Adapter_Food_Detail_Size_flavour.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Food_Detail_Size_flavour.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }


    private void setDetails(Adapter_Food_Detail_Size_flavour.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);

        Items data=arrayList.get(position);
        if(data!=null ) {
            viewHolder.size.setText(data.getOption_value_name());
            viewHolder.price.setText(data.getOption_price());
            viewHolder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        ((Food_Details_Activity) context).parentChildDataModels.add(new ParentChildDataModel(arrayList.get(position).getItem_choice_group_id(),arrayList.get(position).getId()));
                    }else
                    {
                        for (ParentChildDataModel pa:((Food_Details_Activity) context).parentChildDataModels
                             ) {
                            if(pa.getChild() == arrayList.get(position).getId() && pa.getParent() == arrayList.get(position).getItem_choice_group_id())
                            {
                                ((Food_Details_Activity) context).parentChildDataModels.remove(pa);
                            }
                        }
                    }
                }
            });


        } else  {
            viewHolder.radioButton.setVisibility(View.GONE);
            }



    }

    public Items getSelected() {
        return selected;
    }

    public void setSelected(Items selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView size,price;
        private CheckBox radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            radioButton =   itemView.findViewById(R.id.radioButton);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);



        }
    }


}
