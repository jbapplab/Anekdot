package com.jbapplab.navigationdrawertabs.m_StoryDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.m_UI.PicassoClient;

public class StoryDetailActivityCRUD extends AppCompatActivity {

    TextView retrieveStoryIdDetail, retrieveStoryTitleDetail, retrieveStoryCategoryDetail, retrieveIfOtherSpecifyDetail, retrieveAuthorIdDetail, retrieveStoryDescriptionDetail, retrieveStoryEventsDetail, retrieveOrientationDetail, retrieveComplicatedActionDetail, retrieveEvaluationDetail, retrieveResolutionDetail, retrieveMessageDetail, retrieveStoryMetaDetail, retrieveStageRelatedDetail, retrieveContextRelatedDetail, retrieveStoryFullDetail, retrieveAudienceStageDetail;
    ImageView retrieveStoryImageDetail;

    Button buttonSave, buttonUpdate, buttonDelete;

    //TODO GET IT FROM USER ACTIVITY
    String userId = "1";

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
        String storyId = intent.getExtras().getString("STORY_ID_KEY");
        String storyTitle = intent.getExtras().getString("STORY_TITLE_KEY");
        String storyCategory = intent.getExtras().getString("STORY_CATEGORY_KEY");
        String ifOtherSpecify = intent.getExtras().getString("IF_OTHER_SPECIFY_KEY");
        String authorId = intent.getExtras().getString("AUTHOR_ID_KEY");
        String storyDescription = intent.getExtras().getString("STORY_DESCRIPTION_KEY");
        String storyEvents = intent.getExtras().getString("STORY_EVENTS_KEY");
        String orientation = intent.getExtras().getString("ORIENTATION_KEY");
        String complicatedAction = intent.getExtras().getString("COMPLICATED_ACTION_KEY");
        String evaluation = intent.getExtras().getString("EVALUATION_KEY");
        String resolution = intent.getExtras().getString("RESOLUTION_KEY");
        String message = intent.getExtras().getString("MESSAGE_KEY");
        String storyMeta = intent.getExtras().getString("STORY_META_KEY");
        String stageRelated = intent.getExtras().getString("STAGE_RELATED_KEY");
        String contextRelated = intent.getExtras().getString("CONTEXT_RELATED_KEY");
        String storyFull = intent.getExtras().getString("STORY_FULL_KEY");
        String audienceStage = intent.getExtras().getString("AUDIENCE_STAGE_KEY");
        String imageUrl = intent.getExtras().getString("IMAGE_URL_KEY");

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

        //TODO HANDLE CLICK EVENTS

    }




}
