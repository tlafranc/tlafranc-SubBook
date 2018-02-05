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
import java.util.Locale;

/*
 * I used help from youtube whilst making this class. I used his ideas on how to hande multiple
 * activities.
 * https://www.youtube.com/watch?v=45gq0Q8GFMM&index=7&list=PLGLfVvz_LVvSPjWpLPFEfOCbezi6vATIh
 * Accessed on 2018-01-27
 * By user: Derek Banas
 * Link to the code shown in the video: http://www.newthinktank.com/2014/06/make-android-apps-5/
 */


/**
 * Activity that edits or deletes a subscription. Is called by MainActivity when the user
 * taps on any of the subscriptions in the ListView in MainActivity. Utilizes
 * SubscriptionWatcher in order to ensure the user input is legal.
 *
 * @author tlafranc
 * @see SubscriptionWatcher
 */
public class EditDeleteActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText chargeText;
    private EditText commentText;
    private Subscription sub;

    /**
     * Called when activity is started. Initializes the back button on the action bar, nameText,
     * chargeText, dateText, commentText, edit button and the boxWatcher. Sets the nameText,
     * dateText, chargeText and commentText fields to be the values corresponding to the
     * subscription clicked on in the ListView in MainActivity by retrieving the subscription
     * object that is being passed from MainActivity. Assigns a subscription object to point to
     * the subscription object that is being passed.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button edit = (Button) findViewById(R.id.eddel_edit);

        nameText = (EditText) findViewById(R.id.eddel_name);
        dateText = (EditText) findViewById(R.id.eddel_date);
        chargeText = (EditText) findViewById(R.id.eddel_charge);
        commentText = (EditText) findViewById(R.id.eddel_comment);

    /*
     * I used help from stackoverflow in order to learn how to pass an object that I've created
     * between activities.
     * https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
     * Accessed on: 2018-01-29
     * By user: https://stackoverflow.com/users/236128/samuh
     */
        Intent eddelIntent = getIntent();

        sub = (Subscription) eddelIntent.getSerializableExtra("subObj");

        nameText.setText(sub.getName());
        dateText.setText(sub.getDate().toString());
        String chargeDisplay = String.format(Locale.CANADA, "%.2f", sub.getCharge());
        chargeText.setText(chargeDisplay);
        commentText.setText(sub.getComment());

        SubscriptionWatcher boxWatcher = new SubscriptionWatcher(nameText, dateText, chargeText,
                edit);

        nameText.addTextChangedListener(boxWatcher);
        dateText.addTextChangedListener(boxWatcher);
        chargeText.addTextChangedListener(boxWatcher);
        commentText.addTextChangedListener(boxWatcher);
    }

    /**
     * Called when the user clicks on the "Edit" button in this activity. Changes the subscription
     * object passed from MainActivity using fields nameText, dateText, chargeText and commentText
     * and passes this object back to MainActivity. Sends a resultCode indicating that this is an
     * edit operation. The boxWatcher ensures the fields are not empty and that the values inside of
     * them are valid and filled before the Edit button can be clicked.
     *
     */
    public void onEditClick (View view) {
        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String charge = chargeText.getText().toString();
        String comment = commentText.getText().toString();

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
