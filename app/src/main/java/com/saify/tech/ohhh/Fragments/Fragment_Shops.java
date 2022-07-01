package com.saify.tech.ohhh.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Shopss_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
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

public class Fragment_Shops extends Fragment {

    private View rootView;
    private Context context;

    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
//    DialogUtil dialogUtil;
//    Dialog progress;

    @NotEmpty
    @BindView(R.id.shopss_Rcv)
    RecyclerView Shopss_Rcv;

    @NotEmpty
    @BindView(R.id.cart_LL)
    RelativeLayout cart;

    String BannerImg;
    String ShopImg;




    @OnClick(R.id.cart_LL)
    public void Cart() {

        ((MainActivity)context).startActivity(new Intent(getActivity(), Cart_Activity.class));

    }



//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;
//
//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;




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
//            setDetails();
        }
        return rootView;
    }

    private void idMapping() {


//        ArrayList<ShopssDM> shopssDMS = new ArrayList<>();
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
//                "10-15 mins","","",R.drawable.ic_delivery_boy,
//                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
//                "10-15 mins","","",R.drawable.ic_delivery_boy,
//                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
//                "10-15 mins","","",R.drawable.ic_delivery_boy,
//                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","Dip n Dip","Free delivery",
//                "10-15 mins","","",R.drawable.ic_delivery_boy,
//                R.drawable.ic_time,"", R.drawable.home_cake_1, R.drawable.ic_rating_star));



        if(connectionDetector.isConnectingToInternet())
        {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
            appController.paServices.ohhhShops(new Callback<ShopsDM>() {
                @Override
                public void success(ShopsDM shopsDM, Response response) {
 //                   progress.dismiss();
                    if(shopsDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                    ShopImg=    shopsDM.getOutput().getInfo().get(0).getImage();
                     BannerImg=   shopsDM.getOutput().getInfo().get(0).getBanner_image();



             Shopss_Adapter dm = new Shopss_Adapter(context, shopsDM.getOutput().getInfo());
             LinearLayoutManager l = new LinearLayoutManager(context);
             Shopss_Rcv.setLayoutManager(l);
             Shopss_Rcv.setAdapter(dm);

                    }else
                        Helper.showToast(getActivity(),shopsDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });
        }else
            Helper.showToast(getActivity(),getString(R.string.no_internet_connection));
    }







    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    private void setDetails() {
//       ShowProgress();
//        rootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               DismissProgress();
//            }
//        }, 100);
//
//
//
//
//    }

//    public void ShowProgress()
//    {
//        progress_bar.setVisibility(View.VISIBLE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.GONE);
//    }
//
//    public void DismissProgress()
//    {
//        progress_bar.setVisibility(View.GONE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.VISIBLE);
//    }

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
