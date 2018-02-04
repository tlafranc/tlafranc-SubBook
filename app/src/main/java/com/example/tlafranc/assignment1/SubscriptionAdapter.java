/*
 *     <This program is a SubBook application that keeps a log of a users subscriptions.>
 *     Copyright (C) <2018>  <Thomas Lafrance>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

/*
 * I used youtube in order to understand how to implement my own adapter
 * https://www.youtube.com/watch?v=rhj4_KBD6BQ&list=PLGLfVvz_LVvSPjWpLPFEfOCbezi6vATIh&index=5
 * Accessed on 2018-01-29
 * By user: Derek Banas
 * Link to code shown in video: http://www.newthinktank.com/2014/06/make-android-apps-3/
 */

/**
 * My version of an adapter utilized for the Subscription objects. This adapter sets the layout of
 * each subscription in the subscriptionList to be the layout from list_item.xml
 *
 * @author tlafranc
 */
public class SubscriptionAdapter extends ArrayAdapter<Subscription> {

    /**
     * Initializes an adapter. Makes a call to super with the parameters given and the list_item.xml
     * layout.
     *
     * @param context Context of MainActivity when constructor is called.
     * @param subscriptionList List containing subscriptions that need to be displayed
     */
    public SubscriptionAdapter(@NonNull Context context, ArrayList<Subscription> subscriptionList) {
        super(context, R.layout.list_item, subscriptionList);

    }

    /**
     * Called when the app wants to display the Subscription objects. Sets the TextViews in
     * list_item.xml to their proper values (where the name, date, charge and comment should go)
     *
     * @param position Position of subscription in the list
     * @param parent Parent ViewGroup
     * @return
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        Subscription sub = getItem(position);
        TextView nameText = (TextView) view.findViewById(R.id.listViewName);
        nameText.setText(sub.getName());

        TextView dateText = (TextView) view.findViewById(R.id.listViewDate);
        dateText.setText(sub.getDate().toString());

        TextView chargeText = (TextView) view.findViewById(R.id.listViewCharge);
        String output = String.format("$%.2f", sub.getCharge());
        chargeText.setText(output);

        TextView commentText = (TextView) view.findViewById(R.id.listViewComment);
        commentText.setText(sub.getComment());

        return view;
    }
}