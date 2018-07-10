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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        //Use shared preferences to retrieve user info
        SharedPreferences userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userId = userInfo.getString("userId", "");
        firstName = userInfo.getString("firstName", "");
        lastName = userInfo.getString("lastName", "");
        username = userInfo.getString("username", "");
        password = userInfo.getString("password", "");
        email = userInfo.getString("email", "");


        return inflater.inflate(R.layout.primary_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final TextView tvWelcomeMessage = getView().findViewById(R.id.tvWelcomeMessage);

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
                createIntent.putExtra("userId_KEY", userId);
                createIntent.putExtra("firstName_KEY", firstName);
                createIntent.putExtra("lastName_KEY", lastName);
                createIntent.putExtra("username_KEY", username);
                createIntent.putExtra("password_KEY", password);
                createIntent.putExtra("email_KEY", email);
                createIntent.putExtra("version_KEY", "detailed_guidance");
                //Then we told the current activity to perform that intent
                getActivity().startActivity(createIntent);
            }
        });

        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the Activity
                Intent categoriesIntent = new Intent(getActivity(), SelectCategoryActivity.class);
                categoriesIntent.putExtra("userId_KEY", userId);
                categoriesIntent.putExtra("firstName_KEY", firstName);
                categoriesIntent.putExtra("lastName_KEY", lastName);
                categoriesIntent.putExtra("username_KEY", username);
                categoriesIntent.putExtra("password_KEY", password);
                categoriesIntent.putExtra("email_KEY", email);
                //Then we told the current activity to perform that intent
                getActivity().startActivity(categoriesIntent);
            }
        });

        my_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the Activity
                Intent favouritesIntent = new Intent(getActivity(), MyFavouritesActivity.class);
                favouritesIntent.putExtra("userId_KEY", userId);
                favouritesIntent.putExtra("firstName_KEY", firstName);
                favouritesIntent.putExtra("lastName_KEY", lastName);
                favouritesIntent.putExtra("username_KEY", username);
                favouritesIntent.putExtra("password_KEY", password);
                favouritesIntent.putExtra("email_KEY", email);
                //Then we told the current activity to perform that intent
                getActivity().startActivity(favouritesIntent);
            }
        });
    }
}