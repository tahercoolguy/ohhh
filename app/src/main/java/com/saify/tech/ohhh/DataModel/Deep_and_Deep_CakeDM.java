package com.saify.tech.ohhh.DataModel;

public class Deep_and_Deep_CakeDM {

    private String cake_name;
    private int cake_img;

    public Deep_and_Deep_CakeDM(String cake_name, int cake_img) {
        this.cake_name = cake_name;
        this.cake_img = cake_img;
    }

    public String getCake_name() {
        return cake_name;
    }

    public void setCake_name(String cake_name) {
        this.cake_name = cake_name;
    }

    public int getCake_img() {
        return cake_img;
    }

    public void setCake_img(int cake_img) {
        this.cake_img = cake_img;
    }
}
