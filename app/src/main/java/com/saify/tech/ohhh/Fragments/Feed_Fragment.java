package com.saify.tech.ohhh.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter111;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AllAreaDM;
import com.saify.tech.ohhh.DataModel.AllProductsDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.OffersApiDM;
import com.saify.tech.ohhh.DataModel.ProductsbyAreaIdDM;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Feed_Fragment extends Fragment {

    private View rootView;
    private Context context;

//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;


    @BindView(R.id.feed_Rv)
    RecyclerView Feed;




//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    User user;
    String AreaId;

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
            rootView = inflater.inflate(R.layout.feed_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            user=new User(getActivity());
            AreaId=user.getAreaId();
            idMapping();

            setClickListeners();
//            setDetails();

//
//            ArrayList<Feed_CategoriesDM> feed_categoriesDMS = new ArrayList<>();
//
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_1));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_2));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_3));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_4));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_5));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_6));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_7));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_8));
//            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_9));
//



            if (connectionDetector.isConnectingToInternet()) {
                {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                    //               progress = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                    appController.paServices.AllProducts( new Callback<AllProductsDM>() {
                        @Override
                        public void success(AllProductsDM allProductsDM, Response response) {
                            //                       progress.dismiss();
                            if (allProductsDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

            Feed_Categories_Adapter111 dm = new Feed_Categories_Adapter111(context, allProductsDM.getOutput().getData());
//        LinearLayoutManager l = new LinearLayoutManager.HORIZONTAL(context);

            Feed.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

            Feed.setAdapter(dm);

                            } else
                                Helper.showToast(getActivity(),allProductsDM.getOutput().getMessage() );
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("String", error.toString());
                        }
                    });
                }
            } else
                Helper.showToast(getActivity(), getString(R.string.no_internet_connection));

        }
        return rootView;
    }

    private void idMapping() {


    }

    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    private void setDetails() {
//        ShowProgress();
//        rootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                DismissProgress();
//            }
//        }, 1500);
//
//
//
//
//    }
//
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