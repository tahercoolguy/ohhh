package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class SearchMainOutput {

    private ArrayList<Featured> featured;
    private String success;
    private ArrayList<Discount> discount;
    private ArrayList<Best> best;

    public ArrayList<Featured> getFeatured() {
        return featured;
    }

    public void setFeatured(ArrayList<Featured> featured) {
        this.featured = featured;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<Discount> getDiscount() {
        return discount;
    }

    public void setDiscount(ArrayList<Discount> discount) {
        this.discount = discount;
    }

    public ArrayList<Best> getBest() {
        return best;
    }

    public void setBest(ArrayList<Best> best) {
        this.best = best;
    }
}
