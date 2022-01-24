package com.favmartcompanny.favmarapplication.model;

import com.google.firebase.Timestamp;

import java.util.List;

public class Orders {
    String name,phone,id;
    Timestamp date;
    List<Mycart> Orders;

    public Orders(String name, String phone, String id, Timestamp date, List<Mycart> orders) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.date = date;
        this.Orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Mycart> getOrders() {
        return Orders;
    }

    public void setOrders(List<Mycart> orders) {
        Orders = orders;
    }
}
