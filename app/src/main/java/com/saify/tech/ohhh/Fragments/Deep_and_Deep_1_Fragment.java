package com.saify.tech.ohhh.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
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
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Deep_and_Deep_Cake_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter11;
import com.saify.tech.ohhh.Adapter.Offers_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.Deep_and_Deep_CakeDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.DataModel.ShopsBycatIdDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Deep_and_Deep_1_Fragment extends Fragment {

    private View rootView;
    private Context context;
    Intent intent;

    @BindView(R.id.progress_bar) ProgressBar progress_bar;
    @BindView(R.id.txt_error) TextView txt_error;





    @BindView(R.id.dip_and_dip_cake_1_Rcv)
    RecyclerView dip_and_dip_cake_1_Rcv;

    @BindView(R.id.sub_category_1_Rcv)
    RecyclerView sub_category_1_Rcv;

//    @BindView(R.id.sub_category_2_Rcv)
//    RecyclerView sub_category_2_Rcv;

    @BindView(R.id.back_dip_1RL)
    RelativeLayout back;

    @BindView(R.id.subCategory)
    TextView subCategory;


    @BindView(R.id.cart_dip_1)
    RelativeLayout cart;

    @OnClick(R.id.back_dip_1RL)
    public void bacck() {
        ((MainActivity)context).addFragment(new Fragment_Home_Screen(),false);
      }

    @OnClick(R.id.cart_dip_1)
    public void Cart() {

        ((MainActivity)context).startActivity(new Intent(getActivity(), Cart_Activity.class));

    }

    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    Dialog progress;
    DialogUtil dialogUtil;



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
//           subCategory.setText(Tittle);

            idMapping();

//            Subcaegry_2();

            setDetails();
        }
        return rootView;
    }

//    private void Subcaegry_2() {
//
//        ArrayList<Feed_CategoriesDM> feed_categoriesDMS2 = new ArrayList<>();
//        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_1));
//        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_2));
//        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_3));
//        feed_categoriesDMS2.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_2_cake_4));
//
//        Feed_Categories_Adapter dm2 = new Feed_Categories_Adapter(context, feed_categoriesDMS2);
//        sub_category_2_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        sub_category_2_Rcv.setAdapter(dm2);
//    }

    private void idMapping() {

//        ArrayList<Deep_and_Deep_CakeDM> deep_and_deep_cakeDMS = new ArrayList<>();
//
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Cake",R.drawable.cake));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Donat",R.drawable.cke_1));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Pastries",R.drawable.cupcake));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Custards",R.drawable.pudding));
//         deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "kunafa",R.drawable.kunafa));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "baklava",R.drawable.baklava));
//
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Cake",R.drawable.cake));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Donat",R.drawable.cke_1));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Pastries",R.drawable.cupcake));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "Custards",R.drawable.pudding));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "kunafa",R.drawable.kunafa));
//        deep_and_deep_cakeDMS.add(new Deep_and_Deep_CakeDM( "baklava",R.drawable.baklava));


        if(connectionDetector.isConnectingToInternet())
        {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
            appController.paServices.CatgoryList(new Callback<CatgoryListDM>() {
                @Override
                public void success(CatgoryListDM catgoryListDM, Response response) {
                    //                   progress.dismiss();
                    if(catgoryListDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                      String  CategoryID=catgoryListDM.getOutput().getData().get(0).getId();
                      String  Tittle=catgoryListDM.getOutput().getData().get(0).getTitle_en();

          Deep_and_Deep_Cake_Adapter dm = new Deep_and_Deep_Cake_Adapter(context, catgoryListDM.getOutput().getData(),Deep_and_Deep_1_Fragment.this);
          LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
          dip_and_dip_cake_1_Rcv.setLayoutManager(l);
          dip_and_dip_cake_1_Rcv.setAdapter(dm);

                        SetTittle(Tittle);

                        setClickListeners(CategoryID);
                    }else
                        Helper.showToast(getActivity(),catgoryListDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        }else
            Helper.showToast(getActivity(),getString(R.string.no_internet_connection));

    }

   public void setClickListeners(String CategoryID) {
//        ArrayList<Feed_CategoriesDM> feed_categoriesDMS = new ArrayList<>();
//        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_category_cake_1));
//        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_2));
//        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_3));
//        feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.sub_catogry_cake_4));


        if(connectionDetector.isConnectingToInternet())
        {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
            appController.paServices.ShopsBycatId(CategoryID,new Callback<ShopsBycatIdDM>() {
                @Override
                public void success(ShopsBycatIdDM shopsBycatIdDM, Response response) {
                    //                   progress.dismiss();
                    if(shopsBycatIdDM.getOutput().getSuccess().equalsIgnoreCase("1")) {


                        if(CategoryID!=null){
                            sub_category_1_Rcv.setVisibility(View.VISIBLE);
                            Feed_Categories_Adapter11 dm = new Feed_Categories_Adapter11(context, shopsBycatIdDM.getOutput().getInfo());
                            sub_category_1_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                            sub_category_1_Rcv.setAdapter(dm);
                        }else{
                            sub_category_1_Rcv.setVisibility(View.GONE);
                        }
                            }else
                    {Helper.showToast(getActivity(),getString(R.string.Api_data_not_found));
                          sub_category_1_Rcv.setVisibility(View.INVISIBLE);}

                           }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        }else
            Helper.showToast(getActivity(),getString(R.string.no_internet_connection));
    }


    public void SetTittle(String Tittle)
    {
        subCategory.setText(Tittle);
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
