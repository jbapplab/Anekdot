package com.jbapplab.navigationdrawertabs.m_MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.m_DataObject.Story;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapter;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by JohnB on 12/09/2017.
 */

public class DataParserCRUD extends AsyncTask<Void, Void, Boolean> {
    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<StoryCRUD> stories = new ArrayList<>();

    public DataParserCRUD(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        swipeRefreshLayout.setRefreshing(false);

        if(isParsed){
            //BIND
            recyclerView.setAdapter(new CustomAdapterCRUD(context, stories));
        } else {
            Toast.makeText(context, "Unable to parse.", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            stories.clear();
            StoryCRUD storyCRUD;

            for (int i=0;i<jsonArray.length();i++){
            //for (int i=jsonArray.length()-1;i>-1;i--){
                jsonObject = jsonArray.getJSONObject(i);

                int storyId = (int) parseInt(jsonObject.getString("story_id"));
                String storyTitle = jsonObject.getString("story_title");
                String storyCategory = jsonObject.getString("story_category");
                String ifOtherSpecify = jsonObject.getString("if_other_specify");
                int authorId = (int) parseInt(jsonObject.getString("author_id"));
                String storyDescription = jsonObject.getString("story_description");
                String storyEvents = jsonObject.getString("story_events");
                String orientation = jsonObject.getString("orientation");
                String complicatedAction = jsonObject.getString("complicated_action");
                String evaluation = jsonObject.getString("evaluation");
                String resolution = jsonObject.getString("resolution");
                String message = jsonObject.getString("message");
                String storyMeta = jsonObject.getString("story_meta");
                String stageRelated = jsonObject.getString("stage_related");
                String contextRelated = jsonObject.getString("context_related");
                String storyFull = jsonObject.getString("story_full");
                String imageUrl = jsonObject.getString("image_url");
                String audienceStage = jsonObject.getString("audience_stage");

                storyCRUD = new StoryCRUD();

                storyCRUD.setStoryId(storyId);
                storyCRUD.setStoryTitle(storyTitle);
                storyCRUD.setStoryCategory(storyCategory);
                storyCRUD.setIfOtherSpecify(ifOtherSpecify);
                storyCRUD.setAuthorId(authorId);
                storyCRUD.setStoryDescription(storyDescription);
                storyCRUD.setStoryEvents(storyEvents);
                storyCRUD.setOrientation(orientation);
                storyCRUD.setComplicatedAction(complicatedAction);
                storyCRUD.setEvaluation(evaluation);
                storyCRUD.setResolution(resolution);
                storyCRUD.setMessage(message);
                storyCRUD.setStoryMeta(storyMeta);
                storyCRUD.setStageRelated(stageRelated);
                storyCRUD.setContextRelated(contextRelated);
                storyCRUD.setStoryFull(storyFull);
                storyCRUD.setImageUrl(imageUrl);
                storyCRUD.setAudienceStage(audienceStage);

                stories.add(storyCRUD);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}
