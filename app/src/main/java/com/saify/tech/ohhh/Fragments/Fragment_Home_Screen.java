package com.saify.tech.ohhh.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.Edit_Profile_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Activity.SaveAddressActivity;
import com.saify.tech.ohhh.Adapter.BestFromDesert_Adapter;
import com.saify.tech.ohhh.Adapter.Featured_Shopss_Adapter;
import com.saify.tech.ohhh.Adapter.Offers_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AllAreaDM;
import com.saify.tech.ohhh.DataModel.BestDM;
import com.saify.tech.ohhh.DataModel.FShopsDM;
import com.saify.tech.ohhh.DataModel.GovernatesDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OffersApiDM;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.DataModel.UpdateProfileDM;
import com.saify.tech.ohhh.Helper.BottomForAll;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.ResponseListener;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.DataChangeDM;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Fragment_Home_Screen extends Fragment {

    private View rootView;
    private Context context;
    BottomForAll bottomForAll;
    Dialog dialog;
    String AreaName;
    String AreaID;


//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;

    @BindView(R.id.featuredShopsRcv)
    RecyclerView featured_shops;


    @BindView(R.id.listView)
    ListView listView;

    EditText search;


    @BindView(R.id.view_all_Txt)
    TextView view_all;

    @BindView(R.id.offers_Rcv)
    RecyclerView offers_Rcvv;

    @BindView(R.id.best_fom_desert_Rcv)
    RecyclerView best_fom_desert_Rcvv;

    @BindView(R.id.cart_Rl)
    RelativeLayout Cart;

    @BindView(R.id.AreaName)
    TextView Area_NameTextView;

    ArrayList<DataChangeDM> arrayList = new ArrayList();

    ArrayList<DataChangeDM> arrayList1= new ArrayList<>();
    @OnClick(R.id.Deliver)
    public void Deliver(){


            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                dialog = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));
                appController.paServices.AllArea(new Callback<AllAreaDM>() {
                    @Override
                    public void success(AllAreaDM allAreaDM, Response response) {
                        dialog.dismiss();
                        if (allAreaDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                            for (Info obj : allAreaDM.getOutput().getInfo()) {
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
                                    AreaName = ((DataChangeDM) object).getName();
                                    AreaID = ((DataChangeDM) object).getGovernateId();
                                    user.setAreaId(AreaID);
                                    Area_NameTextView.setText(AreaName);

                                    FeaturesShops();
                                    Offers();
                                    BestFromDesert();
                                }
                            });
                            bottomForAll.show(getActivity().getSupportFragmentManager(), "bottomSheetCountry");

                        } else
                           Helper.showToast(context, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("error", retrofitError.toString());
                    }
                });
            } else
                Helper.showToast(context, getString(R.string.no_internet_connection));

            }


    @OnClick(R.id.cart_Rl)
    public void Cart() {

        ((MainActivity)context).startActivity(new Intent(getActivity(), Cart_Activity.class));

    }


    @OnClick(R.id.view_all_Txt)
    public void ViewAll() {

        ((MainActivity)context).addFragment(new Fragment_Shops(),false);

    }

//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;

    User user;

    DialogUtil dialogUtil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        dialogUtil = new DialogUtil();
        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.home_screen_fragment_layout, container, false);
            search = rootView.findViewById(R.id.edt_search);
            ButterKnife.bind(this,rootView);
            user=new User(getActivity());

            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                dialog = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));
                appController.paServices.AllArea(new Callback<AllAreaDM>() {
                    @Override
                    public void success(AllAreaDM allAreaDM, Response response) {
                        dialog.dismiss();
                        if (allAreaDM.getOutput().getSuccess().equalsIgnoreCase("1")) {


                            AreaName = allAreaDM.getOutput().getInfo().get(0).getTitle_en();
                            AreaID = allAreaDM.getOutput().getInfo().get(0).getId();
                            user.setAreaId(AreaID);
                            Area_NameTextView.setText(AreaName);

                            FeaturesShops();
                            Offers();
                            BestFromDesert();

                        } else
                            Helper.showToast(context, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("error", retrofitError.toString());
                    }
                });
            } else
                Helper.showToast(context, getString(R.string.no_internet_connection));


