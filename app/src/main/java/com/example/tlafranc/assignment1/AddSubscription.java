package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddSubscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);
    }

    public void onAddClick (View view) {
        EditText nameText = (EditText) findViewById(R.id.add_sub_name);
        EditText dateText = (EditText) findViewById(R.id.add_sub_date);
        EditText chargeText = (EditText) findViewById(R.id.add_sub_charge);
        EditText commentText = (EditText) findViewById(R.id.add_sub_comment);

        String name = nameText.getText().toString();
        String date = dateText.getText().toString();
        String charge = chargeText.getText().toString();
        String comment = commentText.getText().toString();

        String [] subInformation = {name, date, charge, comment};

        Intent returnSub = new Intent();
        returnSub.putExtra("SubInformation", subInformation);
        setResult(RESULT_OK, returnSub);
        finish();
    }
}
