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

/**
 * Created by Thomas on 2018-02-03.
 */

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Class made to handle FileStorage for this app.
 *
 * @author tlafranc
 */
public class FileStorage {

    private static final String FILENAME = "tlafranc-save.sav";
    private ArrayList<Subscription> fileSubscriptionList;
    private Context mainContext;

    /**
     * Constructs a FileStorage object. This object will handle saving and loading the
     * subscriptionList from MainActivity.
     *
     * @param subscriptionList A list of subscriptions
     * @param mainContext Context that the main activity was in
     */
    public FileStorage(ArrayList<Subscription> subscriptionList, Context mainContext) {
        this.fileSubscriptionList = subscriptionList;
        this.mainContext = mainContext;
    }


    /*
     * The functions loadFromFile() and saveInFile() were taken from the lonelyTwitter git
     * repository that we worked on in the third week of labs in CMPUT 301.
     * https://github.com/tlafranc/lonelyTwitter
     * By user: tlafranc
     * Forked from: https://github.com/joshua2ua/lonelyTwitter
     */
    /**
     * This method loads the subscription list saved in file and returns it back to MainActivity.
     *
     * @return The suscription list found in file
     * @throws FileNotFoundException thrown if file is not found
     * @throws IOException thrown if error other than file not found
     */
    public ArrayList<Subscription> loadFromFile() {
        try {
            FileInputStream fis = mainContext.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            /*
             * Stackoverflow code on gson to json for an arrayList
             * https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
             * Accessed on 2018-01-24
             * By user: https://stackoverflow.com/users/20634/alex
             */

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();

            fileSubscriptionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

            fileSubscriptionList = new ArrayList<Subscription>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileSubscriptionList;
    }

    /**
     * This function saves the subscription list in file that MainActivity passes to it.
     *
     * @param subscriptionList The subscriptionList in MainActivity.
     * @throws FileNotFoundException thrown if file is not found
     * @throws IOException thrown if error other than file not found
     */
    public void saveInFile(ArrayList<Subscription> subscriptionList) {
        try {
            FileOutputStream fos = mainContext.openFileOutput(FILENAME,
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
}
