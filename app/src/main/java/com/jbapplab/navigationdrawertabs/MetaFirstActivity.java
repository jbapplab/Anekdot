package com.jbapplab.navigationdrawertabs;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MetaFirstActivity extends AppCompatActivity {

    //Navigation drawer declaration
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    //Fragment utilities declaration
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Fragment storyCoreFragment = new StoryCoreFragment();

    String userIdString, actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    String fullStoryForShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_first);

        /*
         * Here we need to retrieve the data that we passed through the intent from the UserAreaActivity
         */
        //Check if update
        Intent intent;
        intent = this.getIntent();
        userIdString = intent.getStringExtra("userId_KEY");
        firstName = intent.getStringExtra("firstName_KEY");
        lastName = intent.getStringExtra("lastName_KEY");
        username = intent.getStringExtra("username_KEY");
        password = intent.getStringExtra("password_KEY");
        email = intent.getStringExtra("email_KEY");
        actionString = intent.getExtras().getString("UPDATE_KEY");

        if ((intent.getExtras() != null) && (intent.getExtras().getString("UPDATE_KEY") != null) &&(actionString.equals("update"))) {
            storyIdString = intent.getExtras().getString("STORY_ID_KEY");
            storyTitle = (intent.getExtras().getString("STORY_TITLE_KEY"));
            ifOtherSpecify = intent.getExtras().getString("IF_OTHER_SPECIFY_KEY");
            authorIdString = intent.getExtras().getString("AUTHOR_ID_KEY");
            storyDescription = intent.getExtras().getString("STORY_DESCRIPTION_KEY");
            orientation = intent.getExtras().getString("ORIENTATION_KEY");
            complicatedAction = intent.getExtras().getString("COMPLICATED_ACTION_KEY");
            evaluation = intent.getExtras().getString("EVALUATION_KEY");
            resolution = intent.getExtras().getString("RESOLUTION_KEY");
            message = intent.getExtras().getString("MESSAGE_KEY");
            stageRelated = intent.getExtras().getString("STAGE_RELATED_KEY");
            contextRelated = intent.getExtras().getString("CONTEXT_RELATED_KEY");
            imageUrl = intent.getExtras().getString("IMAGE_URL_KEY");
        }


        //Set the toolbar as an action bar to later change the label
        Toolbar mainToolbar = findViewById(R.id.toolbarMetaFirst);
        setSupportActionBar(mainToolbar);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = findViewById(R.id.drawerLayoutMetaFirst);
        mNavigationView = findViewById(R.id.navigation_stuffMetaFirst);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        sendDataStoryCoreFragment();
        mFragmentTransaction.replace(R.id.containerViewMetaFirst, storyCoreFragment, "STORYCORE_TAG").commit();
        /*
        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            sendDataStoryCoreFragment();
            mFragmentTransaction.replace(R.id.containerViewMetaFirst, storyCoreFragment, "STORYCORE_TAG").addToBackStack("fragmentStack").commit();
        } else {
            storyCoreFragment = getSupportFragmentManager().findFragmentByTag("STORYCORE_TAG");
            Log.i("METAFIRSTACTIVITY", "Found the old fragment");
        }
        */
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
                    Intent intentGoToUserAreaActivity = new Intent(MetaFirstActivity.this, UserAreaActivity.class);
                    intentGoToUserAreaActivity.putExtra("userId_KEY", userIdString);
                    intentGoToUserAreaActivity.putExtra("firstName_KEY", firstName);
                    intentGoToUserAreaActivity.putExtra("lastName_KEY", lastName);
                    intentGoToUserAreaActivity.putExtra("username_KEY", username);
                    intentGoToUserAreaActivity.putExtra("password_KEY", password);
                    intentGoToUserAreaActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToUserAreaActivity);
                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(MetaFirstActivity.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(MetaFirstActivity.this, LoginActivity.class);
                                    MetaFirstActivity.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(MetaFirstActivity.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.putExtra("userId_KEY", userIdString);
                    intentGoToSelectCategoryActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSelectCategoryActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSelectCategoryActivity.putExtra("username_KEY", username);
                    intentGoToSelectCategoryActivity.putExtra("password_KEY", password);
                    intentGoToSelectCategoryActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(MetaFirstActivity.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("userId_KEY", userIdString);
                    intentGoToMyStoriesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyStoriesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyStoriesActivity.putExtra("username_KEY", username);
                    intentGoToMyStoriesActivity.putExtra("password_KEY", password);
                    intentGoToMyStoriesActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(MetaFirstActivity.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("userId_KEY", userIdString);
                    intentGoToMyFavouritesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyFavouritesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyFavouritesActivity.putExtra("username_KEY", username);
                    intentGoToMyFavouritesActivity.putExtra("password_KEY", password);
                    intentGoToMyFavouritesActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToMyFavouritesActivity);

                }

                //TODO REVERSE ORDER OF METAFIRST FIX THIS
                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(MetaFirstActivity.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intentGoToEvensFirstActivity.putExtra("userId_KEY", userIdString);
                    intentGoToEvensFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToEvensFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToEvensFirstActivity.putExtra("username_KEY", username);
                    intentGoToEvensFirstActivity.putExtra("password_KEY", password);
                    intentGoToEvensFirstActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    sendDataStoryCoreFragment();
                    xfragmentTransaction.replace(R.id.containerViewMetaFirst, storyCoreFragment).addToBackStack("str").commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(MetaFirstActivity.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("userId_KEY", userIdString);
                    intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSettingsActivity.putExtra("username_KEY", username);
                    intentGoToSettingsActivity.putExtra("password_KEY", password);
                    intentGoToSettingsActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(MetaFirstActivity.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("userId_KEY", userIdString);
                    intentGoToHelpActivity.putExtra("firstName_KEY", firstName);
                    intentGoToHelpActivity.putExtra("lastName_KEY", lastName);
                    intentGoToHelpActivity.putExtra("username_KEY", username);
                    intentGoToHelpActivity.putExtra("password_KEY", password);
                    intentGoToHelpActivity.putExtra("email_KEY", email);
                    MetaFirstActivity.this.startActivity(intentGoToHelpActivity);

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarMetaFirst);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    /*
   SEND DATA TO FRAGMENT
    */
    private void sendDataStoryCoreFragment() {
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("USERID_KEY", userIdString);
        bundle.putString("FIRSTNAME_KEY", firstName);
        bundle.putString("LASTNAME_KEY", lastName);
        bundle.putString("USERNAME_KEY", username);
        bundle.putString("PASSWORD_KEY", password);
        bundle.putString("EMAIL_KEY", email);
        bundle.putString("UPDATE_KEY", actionString);
        if ((actionString != null) && actionString.equals("update")){
            bundle.putString("STORY_ID_KEY", storyIdString);
            bundle.putString("STORY_TITLE_KEY", storyTitle);
            bundle.putString("IF_OTHER_SPECIFY_KEY", ifOtherSpecify);
            bundle.putString("AUTHOR_ID_KEY", authorIdString);
            bundle.putString("STORY_DESCRIPTION_KEY", storyDescription);
            bundle.putString("ORIENTATION_KEY", orientation);
            bundle.putString("COMPLICATED_ACTION_KEY", complicatedAction);
            bundle.putString("EVALUATION_KEY", evaluation);
            bundle.putString("RESOLUTION_KEY", resolution);
            bundle.putString("MESSAGE_KEY", message);
            bundle.putString("STAGE_RELATED_KEY", stageRelated);
            bundle.putString("CONTEXT_RELATED_KEY", contextRelated);
            bundle.putString("IMAGE_URL_KEY", imageUrl);
        }

        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        storyCoreFragment.setArguments(bundle);
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
                Intent intentGoToSettingsActivity = new Intent(MetaFirstActivity.this, SettingsActivity.class);
                intentGoToSettingsActivity.putExtra("userId_KEY", userIdString);
                intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                intentGoToSettingsActivity.putExtra("username_KEY", username);
                intentGoToSettingsActivity.putExtra("password_KEY", password);
                intentGoToSettingsActivity.putExtra("email_KEY", email);
                MetaFirstActivity.this.startActivity(intentGoToSettingsActivity);
                return true;

            case R.id.menu_item_share:

                //TODO User chose the "Share" action FULL STORY
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, fullStoryForShare);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShareStoryEvent(EventBusShareStoryMetaFirstActivity event) {
        fullStoryForShare = event.message;
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}