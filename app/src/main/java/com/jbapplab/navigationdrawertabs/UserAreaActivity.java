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

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");
        //for int you need a default value in case it was not passed (-1)
        //int age = intent.getIntExtra("age", -1);

        String message = firstName + lastName + ", welcome to your Anecdot user area" + password;
        tvWelcomeMessage.setText(message);
        etUsername.setText(username);
        etEmail.setText(email);
        //The empty quote is going to convert the age int to a string because we cannot display an int in a textview directly.
        //etAge.setText(age+"");
    }
}
