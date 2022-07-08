package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class Output {

    private ArrayList<Data> data;

    private String success;

    private String message;


    private ArrayList<Addons> addons;


    private ArrayList<Category> category;

 //   private ArrayList<ImageList> images;
    private String  favaorate;
    private String  sub_total;
    private String after_discount;

    //    private String info;

    private ArrayList<Info> info;

    public ArrayList<Info> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
    }

    public ArrayList<Data> getData ()
    {
        return data;
    }

    public void setData (ArrayList<Data> data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public String getFavaorate() {
        return favaorate;
    }

    public void setFavaorate(String favaorate) {
        this.favaorate = favaorate;
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

    public ArrayList<Addons> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<Addons> addons) {
        this.addons = addons;
    }
}
