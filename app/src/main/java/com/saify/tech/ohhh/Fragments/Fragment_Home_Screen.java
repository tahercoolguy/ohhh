package com.saify.tech.ohhh.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Featured_Shopss_Adapter;
import com.saify.tech.ohhh.Adapter.Offers_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Fragment_Home_Screen extends Fragment {

    private View rootView;
    private Context context;

    @BindView(R.id.progress_bar) ProgressBar progress_bar;
    @BindView(R.id.txt_error) TextView txt_error;

    @BindView(R.id.featuredShopsRcv)
    RecyclerView featured_shops;

    @BindView(R.id.offers_Rcv)
    RecyclerView offers_Rcvv;

    @BindView(R.id.best_fom_desert_Rcv)
    RecyclerView best_fom_desert_Rcvv;




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
            rootView = inflater.inflate(R.layout.home_screen_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
             FeaturesShops();
             Offers();
             BestFromDesert();

            setDetails();


        }
        return rootView;
    }

    private void FeaturesShops() {

        ArrayList<ShopssDM> shopssDMS = new ArrayList<>();

         shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
                 "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_1));

        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_2));

        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_1));

        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_2));






        Featured_Shopss_Adapter dm = new Featured_Shopss_Adapter(context, shopssDMS);
//        LinearLayoutManager l = new LinearLayoutManager.HORIZONTAL(context);

        LinearLayoutManager l
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        featured_shops.setLayoutManager(l);
        featured_shops.setAdapter(dm);

    }

    private void Offers() {

        ArrayList<OffersDM> offersDMS = new ArrayList<>();

        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.ofer_cake_img_1));
        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_2));
        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_3));

        Offers_Adapter dm = new Offers_Adapter(context, offersDMS);

        LinearLayoutManager l
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        offers_Rcvv.setLayoutManager(l);
        offers_Rcvv.setAdapter(dm);


    }

    private void BestFromDesert() {

        ArrayList<OffersDM> offersDMS = new ArrayList<>();

        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desrt_cake_1));
        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_2));
        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_3));

        Offers_Adapter dm = new Offers_Adapter(context, offersDMS);

        LinearLayoutManager l
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        best_fom_desert_Rcvv.setLayoutManager(l);
        best_fom_desert_Rcvv.setAdapter(dm);


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
