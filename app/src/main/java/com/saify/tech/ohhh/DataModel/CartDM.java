package com.saify.tech.ohhh.DataModel;

public class CartDM {

    private String pastries;
    private String extra_meyonese;
    private String kwd;
    private String item_count;

    private int pastry_img;

    public CartDM(String pastries, String extra_meyonese, String kwd, String item_count, int pastry_img) {
        this.pastries = pastries;
        this.extra_meyonese = extra_meyonese;
        this.kwd = kwd;
        this.item_count = item_count;
        this.pastry_img = pastry_img;
    }

    public String getPastries() {
        return pastries;
    }

    public void setPastries(String pastries) {
        this.pastries = pastries;
    }

    public String getExtra_meyonese() {
        return extra_meyonese;
    }

    public void setExtra_meyonese(String extra_meyonese) {
        this.extra_meyonese = extra_meyonese;
    }

    public String getKwd() {
        return kwd;
    }

    public void setKwd(String kwd) {
        this.kwd = kwd;
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public int getPastry_img() {
        return pastry_img;
    }

    public void setPastry_img(int pastry_img) {
        this.pastry_img = pastry_img;
    }
}
