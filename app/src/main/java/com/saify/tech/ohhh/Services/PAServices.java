package com.saify.tech.ohhh.Services;


//import io.opencensus.stats.Stats;


import com.saify.tech.ohhh.DataModel.BannerDM;
import com.saify.tech.ohhh.DataModel.EventsDM;
import com.saify.tech.ohhh.DataModel.NewsDM;
import com.saify.tech.ohhh.DataModel.RestaurentDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.DataModel.SignUpDM;
import com.saify.tech.ohhh.DataModel.VideoDM;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.MultipartTypedOutput;

public interface PAServices {
    @Headers("Cache-Control: no-cache;")
    @POST("/auth/signin")
    void SignUp(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/record_video")
    void RecordedVideo(@Body MultipartTypedOutput multipartTypedOutput, Callback<VideoDM> videoDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/user_login")
    void LoginIn(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_home_banner")
    void Banner(Callback<BannerDM> bannerDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_news")
    void News(Callback<NewsDM> newsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_events")
    void Events(Callback<EventsDM> eventsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_restaurants")
    void Restaurent(Callback<RestaurentDM> restaurentDMCallback);


    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_shops")
    void Shops(Callback<ShopsDM> shopsDMCallback);

}
