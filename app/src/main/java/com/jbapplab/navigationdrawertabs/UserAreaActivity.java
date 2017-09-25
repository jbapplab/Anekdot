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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class UserAreaActivity extends AppCompatActivity {

    //Navigation drawer declaration
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    //Fragment utilities declaration
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TabFragment tabFragment = new TabFragment();

    //Variables
    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId_KEY");
        firstName = intent.getStringExtra("firstName_KEY");
        lastName = intent.getStringExtra("lastName_KEY");
        username = intent.getStringExtra("username_KEY");
        password = intent.getStringExtra("password_KEY");
        email = intent.getStringExtra("email_KEY");
        //for int you need a default value in case it was not passed (-1)
        //int age = intent.getIntExtra("age", -1);

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
         */

         mFragmentManager = getSupportFragmentManager();
         mFragmentTransaction = mFragmentManager.beginTransaction();
         sendDataTabFragment();
         mFragmentTransaction.replace(R.id.containerView, tabFragment).commit();


        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                //Can add more according to the buttons I will need
                //Added the addToBackStack("str") bit to use back button
                if (menuItem.getItemId() == R.id.nav_item_home) {
                    //Toast.makeText(UserAreaActivity.this, "Home", Toast.LENGTH_LONG).show();
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    sendDataTabFragment();
                    xfragmentTransaction.replace(R.id.containerView, tabFragment).addToBackStack("str").commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(UserAreaActivity.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(UserAreaActivity.this, LoginActivity.class);
                                    UserAreaActivity.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(UserAreaActivity.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(UserAreaActivity.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(UserAreaActivity.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToMyFavouritesActivity);

                }

                //TODO REVERSE ORDER OF METAFIRST
                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(UserAreaActivity.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    Intent intentGoToMetaFirstActivity = new Intent(UserAreaActivity.this, MetaFirstActivity.class);
                    intentGoToMetaFirstActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToMetaFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(UserAreaActivity.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(UserAreaActivity.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("USERID_KEY", userId);
                    UserAreaActivity.this.startActivity(intentGoToHelpActivity);

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
                    .setTitle("Exit Anecdot?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        }
                    }).create().show();
        }
        else {
            super.onBackPressed();
        }
    }

    /*
    SEND DATA TO FRAGMENT
     */
    private void sendDataTabFragment() {
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("userId_KEY", userId);
        bundle.putString("firstName_KEY", firstName);
        bundle.putString("lastName_KEY", lastName);
        bundle.putString("username_KEY", username);
        bundle.putString("password_KEY", password);
        bundle.putString("email_KEY", email);

        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        tabFragment.setArguments(bundle);
    }

    /*
    CREATE THE OPTIONS MENU
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    HANDLE CLICK ON MENU ACTIONS
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_item_share:
                //TODO User chose the "Share" action WE PUT CONTEXT SPECIFIC SHARE
                //Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Anecdote user: "+firstName+" "+lastName+" - "+username+" - "+email);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
