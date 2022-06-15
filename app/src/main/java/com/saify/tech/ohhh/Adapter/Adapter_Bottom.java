package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.DataChangeDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Adapter_Bottom extends BaseAdapter {

    private Context context;
    private ArrayList<DataChangeDM> arrayList;
    private DataChangeDM selected;
    private int position;
    User user;

    public Adapter_Bottom(Context context, ArrayList<DataChangeDM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user=new User(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_item_for_bottom_sheet_list_with_image, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setDetails(viewHolder, position);
        return convertView;
    }


    private void setDetails(ViewHolder viewHolder, int position) {
        DataChangeDM data = arrayList.get(position);
        viewHolder.Name.setText(data.getName());
//          viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  viewHolder.checkBox.setVisibility(View.VISIBLE);
//
//              }
//          });
//        viewHolder.price.setText(data.getUnitPriceKWD());

//        if (selected==arrayList.get(position)) {
//            viewHolder.image.setVisibility(View.VISIBLE);
//                 }
//        else {
//            viewHolder.image.setVisibility(View.INVISIBLE);
//              }


    }

    public DataChangeDM getSelected() {
        return selected;
    }

    public void setSelected(DataChangeDM selected) {
        this.selected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private static class ViewHolder {
        private TextView Name;
        private ImageView image;


        private ViewHolder(View view) {
            Name= view.findViewById(R.id.areaTV);
           image= view.findViewById(R.id.image_check);

//            linearLayout=view.findViewById(R.id.linear1);
//            checkBox=view.findViewById(R.id.checkbox1);
//            price = view.findViewById(R.id.prizeTV);
        }
    }

}
