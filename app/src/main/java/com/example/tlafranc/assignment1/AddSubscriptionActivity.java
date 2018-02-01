package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddSubscriptionActivity extends AppCompatActivity {

    private EditText nameText;
    private String name;
    private EditText dateText;
    private String date;
    private EditText chargeText;
    private String charge;
    private EditText commentText;
    private String comment;

    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        nameText = (EditText) findViewById(R.id.add_sub_name);
        dateText = (EditText) findViewById(R.id.add_sub_date);
        chargeText = (EditText) findViewById(R.id.add_sub_charge);
        commentText = (EditText) findViewById(R.id.add_sub_comment);
    }

    public void onAddClick (View view) {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        Subscription newSub = new Subscription(name, date, charge, comment);

        Intent returnSub = new Intent();
        returnSub.putExtra("Subscription", newSub);
        setResult(RESULT_OK, returnSub);
        finish();
    }
}