//            setDetails();
            funSearch();


        }
        return rootView;
    }

    private void FeaturesShops() {

//        ArrayList<ShopssDM> shopssDMS = new ArrayList<>();
//
//         shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
//                 "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_1));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
//                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_2));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
//                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_1));
//
//        shopssDMS.add(new ShopssDM("4.5","(25+)","McDonald’s","Free delivery","10-15 mins","",
//                "",R.drawable.ic_delivery_boy, R.drawable.ic_time,"",R.drawable.home_cake_1,R.drawable.home_cake_2));

        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                             dialog = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                appController.paServices.FShops(AreaID, new Callback<FShopsDM>() {
                @Override
                public void success(FShopsDM fShopsDM, Response response) {
//                    dialog .dismiss();
               if (fShopsDM.getOutput().getSuccess().equalsIgnoreCase("1")) {



           Featured_Shopss_Adapter dm = new Featured_Shopss_Adapter(context,fShopsDM.getOutput().getInfo());
//        LinearLayoutManager l = new LinearLayoutManager.HORIZONTAL(context);
           LinearLayoutManager l = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        featured_shops.setLayoutManager(l);
        featured_shops.setAdapter(dm);

                        }
//               else
//
//                            Helper.showToast(getActivity(),fShopsDM.getOutput().getMessage() );
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    dialog.dismiss();
                    Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));


    }

    private void Offers() {

//        ArrayList<OffersDM> offersDMS = new ArrayList<>();
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.ofer_cake_img_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_3));
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.ofer_cake_img_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_3));
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.ofer_cake_img_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.offer_cake_img_3));


        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                dialog = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                appController.paServices.Offers(AreaID, new Callback<OffersApiDM>() {
                    @Override
                    public void success(OffersApiDM offersApiDM, Response response) {
//                        dialog.dismiss();
                        if (offersApiDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                Offers_Adapter dm = new Offers_Adapter(context, offersApiDM.getOutput().getInfo());
                LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                offers_Rcvv.setLayoutManager(l);
                offers_Rcvv.setAdapter(dm);

                        }
//                        else
//
//                            Helper.showToast(getActivity(),offersApiDM.getOutput().getMessage() );
                    }
                    @Override
                    public void failure(RetrofitError error) {
 //                       dialog.dismiss();
                        Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
    }



    private void BestFromDesert() {

//        ArrayList<OffersDM> offersDMS = new ArrayList<>();
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desrt_cake_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_3));
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desrt_cake_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_3));
//
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desrt_cake_1));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_2));
//        offersDMS.add(new OffersDM( "Chocolate Cake",R.drawable.desert_cake_3));
//




        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

 //                              dialog= dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

                appController.paServices.Best(AreaID, new Callback<BestDM>() {
                    @Override
                    public void success(BestDM bestDM, Response response) {
 //                                           dialog.dismiss();
                        if (bestDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
           BestFromDesert_Adapter dm = new BestFromDesert_Adapter(context, bestDM.getOutput().getInfo());
           LinearLayoutManager l = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
           best_fom_desert_Rcvv.setLayoutManager(l);
           best_fom_desert_Rcvv.setAdapter(dm);
                        }
//                        else
//
//                            Helper.showToast(getActivity(),bestDM.getOutput().getMessage() );
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



     public void funSearch()
        {
//            search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//
//                        // Your piece of code on keyboard search click
//                        if(connectionDetector.isConnectingToInternet())
//                        {
////                            ShowProgress();
//                            appController.paServices.Restaurant_Search(search.getText().toString(),new Callback<RestaurentDM>() {
//                                @Override
//                                public void success(RestaurentDM restaurentDM, Response response) {
// //                                   DismissProgress();
//                                    if(restaurentDM.getStatus().equals("1")) {
//                                        adapter = new Adapter_Restaurent_List(getActivity(), restaurentDM.getResult(),true);
//                                        listView.setAdapter(adapter);
//                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                            @Override
//                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                                RestaurentDetails_Fragment restaurentDetails_fragment = new RestaurentDetails_Fragment();
//                                                Bundle bd = new Bundle();
//                                                bd.putParcelable("data", restaurentDM.getResult().get(i));
//                                                restaurentDetails_fragment.setArguments(bd);
//                                                ((MainActivity) getActivity()).addFragment(restaurentDetails_fragment, true);
//                                            }
//                                        });
//                                    }else {
//                                        ArrayList<RestaurentResult> restaurentResultArrayList=new ArrayList<>();
//                                        adapter = new Adapter_Restaurent_List(getActivity(), restaurentResultArrayList,true);
//                                        listView.setAdapter(adapter);
//                                    }
//                                }
//                                @Override
//                                public void failure(RetrofitError error) {
//                                    DismissProgress();
//
//                                    ArrayList<RestaurentResult> restaurentResultArrayList=new ArrayList<>();
//                                    adapter = new Adapter_Restaurent_List(getActivity(), restaurentResultArrayList,true);
//                                    listView.setAdapter(adapter);
//                                    Log.e("String", error.toString());
//                                }
//                            });
//
//                        } else
//                            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
//
//
//                        return true;
//                    }
//                    return false;
//                }
//            });

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
