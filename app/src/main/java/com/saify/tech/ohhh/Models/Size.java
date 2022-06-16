package com.saify.tech.ohhh.Models;

public class Size {

    String  size;
    String  price;

    public Size(String size, String price) {
        this.size = size;
        this.price = price;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
