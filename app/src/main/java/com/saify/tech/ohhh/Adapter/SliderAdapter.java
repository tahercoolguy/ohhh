package com.saify.tech.ohhh.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.makeramen.roundedimageview.RoundedImageView;

import com.saify.tech.ohhh.DataModel.Adv;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
//    private final List<SliderData> mSliderItems;
     private ArrayList<Adv> arrayList;
    private Info selected;
    Context context;

    // Constructor
    public SliderAdapter(Context context, ArrayList<Adv> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {

        Adv data= arrayList.get(position);

        viewHolder.text1.setText(data.getTitle_en());
        if(data.getImage()!=null && !data.getImage().equalsIgnoreCase("")){
            Picasso.with(context).load(data.getImage()).into(viewHolder.imageViewBackground);}
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return arrayList.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        TextView text1;

        RoundedImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            text1 = itemView.findViewById(R.id.text1);


            this.itemView = itemView;
        }
    }
}
