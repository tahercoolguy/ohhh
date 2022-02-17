package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.CartDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<CartDM> cartDMS;

    public Cart_Adapter(Context context, ArrayList<CartDM> cartDMS) {
        this.context = context;
        this.cartDMS = cartDMS;
    }

    public Cart_Adapter(ArrayList<CartDM> cartDMS) {
    }

    public Cart_Adapter(MainActivity context, ArrayList<CartDM> cartDMS) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_item_cart, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        CartDM cartDM = cartDMS.get(position);

        holder.pastri.setText(cartDM.getPastries());
        holder.extra.setText(cartDM.getExtra_meyonese());
        holder.count0.setText(cartDM.getItem_count());
        holder.kd.setText(cartDM.getKwd());
        holder.pastryimg.setImageResource(R.drawable.ic_delivery_boy);
        holder.minus.setImageResource(cartDM.getCount_minus());
        holder.plus.setImageResource(cartDM.getCount_plus());
        holder.crosss.setImageResource(cartDM.getCart_cross());


    }

    @Override
    public int getItemCount() {
        return cartDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView pastri,extra,count0,kd;
         ImageView pastryimg,minus,plus,crosss;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            pastri = (TextView) itemView.findViewById(R.id.pastries_txt);
            extra = (TextView) itemView.findViewById(R.id.extra_mayonese);
            count0 = (TextView) itemView.findViewById(R.id.items_count_Txt);
            kd = (TextView) itemView.findViewById(R.id.price_Kwd_Txt);

            pastryimg = (ImageView) itemView.findViewById(R.id.pastries_Img);
             minus = (ImageView) itemView.findViewById(R.id.minus_product_img);
            plus = (ImageView) itemView.findViewById(R.id.plus_product_Img);
            crosss = (ImageView) itemView.findViewById(R.id.cancel_cart);


//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}
