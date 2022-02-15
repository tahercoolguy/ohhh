package com.saify.tech.ohhh.DataModel;

public class SavedAddressDM {

     private String address;
    private String home;

    public SavedAddressDM(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    private int home_img;
    private int delete_img;
    private int edit_img;

    public SavedAddressDM(String address, int home_img, int delete_img, int edit_img) {
        this.address = address;
        this.home_img = home_img;
        this.delete_img = delete_img;
        this.edit_img = edit_img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHome_img() {
        return home_img;
    }

    public void setHome_img(int home_img) {
        this.home_img = home_img;
    }

    public int getDelete_img() {
        return delete_img;
    }

    public void setDelete_img(int delete_img) {
        this.delete_img = delete_img;
    }

    public int getEdit_img() {
        return edit_img;
    }

    public void setEdit_img(int edit_img) {
        this.edit_img = edit_img;
    }
}
