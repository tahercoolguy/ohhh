package com.saify.tech.ohhh.Models;

public class ParentChildDataModel {
    String parent;
    String child;

    public ParentChildDataModel(String parent, String child) {
        this.parent = parent;
        this.child = child;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }
}
