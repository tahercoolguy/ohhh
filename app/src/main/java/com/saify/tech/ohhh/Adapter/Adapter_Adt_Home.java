package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.DataModel.Adv;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_Adt_Home extends RecyclerView.Adapter<Adapter_Adt_Home.ViewHolder>{


    private Context context;
    private ArrayList<Adv> arrayList;
    private Info selected;
    User user;



    int selectedPosition = 0;

    public Adapter_Adt_Home(Context context, ArrayList<Adv> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Adt_Home.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusom_item_reccycleview_adt_home, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Adt_Home.ViewHolder vh = new Adapter_Adt_Home.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Adt_Home.ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Adt_Home.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
//        viewHolder.companyName.setText(arrayList.get(position).getShop_name());
//        viewHolder.status.setText(arrayList.get(position).getStatus());
//        Picasso.with(context).load(arrayList.get(position).getAttachment()[0]).into(viewHolder.companyIcon);
        Adv data= arrayList.get(position);

        viewHolder.name.setText(data.getTitle_en());
        if(data.getImage()!=null && !data.getImage().equalsIgnoreCase("")){
        Picasso.with(context).load(data.getImage()).into(viewHolder.Image);}

    }

    public Info getSelected() {
        return selected;
    }

    public void setSelected(Info selected) {
        this.selected = selected;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       ImageView Image;
        TextView name;
//        private ImageView companyIcon,img;

        public ViewHolder(View itemView) {
            super(itemView);
           Image = (ImageView)  itemView.findViewById(R.id.image1);
           name= (TextView) itemView.findViewById(R.id.text1);

            //
        }
    }

}
