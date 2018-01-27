package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "newfile.sav";
    private ListView sublist;

    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    }
}
