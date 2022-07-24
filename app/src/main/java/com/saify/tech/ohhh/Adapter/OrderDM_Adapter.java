package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Activity.View_Order_Activity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddQuantityDM;
import com.saify.tech.ohhh.DataModel.CancelOrderDM;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.DataModel.Output;
import com.saify.tech.ohhh.DataModel.OutputMyOrder;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrderDM_Adapter extends RecyclerView.Adapter<OrderDM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<OutputMyOrder> arrayList;
    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    public OrderDM_Adapter(Context context, ArrayList<OutputMyOrder> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dialogUtil = new DialogUtil();
        user=new User(context);
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);
    }

    public OrderDM_Adapter(ArrayList<OutputMyOrder> arrayList) {
    }

    public OrderDM_Adapter(MainActivity context, ArrayList<OutputMyOrder> arrayList) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_item_ongoing, parent, false);
        return new Programming_AdapterViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderDM_Adapter.Programming_AdapterViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private void setDetails(OrderDM_Adapter.Programming_AdapterViewHolder holder, int position) {

        OutputMyOrder data = arrayList.get(position);
        if(data.getInfo()!=null) {
            holder.Pastriess.setText(data.getInfo().get(0).getProduct_name());
//        holder.product_ids.setText(data.getProduct_id());
            holder.pricekwds.setText(data.getInfo().get(0).getProduct_price()+" KD");
            holder.count_products.setText(data.getInfo().get(0).getProduct_qty());
//        holder.pastri_imgs.setImageResource(R.drawable.pastries_1);
            Picasso.with(context).load(data.getInfo().get(0).getImage()).into(holder.pastri_imgs);
        }
        holder.order_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.CancelOrder(String.valueOf(user.getId()),data.getSale_code(), new Callback<CancelOrderDM>() {
                            @Override
                            public void success(CancelOrderDM cancelOrderDM, Response response) {
//                        progress.dismiss();
                                if (cancelOrderDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    arrayList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyDataSetChanged();
                                    Helper.showToast(context,cancelOrderDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, "item does not Cancel");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.e("String", error.toString());
                            }
                        });
                    }
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));

            }
        });

        holder.view_order_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),View_Order_Activity.class);
                if(data.getSale_code()!=null) {
                    myIntent.putExtra("Sale_Code", data.getSale_code());
                }
                view.getContext().startActivity(myIntent);
               }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
