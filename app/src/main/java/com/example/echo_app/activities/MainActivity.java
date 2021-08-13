package com.example.echo_app.activities;

import android.os.Bundle;

import com.example.echo_app.R;
import com.example.echo_app.model.UserInput;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import com.example.echo_app.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserInput userInputList;
    private TextView menuView;
    private boolean mPrefShowOldEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        userInputList = new UserInput();
        menuView = findViewById(R.id.only_with_menu);
        EditText userInput = findViewById(R.id.userInput); //the EditText (user's input)
        TextView output = findViewById(R.id.output); //the second TextView

        /*
        When user clicks on FAB, the contents of output variable (initially the about message)
        changes to what is stored in userInput. If no text has been entered, the SnackBar appears,
        displaying an error message telling users to enter text before clicking on FAB.
        */

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userInput.getText().length() == 0 || userInput.getText() == null){
                    Snackbar.make(view, "Please make sure to enter some text before clicking " +
                            "the echo button", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    userInputList.addUserInput(userInput);
                    output.setText(userInput.getText());

                    if (mPrefShowOldEntries && menuView != null)
                    {
                        menuView.setText(userInputList.toString());
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_toggle_show_previous)
        {
            boolean toggle = toggleMenuItem(item);
            showEntries(toggle);
            mPrefShowOldEntries = item.isChecked();
            return true;
        }

        else if (id == R.id.about)
        {
          showAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearList(MenuItem item)
    {
        menuView.setText("");
        userInputList.clearUserEntries();
    }

    private boolean toggleMenuItem(MenuItem item)
    {
        item.setChecked(!item.isChecked());
        return item.isChecked();
    }

    private void showEntries(boolean toggledValue)
    {
        if (toggledValue)
        {
            menuView.setText(userInputList.toString());
            menuView.setMovementMethod(new ScrollingMovementMethod());
        }
        else
        {
            menuView.setText("");
        }
    }

    private void showAbout()
    {

    }
}