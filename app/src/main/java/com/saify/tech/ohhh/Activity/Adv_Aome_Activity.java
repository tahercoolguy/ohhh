package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Adapter.Adapter_Address;
import com.saify.tech.ohhh.Adapter.Adapter_Adt_Home;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AdvHomeDM;
import com.saify.tech.ohhh.DataModel.UpdateProfileDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adv_Aome_Activity extends AppCompatActivity {
    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    @OnClick(R.id.Skip_Btn)
    public void  Skip_Btn(){

        if(user.getId()==0){

            startActivity(new Intent(Adv_Aome_Activity.this,LoginActivity.class));
        }
        else{
            startActivity(new Intent(Adv_Aome_Activity.this,MainActivity.class));
        }
        finish();

    }

    @BindView(R.id.recycleviewAdtHome)
    RecyclerView recycleviewAdtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_home);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Adv_Aome_Activity.this);
        user = new User(this);

        Binding();
    }

    public void Binding()
    {
        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                progress = dialogUtil.showProgressDialog(Adv_Aome_Activity.this, getString(R.string.please_wait));

                appController.paServices.AdvHome( new Callback<AdvHomeDM>() {
                    @Override
                    public void success(AdvHomeDM advHomeDM, Response response) {
                        progress.dismiss();
                        if (advHomeDM.getOutput().getSuccess().equalsIgnoreCase("1")) {


                            Adapter_Adt_Home dm = new Adapter_Adt_Home(Adv_Aome_Activity.this, advHomeDM.getOutput().getAdv());
                            LinearLayoutManager l = new LinearLayoutManager(Adv_Aome_Activity.this,LinearLayoutManager.HORIZONTAL,false);
                            recycleviewAdtHome.setLayoutManager(l);
                            recycleviewAdtHome.setAdapter(dm);

                        } else

                            Helper.showToast(Adv_Aome_Activity.this,advHomeDM.getOutput().getMessage() );
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(Adv_Aome_Activity.this, getString(R.string.no_internet_connection));
    }
}