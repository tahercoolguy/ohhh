package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class HistoryDM_Adapter extends RecyclerView.Adapter<HistoryDM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<HistoryDM> historyDMS;

    public HistoryDM_Adapter(Context context, ArrayList<HistoryDM> historyDMS) {
        this.context = context;
        this.historyDMS = historyDMS;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custom_item_history,parent,false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        HistoryDM historyDM = historyDMS.get(position);
        holder.Pastriess.setText(historyDM.getPastries());
        holder.product_ids.setText(historyDM.getProduct_id());
        holder.pricekwds.setText(historyDM.getPricekwd());
        holder.count_products.setText(historyDM.getCount_product());
        holder.pastri_imgs.setImageResource(historyDM.getPastri_img());
        holder.date.setText(historyDM.getDate());



    }

    @Override
    public int getItemCount() {
        return historyDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder{

        TextView Pastriess;
        TextView product_ids;
        TextView pricekwds;
        TextView count_products;
        TextView date;
        ImageView pastri_imgs;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            Pastriess = (TextView) itemView.findViewById(R.id.pastries_txt);
            product_ids=(TextView) itemView.findViewById(R.id.code_pastries);
            pricekwds = (TextView) itemView.findViewById(R.id.price_Kwd_Txt);
            count_products = (TextView) itemView.findViewById(R.id.items_count_Txt);
            pastri_imgs = (ImageView) itemView.findViewById(R.id.pastries_Img);
            date = (TextView) itemView.findViewById(R.id.dtm_Txt);
         }
    }
}
