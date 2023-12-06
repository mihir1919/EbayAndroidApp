package com.example.myapplication.model;

public class YourItemModel {
    private String title;
    private String price;
    private String timeLeft;
    private String zipcode;
    private String imageUrl;

    public YourItemModel(String title, String price, String timeLeft, String zipcode, String imageUrl) {
        this.title = title;
        this.price = price;
        this.timeLeft = timeLeft;
        this.zipcode = zipcode;
        this.imageUrl = imageUrl;
    }

    // Getters and setters for each field (title, price, timeLeft, zipcode, imageUrl)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
