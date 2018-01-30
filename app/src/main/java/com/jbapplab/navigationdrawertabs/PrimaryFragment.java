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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
        lastName = this.getArguments().getString("lastName_KEY"); //not immediately used
        username = this.getArguments().getString("username_KEY");
        password = this.getArguments().getString("password_KEY"); //not immediately used
        email = this.getArguments().getString("email_KEY"); //not immediately used

        return inflater.inflate(R.layout.primary_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final TextView tvWelcomeMessage = getView().findViewById(R.id.tvWelcomeMessage);

        //TODO SEND THIS BACK AND FORTH TO ALL
        Log.i("UserId-UserArea: ", userId);


        String message = "Hi "+ firstName + ", how are you going to influence the word today?";
        tvWelcomeMessage.setText(message);

        ImageButton create_story = getView().findViewById(R.id.imageButtonCreate);
        ImageButton categories = getView().findViewById(R.id.imageButtonCategories);
        ImageButton my_favourites = getView().findViewById(R.id.imageButtonFavourites);

        create_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the Activity
                Intent createIntent = new Intent(getActivity(), MetaFirstActivity.class);
                createIntent.putExtra("USERID_KEY", userId);
                //Then we told the current activity to perform that intent
                getActivity().startActivity(createIntent);
            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the Activity
                Intent categoriesIntent = new Intent(getActivity(), SelectCategoryActivity.class);
                categoriesIntent.putExtra("USERID_KEY", userId);
                //Then we told the current activity to perform that intent
                getActivity().startActivity(categoriesIntent);
            }
        });

        my_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the Activity
                Intent favouritesIntent = new Intent(getActivity(), MyFavouritesActivity.class);
                favouritesIntent.putExtra("USERID_KEY", userId);
                //Then we told the current activity to perform that intent
                getActivity().startActivity(favouritesIntent);
            }
        });

        //etUsername.setText(username);
        //etEmail.setText(email);
        //The empty quote is going to convert the age int to a string because we cannot display an int in a textview directly.
        //etAge.setText(age+"");
    }
}