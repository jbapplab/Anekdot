package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_StoryDetailActivity.StoryDetailActivityCRUD;

import java.util.ArrayList;

/**
 * Created by JohnB on 12/09/2017.
 */

public class CustomAdapterCRUD extends RecyclerView.Adapter<MyViewHolderCRUD> {

    Context context;
    ArrayList<StoryCRUD> stories;

    //Variables
    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    public CustomAdapterCRUD(Context context, ArrayList<StoryCRUD> stories, String userIdAdapter, String firstNameAdapter, String lastNameAdapter, String usernameAdapter, String passwordAdapter, String emailAdapter) {
        this.context = context;
        this.stories = stories;

        userId = userIdAdapter;
        firstName = firstNameAdapter;
        lastName = lastNameAdapter;
        username = usernameAdapter;
        password = passwordAdapter;
        email = emailAdapter;
    }

    @Override
    public MyViewHolderCRUD onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.retrieve_story_item, parent, false);
        return new MyViewHolderCRUD(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCRUD holder, final int position) {

        final StoryCRUD story = stories.get(position);

        holder.storyTitle.setText(story.getStoryTitle());
        holder.storyCategory.setText(story.getStoryCategory());
        holder.ifOtherSpecify.setText(story.getIfOtherSpecify());
        holder.storyDescription.setText(story.getStoryDescription());

        switch (story.getAudienceStage()){
            case "Precontemplation":
                holder.audienceStage.setText(R.string.precontemplation_info);
                break;
            case "Contemplation":
                holder.audienceStage.setText(R.string.contemplation_info);
                break;
            case "Preparation":
                holder.audienceStage.setText(R.string.preparation_info);
                break;
            case "Action":
                holder.audienceStage.setText(R.string.action_info);
                break;
            default:
                holder.audienceStage.setText(R.string.default_info);
        }
        PicassoClient.downloadImage(context, story.getStoryCategory(), holder.storyImage);

        holder.setItemClickListener(new StoryItemClickListener() {
            @Override
            public void onItemClick() {
                openStoryDetailActivityCRUD(Integer.toString(story.getStoryId()), story.getStoryTitle(), story.getStoryCategory(), story.getIfOtherSpecify(), Integer.toString(story.getAuthorId()), story.getStoryDescription(), story.getStoryEvents(), story.getOrientation(), story.getComplicatedAction(), story.getEvaluation(), story.getResolution(), story.getMessage(), story.getStoryMeta(), story.getStageRelated(), story.getContextRelated(), story.getStoryFull(), story.getAudienceStage(), story.getImageUrl(), userId, firstName, lastName, username, password, email);
            }
        });


    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    private void openStoryDetailActivityCRUD(String storyId, String storyTitle, String storyCategory, String ifOtherSpecify, String authorId, String storyDescription, String storyEvents, String orientation, String complicatedAction, String evaluation, String resolution, String message, String storyMeta, String stageRelated, String contextRelated, String storyFull, String audienceStage, String imageUrl, String userId, String firstName, String lastName, String username, String password, String email){
        Intent intent = new Intent(context, StoryDetailActivityCRUD.class);

        //PACK DATA
        intent.putExtra("userId_KEY", userId);
        intent.putExtra("firstName_KEY", firstName);
        intent.putExtra("lastName_KEY", lastName);
        intent.putExtra("username_KEY", username);
        intent.putExtra("password_KEY", password);
        intent.putExtra("email_KEY", email);
        intent.putExtra("STORY_ID_KEY", storyId);
        intent.putExtra("STORY_TITLE_KEY", storyTitle);
        intent.putExtra("STORY_CATEGORY_KEY", storyCategory);
        intent.putExtra("IF_OTHER_SPECIFY_KEY", ifOtherSpecify);
        intent.putExtra("AUTHOR_ID_KEY", authorId);
        intent.putExtra("STORY_DESCRIPTION_KEY", storyDescription);
        intent.putExtra("STORY_EVENTS_KEY", storyEvents);
        intent.putExtra("ORIENTATION_KEY", orientation);
        intent.putExtra("COMPLICATED_ACTION_KEY", complicatedAction);
        intent.putExtra("EVALUATION_KEY", evaluation);
        intent.putExtra("RESOLUTION_KEY", resolution);
        intent.putExtra("MESSAGE_KEY", message);
        intent.putExtra("STORY_META_KEY", storyMeta);
        intent.putExtra("STAGE_RELATED_KEY", stageRelated);
        intent.putExtra("CONTEXT_RELATED_KEY", contextRelated);
        intent.putExtra("STORY_FULL_KEY", storyFull);
        intent.putExtra("AUDIENCE_STAGE_KEY", audienceStage);
        intent.putExtra("IMAGE_URL_KEY", imageUrl);

        context.startActivity(intent);
    }
}
