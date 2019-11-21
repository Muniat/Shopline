package com.example.userregistration.Model;

public class Item {
    private String mImageURL;
    private String mName;
    private String mPrice;
    private String mDescription;

    public Item(String mImageURL, String mName, String mPrice, String mDescription) {
        this.mImageURL = mImageURL;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mDescription = mDescription;
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

    public String getmDescription(){
        return mDescription;
    }

}
