package com.saify.tech.ohhh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.DataModel.Category;
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Deep_and_Deep_Cake_Adapter extends RecyclerView.Adapter<Deep_and_Deep_Cake_Adapter.Programming_AdapterViewHolder> {


    private Context context;
    private ArrayList<Category> arrayList;
    int row_index = 0;


    Deep_and_Deep_1_Fragment deep_and_deep_1_fragment;

    public Deep_and_Deep_Cake_Adapter(Context context, ArrayList<Category> arraylist, Deep_and_Deep_1_Fragment deep) {
        this.context = context;
        this.arrayList =arraylist;
        this.deep_and_deep_1_fragment  = deep;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_deep_and_deep_cake_item, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Category data= arrayList.get(position);
//
//        if(!data..equalsIgnoreCase(""))
//        {
//            Picasso.with(context).load(data.getImage()).into(holder.cakeImg);
//        }

//              holder.cakeImg.setImageResource(deep_and_deep_cakeDM.getCake_img());

         holder.cakename.setText(data.getCategory_name());


        holder.linearLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                deep_and_deep_1_fragment.setClickListeners(data.getCategory_id());
                deep_and_deep_1_fragment.SetTittle(data.getCategory_name());
//                deep_and_deep_1_fragment.
                notifyDataSetChanged();


            }
        });

        if (row_index == position) {
            holder.cakename.setTextColor(Color.parseColor("#FFFFFFFF"));
            holder.cake_deep_and_deepRL.setBackgroundResource(R.drawable.rounded_corner_deep_deep_cake);


        }else {
            holder.cakename.setTextColor(Color.parseColor("#E67826"));

            holder.cake_deep_and_deepRL.setBackgroundResource(R.drawable.rounded_corner_deep_and_deep_white);
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView cakename;
        ImageView cakeImg;
        RelativeLayout cake_deep_and_deepRL;
        LinearLayout linearLL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            cakename = (TextView) itemView.findViewById(R.id.cake_name_deep_Txt);
            cakeImg = itemView.findViewById(R.id.cake_deep_Img);
            cake_deep_and_deepRL = itemView.findViewById(R.id.cake_deep_and_deepRL);
            linearLL = itemView.findViewById(R.id.linearLLll);
        }
    }
}
