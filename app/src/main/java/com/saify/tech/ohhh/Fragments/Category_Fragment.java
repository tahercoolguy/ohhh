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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Category_Fragment extends Fragment {

    private View rootView;
    private Context context;

//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;



    @BindView(R.id.categories_Rv)
    RecyclerView categories;


//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
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
            rootView = inflater.inflate(R.layout.categories_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();

            setClickListeners();
 //           setDetails();


            ArrayList<Feed_CategoriesDM> feed_categoriesDMS = new ArrayList<>();

            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_1));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_2));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_3));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_4));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_5));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_6));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_7));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_8));
            feed_categoriesDMS.add(new Feed_CategoriesDM("Assortment of pieces","Pasties","10.000 KD",R.drawable.feed_cake_9));





            Feed_Categories_Adapter dm = new Feed_Categories_Adapter(context, feed_categoriesDMS);
//        LinearLayoutManager l = new LinearLayoutManager.HORIZONTAL(context);

            categories.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

            categories.setAdapter(dm);

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