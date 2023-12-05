package com.example.myapplication.model;

public class GridArrayList {
    private final String socialMediaIcon;
    private final String socialMediaName;

    private final String zipCode;
    private final String price;
    private final String condition;
    private final String shipping;

    private String details;


    private final String CurrentlyInWishlistSection;

    private String inWishlist;

    private String itemId;

    public String getCurrentlyInWishlistSection() {
        return CurrentlyInWishlistSection;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getItemId() {
        return itemId;
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

    public String getDetails(){
        return details;
    }

    public String getInWishlist(){return inWishlist;}

    public void setInWishlist(String t){
        inWishlist = t;
    }

    public GridArrayList(String socialMediaIcon, String socialMediaName, String zipCode, String price, String condition, String shipping, String inWishlist, String CurrentlyInWishlistSection, String itemId, String details) {
        this.socialMediaIcon = socialMediaIcon;
        this.socialMediaName = socialMediaName;
        this.zipCode = zipCode;
        this.price = price;
        this.condition = condition;
        this.shipping = shipping;
        this.inWishlist = inWishlist;
        this.CurrentlyInWishlistSection = CurrentlyInWishlistSection;
        this.itemId = itemId;
        this.details = details;
    }

    public String getSocialMediaIcon() {
        return socialMediaIcon;
    }

    public String getSocialMediaName() {
        return socialMediaName;
    }
}
