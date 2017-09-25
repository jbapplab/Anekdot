package com.jbapplab.navigationdrawertabs;

/**
 Now this is an inner fragment for the TabLayout Fragment .
 So basically, TabLayout Is an Fragment for the MainActivity
 while this is an fragment for the TabLayout .

 Here , lets create a new class file and call it PrimaryFragment
 and add the following content . It is a very straightforward
 implementation of a fragment which inflates a layout from the
 onCreateView method which replaces the content of the ViewPager
 holding it.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class PrimaryFragment extends Fragment {

    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */

        userId = this.getArguments().getString("userId_KEY");
        firstName = this.getArguments().getString("firstName_KEY");
        lastName = this.getArguments().getString("lastName_KEY");
        username = this.getArguments().getString("username_KEY");
        password = this.getArguments().getString("password_KEY");
        email = this.getArguments().getString("email_KEY");

        return inflater.inflate(R.layout.primary_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final EditText etEmail = getView().findViewById(R.id.etEmail);
        final EditText etUsername = getView().findViewById(R.id.etUsername);
        final TextView tvWelcomeMessage = getView().findViewById(R.id.tvWelcomeMessage);

        Log.i("UserId-UserArea: ", userId);


        String message = firstName + lastName + ", welcome to your Anecdot user area" + password;
        tvWelcomeMessage.setText(message);
        etUsername.setText(username);
        etEmail.setText(email);
        //The empty quote is going to convert the age int to a string because we cannot display an int in a textview directly.
        //etAge.setText(age+"");
    }
}