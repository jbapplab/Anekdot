package com.jbapplab.navigationdrawertabs.m_StoryDetailActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbapplab.navigationdrawertabs.HelpActivity;
import com.jbapplab.navigationdrawertabs.LoginActivity;
import com.jbapplab.navigationdrawertabs.MetaFirstActivity;
import com.jbapplab.navigationdrawertabs.MyFavouritesActivity;
import com.jbapplab.navigationdrawertabs.MyStoriesActivity;
import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.RetrieveStoriesCRUDActivity;
import com.jbapplab.navigationdrawertabs.SelectCategoryActivity;
import com.jbapplab.navigationdrawertabs.SettingsActivity;
import com.jbapplab.navigationdrawertabs.UserAreaActivity;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusOnServerSuccess;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.PicassoClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StoryDetailActivityCRUD extends AppCompatActivity {

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

    String success;

    TextView retrieveStoryIdDetail, retrieveStoryTitleDetail, retrieveStoryCategoryDetail, retrieveIfOtherSpecifyDetail, retrieveAuthorIdDetail, retrieveStoryDescriptionDetail, retrieveStoryEventsDetail, retrieveOrientationDetail, retrieveComplicatedActionDetail, retrieveEvaluationDetail, retrieveResolutionDetail, retrieveMessageDetail, retrieveStoryMetaDetail, retrieveStageRelatedDetail, retrieveContextRelatedDetail, retrieveStoryFullDetail, retrieveAudienceStageDetail;
    ImageView retrieveStoryImageDetail;

    Button buttonUpdate, buttonDelete;
    ImageButton buttonSave, buttonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_story_detail);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */
        Intent intentGet = getIntent();
        userId = intentGet.getStringExtra("userId_KEY");
        firstName = intentGet.getStringExtra("firstName_KEY");
        lastName = intentGet.getStringExtra("lastName_KEY");
        username = intentGet.getStringExtra("username_KEY");
        password = intentGet.getStringExtra("password_KEY");
        email = intentGet.getStringExtra("email_KEY");

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

                    Intent intentGoToUserAreaActivity = new Intent(StoryDetailActivityCRUD.this, UserAreaActivity.class);
                    intentGoToUserAreaActivity.putExtra("userId_KEY", userId);
                    intentGoToUserAreaActivity.putExtra("firstName_KEY", firstName);
                    intentGoToUserAreaActivity.putExtra("lastName_KEY", lastName);
                    intentGoToUserAreaActivity.putExtra("username_KEY", username);
                    intentGoToUserAreaActivity.putExtra("password_KEY", password);
                    intentGoToUserAreaActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToUserAreaActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(StoryDetailActivityCRUD.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(StoryDetailActivityCRUD.this, LoginActivity.class);
                                    StoryDetailActivityCRUD.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(StoryDetailActivityCRUD.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.putExtra("userId_KEY", userId);
                    intentGoToSelectCategoryActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSelectCategoryActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSelectCategoryActivity.putExtra("username_KEY", username);
                    intentGoToSelectCategoryActivity.putExtra("password_KEY", password);
                    intentGoToSelectCategoryActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(StoryDetailActivityCRUD.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyStoriesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyStoriesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyStoriesActivity.putExtra("username_KEY", username);
                    intentGoToMyStoriesActivity.putExtra("password_KEY", password);
                    intentGoToMyStoriesActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(StoryDetailActivityCRUD.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyFavouritesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyFavouritesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyFavouritesActivity.putExtra("username_KEY", username);
                    intentGoToMyFavouritesActivity.putExtra("password_KEY", password);
                    intentGoToMyFavouritesActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToMyFavouritesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(StoryDetailActivityCRUD.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToEvensFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToEvensFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToEvensFirstActivity.putExtra("username_KEY", username);
                    intentGoToEvensFirstActivity.putExtra("password_KEY", password);
                    intentGoToEvensFirstActivity.putExtra("email_KEY", email);
                    intentGoToEvensFirstActivity.putExtra("version_KEY", "detailed_guidance");
                    StoryDetailActivityCRUD.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    Intent intentGoToMetaFirstActivity = new Intent(StoryDetailActivityCRUD.this, MetaFirstActivity.class);
                    intentGoToMetaFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToMetaFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMetaFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMetaFirstActivity.putExtra("username_KEY", username);
                    intentGoToMetaFirstActivity.putExtra("password_KEY", password);
                    intentGoToMetaFirstActivity.putExtra("email_KEY", email);
                    intentGoToMetaFirstActivity.putExtra("version_KEY", "basic_instructions");
                    StoryDetailActivityCRUD.this.startActivity(intentGoToMetaFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(StoryDetailActivityCRUD.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                    intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSettingsActivity.putExtra("username_KEY", username);
                    intentGoToSettingsActivity.putExtra("password_KEY", password);
                    intentGoToSettingsActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(StoryDetailActivityCRUD.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("userId_KEY", userId);
                    intentGoToHelpActivity.putExtra("firstName_KEY", firstName);
                    intentGoToHelpActivity.putExtra("lastName_KEY", lastName);
                    intentGoToHelpActivity.putExtra("username_KEY", username);
                    intentGoToHelpActivity.putExtra("password_KEY", password);
                    intentGoToHelpActivity.putExtra("email_KEY", email);
                    StoryDetailActivityCRUD.this.startActivity(intentGoToHelpActivity);

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


        //retrieveStoryIdDetail = findViewById(R.id.retrieveStoryIdDetail);
        retrieveStoryTitleDetail = findViewById(R.id.retrieveStoryTitleDetail);
        retrieveStoryCategoryDetail = findViewById(R.id.retrieveStoryCategoryDetail);
        retrieveIfOtherSpecifyDetail = findViewById(R.id.retrieveIfOtherSpecifyDetail);
        //retrieveAuthorIdDetail = findViewById(R.id.retrieveAuthorIdDetail);
        //retrieveStoryDescriptionDetail = findViewById(R.id.retrieveStoryDescriptionDetail);
        //retrieveStoryEventsDetail = findViewById(R.id.retrieveStoryEventsDetail);
        //retrieveOrientationDetail = findViewById(R.id.retrieveOrientationDetail);
        //retrieveComplicatedActionDetail = findViewById(R.id.retrieveComplicatedActionDetail);
        //retrieveEvaluationDetail = findViewById(R.id.retrieveEvaluationDetail);
        //retrieveResolutionDetail = findViewById(R.id.retrieveResolutionDetail);
        //retrieveMessageDetail = findViewById(R.id.retrieveMessageDetail);
        //retrieveStoryMetaDetail = findViewById(R.id.retrieveStoryMetaDetail);
        //retrieveContextRelatedDetail = findViewById(R.id.retrieveContextRelatedDetail);
        //retrieveStageRelatedDetail = findViewById(R.id.retrieveStageRelatedDetail);
        retrieveStoryFullDetail = findViewById(R.id.retrieveStoryFullDetail);
        retrieveAudienceStageDetail = findViewById(R.id.retrieveAudienceStageDetail);
        retrieveStoryImageDetail = findViewById(R.id.retrieveStoryImageDetail);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);
        buttonShare = findViewById(R.id.buttonShare);


        //RECEIVE
        Intent intent = this.getIntent();
        final String storyId = intent.getExtras().getString("STORY_ID_KEY");
        final String storyTitle = intent.getExtras().getString("STORY_TITLE_KEY");
        final String storyCategory = intent.getExtras().getString("STORY_CATEGORY_KEY");
        final String ifOtherSpecify = intent.getExtras().getString("IF_OTHER_SPECIFY_KEY");
        final String authorId = intent.getExtras().getString("AUTHOR_ID_KEY");
        final String storyDescription = intent.getExtras().getString("STORY_DESCRIPTION_KEY");
        final String storyEvents = intent.getExtras().getString("STORY_EVENTS_KEY");
        final String orientation = intent.getExtras().getString("ORIENTATION_KEY");
        final String complicatedAction = intent.getExtras().getString("COMPLICATED_ACTION_KEY");
        final String evaluation = intent.getExtras().getString("EVALUATION_KEY");
        final String resolution = intent.getExtras().getString("RESOLUTION_KEY");
        final String message = intent.getExtras().getString("MESSAGE_KEY");
        final String storyMeta = intent.getExtras().getString("STORY_META_KEY");
        final String stageRelated = intent.getExtras().getString("STAGE_RELATED_KEY");
        final String contextRelated = intent.getExtras().getString("CONTEXT_RELATED_KEY");
        final String storyFull = intent.getExtras().getString("STORY_FULL_KEY");
        final String audienceStage = intent.getExtras().getString("AUDIENCE_STAGE_KEY");
        final String imageUrl = intent.getExtras().getString("IMAGE_URL_KEY");

        //BIND
        //retrieveStoryIdDetail.setText(storyId);
        retrieveStoryTitleDetail.setText(storyTitle);
        retrieveStoryCategoryDetail.setText(storyCategory);
        retrieveIfOtherSpecifyDetail.setText(ifOtherSpecify);
        //retrieveAuthorIdDetail.setText(authorId);
        //retrieveStoryDescriptionDetail.setText(storyDescription);
        //retrieveStoryEventsDetail.setText(storyEvents);
        //retrieveOrientationDetail.setText(orientation);
        //retrieveComplicatedActionDetail.setText(complicatedAction);
        //retrieveEvaluationDetail.setText(evaluation);
        //retrieveResolutionDetail.setText(resolution);
        //retrieveMessageDetail.setText(message);
        //retrieveStoryMetaDetail.setText(storyMeta);
        //retrieveStageRelatedDetail.setText(stageRelated);
        //retrieveContextRelatedDetail.setText(contextRelated);
        retrieveStoryFullDetail.setText(imageUrl);

        switch (audienceStage){
            case "Precontemplation":
                retrieveAudienceStageDetail.setText(R.string.precontemplation_info);
                break;
            case "Contemplation":
                retrieveAudienceStageDetail.setText(R.string.contemplation_info);
                break;
            case "Preparation":
                retrieveAudienceStageDetail.setText(R.string.preparation_info);
                break;
            case "Action":
                retrieveAudienceStageDetail.setText(R.string.action_info);
                break;
            default:
                retrieveAudienceStageDetail.setText(R.string.default_info);
        }

        PicassoClient.downloadImage(this, storyCategory, retrieveStoryImageDetail);

        //Rename title
        mainToolbar.setTitle(storyTitle);

        //Log.i("USER_ID", userId);
        //Log.i("AUTHOR_ID", authorId);
        if(authorId.equals(userId)){
            buttonUpdate.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonSave.setEnabled(true);
        }

        //TODO HANDLE CLICK EVENT

        //SAVE TO FAVOURITES
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(StoryDetailActivityCRUD.this)
                        .setTitle("Save this story to your Favourites?")
                        .setMessage("Are you sure you want to save this story in your favourites?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                            new MySQLClientCRUD(StoryDetailActivityCRUD.this, userId, firstName, lastName, username, password, email).favourite(Integer.parseInt(storyId), Integer.parseInt(userId));

                                /* Do not move to the new screen

                                Intent intentFavouriteStory = new Intent(StoryDetailActivityCRUD.this, MyFavouritesActivity.class);
                                intentFavouriteStory.putExtra("userId_KEY", userId);
                                intentFavouriteStory.putExtra("firstName_KEY", firstName);
                                intentFavouriteStory.putExtra("lastName_KEY", lastName);
                                intentFavouriteStory.putExtra("username_KEY", username);
                                intentFavouriteStory.putExtra("password_KEY", password);
                                intentFavouriteStory.putExtra("email_KEY", email);
                                StoryDetailActivityCRUD.this.startActivity(intentFavouriteStory);

                                */

                            }
                        }).create().show();
                //Toast.makeText(StoryDetailActivityCRUD.this, "Story saved in favourites second?.", Toast.LENGTH_SHORT).show();


            }
        });

        //SHARE STORY
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, retrieveStoryFullDetail.getText());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });

        //DELETE OWN STORY
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(StoryDetailActivityCRUD.this)
                        .setTitle("Delete your story?")
                        .setMessage("Are you sure you want to delete your story permanently? \n" +
                                "Other users will not be able to access it any more!")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                new MySQLClientCRUD(StoryDetailActivityCRUD.this, userId, firstName, lastName, username, password, email).delete(Integer.parseInt(storyId));

                                /*
                                Intent intentDeleteStory = new Intent(StoryDetailActivityCRUD.this, MyStoriesActivity.class);
                                intentDeleteStory.putExtra("userId_KEY", userId);
                                intentDeleteStory.putExtra("firstName_KEY", firstName);
                                intentDeleteStory.putExtra("lastName_KEY", lastName);
                                intentDeleteStory.putExtra("username_KEY", username);
                                intentDeleteStory.putExtra("password_KEY", password);
                                intentDeleteStory.putExtra("email_KEY", email);
                                StoryDetailActivityCRUD.this.startActivity(intentDeleteStory);
                                */

                            }
                        }).create().show();
            }
        });

        //UPDATE OWN STORY
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentSendUpdate = new Intent(StoryDetailActivityCRUD.this, MetaFirstActivity.class);

                //PACK DATA
                intentSendUpdate.putExtra("UPDATE_KEY", "update");
                intentSendUpdate.putExtra("userId_KEY", userId);
                intentSendUpdate.putExtra("firstName_KEY", firstName);
                intentSendUpdate.putExtra("lastName_KEY", lastName);
                intentSendUpdate.putExtra("username_KEY", username);
                intentSendUpdate.putExtra("password_KEY", password);
                intentSendUpdate.putExtra("email_KEY", email);
                intentSendUpdate.putExtra("STORY_ID_KEY", storyId);
                intentSendUpdate.putExtra("STORY_TITLE_KEY", storyTitle);
                intentSendUpdate.putExtra("STORY_CATEGORY_KEY", storyCategory);
                intentSendUpdate.putExtra("IF_OTHER_SPECIFY_KEY", ifOtherSpecify);
                intentSendUpdate.putExtra("AUTHOR_ID_KEY", authorId);
                intentSendUpdate.putExtra("STORY_DESCRIPTION_KEY", storyDescription);
                //intentSendUpdate.putExtra("STORY_EVENTS_KEY", storyEvents);
                intentSendUpdate.putExtra("ORIENTATION_KEY", orientation);
                intentSendUpdate.putExtra("COMPLICATED_ACTION_KEY", complicatedAction);
                intentSendUpdate.putExtra("EVALUATION_KEY", evaluation);
                intentSendUpdate.putExtra("RESOLUTION_KEY", resolution);
                intentSendUpdate.putExtra("MESSAGE_KEY", message);
                //intentSendUpdate.putExtra("STORY_META_KEY", storyMeta);
                intentSendUpdate.putExtra("STAGE_RELATED_KEY", stageRelated);
                intentSendUpdate.putExtra("CONTEXT_RELATED_KEY", contextRelated);
                //intentSendUpdate.putExtra("STORY_FULL_KEY", storyFull);
                intentSendUpdate.putExtra("AUDIENCE_STAGE_KEY", audienceStage);
                intentSendUpdate.putExtra("IMAGE_URL_KEY", imageUrl);
                intentSendUpdate.putExtra("version_KEY", "update_story");

                StoryDetailActivityCRUD.this.startActivity(intentSendUpdate);
            }
        });

    }

    /*
    CREATE THE OPTIONS MENU
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);

        MenuItem menuShareStory = menu.findItem(R.id.menu_item_share_details);
        menuShareStory.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    /*
    HANDLE CLICK ON MENU ACTIONS
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notifications:
                Intent intentGoToSettingsActivity = new Intent(StoryDetailActivityCRUD.this, SettingsActivity.class);
                intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                intentGoToSettingsActivity.putExtra("username_KEY", username);
                intentGoToSettingsActivity.putExtra("password_KEY", password);
                intentGoToSettingsActivity.putExtra("email_KEY", email);
                StoryDetailActivityCRUD.this.startActivity(intentGoToSettingsActivity);
                return true;

            case R.id.action_logout:
                new AlertDialog.Builder(StoryDetailActivityCRUD.this)
                        .setTitle("Log Out?")
                        .setMessage("Are you sure you want to log out?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent intentGoToLoginActivity = new Intent(StoryDetailActivityCRUD.this, LoginActivity.class);
                                StoryDetailActivityCRUD.this.startActivity(intentGoToLoginActivity);

                            }
                        }).create().show();
                return true;

            case R.id.menu_item_share_story:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, retrieveStoryFullDetail.getText());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerSuccess(EventBusOnServerSuccess eventBusOnServerSuccess) {
        success = eventBusOnServerSuccess.message;

        switch (success) {
            case "success_favourite":
                Log.i("Success: ", "Favourite!");
                break;
            case "success_delete":
                Intent intentDeleteStory = new Intent(StoryDetailActivityCRUD.this, MyStoriesActivity.class);
                intentDeleteStory.putExtra("userId_KEY", userId);
                intentDeleteStory.putExtra("firstName_KEY", firstName);
                intentDeleteStory.putExtra("lastName_KEY", lastName);
                intentDeleteStory.putExtra("username_KEY", username);
                intentDeleteStory.putExtra("password_KEY", password);
                intentDeleteStory.putExtra("email_KEY", email);
                StoryDetailActivityCRUD.this.startActivity(intentDeleteStory);
                break;
            default:
                Log.i("StoryDetailActivityCRUD", "Event Bus Error");
        }
    }
}
