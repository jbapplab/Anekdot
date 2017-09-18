package com.jbapplab.navigationdrawertabs.m_MySQL;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by JohnB on 15/09/2017.
 */

public class MySQLClientCRUD {

    //Save/Retrieve URLS
    private static final String DATA_INSERT_URL = "http://applabjb.000webhostapp.com/create_CRUD.php";
    private static final String DATA_RETRIEVE_URL = "http://applabjb.000webhostapp.com/create_index.php";

    //Instance fields
    private final Context context;
    private CustomAdapterCRUD customAdapterCRUD;

    public MySQLClientCRUD(Context context){
        this.context = context;
    }

    /*
    * Save/Insert //The ... is params, like an array we can pass as many view objects as we like
    */
    public void add(StoryCRUD storyCRUD, final View...inputViews) {

        if(storyCRUD==null){
            Toast.makeText(context, "No data to save.", Toast.LENGTH_SHORT).show();
        } else {
            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("story_title", storyCRUD.getStoryTitle())
                    .addBodyParameter("story_category", String.valueOf(storyCRUD.getStoryCategory()))
                    .addBodyParameter("if_other_specify", storyCRUD.getIfOtherSpecify())
                    .addBodyParameter("author_id", String.valueOf(storyCRUD.getAuthorId()))
                    .addBodyParameter("story_description", storyCRUD.getStoryDescription())
                    .addBodyParameter("story_events", storyCRUD.getStoryEvents())
                    .addBodyParameter("orientation", storyCRUD.getOrientation())
                    .addBodyParameter("complicated_action", storyCRUD.getComplicatedAction())
                    .addBodyParameter("evaluation", storyCRUD.getEvaluation())
                    .addBodyParameter("resolution", storyCRUD.getResolution())
                    .addBodyParameter("message", storyCRUD.getMessage())
                    .addBodyParameter("story_meta", storyCRUD.getStoryMeta())
                    .addBodyParameter("stage_related", storyCRUD.getStageRelated())
                    .addBodyParameter("context_related", storyCRUD.getContextRelated())
                    .addBodyParameter("story_full", storyCRUD.getStoryFull())
                    .addBodyParameter("image_url", storyCRUD.getImageUrl())
                    .addBodyParameter("audience_stage", storyCRUD.getAudienceStage())
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if(response != null){
                                try {

                                    //Show response from server
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(context, "Server response: " + responseString, Toast.LENGTH_SHORT).show();

                                    if(responseString.equalsIgnoreCase("Success")){

                                        //Reset views
                                        Spinner storyCategory = (Spinner) inputViews[0];
                                        EasyTextInputLayout storyTitle = (EasyTextInputLayout) inputViews[1];
                                        EasyTextInputLayout ifOtherSpecify = (EasyTextInputLayout) inputViews[2];
                                        //EasyTextInputLayout authorId = (EasyTextInputLayout) inputViews[3];
                                        EasyTextInputLayout storyDescription = (EasyTextInputLayout) inputViews[3];
                                        //EasyTextInputLayout storyEvents = (EasyTextInputLayout) inputViews[5];
                                        EasyTextInputLayout orientation = (EasyTextInputLayout) inputViews[4];
                                        EasyTextInputLayout complicatedAction = (EasyTextInputLayout) inputViews[5];
                                        EasyTextInputLayout evaluation = (EasyTextInputLayout) inputViews[6];
                                        EasyTextInputLayout resolution = (EasyTextInputLayout) inputViews[7];
                                        EasyTextInputLayout messgage = (EasyTextInputLayout) inputViews[8];
                                        //EasyTextInputLayout story_meta = (EasyTextInputLayout) inputViews[11];
                                        EasyTextInputLayout stage_related = (EasyTextInputLayout) inputViews[9];
                                        EasyTextInputLayout context_related = (EasyTextInputLayout) inputViews[10];
                                        //EasyTextInputLayout story_full = (EasyTextInputLayout) inputViews[14];
                                        EasyTextInputLayout image_url = (EasyTextInputLayout) inputViews[11];
                                        Spinner audienceStage = (Spinner) inputViews[12];



                                        storyCategory.setSelection(0);
                                        storyTitle.getEditText().setText("");
                                        ifOtherSpecify.getEditText().setText("");
                                        storyDescription.getEditText().setText("");
                                        orientation.getEditText().setText("");
                                        complicatedAction.getEditText().setText("");
                                        evaluation.getEditText().setText("");
                                        resolution.getEditText().setText("");
                                        messgage.getEditText().setText("");
                                        stage_related.getEditText().setText("");
                                        context_related.getEditText().setText("");
                                        image_url.getEditText().setText("");
                                        audienceStage.setSelection(0);
                                    } else {

                                        Toast.makeText(context, "Server transaction was not successful.", Toast.LENGTH_SHORT).show();

                                    }

                                } catch (JSONException e){

                                    e.printStackTrace();
                                    Toast.makeText(context, "Server responded but Anekdot could not parse the data: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }

                        }

                        //Error
                        @Override
                        public void onError(ANError anError) {

                            Toast.makeText(context, "Unsuccessful connection to the server: Error - " + anError.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });

        }

    }


    //Retrieve/Select/Refresh

    public void retrieve(final RecyclerView recyclerView, final SwipeRefreshLayout swipeRefreshLayout){
        final ArrayList<StoryCRUD> stories = new ArrayList<>();

        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject;
                        StoryCRUD storyCRUD;
                        try {
                            //for (int i=0;i<response.length();i++){
                            for (int i=response.length()-1;i>-1;i--){
                                jsonObject = response.getJSONObject(i);

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

                            //SET TO RECYCLERVIEW
                            customAdapterCRUD = new CustomAdapterCRUD(context, stories);
                            recyclerView.setAdapter(customAdapterCRUD);

                            //stop refreshing
                            swipeRefreshLayout.setRefreshing(false);


                        }catch (JSONException e){

                            Toast.makeText(context, "Good server response but Anecdot can't parse the data (JSON) it received.", Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(context, "Unsuccessful: Error is - "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }

}
