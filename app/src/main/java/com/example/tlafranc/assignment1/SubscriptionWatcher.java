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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thomas on 2018-02-04.
 */

/*
 * I used help from stackoverflow to have an idea on how to go about disabling a button.
 * https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty
 * Accessed on 2018-01-30
 * By user: - https://stackoverflow.com/users/3110978/androidbeginner
 */

/**
 * Class made to determines if the entries in AddSubscriptionActivity or EditDeleteActivity are
 * legal entries (legal entries are defined by the app requirements).
 *
 * @author tlafranc
 */
public class SubscriptionWatcher implements TextWatcher {

    private EditText nameText;
    private EditText dateText;
    private EditText chargeText;
    private Button confirm;


    /**
     * Initializes a SubscriptionWatcher object.
     *
     * @param nameText EditText object where the name of the subscription goes
     * @param dateText EditText object where the date of the subscription goes
     * @param chargeText EditText object where the charge of the subscription goes
     * @param confirm Button object that needs to be disabled if there are some illegal entries
     */
    public SubscriptionWatcher(EditText nameText, EditText dateText, EditText chargeText,
                               Button confirm){
        this.nameText = nameText;
        this.dateText = dateText;
        this.chargeText = chargeText;
        this.confirm = confirm;
    }

    /**
     * Function needs to be implemented in order to implement TextWatcher but is not used.
     */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    /**
     * Function that upon text changes makes a call to checkFieldsForIllegalEntry to determine if
     * the changes made have fixed an illegal entry or created one.
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        checkFieldsForIllegalEntry();
    }

    /**
     * Function needs to be implemented in order to implement TextWatcher but is not used.
     */
    @Override
    public void afterTextChanged(Editable editable) {
    }

    /**
     * Function that determines if there are any blank fields for name, date and charge as these
     * fields must be filled as specified by the requirements. Also checks to see if the date is of
     * form YYYY-MM-DD and utilizes checkLegalDate to determine if the date exits.
     */
    public void checkFieldsForIllegalEntry() {
        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String charge = chargeText.getText().toString();

        Pattern datePattern = Pattern.compile("\\d{4}-(\\d{2})-(\\d{2})");
        Matcher properDate = datePattern.matcher(date);

        confirm.setEnabled(true);

        if (!properDate.matches()) {
            confirm.setEnabled(false);
            dateText.setError("Date must be in form YYYY-MM-DD");
        }
        else {
            boolean result = checkLegalDate(Integer.parseInt(properDate.group(1)),
                    Integer.parseInt(properDate.group(2)));
            if (!result) {
                confirm.setEnabled(false);
                dateText.setError("Date does not exist. Form must be YYYY-MM-DD");
            }
        }

        if (name.equals("")) {
            confirm.setEnabled(false);
            nameText.setError("Name cannot be left blank");
        }
        if (date.equals("")) {
            confirm.setEnabled(false);
            dateText.setError("Date cannot be left blank");
        }
        if (charge.equals("")) {
            confirm.setEnabled(false);
            chargeText.setError("Charge cannot be left blank");
        }
    }

    /**
     * Function used by checkForIllegalEntry to determine if the date given is actually a real
     * date (e.g. Decemember 40th is not a real date). Assumes that no subscriptions are made
     * on leap day (February 29 is a day every 4 years).
     *
     * @param month Integer represents the month where 1 = January and 12 = December
     * @param day Integer representing the day
     * @return Returns true if the date is valid and false otherwise
     */
    private boolean checkLegalDate(int month, int day) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                month == 12) {
            return day > 0 && day <= 31;
        }
        else if (month == 2) {
            return day > 0 && day <= 28;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day > 0 && day <= 30;
        }
        else return false;
    }

}