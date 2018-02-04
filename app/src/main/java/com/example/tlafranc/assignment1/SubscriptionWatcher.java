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
    private EditText commentText;
    private Button confirm;


    /**
     * Initializes a SubscriptionWatcher object.
     *
     * @param nameText EditText object where the name of the subscription goes
     * @param dateText EditText object where the date of the subscription goes
     * @param chargeText EditText object where the charge of the subscription goes
     * @param commentText EditText object where the comment of the subscription goes
     * @param confirm Button object that needs to be disabled if there are some illegal entries
     */
    public SubscriptionWatcher(EditText nameText, EditText dateText, EditText chargeText,
                                    EditText commentText, Button confirm){
        this.nameText = nameText;
        this.dateText = dateText;
        this.chargeText = chargeText;
        this.commentText = commentText;
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

    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    /**
     * Function that determines if there are any blank fields for name, date and charge as these
     * fields must be filled as specified by the requirements. Also checks to see if the date is of
     * form YYYY-MM-DD
     */
    public void checkFieldsForIllegalEntry() {
        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String charge = chargeText.getText().toString();

        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher properDate = datePattern.matcher(date);

        if (name.equals("") || date.equals("") || charge.equals("") || !properDate.matches()) {
            confirm.setEnabled(false);
            if (name.equals("")) {
                nameText.setError("Name cannot be left blank");
            }
            if (!properDate.matches()) {
                dateText.setError("Date must be in form YYYY-MM-DD");
            }
            if (date.equals("")) {
                dateText.setError("Date cannot be left blank");
            }
            if (charge.equals("")) {
                chargeText.setError("Charge cannot be left blank");
            }
        }
        else {
            confirm.setEnabled(true);
        }
    }
}


/*
Add Subscription
    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void checkFieldsForEmptyValues() {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher properDate = datePattern.matcher(date);

        if (name.equals("") || date.equals("") || charge.equals("") || !properDate.matches()) {
            add.setEnabled(false);
            if (name.equals("")) {
                nameText.setError("Name cannot be left blank");
            }
            if (!properDate.matches()) {
                dateText.setError("Date must be in form YYYY-MM-DD");
            }
            if (date.equals("")) {
                dateText.setError("Date cannot be left blank");
            }
            if (charge.equals("")) {
                chargeText.setError("Charge cannot be left blank");
            }
        }
        else {
            add.setEnabled(true);
        }
    }
 */

/*
Edit Subscription
    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void checkFieldsForEmptyValues() {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher properDate = datePattern.matcher(date);

        if (name.equals("") || date.equals("") || charge.equals("") || !properDate.matches()) {
            edit.setEnabled(false);
            if (name.equals("")) {
                nameText.setError("Name cannot be left blank");
            }
            if (!properDate.matches()) {
                dateText.setError("Date must be in form YYYY-MM-DD");
            }
            if (date.equals("")) {
                dateText.setError("Date cannot be left blank");
            }
            if (charge.equals("")) {
                chargeText.setError("Charge cannot be left blank");
            }
        } else {
            edit.setEnabled(true);
        }
    }
 */