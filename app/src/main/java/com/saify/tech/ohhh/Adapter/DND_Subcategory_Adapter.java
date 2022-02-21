package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.DND_Subcategory_DM;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class DND_Subcategory_Adapter extends RecyclerView.Adapter<DND_Subcategory_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<DND_Subcategory_DM> dnd_subcategory_dms;

    public DND_Subcategory_Adapter(Context context, ArrayList<DND_Subcategory_DM> dnd_subcategory_dms) {
        this.context = context;
        this.dnd_subcategory_dms = dnd_subcategory_dms;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_subcategory_item, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        DND_Subcategory_DM dnd_subcategory_dm = dnd_subcategory_dms.get(position);

        holder.img.setImageResource(dnd_subcategory_dm.getImgcake());
        holder.heading1.setText(dnd_subcategory_dm.getHeading1());
        holder.heading2.setText(dnd_subcategory_dm.getHeading2());
        holder.price.setText(dnd_subcategory_dm.getHeading3());


        holder.subcategory_item_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).startActivity(new Intent(context,Food_Details_Activity.class));

            }
        });


    }

    @Override
    public int getItemCount() {
        return dnd_subcategory_dms.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView heading1;
        TextView heading2;
        TextView price;
        ImageView img;
        LinearLayout subcategory_item_LL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            heading1 = (TextView) itemView.findViewById(R.id.pieces_Txt);
            heading2 = (TextView) itemView.findViewById(R.id.pastries_txt);
            price = (TextView) itemView.findViewById(R.id.price_kwd_Txt);
            img = itemView.findViewById(R.id.cake_Img);
            subcategory_item_LL=itemView.findViewById(R.id.subcategory_item_LL);
        }
    }
}
