package com.example.tlafranc.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.*;

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

    private Context addContext = this;

    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = (Button) findViewById(R.id.add_complete_button);

        nameText = (EditText) findViewById(R.id.add_sub_name);
        dateText = (EditText) findViewById(R.id.add_sub_date);
        chargeText = (EditText) findViewById(R.id.add_sub_charge);
        commentText = (EditText) findViewById(R.id.add_sub_comment);

        nameText.addTextChangedListener(textWatcher);
        dateText.addTextChangedListener(textWatcher);
        chargeText.addTextChangedListener(textWatcher);
        commentText.addTextChangedListener(textWatcher);

        checkFieldsForEmptyValues();
    }

    public void onAddClick(View view) {
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

    // https://stackoverflow.com/questions/20682865/disable-button-when-edit-text-fields-empty accessed on 2018-01-30

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void checkFieldsForEmptyValues() {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher properDate = datePattern.matcher(date);

        if (name.equals("") || date.equals("") || charge.equals("") || !properDate.matches()) {
            add.setEnabled(false);
            if (name.equals("")) {
                nameText.setError("Name cannot be left blank");
            }
            if (!properDate.matches()) {
                dateText.setError("Date must be in form YYYY-MM-DD");
            }
            if (date.equals("")) {
                dateText.setError("Date cannot be left blank");
            }
            if (charge.equals("")) {
                chargeText.setError("Charge cannot be left blank");
            }
        }
        else {
            add.setEnabled(true);
        }
    }
}