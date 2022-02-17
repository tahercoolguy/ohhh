package com.saify.tech.ohhh.DataModel;

import java.security.PrivateKey;

public class OffersDM {

    private String offer_chocklate_1;
    private int chocklate_img_1;

    public OffersDM(String offer_chocklate_1, int chocklate_img_1) {
        this.offer_chocklate_1 = offer_chocklate_1;
        this.chocklate_img_1 = chocklate_img_1;
    }

    public String getOffer_chocklate_1() {
        return offer_chocklate_1;
    }

    public void setOffer_chocklate_1(String offer_chocklate_1) {
        this.offer_chocklate_1 = offer_chocklate_1;
    }

    public int getChocklate_img_1() {
        return chocklate_img_1;
    }

    public void setChocklate_img_1(int chocklate_img_1) {
        this.chocklate_img_1 = chocklate_img_1;
    }
}
