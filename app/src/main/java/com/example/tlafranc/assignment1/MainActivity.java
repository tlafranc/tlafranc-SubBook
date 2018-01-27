package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

        Button addButton = (Button) findViewById(R.id.AddSub);
        sublist = (ListView) findViewById(R.id.SubList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                AddSubscription(view);
            }
        });

    }

    /** Called when the user taps the "Add a Subscription" button */
    public void AddSubscription (View view) {
        Intent intent = new Intent(this, AddSubscription.class);
        startActivity(intent);
    }
}
