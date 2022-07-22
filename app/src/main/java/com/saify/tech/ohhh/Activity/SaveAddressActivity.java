package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Adapter.Adapter_MyWishlist;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AreaDM;
import com.saify.tech.ohhh.DataModel.EditAddressDM;
import com.saify.tech.ohhh.DataModel.GovernatesDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.MyWishlistDM;
import com.saify.tech.ohhh.DataModel.SaveAddressDM;
import com.saify.tech.ohhh.Helper.BottomForAll;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.ResponseListener;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.DataChangeDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SaveAddressActivity extends AppCompatActivity {


    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog dialog;
    Dialog progress;
    String Name,Email,CountryCode,mobile,Governate,Area,buildingNo,Block,Street,floor_no,AddressID;

    @BindView(R.id.firstNameET)
      EditText full_name_ET;

     @BindView(R.id.emailET)
     EditText emailET;

     @BindView(R.id.pinCodeTxt)
     EditText pinCodeTxt;

     @BindView(R.id.mobileET)
     EditText mobileET;

     @BindView(R.id.areaET)
     EditText areaET;

      @BindView(R.id.governateET)
      EditText governateET;

     @BindView(R.id.building_no)
     EditText building_no;

     @BindView(R.id.block)
     EditText block;

    @BindView(R.id.streetET)
    EditText streetET;

    @BindView(R.id.floor_noET)
    EditText floor_noET;

//    @BindView(R.id.latET)
//    EditText latET;

//    @BindView(R.id.lanET)
//    EditText lanET;

    BottomForAll bottomForAll;
    BottomForAllForArea bottomForAllForArea;
      public String governateName;
      public String governateId;
     public String areaName;
     Boolean isFirstTimeGovernate=true;
    Boolean isFirstTimeArea=true;

    ArrayList<DataChangeDM> arrayList = new ArrayList();

    ArrayList<DataChangeDM> arrayList1= new ArrayList<>();
    @OnClick(R.id.governateET)
    public void governateET() {

        if(isFirstTimeGovernate) {
            isFirstTimeGovernate=false;
            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                dialog = dialogUtil.showProgressDialog(SaveAddressActivity.this, getString(R.string.please_wait));
                appController.paServices.Governates(new Callback<GovernatesDM>() {
                    @Override
                    public void success(GovernatesDM governatesDM, Response response) {
                        dialog.dismiss();
                        if (governatesDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            governateId = governatesDM.getOutput().getInfo().get(0).getId();
                            for (Info obj : governatesDM.getOutput().getInfo()) {
                                DataChangeDM s = new DataChangeDM();
                                s.setName(obj.getTitle_en());
                                s.setGovernateId(obj.getId());
                                arrayList.add(s);
                            }
                            bottomForAll = new BottomForAll();
                            bottomForAll.arrayList = arrayList;

                            bottomForAll.setResponseListener(new ResponseListener() {
                                @Override
                                public void response(Object object) {
                                    governateName = ((DataChangeDM) object).getName();
                                    governateId = ((DataChangeDM) object).getGovernateId();
                                    governateET.setText(governateName);


                                }
                            });
                            bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");

                        } else
                            Helper.showToast(SaveAddressActivity.this, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("error", retrofitError.toString());
                    }
                });
            } else
                Helper.showToast(SaveAddressActivity.this, getString(R.string.no_internet_connection));
        }else
        {
            bottomForAll = new BottomForAll();
            bottomForAll.arrayList = arrayList;

            bottomForAll.setResponseListener(new ResponseListener() {
                @Override
                public void response(Object object) {
                    governateName = ((DataChangeDM) object).getName();
                    governateId = ((DataChangeDM) object).getGovernateId();
                    governateET.setText(governateName);


                }
            });
            bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");
        }
    }




//    @OnClick(R.id.areaET)
//    public void NewEt()
//    {
//        bottomForAllForArea = new BottomForAllForArea();
//        bottomForAllForArea.arrayList = arrayList1;
//        bottomForAllForArea.setResponseListener(new ResponseListener() {
//            @Override
//            public void response(Object object) {
//                areaName = ((DataChangeDM)object).getAreaName();
//                areaET.setText(areaName);
//
//            }
//        });
//        bottomForAllForArea.show(getSupportFragmentManager(), "bottomSheetCountry");
//    }
    @OnClick(R.id.areaET)
    public void areaET() {

        arrayList1= new ArrayList<>();
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            dialog = dialogUtil.showProgressDialog(SaveAddressActivity.this, getString(R.string.please_wait));
            appController.paServices.Area(governateId,new Callback<AreaDM>() {
                @Override
                public void success(AreaDM areaDM, Response response) {
                    dialog.dismiss();
                    if (areaDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        for (Info obj : areaDM.getOutput().getInfo())
                        {
                            DataChangeDM s=new DataChangeDM();
                            s.setName(obj.getTitle2());
                            arrayList1.add(s);
                        }


                        bottomForAll = new BottomForAll();
                        bottomForAll.arrayList =arrayList1;
                        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(Object object) {
                areaName = ((DataChangeDM)object).getName();
                areaET.setText(areaName);

            }
        });
                        bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");

                    } else
                        Helper.showToast(SaveAddressActivity.this, getString(R.string.Api_data_not_found));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());
                }
            });
        } else
            Helper.showToast(SaveAddressActivity.this, getString(R.string.no_internet_connection));

    }





    @OnClick(R.id.saveTxtt)
    public void saveTxtt() {
       if(AddressID!=null)
       {
          EditAddress();
       }else{
        Binding();}

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_address);
        user = new User(SaveAddressActivity.this);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(SaveAddressActivity.this);
        dialogUtil = new DialogUtil();
        ButterKnife.bind(this);

        setDetail();

