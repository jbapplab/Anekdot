package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.m_DataObject.Story;
import com.jbapplab.navigationdrawertabs.m_StoryDetailActivity.StoryDetailActivity;

import java.util.ArrayList;

/**
 * Created by JohnB on 12/09/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<Story> stories;

    public CustomAdapter(Context context, ArrayList<Story> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Story story = stories.get(position);

        holder.storyId.setText(story.getId());
        holder.firstName.setText(story.getFirstName());
        holder.lastName.setText(story.getLastName());
        holder.username.setText(story.getUsername());
        holder.password.setText(story.getPassword());
        holder.email.setText(story.getEmail());
        PicassoClient.downloadImage(context, story.getImageUrl(), holder.storyImage);

        holder.setItemClickListener(new StoryItemClickListener() {
            @Override
            public void onItemClick() {
                openStoryDetailActivity(story.getId(), story.getFirstName(), story.getLastName(), story.getUsername(), story.getPassword(), story.getEmail(), story.getImageUrl());
            }
        });


    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    private void openStoryDetailActivity(String id, String firstName, String lastName, String username, String password, String email, String imageUrl){
        Intent intent = new Intent(context, StoryDetailActivity.class);

        //PACK DATA
        intent.putExtra("ID_KEY", id);
        intent.putExtra("FIRST_NAME_KEY", firstName);
        intent.putExtra("LAST_NAME_KEY", lastName);
        intent.putExtra("USERNAME_KEY", username);
        intent.putExtra("PASSWORD_KEY", password);
        intent.putExtra("EMAIL_KEY", email);
        intent.putExtra("IMAGE_URL_KEY", imageUrl);

        context.startActivity(intent);
    }
}
