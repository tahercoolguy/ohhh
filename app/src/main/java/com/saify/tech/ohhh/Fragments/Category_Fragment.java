package com.saify.tech.ohhh.Fragments;

import android.app.Dialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Adapter_Sub_Categories;
import com.saify.tech.ohhh.Adapter.Adapter_Sub_Category_Items;
import com.saify.tech.ohhh.Adapter.Adapter_categories;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.ProductsBysubcatIdDM;
import com.saify.tech.ohhh.DataModel.ProductsbyAreaIdDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
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

public class Category_Fragment extends Fragment {

    private View rootView;
    private Context context;

//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;



    @BindView(R.id.categories_Rv)
    RecyclerView categories_rv;

    @BindView(R.id.sub_category_item_Rcv)
    RecyclerView sub_category_item_Rcv;

    @BindView(R.id.categoryName)
    TextView categoryNameTV;


//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    private User user;
    DialogUtil dialogUtil;
    Dialog progress;

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
            rootView = inflater.inflate(R.layout.categories_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            dialogUtil = new DialogUtil();
            idMapping();


 //           setDetails();

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



            if (connectionDetector.isConnectingToInternet()) {
                {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                    //               progress = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                    appController.paServices.CatgoryList( new Callback<CatgoryListDM>() {
                        @Override
                        public void success(CatgoryListDM catgoryListDM, Response response) {
                            //                       progress.dismiss();
                            if (catgoryListDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                             String  newName=catgoryListDM.getOutput().getData().get(0).getTitle_en();

            Adapter_categories dm = new Adapter_categories(context, catgoryListDM.getOutput().getData(),Category_Fragment.this);
//         LinearLayoutManager l = new LinearLayoutManager.HORIZONTAL(context);

            categories_rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

            categories_rv.setAdapter(dm);

                             CategoryName(newName);



                            } else
                                Helper.showToast(getActivity(),catgoryListDM.getOutput().getMessage() );
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


    public void CategoryName(String name)
    {
        categoryNameTV.setText(name);
        categoryNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                        //               progress = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                        appController.paServices.CatgoryList( new Callback<CatgoryListDM>() {
                            @Override
                            public void success(CatgoryListDM catgoryListDM, Response response) {
                                //                       progress.dismiss();
                                if (catgoryListDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
                                               Adapter_Sub_Categories dm = new Adapter_Sub_Categories(context, catgoryListDM.getOutput().getData(), Category_Fragment.this);
//
                                               categories_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                                               categories_rv.setAdapter(dm);





                                } else
                                    Helper.showToast(getActivity(),catgoryListDM.getOutput().getMessage() );
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
        });

    }



    public void setClickListeners(String cat_id) {

        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                progress = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                appController.paServices.ProductsBysubcatId(cat_id, new Callback<ProductsBysubcatIdDM>() {
                    @Override
                    public void success(ProductsBysubcatIdDM productsBysubcatIdDM, Response response) {
                                            progress.dismiss();
                        if (productsBysubcatIdDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                            if(cat_id!=null){
                            Adapter_Sub_Category_Items adapter_sub_category_items = new Adapter_Sub_Category_Items(getActivity(),productsBysubcatIdDM.getOutput().getInfo());

                            sub_category_item_Rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                            sub_category_item_Rcv.setAdapter(adapter_sub_category_items);
                        }else{
                                sub_category_item_Rcv.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        Helper.showToast(getActivity(),getString(R.string.Api_data_not_found));
                        sub_category_item_Rcv.setVisibility(View.INVISIBLE);}



                    }
                    @Override
                    public void failure(RetrofitError error) {
                        progress.dismiss();
                        Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(context, getString(R.string.no_internet_connection));

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