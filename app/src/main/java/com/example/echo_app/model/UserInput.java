package com.example.echo_app.model;

import android.widget.EditText;

import java.util.ArrayList;

import com.google.gson.Gson;

public class UserInput
{
    private final ArrayList<String> userInputList;

    public UserInput()
    {
        this.userInputList = new ArrayList<>();
    }

    public void addUserInput(EditText editText)
    {
        this.userInputList.add(editText.getText().toString());
    }

    public ArrayList<String> getUserInputList()
    {
        return this.userInputList;
    }

    public String getUserInputListAsString()
    {
        StringBuilder sbEntries = new StringBuilder();

        for (String entry : userInputList)
        {
            sbEntries.append(entry).append("\n");
        }

        return sbEntries.toString();
    }

    public String getLastUserEntry()
    {
        return userInputList.get(userInputList.size() - 1);
    }

    public void clearUserEntries()
    {
        userInputList.clear();
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (String editText : userInputList)
        {
            builder.append(editText).append("\n");
        }

        return builder.toString();
    }

    public String getJSONStringFromThis()
    {
        return UserInput.getJSONStringFromEchoListObject(this);
    }

    public static UserInput getEchoListObjectFromJSON(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, UserInput.class);
    }

    public static String getJSONStringFromEchoListObject(UserInput userEntryList)
    {
        Gson gson = new Gson();
        return gson.toJson(userEntryList);
    }
}
