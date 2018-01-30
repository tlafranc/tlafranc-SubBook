package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "newfile.sav";
    private ListView sublist;

    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;
    private double totalCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sublist = (ListView) findViewById(R.id.main_sub_list);

        /*
        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //this
            }
        });
        */

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        subscriptionList = new ArrayList<Subscription>();
        Subscription testSub = new Subscription("Netflix", "1997-10-06", "10", "Comment");
        subscriptionList.add(testSub);
        adapter = new ArrayAdapter<Subscription>(this, R.layout.list_item, subscriptionList);
        sublist.setAdapter(adapter);
        setTotalCharge();
    }


    /** Called when the user taps the "Add a Subscription" button */
    public void onAddSubscriptionClick (View view) {
        Intent addSubIntent = new Intent(this, AddSubscription.class);
        final int result = 1;

        startActivityForResult(addSubIntent, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String [] subInfo = data.getStringArrayExtra("SubInformation");
        Subscription newSub = new Subscription(subInfo[0], subInfo[1], subInfo[2], subInfo[3]);
        subscriptionList.add(newSub);
        adapter.notifyDataSetChanged();
        System.out.println(subscriptionList);
        //setTotalCharge();

    }

    private void setTotalCharge() {
        totalCharge = 0.00;
        for (int i = 0; i < subscriptionList.size(); i++) {
            totalCharge += Double.parseDouble(subscriptionList.get(i).getCharge());
        }
        System.out.println("Total Charge is " + Double.toString(totalCharge));
        TextView text = (TextView) findViewById(R.id.TotalCharge);
        text.setText("Total Monthly Charge: $" + Double.toString(totalCharge));
    }

}
