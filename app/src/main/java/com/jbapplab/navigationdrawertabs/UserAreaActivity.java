package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        //for int you need a default value in case it was not passed (-1)
        int age = intent.getIntExtra("age", -1);

        String message = name + ", welcome to your Anecdot user area";
        tvWelcomeMessage.setText(message);
        etUsername.setText(username);
        //The empty quote is going to convert the age int to a string because we cannot display an int in a textview directly.
        etAge.setText(age+"");
    }
}
