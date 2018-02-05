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

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;

/**
 * MainActivity class that is called upon starting the application. Makes calls to
 * AddSubscriptionActivity and EditDeleteActivity in order to manage the subscriptions of the user.
 * Utilizes the FileStorage class to save the subscriptions in file to achieve persistence.
 * Utilizes SubscriptionAdapter class to display the subscriptions in a ListView.
 *
 * @author tlafranc
 * @see Subscription
 * @see AddSubscriptionActivity
 * @see EditDeleteActivity
 * @see FileStorage
 * @see SubscriptionAdapter
 */
public class MainActivity extends AppCompatActivity {

    private FileStorage appFile;
    private ListView sublist;
    private Context mainContext = this;
    private ArrayList<Subscription> subscriptionList;
    private SubscriptionAdapter adapter;
    private Subscription modifySub;

    /**
     * Called when activity is started. Initializes the appFile, subscriptionList and subList.
     *
      * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appFile = new FileStorage(subscriptionList, mainContext);
        subscriptionList = appFile.loadFromFile();

        sublist = (ListView) findViewById(R.id.main_sub_list);

        /*
         * I used youtube in order to understand how to implement onClick for a ListView
         * https://www.youtube.com/watch?v=rhj4_KBD6BQ&list=PLGLfVvz_LVvSPjWpLPFEfOCbezi6vATIh&index=5
         * Accessed on 2018-01-29
         * By user: Derek Banas
         * Linked to code shown in video: http://www.newthinktank.com/2014/06/make-android-apps-3/
         */
        /*
         * I used code from stackoverflow to overcome a bug in my program where I needed to pass
         * the context of the MainActivity but I was getting an error.
         * https://stackoverflow.com/questions/5971817/using-an-intent-in-a-list-onitemclick
         * Accessed on 2018-01-29
         * By user: https://stackoverflow.com/users/717927/gix
         */
        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent edDelIntent = new Intent(mainContext, EditDeleteActivity.class);
                final int result = 2;

                modifySub = (Subscription) adapterView.getItemAtPosition(position);
                edDelIntent.putExtra("subObj", modifySub);

                startActivityForResult(edDelIntent, result);
            }
        });
    }

    /**
     * Called upon starting the activity. Ensures subscriptionList is up to date and inititializes
     * the adapter in order to display the subscriptionList in the ListView.
     *
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        subscriptionList =  appFile.loadFromFile();
        adapter = new SubscriptionAdapter(this, subscriptionList);
        sublist.setAdapter(adapter);
        setTotalCharge();
    }

    /**
     * Called when the user taps the "Add a Subscription" button. Calls the
     * AddSubscriptionActivity.
     *
     */
    public void onAddSubscriptionClick(View view) {
        Intent addSubIntent = new Intent(this, AddSubscriptionActivity.class);
        final int result = 1;
        startActivityForResult(addSubIntent, result);
    }

    /*
     * I used code from stackoverflow in order to learn how to distinguish between two onActivityResults
     * https://stackoverflow.com/questions/5030565/multiple-onactivityresult-for-1-activity
     * Accessed on 2018-01-29
     * By user: https://stackoverflow.com/users/603059/thame90
     */
    /**
     * Called upon completion of AddSubscriptionActivity or EditDeleteActivity. If the result
     * is coming from AddSubscriptionActivity, add this subscription to the subscriptionList
     * and save it in file. If the result is coming from EditDeleteActivity, determine whether
     * it was an edit or delete button that was pressed and do the corresponding operation. For an
     * edit button press, update the subscription that was sent to the EditDelete activity. For a
     * delete button press, remove this subscription from the ArrayList. Save the changes in file.
     *
     * @param requestCode Code sent with activity
     * @param data Information sent back from activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 1:
                    super.onActivityResult(requestCode, resultCode, data);
                    Subscription newsub = (Subscription) data.getExtras().getSerializable("Subscription");

                    subscriptionList.add(newsub);
                    adapter.notifyDataSetChanged();
                    appFile.saveInFile(subscriptionList);
                    break;
                case 2:
                    super.onActivityResult(requestCode, resultCode, data);
                    int val = data.getIntExtra("code", 0);
                    if (val == 0) {
                        subscriptionList.remove(modifySub);
                    } else {
                        Subscription edited_sub = (Subscription) data.getExtras().getSerializable("editedsub");
                        modifySub.setName(edited_sub.getName());
                        modifySub.setDate(edited_sub.getDate());
                        modifySub.setCharge(edited_sub.getCharge());
                        modifySub.setComment(edited_sub.getComment());
                    }
                    adapter.notifyDataSetChanged();
                    appFile.saveInFile(subscriptionList);
                    break;
            }
        }
    }

    /**
     * Private function used to calculate the total charge at the moment of calling between all
     * subscriptions. Displays this total charge at the bottom of the screen.
     *
     */
    private void setTotalCharge() {
        double totalCharge = 0.00;
        for (int i = 0; i < subscriptionList.size(); i++) {
            totalCharge += subscriptionList.get(i).getCharge();
        }
        TextView text = (TextView) findViewById(R.id.mainTotalCharge);
        String output = String.format(Locale.CANADA, "Total Monthly Charge: $%.2f",
                totalCharge);
        text.setText(output);
    }
}
