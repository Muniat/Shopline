package com.example.userregistration.Model;

public class CartItem {
    private String mImageURL;
    private String mName;
    private String mPrice;

    public CartItem(String mImageURL, String mName, String mPrice) {
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

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
