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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {

    private ListView sublist;

    private Context mainContext = this;
    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;

    private Subscription modify_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sublist = (ListView) findViewById(R.id.main_sub_list);
        subscriptionList = new ArrayList<Subscription>();
        adapter = new ArrayAdapter<Subscription>(this, R.layout.list_item, subscriptionList);
        sublist.setAdapter(adapter);

        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // https://stackoverflow.com/questions/5971817/using-an-intent-in-a-list-onitemclick accessed on 2018-01-29

                Intent edDelIntent = new Intent(mainContext, EditDeleteActivity.class);
                final int result = 2;

                modify_sub = (Subscription) adapterView.getItemAtPosition(position);
                edDelIntent.putExtra("subObj", modify_sub);

                startActivityForResult(edDelIntent, result);
            }
        });
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        setTotalCharge();
    }

    /** Called when the user taps the "Add a Subscription" button */
    public void onAddSubscriptionClick(View view) {
        Intent addSubIntent = new Intent(this, AddSubscriptionActivity.class);
        final int result = 1;
        startActivityForResult(addSubIntent, result);
    }

    // https://stackoverflow.com/questions/5030565/multiple-onactivityresult-for-1-activity accessed on 2018-01-29
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                super.onActivityResult(requestCode, resultCode, data);
                Subscription newsub = (Subscription) data.getExtras().getSerializable("Subscription");

                subscriptionList.add(newsub);
                adapter.notifyDataSetChanged();
                System.out.println(subscriptionList);
                break;
            case 2:
                super.onActivityResult(requestCode, resultCode, data);
                int val = data.getIntExtra("code", 0);
                if (val == 0) {
                    if (modify_sub == subscriptionList.get(0)) {
                        System.out.println("SAME");
                    }
                    else {
                        System.out.println("DIFFERENT");
                    }
                    subscriptionList.remove(modify_sub);
                    System.out.println(subscriptionList);
                } else {
                    Subscription edited_sub = (Subscription) data.getExtras().getSerializable("editedsub");
                    modify_sub.setName(edited_sub.getName());
                    modify_sub.setDate(edited_sub.getDate());
                    modify_sub.setCharge(edited_sub.getCharge());
                    modify_sub.setComment(edited_sub.getComment());
                    System.out.println(modify_sub);
                }
                break;
        }

    }

    private void setTotalCharge() {
        double totalCharge = 0.00;
        for (int i = 0; i < subscriptionList.size(); i++) {
            totalCharge += Double.parseDouble(subscriptionList.get(i).getCharge());
        }
        System.out.println("Total Charge is " + Double.toString(totalCharge));
        TextView text = (TextView) findViewById(R.id.mainTotalCharge);
        String output = String.format("%.2f", totalCharge);
        text.setText("Total Monthly Charge: $" + output);
    }
}
