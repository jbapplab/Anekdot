package com.jbapplab.navigationdrawertabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import java.util.ArrayList;

public class MyFavouritesActivity extends AppCompatActivity {

    //Navigation drawer declaration
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    //Variables
    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites);

        /**
         * Here we need to retrieve the data that we passed through the intent
         */
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId_KEY");
        firstName = intent.getStringExtra("firstName_KEY");
        lastName = intent.getStringExtra("lastName_KEY");
        username = intent.getStringExtra("username_KEY");
        password = intent.getStringExtra("password_KEY");
        email = intent.getStringExtra("email_KEY");

        //Set the toolbar as an action bar to later change the label
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_stuff);

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
                    Intent intentGoToUserAreaActivity = new Intent(MyFavouritesActivity.this, UserAreaActivity.class);
                    intentGoToUserAreaActivity.putExtra("userId_KEY", userId);
                    intentGoToUserAreaActivity.putExtra("firstName_KEY", firstName);
                    intentGoToUserAreaActivity.putExtra("lastName_KEY", lastName);
                    intentGoToUserAreaActivity.putExtra("username_KEY", username);
                    intentGoToUserAreaActivity.putExtra("password_KEY", password);
                    intentGoToUserAreaActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToUserAreaActivity);
                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(MyFavouritesActivity.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(MyFavouritesActivity.this, LoginActivity.class);
                                    MyFavouritesActivity.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(MyFavouritesActivity.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.putExtra("userId_KEY", userId);
                    intentGoToSelectCategoryActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSelectCategoryActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSelectCategoryActivity.putExtra("username_KEY", username);
                    intentGoToSelectCategoryActivity.putExtra("password_KEY", password);
                    intentGoToSelectCategoryActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(MyFavouritesActivity.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyStoriesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyStoriesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyStoriesActivity.putExtra("username_KEY", username);
                    intentGoToMyStoriesActivity.putExtra("password_KEY", password);
                    intentGoToMyStoriesActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(MyFavouritesActivity.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyFavouritesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyFavouritesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyFavouritesActivity.putExtra("username_KEY", username);
                    intentGoToMyFavouritesActivity.putExtra("password_KEY", password);
                    intentGoToMyFavouritesActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToMyFavouritesActivity);

                }

                //TODO REVERSE ORDER OF METAFIRST
                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(MyFavouritesActivity.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToEvensFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToEvensFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToEvensFirstActivity.putExtra("username_KEY", username);
                    intentGoToEvensFirstActivity.putExtra("password_KEY", password);
                    intentGoToEvensFirstActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    Intent intentGoToMetaFirstActivity = new Intent(MyFavouritesActivity.this, MetaFirstActivity.class);
                    intentGoToMetaFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToMetaFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMetaFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMetaFirstActivity.putExtra("username_KEY", username);
                    intentGoToMetaFirstActivity.putExtra("password_KEY", password);
                    intentGoToMetaFirstActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToMetaFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(MyFavouritesActivity.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                    intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSettingsActivity.putExtra("username_KEY", username);
                    intentGoToSettingsActivity.putExtra("password_KEY", password);
                    intentGoToSettingsActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(MyFavouritesActivity.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("userId_KEY", userId);
                    intentGoToHelpActivity.putExtra("firstName_KEY", firstName);
                    intentGoToHelpActivity.putExtra("lastName_KEY", lastName);
                    intentGoToHelpActivity.putExtra("username_KEY", username);
                    intentGoToHelpActivity.putExtra("password_KEY", password);
                    intentGoToHelpActivity.putExtra("email_KEY", email);
                    MyFavouritesActivity.this.startActivity(intentGoToHelpActivity);

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        //This sets the loading progress bar
        progressBar = findViewById(R.id.progressBarRetrieveFavourites);

        //REFERENCE VIEWS
        recyclerView = findViewById(R.id.recyclerViewRetrieveFavouritesStoriesCRUD);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyFavouritesActivity.this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapterCRUD customAdapterCRUD = new CustomAdapterCRUD(this, dummyArrayList, userId, firstName, lastName, username, password, email);
        recyclerView.setAdapter(customAdapterCRUD);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeLayoutRetrieveFavouritesStoriesCRUD);

        //Retrieve
        mainToolbar.setTitle("My Favourites");
        new MySQLClientCRUD(MyFavouritesActivity.this, userId, firstName, lastName, username, password, email).retrieveMyFavourites(recyclerView, swipeRefreshLayout, progressBar);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(true);
                }
                new MySQLClientCRUD(MyFavouritesActivity.this, userId, firstName, lastName, username, password, email).retrieveMyFavourites(recyclerView, swipeRefreshLayout, progressBar);
            }
        });
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
                Intent intentGoToSettingsActivity = new Intent(MyFavouritesActivity.this, SettingsActivity.class);
                intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                intentGoToSettingsActivity.putExtra("username_KEY", username);
                intentGoToSettingsActivity.putExtra("password_KEY", password);
                intentGoToSettingsActivity.putExtra("email_KEY", email);
                MyFavouritesActivity.this.startActivity(intentGoToSettingsActivity);
                return true;

            case R.id.menu_item_share:
                Toast.makeText(MyFavouritesActivity.this, "If you want to share a story look at it more closely!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
