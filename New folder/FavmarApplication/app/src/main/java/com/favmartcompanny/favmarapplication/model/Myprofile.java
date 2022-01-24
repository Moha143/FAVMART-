package com.favmartcompanny.favmarapplication.model;

public class Myprofile {
    String PName, PPhone, id;

    public Myprofile(String PName, String PPhone, String id) {
        this.PName = PName;
        this.PPhone = PPhone;
        this.id=id;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPName() {
        return PName;
    }
    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPPhone() {
        return PPhone;
    }

    public void setPPhone(String PPhone) {
        this.PPhone = PPhone;
    }
}
