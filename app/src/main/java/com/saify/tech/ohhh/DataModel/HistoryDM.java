package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class HistoryDM {

    private String Pastries;
    private String product_id;
    private String pricekwd;
    private String count_product;
    private int pastri_img;
    private String date;

    public HistoryDM(String pastries, String product_id, String pricekwd, String count_product, int pastri_img, String date) {
        Pastries = pastries;
        this.product_id = product_id;
        this.pricekwd = pricekwd;
        this.count_product = count_product;
        this.pastri_img = pastri_img;
        this.date = date;
    }

    public String getPastries() {
        return Pastries;
    }

    public void setPastries(String pastries) {
        Pastries = pastries;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPricekwd() {
        return pricekwd;
    }

    public void setPricekwd(String pricekwd) {
        this.pricekwd = pricekwd;
    }

    public String getCount_product() {
        return count_product;
    }

    public void setCount_product(String count_product) {
        this.count_product = count_product;
    }

    public int getPastri_img() {
        return pastri_img;
    }

    public void setPastri_img(int pastri_img) {
        this.pastri_img = pastri_img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
