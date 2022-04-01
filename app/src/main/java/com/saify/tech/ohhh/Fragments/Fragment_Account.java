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

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
 import com.saify.tech.ohhh.Activity.Change_Password_Activity;
import com.saify.tech.ohhh.Activity.Edit_Profile_Activity;
import com.saify.tech.ohhh.Activity.LoginActivity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Activity.My_Order_Activity;
import com.saify.tech.ohhh.Activity.Privacy_Policy_Activity;
import com.saify.tech.ohhh.Activity.Refund_Policy_Activity;
import com.saify.tech.ohhh.Activity.Saved_Address_Activity;
import com.saify.tech.ohhh.Activity.Term_And_Condition_Activity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;

public class Fragment_Account extends Fragment {

    private View rootView;
    private Context context;

//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;

    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;


    @NotEmpty
    @BindView(R.id.edit_user_name_Img)
    ImageView Edit_Name;

    @NotEmpty
    @BindView(R.id.user_nameTxt)
    TextView User_Name;

    @NotEmpty
    @BindView(R.id.order_history_LL)
    LinearLayout Order_History;

    @NotEmpty
    @BindView(R.id.saved_address_LL)
    LinearLayout Saved_Address;

    @NotEmpty
    @BindView(R.id.push_notification_LL)
    LinearLayout Push_Notification;

    @NotEmpty
    @BindView(R.id.change_password_LL)
    LinearLayout Change_Password;

    @NotEmpty
    @BindView(R.id.term_and_condition_LL)
    LinearLayout Term_and_Condition;

    @NotEmpty
    @BindView(R.id.refund_policy_LL)
    LinearLayout Refund_Policy;

    @NotEmpty
    @BindView(R.id.privacy_policy_LL)
    LinearLayout Privacy_Policy;

    @NotEmpty
    @BindView(R.id.contact_us_LL)
    LinearLayout Contact_Us;

    @NotEmpty
    @BindView(R.id.logout_LL)
    LinearLayout Logout;


    @OnClick(R.id.edit_user_name_Img)
    public void Edit_USer_Name() {
        startActivity(new Intent(context, Edit_Profile_Activity.class));

    }

    @OnClick(R.id.order_history_LL)
    public void Order() {
        startActivity(new Intent(context, My_Order_Activity.class));
    }

    @OnClick(R.id.saved_address_LL)
    public void Saved() {
        startActivity(new Intent(context, Saved_Address_Activity.class));

    }


    @OnClick(R.id.push_notification_LL)
    public void Push() {

    }


    @OnClick(R.id.change_password_LL)
    public void ChangePassword() {
        startActivity(new Intent(context, Change_Password_Activity.class));

    }


    @OnClick(R.id.term_and_condition_LL)
    public void Term_condition() {
        startActivity(new Intent(context, Term_And_Condition_Activity.class));


    }


    @OnClick(R.id.refund_policy_LL)
    public void Refund_Policy() {
      startActivity(new Intent(context, Refund_Policy_Activity.class));

    }


    @OnClick(R.id.privacy_policy_LL)
    public void PrivacyPolicy() {

        startActivity(new Intent(context, Privacy_Policy_Activity.class));

    }


    @OnClick(R.id.contact_us_LL)
    public void Contact_Us() {

    }


    @OnClick(R.id.logout_LL)
    public void Log_Out() {
        startActivity(new Intent(context, LoginActivity.class));
        ((MainActivity)context).finish();

    }

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
            rootView = inflater.inflate(R.layout.account_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();

            setClickListeners();
//            setDetails();
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
