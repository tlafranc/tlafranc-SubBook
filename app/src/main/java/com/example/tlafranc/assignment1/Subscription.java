/*
 *     <This program is a SubBook application that keeps a log of a users subscriptions.>
 *     Copyright (C) <2018>  <Thomas Lafrance>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

/**
 * Represents a subscription/
 *
 * @author tlafranc
 */
class Subscription implements Serializable{
    private String name;
    private LocalDate date;
    private float charge;
    private String comment;

    /**
     * Constructs a subscription object.
     *
     * @param name subscription name
     * @param date subscription date
     * @param charge subscription charge
     * @param comment subscription comment
     */
    public Subscription(String name, LocalDate date, Float charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    /**
     * Returns the name of the subscription object.
     *
     * @return subscription name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of the subscription object.
     *
     * @return subscription date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the charge of the subscription object.
     *
     * @return subscription charge
     */
    public float getCharge() {
        return charge;
    }

    /**
     * Returns the comment of the subscription object.
     *
     * @return subscription comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets subscription name.
     *
     * @param name subscription name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets subscription date.
     *
     * @param date subscription date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets subscription charge.
     *
     * @param charge subscription charge
     */
    public void setCharge(float charge) {
        this.charge = charge;
    }

    /**
     * Sets subscription comment.
     *
     * @param comment subscription comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
