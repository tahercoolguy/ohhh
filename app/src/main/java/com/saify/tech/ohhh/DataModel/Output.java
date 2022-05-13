package com.saify.tech.ohhh.DataModel;

import java.util.ArrayList;

public class Output {

    private ArrayList<Data> data;

    private String success;

    private String message;

    private  String info;

    private ArrayList<Info> info1;

    public ArrayList<Info> getInfo() {
        return info1;
    }

    public void setInfo(ArrayList<Info> info1) {
        this.info1 = info1;
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

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<Info> getInfo1() {
        return info1;
    }

    public void setInfo1(ArrayList<Info> info1) {
        this.info1 = info1;
    }
}
