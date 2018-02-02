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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "tlafranc-save.sav";
    private ListView sublist;

    private Context mainContext = this;
    private ArrayList<Subscription> subscriptionList;
    private ArrayAdapter<Subscription> adapter;

    private Subscription modify_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();

        sublist = (ListView) findViewById(R.id.main_sub_list);

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
        loadFromFile();
        adapter = new SubscriptionAdapter(this, subscriptionList);
        sublist.setAdapter(adapter);
        setTotalCharge();
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            //Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-24

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();

            subscriptionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

            subscriptionList = new ArrayList<Subscription>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(subscriptionList, out);

            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** Called when the user taps the "Add a Subscription" button */
    public void onAddSubscriptionClick(View view) {
        Intent addSubIntent = new Intent(this, AddSubscriptionActivity.class);
        final int result = 1;
        startActivityForResult(addSubIntent, result);
        saveInFile();
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
                saveInFile();
                break;
            case 2:
                super.onActivityResult(requestCode, resultCode, data);
                int val = data.getIntExtra("code", 0);
                if (val == 0) {
                    subscriptionList.remove(modify_sub);
                } else {
                    Subscription edited_sub = (Subscription) data.getExtras().getSerializable("editedsub");
                    modify_sub.setName(edited_sub.getName());
                    modify_sub.setDate(edited_sub.getDate());
                    modify_sub.setCharge(edited_sub.getCharge());
                    modify_sub.setComment(edited_sub.getComment());
                }
                adapter.notifyDataSetChanged();
                saveInFile();
                break;
        }

    }

    private void setTotalCharge() {
        double totalCharge = 0.00;
        for (int i = 0; i < subscriptionList.size(); i++) {
            totalCharge += Double.parseDouble(subscriptionList.get(i).getCharge());
        }
        TextView text = (TextView) findViewById(R.id.mainTotalCharge);
        String output = String.format("%.2f", totalCharge);
        text.setText("Total Monthly Charge: $" + output);
    }
}
