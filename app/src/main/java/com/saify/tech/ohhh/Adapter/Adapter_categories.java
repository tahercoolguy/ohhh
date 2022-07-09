package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.Fragments.Category_Fragment;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_categories extends RecyclerView.Adapter<Adapter_categories.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Data> feed_categoriesDMS;

    Category_Fragment category_fragment;

    public Adapter_categories(Context context, ArrayList<Data> feed_categoriesDMS,Category_Fragment cat) {
        this.context = context;
        this.feed_categoriesDMS = feed_categoriesDMS;
        this.category_fragment=cat;
    }


    @NonNull
    @Override
    public Adapter_categories.Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_category, parent, false);
        return new Adapter_categories.Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_categories.Programming_AdapterViewHolder holder, int position) {

        Data feed_categoriesDM = feed_categoriesDMS.get(position);


        holder.category.setText(feed_categoriesDM.getTitle_en());



        holder.LinearLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    category_fragment.CategoryName(feed_categoriesDM.getTitle_en());

            }
        });
    }

    @Override
    public int getItemCount() {
        return feed_categoriesDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {


        TextView category;

        LinearLayout LinearLL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
           category = (TextView) itemView.findViewById(R.id.category);
            LinearLL= itemView.findViewById(R.id.linearlayout);
        }
    }

}
