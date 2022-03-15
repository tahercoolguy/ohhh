package com.saify.tech.ohhh.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Shopss_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;

public class Fragment_Shops extends Fragment {

    private View rootView;
    private Context context;

    @NotEmpty
    @BindView(R.id.shopss_Rcv)
    RecyclerView Shopss_Rcv;

    @NotEmpty
    @BindView(R.id.cart_Img)
    ImageView cart;



    @OnClick(R.id.cart_Img)
    public void Cart() {

        ((MainActivity)context).startActivity(new Intent(getActivity(), Cart_Activity.class));

    }



    @BindView(R.id.progress_bar) ProgressBar progress_bar;
    @BindView(R.id.txt_error) TextView txt_error;

    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.shops_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();

            setClickListeners();
            setDetails();
        }
        return rootView;
    }

    private void idMapping() {


        ArrayList<ShopssDM> shopssDMS = new ArrayList<>();

        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
                "10-15 mins","","",R.drawable.ic_delivery_boy,
                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));

        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
                "10-15 mins","","",R.drawable.ic_delivery_boy,
                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));

        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
                "10-15 mins","","",R.drawable.ic_delivery_boy,
                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));

        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
                "10-15 mins","","",R.drawable.ic_delivery_boy,
                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));




        Shopss_Adapter dm = new Shopss_Adapter(context, shopssDMS);
        LinearLayoutManager l = new LinearLayoutManager(context);
        Shopss_Rcv.setLayoutManager(l);
        Shopss_Rcv.setAdapter(dm);


    }




    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setDetails() {
       ShowProgress();
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
               DismissProgress();
            }
        }, 100);




    }

    public void ShowProgress()
    {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress()
    {
        progress_bar.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_back).setVisible(false);
    }
}
