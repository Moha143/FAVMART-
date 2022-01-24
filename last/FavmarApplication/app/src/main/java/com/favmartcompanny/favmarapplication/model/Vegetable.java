package com.favmartcompanny.favmarapplication.model;

public class Vegetable {
    String Vname;
    String  Vimage;
    String Vdate;
    String Vprice;
    String Vunity;
    String Vdescription;

    public Vegetable(String vname, String vimage, String vdate, String vprice, String vunity, String vdescription) {
        Vname = vname;
        Vimage = vimage;
        Vdate = vdate;
        Vprice = vprice;
        Vunity = vunity;
        Vdescription = vdescription;
    }

    public String getVname() {
        return Vname;
    }

    public void setVname(String vname) {
        Vname = vname;
    }

    public String getVimage() {
        return Vimage;
    }
    public void setVimage(String vimage) {
        Vimage = vimage;
    }

    public String getVdate() {
        return Vdate;
    }

    public void setVdate(String vdate) {
        Vdate = vdate;
    }

    public String getVprice() {
        return Vprice;
    }

    public void setVprice(String vprice) {
        Vprice = vprice;
    }

    public String getVunity() {
        return Vunity;
    }

    public void setVunity(String vunity) {
        Vunity = vunity;
    }

    public String getVdescription() {
        return Vdescription;
    }

    public void setVdescription(String vdescription) {
        Vdescription = vdescription;
    }
}
