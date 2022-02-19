package com.saify.tech.ohhh.DataModel;

public class Feed_CategoriesDM {

    private String pieces;
    private String pasties;
    private String pricekwd;
     private int pastri_img;

    public Feed_CategoriesDM(String pieces, String pasties, String pricekwd, int pastri_img) {
        this.pieces = pieces;
        this.pasties = pasties;
        this.pricekwd = pricekwd;
        this.pastri_img = pastri_img;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getPasties() {
        return pasties;
    }

    public void setPasties(String pasties) {
        this.pasties = pasties;
    }

    public String getPricekwd() {
        return pricekwd;
    }

    public void setPricekwd(String pricekwd) {
        this.pricekwd = pricekwd;
    }

    public int getPastri_img() {
        return pastri_img;
    }

    public void setPastri_img(int pastri_img) {
        this.pastri_img = pastri_img;
    }
}
