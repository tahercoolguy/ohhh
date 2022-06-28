package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryDM_Adapter extends RecyclerView.Adapter<HistoryDM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;

    public HistoryDM_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList= arrayList;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custom_item_history,parent,false);
        return new Programming_AdapterViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info data = arrayList.get(position);
        holder.Pastriess.setText(data.getProduct_name());
//        holder.product_ids.setText(data.getProduct_id());
        holder.pricekwds.setText(data.getProduct_price()+" "+"KWD");
        holder.count_products.setText(data.getProduct_qty()+" "+"Items");
//        holder.pastri_imgs.setImageResource(data.getPastri_img());

        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null; //YOUR DATE HERE
        try {
            date = df.parse(data.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.applyPattern("dd LLL, HH:mm");
        String newDate = df.format(date);


        holder.date.setText(newDate);




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
