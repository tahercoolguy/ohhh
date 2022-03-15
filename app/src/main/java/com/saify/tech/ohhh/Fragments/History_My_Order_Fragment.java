package com.saify.tech.ohhh.Fragments;

import android.app.Dialog;
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

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Adapter.HistoryDM_Adapter;
import com.saify.tech.ohhh.Adapter.OrderDM_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;


public class History_My_Order_Fragment extends Fragment {

    private View rootView;
    private Context context;





    @NotEmpty
    @BindView(R.id.history_Rcv)
    RecyclerView history_Rcvv;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;

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
//        ((MainActivity) context).setTitle(getString(R.string.home));
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.history_my_order_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            idMapping();

            setClickListeners();
            setDetails();


            ArrayList<HistoryDM> historyDMS = new ArrayList<>();

            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_1,"29 Jan, 12:30"));
            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_2,"29 Jan, 12:30"));
            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_3,"29 Jan, 12:30"));

            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_1,"29 Jan, 12:30"));
            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_2,"29 Jan, 12:30"));
            historyDMS.add(new HistoryDM("Pastries","#162432","10,000KWD","03 Items",R.drawable.pastries_3,"29 Jan, 12:30"));

//            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
//            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));

//
            HistoryDM_Adapter dm = new HistoryDM_Adapter(context,historyDMS);
            LinearLayoutManager l = new LinearLayoutManager(context);
            history_Rcvv.setLayoutManager(l);
            history_Rcvv.setAdapter(dm);



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