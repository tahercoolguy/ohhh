package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Activity.View_Order_Activity;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class OrderDM_Adapter extends RecyclerView.Adapter<OrderDM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<OrderDM> orderDMS;

    public OrderDM_Adapter(Context context, ArrayList<OrderDM> orderDMS) {
        this.context = context;
        this.orderDMS = orderDMS;
    }

    public OrderDM_Adapter(ArrayList<OrderDM> orderDMS) {
    }

    public OrderDM_Adapter(MainActivity context, ArrayList<HistoryDM> historyDMS) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_item_ongoing, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        OrderDM orderDM = orderDMS.get(position);

        holder.Pastriess.setText(orderDM.getPastries());
        holder.product_ids.setText(orderDM.getProduct_id());
        holder.pricekwds.setText(orderDM.getPricekwd());
        holder.count_products.setText(orderDM.getCount_product());
        holder.pastri_imgs.setImageResource(R.drawable.pastries_1);

        holder.order_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.view_order_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),View_Order_Activity.class);
//                myIntent.putExtra("intVariableName", eventsList.get(position).getEvent_id());
                view.getContext().startActivity(myIntent);
               }
        });


    }

    @Override
    public int getItemCount() {
        return orderDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView Pastriess;
        TextView product_ids;
        TextView pricekwds;
        TextView count_products;
        ImageView pastri_imgs;
        TextView view_order_Btn,order_cancel_btn;



        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            Pastriess = (TextView) itemView.findViewById(R.id.pastries_txt);
            product_ids = (TextView) itemView.findViewById(R.id.code_pastries);
            pricekwds = (TextView) itemView.findViewById(R.id.price_Kwd_Txt);
            count_products = (TextView) itemView.findViewById(R.id.items_count_Txt);
            pastri_imgs = (ImageView) itemView.findViewById(R.id.pastries_Img);
            view_order_Btn=itemView.findViewById(R.id.view_order_Btn);
            order_cancel_btn=itemView.findViewById(R.id.order_cancel_btn);
        }
    }
}
