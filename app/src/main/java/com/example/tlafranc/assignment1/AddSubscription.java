package com.example.tlafranc.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSubscription extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText chargeText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        nameText = (EditText) findViewById(R.id.Name);
        dateText = (EditText) findViewById(R.id.Date);
        chargeText = (EditText) findViewById(R.id.Monthly_Charge);
        commentText = (EditText) findViewById(R.id.Comment);

        Button addButton = (Button) findViewById(R.id.SubComplete);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                String name = nameText.getText().toString();
                String date = dateText.getText().toString();
                String charge = chargeText.getText().toString();
                String comment = commentText.getText().toString();



            }
        });


    }
}
