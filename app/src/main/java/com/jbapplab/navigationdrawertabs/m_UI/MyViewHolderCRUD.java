package com.jbapplab.navigationdrawertabs.m_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbapplab.navigationdrawertabs.R;

/**
 * Created by JohnB on 12/09/2017.
 */

public class MyViewHolderCRUD extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView storyId;
    TextView storyTitle;
    TextView storyCategory;
    TextView ifOtherSpecify;
    TextView authorId;
    TextView storyDescription;
    TextView storyEvents;
    TextView orientation;
    TextView complicatedAction;
    TextView evaluation;
    TextView resolution;
    TextView message;
    TextView storyMeta;
    TextView stageRelated;
    TextView contextRelated;
    TextView storyFull;
    TextView audienceStage;
    ImageView storyImage;
    StoryItemClickListener itemClickListener;

    public MyViewHolderCRUD(View itemView) {
        super(itemView);

        //storyId = itemView.findViewById(R.id.retrieveStoryId);
        storyTitle = itemView.findViewById(R.id.retrieveStoryTitle);
        storyCategory = itemView.findViewById(R.id.retrieveStoryCategory);
        ifOtherSpecify = itemView.findViewById(R.id.retrieveIfOtherSpecify);
        //authorId = itemView.findViewById(R.id.retrieveAuthorId);
        storyDescription = itemView.findViewById(R.id.retrieveStoryDescription);
        //storyEvents = itemView.findViewById(R.id.retrieveStoryEvents);
        //orientation = itemView.findViewById(R.id.retrieveOrientation);
        //complicatedAction = itemView.findViewById(R.id.retrieveComplicatedAction);
        //evaluation = itemView.findViewById(R.id.retrieveEvaluation);
        //resolution = itemView.findViewById(R.id.retrieveResolution);
        //message = itemView.findViewById(R.id.retrieveMessage);
        //storyMeta = itemView.findViewById(R.id.retrieveStoryMeta);
        //stageRelated = itemView.findViewById(R.id.retrieveStageRelated);
        //contextRelated = itemView.findViewById(R.id.retrieveContextRelated);
       // storyFull = itemView.findViewById(R.id.retrieveStoryFull);
        audienceStage = itemView.findViewById(R.id.retrieveAudienceStage);

        storyImage = itemView.findViewById(R.id.retrieveStoryImage);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick();
    }

    public void setItemClickListener(StoryItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