//        Binding();

    }


        public void Binding() {

            if (connectionDetector.isConnectingToInternet()) {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
                String user_id= String.valueOf(user.getId());
                appController.paServices.SaveAddress(user_id,full_name_ET.getText().toString(),emailET.getText().toString(),pinCodeTxt.getText().toString(),
                        mobileET.getText().toString(),areaET.getText().toString(),governateET.getText().toString(),building_no.getText().toString(),block.getText().toString(),
                        streetET.getText().toString(),floor_noET.getText().toString(),"12","12", new Callback<SaveAddressDM>() {
                    @Override
                    public void success(SaveAddressDM saveAddressDM, Response response) {
                        //                   progress.dismiss();
                        if (saveAddressDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            Helper.showToast(SaveAddressActivity.this,saveAddressDM.getOutput().getMessage());
                        } else
                            Helper.showToast(SaveAddressActivity.this, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();

                        Log.e("error", retrofitError.toString());

                    }
                });
            } else
                Helper.showToast(SaveAddressActivity.this, getString(R.string.no_internet_connection));
            }



    public void setDetail()
    {
        Name=getIntent().getStringExtra("name");
             full_name_ET.setText(Name);
        Email=getIntent().getStringExtra("email");
        emailET.setText(Email);
        CountryCode=getIntent().getStringExtra("code");
        pinCodeTxt.setText(CountryCode);
        mobile=getIntent().getStringExtra("mobileNo");
        mobileET.setText(mobile);
        Governate=getIntent().getStringExtra("governate");
        areaET.setText(Governate);
        Area=getIntent().getStringExtra("area");
        governateET.setText(Area);
        buildingNo=getIntent().getStringExtra("building");
        building_no.setText(buildingNo);
        Block=getIntent().getStringExtra("block");
       block.setText(Block);
        Street=getIntent().getStringExtra("street");
        streetET.setText(Name);
        floor_no=getIntent().getStringExtra("floor");
        floor_noET.setText(Name);
        AddressID=getIntent().getStringExtra("AddressId");
    }

    public void EditAddress() {

        if (connectionDetector.isConnectingToInternet()) {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
            String user_id= String.valueOf(user.getId());
            appController.paServices.EditAddress(user_id,full_name_ET.getText().toString(),emailET.getText().toString(),pinCodeTxt.getText().toString(),
                    mobileET.getText().toString(),areaET.getText().toString(),governateET.getText().toString(),building_no.getText().toString(),block.getText().toString(),
                    streetET.getText().toString(),floor_noET.getText().toString(),"12","12",AddressID, new Callback<EditAddressDM>() {
                        @Override
                        public void success(EditAddressDM editAddressDM, Response response) {
                            //                   progress.dismiss();
                            if (editAddressDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                                Helper.showToast(SaveAddressActivity.this,editAddressDM.getOutput().getMessage());
                            } else
                                Helper.showToast(SaveAddressActivity.this, getString(R.string.Api_data_not_found));
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();

                            Log.e("error", retrofitError.toString());

                        }
                    });
        } else
            Helper.showToast(SaveAddressActivity.this, getString(R.string.no_internet_connection));
    }





}
