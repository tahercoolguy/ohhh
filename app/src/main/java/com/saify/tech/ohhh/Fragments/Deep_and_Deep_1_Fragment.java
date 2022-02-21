package com.saify.tech.ohhh.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Deep_and_Deep_Cake_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Adapter.Offers_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Deep_and_Deep_1_Fragment extends Fragment {

    private View rootView;
    private Context context;

    @BindView(R.id.progress_bar) ProgressBar progress_bar;
    @BindView(R.id.txt_error) TextView txt_error;




    @BindView(R.id.dip_and_dip_cake_1_Rcv)
    RecyclerView dip_and_dip_cake_1_Rcv;

    @BindView(R.id.sub_category_1_Rcv)
    RecyclerView sub_category_1_Rcv;

    @BindView(R.id.sub_category_2_Rcv)
    RecyclerView sub_category_2_Rcv;

    @BindView(R.id.back_dip_1RL)
    RelativeLayout back;

    @BindView(R.id.cart_dip_1)
    RelativeLayout cart;

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
            rootView = inflater.inflate(R.layout.deep_and_deep_1_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();

            Subcaegry_2();

            setClickListeners();
            setDetails();
        }
        return rootView;
    }

    private void Subcaegry_2() {

        ArrayList<Feed_CategoriesDM> feed_categoriesDMS2 = new ArrayList<>();
        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_1));
        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_2));
        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_3));
        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_4));

        Feed_Categories_Adapter dm2 = new Feed_Categories_Adapter(context, feed_categoriesDMS2);
        sub_category_2_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        sub_category_2_Rcv.setAdapter(dm2);
    }

    private void idMapping() {

        ArrayList<Deep_and_Deep_CakeDM> deep_and_deep_cakeDMS = new ArrayList<>();

        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Cake",R.drawable.cake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Donat",R.drawable.cke_1));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Pastries",R.drawable.cupcake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Custards",R.drawable.pudding));
         deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "kunafa",R.drawable.kunafa));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "baklava",R.drawable.baklava));

        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Cake",R.drawable.cake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Donat",R.drawable.cke_1));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Pastries",R.drawable.cupcake));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Custards",R.drawable.pudding));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "kunafa",R.drawable.kunafa));
        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "baklava",R.drawable.baklava));

        Deep_and_Deep_Cake_Adapter dm = new Deep_and_Deep_Cake_Adapter(context, deep_and_deep_cakeDMS);

        LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        dip_and_dip_cake_1_Rcv.setLayoutManager(l);
        dip_and_dip_cake_1_Rcv.setAdapter(dm);

    }

    private void setClickListeners() {
        ArrayList<Feed_CategoriesDM> feed_categoriesDMS = new ArrayList<>();
        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_category_cake_1));
        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_2));
        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_3));
        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_4));

        Feed_Categories_Adapter dm = new Feed_Categories_Adapter(context, feed_categoriesDMS);
        sub_category_1_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        sub_category_1_Rcv.setAdapter(dm);

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
        }, 1500);




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