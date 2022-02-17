package com.saify.tech.ohhh.DataModel;

public class CartDM {

    private String pastries;
    private String extra_meyonese;
    private String kwd;
    private String item_count;
    private int count_plus;
    private int count_minus;
    private int cart_cross;
    private int pastry_img;

    public CartDM(String pastries, String extra_meyonese, String kwd, String item_count, int count_plus, int count_minus, int cart_cross, int pastry_img) {
        this.pastries = pastries;
        this.extra_meyonese = extra_meyonese;
        this.kwd = kwd;
        this.item_count = item_count;
        this.count_plus = count_plus;
        this.count_minus = count_minus;
        this.cart_cross = cart_cross;
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

    public int getCount_plus() {
        return count_plus;
    }

    public void setCount_plus(int count_plus) {
        this.count_plus = count_plus;
    }

    public int getCount_minus() {
        return count_minus;
    }

    public void setCount_minus(int count_minus) {
        this.count_minus = count_minus;
    }

    public int getCart_cross() {
        return cart_cross;
    }

    public void setCart_cross(int cart_cross) {
        this.cart_cross = cart_cross;
    }

    public int getPastry_img() {
        return pastry_img;
    }

    public void setPastry_img(int pastry_img) {
        this.pastry_img = pastry_img;
    }
}
