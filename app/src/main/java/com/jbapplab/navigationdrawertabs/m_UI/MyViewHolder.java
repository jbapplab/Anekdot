package com.jbapplab.navigationdrawertabs.m_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbapplab.navigationdrawertabs.R;

/**
 * Created by JohnB on 12/09/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView storyId;
    TextView firstName;
    TextView lastName;
    TextView username;
    TextView password;
    TextView email;
    ImageView storyImage;
    StoryItemClickListener itemClickListener;

    public MyViewHolder(View itemView) {
        super(itemView);

        storyId = itemView.findViewById(R.id.storyId);
        firstName = itemView.findViewById(R.id.firstName);
        lastName = itemView.findViewById(R.id.lastName);
        username = itemView.findViewById(R.id.username);
        password = itemView.findViewById(R.id.password);
        email = itemView.findViewById(R.id.email);
        storyImage = itemView.findViewById(R.id.storyImage);

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
