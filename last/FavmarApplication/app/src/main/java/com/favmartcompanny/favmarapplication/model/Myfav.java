package com.favmartcompanny.favmarapplication.model;

public class Myfav {
    String FName,Image,name,Phone,Price,date, userId,unity,Description;

    public Myfav(String FName, String image, String name, String phone,
                 String price, String date, String userId, String unity, String description) {
        this.FName = FName;
        Image = image;
        this.name = name;
        Phone = phone;
        Price = price;
        this.date = date;
        this.userId = userId;
        this.unity = unity;
        this.Description = description;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
