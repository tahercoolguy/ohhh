package com.saify.tech.ohhh.Adapter;



import android.app.Activity;
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.DataModel.Feed_Categories_Adapter22;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.info111;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Feed_Categories_Adapter11 extends RecyclerView.Adapter<Feed_Categories_Adapter11.ViewHolder> {

    private Context context;
    private ArrayList<Data> arrayList;
    User user;


    public Feed_Categories_Adapter11(Context context, ArrayList<Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user=new User(context);
    }


    View v;
    @NonNull
    @Override
    public Feed_Categories_Adapter11.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_for_title, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Feed_Categories_Adapter11.ViewHolder vh = new Feed_Categories_Adapter11.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Feed_Categories_Adapter11.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    private void setDetails(Feed_Categories_Adapter11.ViewHolder viewHolder, int position) {


        Data data = arrayList.get(position);


//         Picasso.with(context).load(data.getItems().get(0).getImage()).into(viewHolder.feed_category_img);
       if(data!=null) {
//        viewHolder.pricekwds.setText(data.get);
           viewHolder.text.setText(data.getTitle_en());
//        viewHolder.pastriess.setText(data.getShop_name_en());

           Feed_Categories_Adapter22 dm2 = new Feed_Categories_Adapter22(context, data.getItems());
           viewHolder.sub_category_2_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
           viewHolder.sub_category_2_Rcv.setAdapter(dm2);
       }
//
//        viewHolder.feed_cakeLinearLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                Intent intent=new Intent(context, Food_Details_Activity.class);
//                intent.putExtra("id",data.getId());
//                context.startActivity(intent);
//
//    }
//        });
    }


    public static class ViewHolder   extends RecyclerView.ViewHolder{
//        RoundedImageView feed_category_img;

        TextView text;
        RecyclerView sub_category_2_Rcv;
//        LinearLayout feed_cakeLinearLL;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            pastriess = (TextView) itemView.findViewById(R.id.pastries_txt);
//            pricekwds = (TextView) itemView.findViewById(R.id.price_kwd_Txt);
            text = (TextView) itemView.findViewById(R.id.subCategoryTitle);
            sub_category_2_Rcv=itemView.findViewById(R.id.sub_category_2_Rcv);
//            feed_category_img=itemView.findViewById(R.id.feed_category_img);
//            feed_cakeLinearLL=itemView.findViewById(R.id.feed_cakeLinearLL);
        }
    }
}
