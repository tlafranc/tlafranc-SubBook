package com.example.tlafranc.assignment1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thomas on 2018-02-01.
 */

public class SubscriptionAdapter extends ArrayAdapter<Subscription> {

    public SubscriptionAdapter(@NonNull Context context, ArrayList<Subscription> subscriptionList) {
        super(context, R.layout.list_item, subscriptionList);

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        Subscription sub = getItem(position);
        TextView nameText = (TextView) view.findViewById(R.id.listViewName);
        nameText.setText(sub.getName());

        TextView dateText = (TextView) view.findViewById(R.id.listViewDate);
        dateText.setText(sub.getDate());

        TextView chargeText = (TextView) view.findViewById(R.id.listViewCharge);
        String output = String.format("$%.2f", Double.parseDouble(sub.getCharge()));
        chargeText.setText(output);

        TextView commentText = (TextView) view.findViewById(R.id.listViewComment);
        commentText.setText(sub.getComment());

        return view;
    }
}