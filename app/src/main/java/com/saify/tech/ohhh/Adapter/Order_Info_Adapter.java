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
import com.saify.tech.ohhh.DataModel.OrderInfoDM;
import com.saify.tech.ohhh.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Order_Info_Adapter extends RecyclerView.Adapter<Order_Info_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<OrderInfoDM> orderInfoDMS;

    public Order_Info_Adapter(Context context, ArrayList<OrderInfoDM> orderInfoDMS) {
        this.context = context;
        this.orderInfoDMS = orderInfoDMS;
    }

    public Order_Info_Adapter(ArrayList<OrderInfoDM> orderInfoDMS) {
    }

    public Order_Info_Adapter(MainActivity context, ArrayList<OrderInfoDM> orderInfoDMS) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_order_info, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        OrderInfoDM orderInfoDM = orderInfoDMS.get(position);

        holder.aa.setText(orderInfoDM.getA());
        holder.bb.setText(orderInfoDM.getB());
    }

    @Override
    public int getItemCount() {
        return orderInfoDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

       TextView aa, bb;

        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            aa = (TextView) itemView.findViewById(R.id.subtotal_a_Txt);
            bb = (TextView) itemView.findViewById(R.id.price_b_Txt);


//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}
