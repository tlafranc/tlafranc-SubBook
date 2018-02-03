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

public class FileStorage {

    private static final String FILENAME = "tlafranc-save.sav";
    private ArrayList<Subscription> fileSubscriptionList;
    private Context mainContext;

    public FileStorage(ArrayList<Subscription> subscriptionList, Context mainContext) {
        this.fileSubscriptionList = subscriptionList;
        this.mainContext = mainContext;
    }

    public ArrayList<Subscription> loadFromFile() {
        try {
            FileInputStream fis = mainContext.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-24

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
