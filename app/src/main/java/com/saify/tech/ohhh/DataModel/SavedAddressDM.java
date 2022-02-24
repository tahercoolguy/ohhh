package com.saify.tech.ohhh.DataModel;

public class SavedAddressDM {

     private String address;
    private String home;
    private int home_img;

    public SavedAddressDM(String address, String home, int home_img) {
        this.address = address;
        this.home = home;
        this.home_img = home_img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public int getHome_img() {
        return home_img;
    }

    public void setHome_img(int home_img) {
        this.home_img = home_img;
    }
}
