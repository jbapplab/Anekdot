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

    TextView storyTitle;
    TextView storyCategory;
    TextView ifOtherSpecify;
    TextView storyDescription;
    TextView audienceStage;
    ImageView storyImage;
    StoryItemClickListener itemClickListener;

    public MyViewHolderCRUD(View itemView) {
        super(itemView);

        storyTitle = itemView.findViewById(R.id.retrieveStoryTitle);
        storyCategory = itemView.findViewById(R.id.retrieveStoryCategory);
        ifOtherSpecify = itemView.findViewById(R.id.retrieveIfOtherSpecify);
        storyDescription = itemView.findViewById(R.id.retrieveStoryDescription);
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
