package com.example.echo_app;

import android.widget.EditText;

import java.util.ArrayList;

public class UserInput {
    private final ArrayList<String> userInputList;

    public UserInput(){
        this.userInputList = new ArrayList<>();
    }

    public void addUserInput(EditText editText){
        this.userInputList.add(editText.getText().toString());
    }

    public ArrayList<String> getUserInputList(){
       return this.userInputList;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();

        for (String editText : userInputList){
            builder.append(editText).append("\n");
        }

        return builder.toString();
    }
}
