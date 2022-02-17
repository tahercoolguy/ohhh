package com.saify.tech.ohhh.DataModel;

public class ShopssDM {

    private String rating_star;
    private String rating_count;
    private String dip_and_dip;
    private String free_delivery;
    private String delivery_time;
    private String desert;
    private String kuwaiti;
    private int delivery_Img;
    private int delivery_time_img;
    private String like_img;
    private  int cake_img;

    public String getRating_star() {
        return rating_star;
    }

    public void setRating_star(String rating_star) {
        this.rating_star = rating_star;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getDip_and_dip() {
        return dip_and_dip;
    }

    public void setDip_and_dip(String dip_and_dip) {
        this.dip_and_dip = dip_and_dip;
    }

    public String getFree_delivery() {
        return free_delivery;
    }

    public void setFree_delivery(String free_delivery) {
        this.free_delivery = free_delivery;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getDesert() {
        return desert;
    }

    public void setDesert(String desert) {
        this.desert = desert;
    }

    public String getKuwaiti() {
        return kuwaiti;
    }

    public void setKuwaiti(String kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public int getDelivery_Img() {
        return delivery_Img;
    }

    public void setDelivery_Img(int delivery_Img) {
        this.delivery_Img = delivery_Img;
    }

    public int getDelivery_time_img() {
        return delivery_time_img;
    }

    public void setDelivery_time_img(int delivery_time_img) {
        this.delivery_time_img = delivery_time_img;
    }

    public String getLike_img() {
        return like_img;
    }

    public void setLike_img(String like_img) {
        this.like_img = like_img;
    }

    public int getCake_img() {
        return cake_img;
    }

    public void setCake_img(int cake_img) {
        this.cake_img = cake_img;
    }

    public ShopssDM(String rating_star, String rating_count, String dip_and_dip, String free_delivery, String delivery_time, String desert, String kuwaiti, int delivery_Img, int delivery_time_img, String like_img, int home_cake_1, int cake_img) {
        this.rating_star = rating_star;
        this.rating_count = rating_count;
        this.dip_and_dip = dip_and_dip;
        this.free_delivery = free_delivery;
        this.delivery_time = delivery_time;
        this.desert = desert;
        this.kuwaiti = kuwaiti;
        this.delivery_Img = delivery_Img;
        this.delivery_time_img = delivery_time_img;
        this.like_img = like_img;
        this.cake_img = cake_img;
    }
}
