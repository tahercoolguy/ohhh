package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class OutputMyWishlist {
    private String success;
    private ArrayList<InfoMyWIshlist> info;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<InfoMyWIshlist> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<InfoMyWIshlist> info) {
        this.info = info;
    }
}
