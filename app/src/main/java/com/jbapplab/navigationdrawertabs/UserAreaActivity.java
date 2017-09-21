package com.jbapplab.navigationdrawertabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserAreaActivity extends AppCompatActivity {

    //Navigation drawer declaration
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    /*Fragment utilities declaration
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //Set the toolbar as an action bar to later change the label
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_stuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         *

         mFragmentManager = getSupportFragmentManager();
         mFragmentTransaction = mFragmentManager.beginTransaction();
         mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();*/

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                //Can add more according to the buttons I will need
                //Added the addToBackStack("str") bit to use back button
                if (menuItem.getItemId() == R.id.nav_item_inbox) {
                    Toast.makeText(UserAreaActivity.this, "INBOX", Toast.LENGTH_LONG).show();
                    //FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    //xfragmentTransaction.replace(R.id.containerView, new TabFragment()).addToBackStack("str").commit();
                    //getSupportActionBar().setTitle("Home");
                }

                if (menuItem.getItemId() == R.id.nav_item_sent) {
                    Toast.makeText(UserAreaActivity.this, "SENT", Toast.LENGTH_LONG).show();
                    //FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    //fragmentTransaction.replace(R.id.containerView, new SentFragment()).addToBackStack("str").commit();
                    //This is to try and find the id of the item selected
                    //getSupportActionBar().setTitle("Sent");

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId_KEY");
        String firstName = intent.getStringExtra("firstName_KEY");
        String lastName = intent.getStringExtra("lastName_KEY");
        String username = intent.getStringExtra("username_KEY");
        String password = intent.getStringExtra("password_KEY");
        String email = intent.getStringExtra("email_KEY");
        //for int you need a default value in case it was not passed (-1)
        //int age = intent.getIntExtra("age", -1);

        Log.i("UserId-UserArea: ", userId);


        String message = firstName + lastName + ", welcome to your Anecdot user area" + password;
        tvWelcomeMessage.setText(message);
        etUsername.setText(username);
        etEmail.setText(email);
        //The empty quote is going to convert the age int to a string because we cannot display an int in a textview directly.
        //etAge.setText(age+"");
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        //This is to not accidentally exit the app
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit Anekdot?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            UserAreaActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
        else {
            super.onBackPressed();
        }
    }
}
