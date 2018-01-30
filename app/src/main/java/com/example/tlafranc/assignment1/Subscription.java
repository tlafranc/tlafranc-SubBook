package com.example.tlafranc.assignment1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Thomas on 2018-01-26.
 */

class Subscription implements Serializable{
    private String name;
    private String date;
    private String charge;
    private String comment;

    public Subscription(String name, String date, String charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCharge() {
        return charge;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*
    public ArrayList<String> returnStringList() {
        ArrayList<String> retList = new ArrayList<String>();
        retList.add(this.name);
        retList.add(this.date);
        retList.add(this.charge);
        retList.add(this.comment);
        return retList;
    }

    public Subscription returnSubscription(ArrayList<String> subList) {
        return Subscription()
    }
    */

    @Override
    public String toString(){
        return this.name + "\nDate: " + this.date + "    Charge: $" + this.charge;
    }
}
