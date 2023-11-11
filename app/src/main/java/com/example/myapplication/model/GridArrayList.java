package com.example.myapplication.model;

import com.squareup.picasso.Picasso;

public class GridArrayList {
    private final String socialMediaIcon;
    private final String socialMediaName;

    private final String zipCode;
    private final String price;
    private final String condition;
    private final String shipping;

    public String getZipCode() {
        return zipCode;
    }

    public String getPrice() {
        return price;
    }

    public String getCondition() {
        return condition;
    }

    public String getShipping() {
        return shipping;
    }

    public GridArrayList(String socialMediaIcon, String socialMediaName, String zipCode, String price, String condition, String shipping) {
        this.socialMediaIcon = socialMediaIcon;
        this.socialMediaName = socialMediaName;
        this.zipCode = zipCode;
        this.price = price;
        this.condition = condition;
        this.shipping = shipping;
    }

    public String getSocialMediaIcon() {
        return socialMediaIcon;
    }

    public String getSocialMediaName() {
        return socialMediaName;
    }
}
