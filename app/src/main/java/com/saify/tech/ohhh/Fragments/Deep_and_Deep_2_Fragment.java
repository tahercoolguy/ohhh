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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.DND_Subcategory_Adapter;
import com.saify.tech.ohhh.Adapter.Deep_and_Deep_Cake_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.DND_Subcategory_DM;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;

public class Deep_and_Deep_2_Fragment extends Fragment {

    private View rootView;
    private Context context;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;



    @BindView(R.id.dip_and_dip_cake_1_Rcv)
    RecyclerView dip_and_dip_cake_1_Rcv;

    @BindView(R.id.sub_category_1_Rcv)
    RecyclerView sub_category_1_Rcv;

    @BindView(R.id.sub_category_2_Rcv)
    RecyclerView sub_category_2_Rcv;

    @BindView(R.id.back_dip_1RL)
    ImageView back;

    @BindView(R.id.cart_dip_1)
    RelativeLayout cart;


    @OnClick(R.id.back_dip_1RL)
    public void bacck() {

//        ((MainActivity)context).addFragment(Deep_and_Deep_2_Fragment.this,false);

//        startActivity(new Intent(getActivity(),Deep_and_Deep_1_Fragment.class));
    }


    @OnClick(R.id.cart_dip_1)
    public void Cart() {

        ((MainActivity)context).startActivity(new Intent(getActivity(), Cart_Activity.class));

    }


    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

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
            rootView = inflater.inflate(R.layout.deep_and_deep_2_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            idMapping();

            setClickListeners();
            setDetails();
        }
        return rootView;
    }

    private void idMapping() {

        ArrayList<Deep_and_Deep_CakeDM> deep_and_deep_cakeDMS = new ArrayList<>();

        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Cake",R.drawable.cake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Donat",R.drawable.cke_1));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Pastries",R.drawable.cupcake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Custards",R.drawable.pudding));


        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "baklava",R.drawable.baklava));

        Deep_and_Deep_Cake_Adapter dm = new Deep_and_Deep_Cake_Adapter(context, deep_and_deep_cakeDMS);

        LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        dip_and_dip_cake_1_Rcv.setLayoutManager(l);
        dip_and_dip_cake_1_Rcv.setAdapter(dm);
    }

    private void setClickListeners() {
        ArrayList<DND_Subcategory_DM> dnd_subcategory_dms = new ArrayList<>();

        dnd_subcategory_dms.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.feed_cake_1));
        dnd_subcategory_dms.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.feed_cake_1));
        dnd_subcategory_dms.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.feed_cake_1));

        DND_Subcategory_Adapter dm = new DND_Subcategory_Adapter(context, dnd_subcategory_dms);



        ArrayList<DND_Subcategory_DM> dnd_subcategory_dms2 = new ArrayList<>();

        dnd_subcategory_dms2.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.sub_catogry_2_cake_1));
        dnd_subcategory_dms2.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.sub_catogry_2_cake_2));
        dnd_subcategory_dms2.add(new DND_Subcategory_DM("Assortment of pieces","Pasties","10.000 KWD",R.drawable.sub_catogry_2_cake_3));

        DND_Subcategory_Adapter dm2 = new DND_Subcategory_Adapter(context, dnd_subcategory_dms2);

        LinearLayoutManager l = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        sub_category_1_Rcv.setLayoutManager(l);
        sub_category_1_Rcv.setAdapter(dm);

        LinearLayoutManager ll = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);

        sub_category_2_Rcv.setLayoutManager(ll);
        sub_category_2_Rcv.setAdapter(dm2);


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

    public void ShowProgress() {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress() {
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
