package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class Addons {

    private String shop_id;
    private String option_maximum;
    private String product_id;
    private String option_group_name;
    private Integer option_minimum;
    private String option_group_name_ar;
    private String id;
    private String created_date;
    private ArrayList<Items> items;
    private String status;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getOption_maximum() {
        return option_maximum;
    }

    public void setOption_maximum(String option_maximum) {
        this.option_maximum = option_maximum;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOption_group_name() {
        return option_group_name;
    }

    public void setOption_group_name(String option_group_name) {
        this.option_group_name = option_group_name;
    }

    public Integer getOption_minimum() {
        return option_minimum;
    }

    public void setOption_minimum(Integer option_minimum) {
        this.option_minimum = option_minimum;
    }

    public String getOption_group_name_ar() {
        return option_group_name_ar;
    }

    public void setOption_group_name_ar(String option_group_name_ar) {
        this.option_group_name_ar = option_group_name_ar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
