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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "newfile.sav";
    private ListView sublist;

    private Context mainContext = this;
    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;
    private double totalCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sublist = (ListView) findViewById(R.id.main_sub_list);

        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // https://stackoverflow.com/questions/5971817/using-an-intent-in-a-list-onitemclick accessed on 2018-01-29

                Intent edDelIntent = new Intent(mainContext, EditDeleteActivity.class);
                final int result = 2;

                System.out.println((Subscription) adapterView.getItemAtPosition(position));
                Subscription eddel_sub = (Subscription) adapterView.getItemAtPosition(position);
                edDelIntent.putExtra("subObj", eddel_sub);

                startActivityForResult(edDelIntent, result);
            }
        });
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


    // https://stackoverflow.com/questions/5030565/multiple-onactivityresult-for-1-activity accessed on 2018-01-29
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                super.onActivityResult(requestCode, resultCode, data);
                String [] subInfo = data.getStringArrayExtra("SubInformation");
                Subscription newSub = new Subscription(subInfo[0], subInfo[1], subInfo[2], subInfo[3]);
                subscriptionList.add(newSub);
                adapter.notifyDataSetChanged();
                System.out.println(subscriptionList);
                //setTotalCharge();
                break;
            case 2:
                super.onActivityResult(requestCode, resultCode, data);
                int val = data.getIntExtra("eddelInformation", 0);

        }

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
