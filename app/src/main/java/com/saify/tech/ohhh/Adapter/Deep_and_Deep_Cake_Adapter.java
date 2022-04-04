package com.saify.tech.ohhh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Deep_and_Deep_Cake_Adapter extends RecyclerView.Adapter<Deep_and_Deep_Cake_Adapter.Programming_AdapterViewHolder> {


    private Context context;
    private ArrayList<Deep_and_Deep_CakeDM> deep_and_deep_cakeDMS;
    int row_index = 0;

    public Deep_and_Deep_Cake_Adapter(Context context, ArrayList<Deep_and_Deep_CakeDM> deep_and_deep_cakeDMS) {
        this.context = context;
        this.deep_and_deep_cakeDMS = deep_and_deep_cakeDMS;
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

        Deep_and_Deep_CakeDM deep_and_deep_cakeDM = deep_and_deep_cakeDMS.get(position);

        holder.cakeImg.setImageResource(deep_and_deep_cakeDM.getCake_img());
        holder.cakename.setText(deep_and_deep_cakeDM.getCake_name());

        holder.linearLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
//                if (position == 0) {
//
//                    holder.cakename.setTextColor(Color.parseColor("#FFFFFFFF"));
//                    holder.cake_deep_and_deepRL.setBackgroundResource(R.drawable.rounded_corner_deep_deep_cake);
//                } else if(position==1){
//                    holder.cakename.setTextColor(Color.parseColor("#FFFFFFFF"));
//                    holder.cake_deep_and_deepRL.setBackgroundResource(R.drawable.rounded_corner_deep_deep_cake);
//                }
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
        return deep_and_deep_cakeDMS.size();
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
            linearLL = itemView.findViewById(R.id.linearLL);
        }
    }
}
