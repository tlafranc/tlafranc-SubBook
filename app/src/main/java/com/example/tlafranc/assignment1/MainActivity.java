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


public class MainActivity extends AppCompatActivity {

    private FileStorage appFile;
    private ListView sublist;

    private Context mainContext = this;
    private ArrayList<Subscription> subscriptionList;
    private SubscriptionAdapter adapter;

    private Subscription modifySub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appFile = new FileStorage(subscriptionList, mainContext);
        subscriptionList = appFile.loadFromFile();

        sublist = (ListView) findViewById(R.id.main_sub_list);

        sublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // https://stackoverflow.com/questions/5971817/using-an-intent-in-a-list-onitemclick accessed on 2018-01-29

                Intent edDelIntent = new Intent(mainContext, EditDeleteActivity.class);
                final int result = 2;

                modifySub = (Subscription) adapterView.getItemAtPosition(position);
                edDelIntent.putExtra("subObj", modifySub);

                startActivityForResult(edDelIntent, result);
            }
        });
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        subscriptionList =  appFile.loadFromFile();
        adapter = new SubscriptionAdapter(this, subscriptionList);
        sublist.setAdapter(adapter);
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
                appFile.saveInFile(subscriptionList);
                break;
            case 2:
                super.onActivityResult(requestCode, resultCode, data);
                int val = data.getIntExtra("code", 0);
                if (val == 0) {
                    subscriptionList.remove(modifySub);
                }
                else {
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

    private void setTotalCharge() {
        double totalCharge = 0.00;
        for (int i = 0; i < subscriptionList.size(); i++) {
            totalCharge += subscriptionList.get(i).getCharge();
        }
        TextView text = (TextView) findViewById(R.id.mainTotalCharge);
        String output = String.format("%.2f", totalCharge);
        text.setText("Total Monthly Charge: $" + output);
    }
}
