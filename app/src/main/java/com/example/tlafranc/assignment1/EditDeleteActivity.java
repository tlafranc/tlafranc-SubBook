package com.example.tlafranc.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.*;

public class EditDeleteActivity extends AppCompatActivity {

    private EditText nameText;
    private String name;
    private EditText dateText;
    private String date;
    private EditText chargeText;
    private String charge;
    private EditText commentText;
    private String comment;

    private Button edit;
    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit = (Button) findViewById(R.id.eddel_edit);

        nameText = (EditText) findViewById(R.id.eddel_name);
        dateText = (EditText) findViewById(R.id.eddel_date);
        chargeText = (EditText) findViewById(R.id.eddel_charge);
        commentText = (EditText) findViewById(R.id.eddel_comment);

        // https://stackoverflow.com/questions/17453297/passing-arraylist-of-string-arrays-from-one-activity-to-another-in-android accessed on 2018-01-29

        Intent eddelIntent = getIntent();

        sub = (Subscription) eddelIntent.getSerializableExtra("subObj");

        nameText.setText(sub.getName());
        dateText.setText(sub.getDate());
        chargeText.setText(sub.getCharge());
        commentText.setText(sub.getComment());

        nameText.addTextChangedListener(textWatcher);
        dateText.addTextChangedListener(textWatcher);
        chargeText.addTextChangedListener(textWatcher);
        commentText.addTextChangedListener(textWatcher);
    }

    public void onEditClick (View view) {
        name = nameText.getText().toString();
        date = dateText.getText().toString();
        charge = chargeText.getText().toString();
        comment = commentText.getText().toString();

        sub.setName(name);
        sub.setDate(date);
        sub.setCharge(charge);
        sub.setComment(comment);

        Intent returnSub = new Intent();
        returnSub.putExtra("code", 1);
        returnSub.putExtra("editedsub", sub);
        setResult(RESULT_OK, returnSub);
        finish();
    }

    public void onDeleteClick(View view) {
        Intent returnSub = new Intent();
        returnSub.putExtra("code", 0);
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
            edit.setEnabled(false);
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
        } else {
            edit.setEnabled(true);
        }
    }
}
