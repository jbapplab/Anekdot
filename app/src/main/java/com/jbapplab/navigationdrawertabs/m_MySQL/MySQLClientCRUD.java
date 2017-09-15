package com.jbapplab.navigationdrawertabs.m_MySQL;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by JohnB on 15/09/2017.
 */

public class MySQLClientCRUD {

    //Save/Retrieve URLS
    private static final String DATA_INSERT_URL = "http://applabjb.000webhostapp.com/create_CRUD.php";

    //Instance fields
    private final Context context;

    public MySQLClientCRUD(Context context){
        this.context = context;
    }

    //Save/Insert //The ... is params, like an array we can pass as many view objects as we like
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
                                        EasyTextInputLayout stageRelated = (EasyTextInputLayout) inputViews[9];
                                        EasyTextInputLayout contextRelated = (EasyTextInputLayout) inputViews[10];
                                        //EasyTextInputLayout storyFull = (EasyTextInputLayout) inputViews[14];
                                        EasyTextInputLayout imageUrl = (EasyTextInputLayout) inputViews[11];
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
                                        stageRelated.getEditText().setText("");
                                        contextRelated.getEditText().setText("");
                                        imageUrl.getEditText().setText("");
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

}
