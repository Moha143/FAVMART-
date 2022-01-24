package com.favmartcompanny.favmarapplication.model;

public class Fruits {
    String name;
    String  image;
    String date;
    String price;
    String description;
    String unity;

    public Fruits(String name, String image, String date, String price , String description,String unity) {
        this.name = name;
        this.image = image;
        this.date = date;
        this.price = price;
        this.description=description;
        this.unity=unity;
    }

    public String getunity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
