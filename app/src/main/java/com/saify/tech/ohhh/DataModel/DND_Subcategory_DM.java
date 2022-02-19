package com.saify.tech.ohhh.DataModel;

public class DND_Subcategory_DM {

    private String heading1;
    private String heading2;
    private String heading3;
    private  int imgcake;

    public DND_Subcategory_DM(String heading1, String heading2, String heading3, int imgcake) {
        this.heading1 = heading1;
        this.heading2 = heading2;
        this.heading3 = heading3;
        this.imgcake = imgcake;
    }

    public String getHeading1() {
        return heading1;
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public String getHeading3() {
        return heading3;
    }

    public void setHeading3(String heading3) {
        this.heading3 = heading3;
    }

    public int getImgcake() {
        return imgcake;
    }

    public void setImgcake(int imgcake) {
        this.imgcake = imgcake;
    }
}
