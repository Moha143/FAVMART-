package com.favmartcompanny.favmarapplication.model;

public class Mycart {
    String id, FName,Image,name,Phone,Price,date, userId,unity,TotalPrice,quantity;
    public Mycart(String id,String FName, String image, String name,
                  String TotalPrice,
                  String phone, String price, String date, String userId,String unity,String quantity) {
        this.FName = FName;
        this.Image = image;
        this.name = name;
        this.Phone = phone;
        this.Price = price;
        this.date = date;
        this.userId = userId;
        this.unity=unity;
        this.TotalPrice=TotalPrice;
        this.quantity=quantity;
        this.id=id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

//    public String getunity() {
//        return unity;
//    }
//    public void setunity(String unity) {
//        unity = unity;
//    }

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




}
