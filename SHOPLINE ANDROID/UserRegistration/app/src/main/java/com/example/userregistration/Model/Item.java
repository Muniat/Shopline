package com.example.userregistration.Model;

public class Item {
    private String mImageURL;
    private String mName;
    private String mPrice;

    public Item(String mImageURL, String mName, String mPrice) {
        this.mImageURL = mImageURL;
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmName() {
        return mName;
    }

    public String getmPrice() {
        return mPrice;
    }
}
