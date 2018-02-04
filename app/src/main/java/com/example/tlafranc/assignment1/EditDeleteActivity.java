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
 * Activity that edits or deletes a subscription. Is called by MainActivity when the user presses
 * the taps on any of the subscriptions in the ListView in MainActivity. Utilizes
 * SubscriptionWatcher in order to ensure the user input is legal.
 *
 * @author tlafranc
 * @see SubscriptionWatcher
 */
public class EditDeleteActivity extends AppCompatActivity {

    private EditText nameText;
    private String name;
    private EditText dateText;
    private String date;
    private EditText chargeText;
    private String charge;
    private EditText commentText;
    private String comment;

    private Button edit;
    private Subscription sub;
    private SubscriptionWatcher boxWatcher;

    /**
     * Called when activity is started. Initializes the back button on the action bar, nameText,
     * chargeText, dateText, commentText, edit button and the boxWatcher. Sets the nameText,
     * dateText, chargeText and commentText fields to be the values corresponding to the
     * subscription clicked on in the ListView in MainActivity.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = (Button) findViewById(R.id.eddel_edit);

        nameText = (EditText) findViewById(R.id.eddel_name);
        dateText = (EditText) findViewById(R.id.eddel_date);
        chargeText = (EditText) findViewById(R.id.eddel_charge);
        commentText = (EditText) findViewById(R.id.eddel_comment);

        // https://stackoverflow.com/questions/17453297/passing-arraylist-of-string-arrays-from-one-activity-to-another-in-android accessed on 2018-01-29

        Intent eddelIntent = getIntent();

        sub = (Subscription) eddelIntent.getSerializableExtra("subObj");

        nameText.setText(sub.getName());
        dateText.setText(sub.getDate().toString());
        chargeText.setText(Float.toString(sub.getCharge()));
        commentText.setText(sub.getComment());

        boxWatcher = new SubscriptionWatcher(nameText, dateText, chargeText, commentText, edit);

        nameText.addTextChangedListener(boxWatcher);
        dateText.addTextChangedListener(boxWatcher);
        chargeText.addTextChangedListener(boxWatcher);
        commentText.addTextChangedListener(boxWatcher);
    }

    /**
     * Called when the user clickes on the "Edit" button in this activity. Changes the subscription
     * object passed from MainActivity using fields nameText, dateText, chargeText and commentText.
     * Sends a resultCode indicating that this is an edit operation as well as the changed
     * subscription back to MainActivity.
     *
     */
    public void onEditClick (View view) {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        String [] dateList = date.split("-");

        LocalDate dateObj = LocalDate.of(Integer.parseInt(dateList[0]), Integer.parseInt(dateList[1]),
                Integer.parseInt(dateList[2]));

        sub.setName(name);
        sub.setDate(dateObj);
        sub.setCharge(Float.parseFloat(charge));
        sub.setComment(comment);

        Intent returnSub = new Intent();
        returnSub.putExtra("code", 1);
        returnSub.putExtra("editedsub", sub);
        setResult(RESULT_OK, returnSub);
        finish();
    }

    /**
     * Called when the user clickes on the "Delete" button in this activity.
     * Sends a resultCode indicating that this is a delete operation back to MainActivity.
     *
     */
    public void onDeleteClick(View view) {
        Intent returnSub = new Intent();
        returnSub.putExtra("code", 0);
        setResult(RESULT_OK, returnSub);
        finish();
    }
}
