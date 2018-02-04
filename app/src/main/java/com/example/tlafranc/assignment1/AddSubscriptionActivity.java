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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;

/**
 * Activity that adds a subscription. Is called by MainActivity when the user presses the "Add
 * Subscription" button from MainActivity. Utilizes SubscriptionWatcher in order to ensure the
 * user input is legal.
 *
 * @author tlafranc
 * @see SubscriptionWatcher
 */
public class AddSubscriptionActivity extends AppCompatActivity {

    private EditText nameText;
    private String name;
    private EditText dateText;
    private String date;
    private EditText chargeText;
    private String charge;
    private EditText commentText;
    private String comment;

    private Button add;
    private SubscriptionWatcher boxWatcher;

    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    /**
     * Called when activity is started. Initializes the back button on the action bar, nameText,
     * dateText, chargeText, commentText, add button and the boxWatcher.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = (Button) findViewById(R.id.add_complete_button);

        nameText = (EditText) findViewById(R.id.add_sub_name);
        dateText = (EditText) findViewById(R.id.add_sub_date);
        chargeText = (EditText) findViewById(R.id.add_sub_charge);
        commentText = (EditText) findViewById(R.id.add_sub_comment);

        boxWatcher = new SubscriptionWatcher(nameText, dateText, chargeText, commentText, add);

        nameText.addTextChangedListener(boxWatcher);
        dateText.addTextChangedListener(boxWatcher);
        chargeText.addTextChangedListener(boxWatcher);
        commentText.addTextChangedListener(boxWatcher);

        boxWatcher.checkFieldsForEmptyValues();
    }

    /**
     * Called when the user clickes on the "Add" button in this activity. Creates a subscription
     * object from the fields nameText, dateText, chargeText and commentText and then sends this
     * subscription back to MainActivity.
     *
     */
    public void onAddClick(View view) {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        String [] dateList = date.split("-");

        LocalDate dateObj = LocalDate.of(Integer.parseInt(dateList[0]), Integer.parseInt(dateList[1]),
                Integer.parseInt(dateList[2]));

        Subscription newSub = new Subscription(name, dateObj, Float.parseFloat(charge), comment);

        Intent returnSub = new Intent();
        returnSub.putExtra("Subscription", newSub);
        setResult(RESULT_OK, returnSub);
        finish();
    }
}