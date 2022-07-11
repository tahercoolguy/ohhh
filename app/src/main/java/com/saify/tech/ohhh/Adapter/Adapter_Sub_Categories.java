package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.ProductsBysubcatIdDM;
import com.saify.tech.ohhh.Fragments.Category_Fragment;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adapter_Sub_Categories  extends RecyclerView.Adapter<Adapter_Sub_Categories.Programming_AdapterViewHolder>{

    Category_Fragment category_fragment;
    private Context context;
    private ArrayList<Data> feed_categoriesDMS;




    public Adapter_Sub_Categories(Context context, ArrayList<Data> feed_categoriesDMS,Category_Fragment category) {
        this.context = context;
        this.feed_categoriesDMS = feed_categoriesDMS;
        this.category_fragment=category;

    }


    @NonNull
    @Override
    public Adapter_Sub_Categories.Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_category, parent, false);
        return new Adapter_Sub_Categories.Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Sub_Categories.Programming_AdapterViewHolder holder, int position) {

        Data feed_categoriesDM = feed_categoriesDMS.get(position);


         if(feed_categoriesDM.getSub_category()!=null) {
             holder.category.setText(feed_categoriesDM.getSub_category().get(0).getTitle_en());
         }

             holder.LinearLL.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     category_fragment.setClickListeners(feed_categoriesDM.getId());

                 }
             });

    }

    @Override
    public int getItemCount() {
        return feed_categoriesDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {


        TextView category;

        LinearLayout LinearLL;
        RecyclerView recyclerView;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.category);
            LinearLL= itemView.findViewById(R.id.linearlayout);
            recyclerView=itemView.findViewById(R.id.sub_category_item_Rcv);
        }
    }


}
