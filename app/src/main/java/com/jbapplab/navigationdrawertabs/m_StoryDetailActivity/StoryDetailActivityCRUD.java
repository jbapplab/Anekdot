package com.jbapplab.navigationdrawertabs.m_StoryDetailActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jbapplab.navigationdrawertabs.LoginActivity;
import com.jbapplab.navigationdrawertabs.LoginRequest;
import com.jbapplab.navigationdrawertabs.MainActivity;
import com.jbapplab.navigationdrawertabs.MetaFirstActivity;
import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.RetrieveStoriesCRUDActivity;
import com.jbapplab.navigationdrawertabs.UserAreaActivity;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.PicassoClient;

import org.json.JSONException;
import org.json.JSONObject;

public class StoryDetailActivityCRUD extends AppCompatActivity {

    TextView retrieveStoryIdDetail, retrieveStoryTitleDetail, retrieveStoryCategoryDetail, retrieveIfOtherSpecifyDetail, retrieveAuthorIdDetail, retrieveStoryDescriptionDetail, retrieveStoryEventsDetail, retrieveOrientationDetail, retrieveComplicatedActionDetail, retrieveEvaluationDetail, retrieveResolutionDetail, retrieveMessageDetail, retrieveStoryMetaDetail, retrieveStageRelatedDetail, retrieveContextRelatedDetail, retrieveStoryFullDetail, retrieveAudienceStageDetail;
    ImageView retrieveStoryImageDetail;

    Button buttonSave, buttonUpdate, buttonDelete;

    //TODO GET IT FROM USER ACTIVITY
   final String userId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_story_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        retrieveStoryIdDetail = findViewById(R.id.retrieveStoryIdDetail);
        retrieveStoryTitleDetail = findViewById(R.id.retrieveStoryTitleDetail);
        retrieveStoryCategoryDetail = findViewById(R.id.retrieveStoryCategoryDetail);
        retrieveIfOtherSpecifyDetail = findViewById(R.id.retrieveIfOtherSpecifyDetail);
        retrieveAuthorIdDetail = findViewById(R.id.retrieveAuthorIdDetail);
        retrieveStoryDescriptionDetail = findViewById(R.id.retrieveStoryDescriptionDetail);
        retrieveStoryEventsDetail = findViewById(R.id.retrieveStoryEventsDetail);
        retrieveOrientationDetail = findViewById(R.id.retrieveOrientationDetail);
        retrieveComplicatedActionDetail = findViewById(R.id.retrieveComplicatedActionDetail);
        retrieveEvaluationDetail = findViewById(R.id.retrieveEvaluationDetail);
        retrieveResolutionDetail = findViewById(R.id.retrieveResolutionDetail);
        retrieveMessageDetail = findViewById(R.id.retrieveMessageDetail);
        retrieveStoryMetaDetail = findViewById(R.id.retrieveStoryMetaDetail);
        retrieveContextRelatedDetail = findViewById(R.id.retrieveContextRelatedDetail);
        retrieveStageRelatedDetail = findViewById(R.id.retrieveStageRelatedDetail);
        retrieveStoryFullDetail = findViewById(R.id.retrieveStoryFullDetail);
        retrieveAudienceStageDetail = findViewById(R.id.retrieveAudienceStageDetail);
        retrieveStoryImageDetail = findViewById(R.id.retrieveStoryImageDetail);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);


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
        retrieveStoryIdDetail.setText(storyId);
        retrieveStoryTitleDetail.setText(storyTitle);
        retrieveStoryCategoryDetail.setText(storyCategory);
        retrieveIfOtherSpecifyDetail.setText(ifOtherSpecify);
        retrieveAuthorIdDetail.setText(authorId);
        retrieveStoryDescriptionDetail.setText(storyDescription);
        retrieveStoryEventsDetail.setText(storyEvents);
        retrieveOrientationDetail.setText(orientation);
        retrieveComplicatedActionDetail.setText(complicatedAction);
        retrieveEvaluationDetail.setText(evaluation);
        retrieveResolutionDetail.setText(resolution);
        retrieveMessageDetail.setText(message);
        retrieveStoryMetaDetail.setText(storyMeta);
        retrieveStageRelatedDetail.setText(stageRelated);
        retrieveContextRelatedDetail.setText(contextRelated);
        retrieveStoryFullDetail.setText(storyFull);
        retrieveAudienceStageDetail.setText(audienceStage);
        PicassoClient.downloadImage(this, imageUrl, retrieveStoryImageDetail);

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

                new MySQLClientCRUD(StoryDetailActivityCRUD.this).favourite(Integer.parseInt(storyId), Integer.parseInt(userId));

                //Toast.makeText(StoryDetailActivityCRUD.this, "Story saved in favourites second?.", Toast.LENGTH_SHORT).show();
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
                                new MySQLClientCRUD(StoryDetailActivityCRUD.this).delete(Integer.parseInt(storyId));
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
                intentSendUpdate.putExtra("USERID_KEY", userId);
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

                StoryDetailActivityCRUD.this.startActivity(intentSendUpdate);
            }
        });

    }

}
