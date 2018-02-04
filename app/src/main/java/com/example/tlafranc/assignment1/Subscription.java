package com.example.tlafranc.assignment1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Thomas on 2018-01-26.
 */

class Subscription implements Serializable{
    private String name;
    private LocalDate date;
    private Float charge;
    private String comment;

    public Subscription(String name, LocalDate date, Float charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Float getCharge() {
        return charge;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCharge(Float charge) {
        this.charge = charge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
