package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class OutputMyOrder {

    private String success;
    private String sub_total;
    private String after_discount;
    private ArrayList<Info> info;

    private  String sale_code;

    private String rating;
    private  String order_id;

    public String getSale_code() {
        return sale_code;
    }

    public void setSale_code(String sale_code) {
        this.sale_code = sale_code;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getAfter_discount() {
        return after_discount;
    }

    public void setAfter_discount(String after_discount) {
        this.after_discount = after_discount;
    }

    public ArrayList<Info> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
    }
}
