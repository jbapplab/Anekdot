package com.jbapplab.navigationdrawertabs;

/**
 Now this is an inner fragment for the TabLayout Fragment .
 So basically, TabLayout Is an Fragment for the MainActivity
 while this is an fragment for the TabLayout .

 Here , lets create a new class file and call it PrimaryFragment
 and add the following content . It is a very straightforward
 implementation of a fragment which inflates a layout from the
 onCreateView method which replaces the content of the ViewPager
 holding it.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusCategorySelected;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusOnServerSuccess;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusShareStoryMetaFirstActivity;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusStageSelected;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import at.blogc.android.views.ExpandableTextView;

public class MetaFirstFormFragment extends Fragment {

    String userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString;
    String actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl, storyCategory, audienceStage, storyEvents, storyMeta, storyFull, stage, version;
    String success;
    String stageToast;
    int authorId;
    int editCounter = 0;
    Boolean spinnerCategoryValid;
    Boolean spinnerStageValid;

    //CategoryFragment categoryFragment = new CategoryFragment();
    //StageFragment stageFragment = new StageFragment();
    Spinner storyCategorySpinner, audienceStageSpinner;


    //Instance fields
    int storyIdInt, authorIdInt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        userIdString = getArguments().getString("USERID_KEY");
        firstNameString = getArguments().getString("FIRSTNAME_KEY");
        lastNameString = getArguments().getString("LASTNAME_KEY");
        usernameString = getArguments().getString("USERNAME_KEY");
        passwordString = getArguments().getString("PASSWORD_KEY");
        emailString = getArguments().getString("EMAIL_KEY");
        version = getArguments().getString("VERSION_KEY");

        if (getArguments().getString("UPDATE_KEY") != null) {
            actionString = getArguments().getString("UPDATE_KEY");
        }
        if ((actionString != null) && actionString.equals("update")) {
            storyIdString = getArguments().getString("STORY_ID_KEY");
            storyTitle = getArguments().getString("STORY_TITLE_KEY");
            ifOtherSpecify = getArguments().getString("IF_OTHER_SPECIFY_KEY");
            authorIdString = getArguments().getString("AUTHOR_ID_KEY");
            storyDescription = getArguments().getString("STORY_DESCRIPTION_KEY");
            orientation = getArguments().getString("ORIENTATION_KEY");
            complicatedAction = getArguments().getString("COMPLICATED_ACTION_KEY");
            evaluation = getArguments().getString("EVALUATION_KEY");
            resolution = getArguments().getString("RESOLUTION_KEY");
            message = getArguments().getString("MESSAGE_KEY");
            stageRelated = getArguments().getString("STAGE_RELATED_KEY");
            contextRelated = getArguments().getString("CONTEXT_RELATED_KEY");
            imageUrl = getArguments().getString("IMAGE_URL_KEY");
            storyCategory = getArguments().getString("STORY_CATEGORY_KEY");
            audienceStage = getArguments().getString("AUDIENCE_STAGE_KEY");
        }

        switch (version){
            case "detailed_guidance":
                return inflater.inflate(R.layout.meta_first_main, null);
            case "basic_instructions":
                return inflater.inflate(R.layout.meta_first_main_simple, null);
            case "update_story":
                return inflater.inflate(R.layout.meta_first_main_simple_update, null);
            default:
                return inflater.inflate(R.layout.meta_first_main, null);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //This is to populate the views with the update info
        if ((actionString != null) && actionString.equals("update")){

            final EasyForm easyForm = getView().findViewById(R.id.meta_first_form_update);

            initialiseExpandButtonsUpdate();

            //Reference views
            storyCategorySpinner = getView().findViewById(R.id.categorySpinner);
            final EasyTextInputLayout storyTitleTxt = getView().findViewById(R.id.storyTitle);
            final EasyTextInputLayout ifOtherSpecifyTxt = getView().findViewById(R.id.ifOtherSpecify);
            final EasyTextInputLayout storyDescriptionTxt = getView().findViewById(R.id.storyDescription);
            final EasyTextInputLayout orientationTxt = getView().findViewById(R.id.orientation);
            final EasyTextInputLayout complicatedActionTxt = getView().findViewById(R.id.complicatedAction);
            final EasyTextInputLayout evaluationTxt = getView().findViewById(R.id.evaluation);
            final EasyTextInputLayout resolutionTxt = getView().findViewById(R.id.resolution);
            final EasyTextInputLayout messgageTxt = getView().findViewById(R.id.message);
            final EasyTextInputLayout stageRelatedTxt = getView().findViewById(R.id.stageRelated);
            final EasyTextInputLayout contextRelatedTxt = getView().findViewById(R.id.contextRelated);
            final EasyTextInputLayout imageUrlTxt = getView().findViewById(R.id.imageUrl);
            audienceStageSpinner = getView().findViewById(R.id.stageSpinner);
            final Button buttonUpdate = getView().findViewById(R.id.updateButton);
            final Button buttonGenerate = getView().findViewById(R.id.generateButton);

            //This is to populate our spinners
            populateSpinners();

            storyCategorySpinner.setFocusable(true);
            audienceStageSpinner.setFocusable(true);
            storyCategorySpinner.setFocusableInTouchMode(true);
            audienceStageSpinner.setFocusableInTouchMode(true);

            switchStoryCategory();

            switchAudienceStage();

            //Put the data from the update
            storyTitleTxt.getEditText().setText(storyTitle);
            ifOtherSpecifyTxt.getEditText().setText(ifOtherSpecify);
            storyDescriptionTxt.getEditText().setText(storyDescription);
            orientationTxt.getEditText().setText(orientation);
            complicatedActionTxt.getEditText().setText(complicatedAction);
            evaluationTxt.getEditText().setText(evaluation);
            resolutionTxt.getEditText().setText(resolution);
            messgageTxt.getEditText().setText(message);
            stageRelatedTxt.getEditText().setText(stageRelated);
            contextRelatedTxt.getEditText().setText(contextRelated);
            imageUrlTxt.getEditText().setText(imageUrl);

            storyIdInt = Integer.parseInt(storyIdString);
            authorIdInt = Integer.parseInt(authorIdString);

            //When the user selects a category
            storyCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerCategoryValid = true;
                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    EventBus.getDefault().post(new EventBusCategorySelected(storyCategorySpinner.getSelectedItem().toString()));
                    if (storyCategorySpinner.getSelectedItemPosition()!=0){
                        Toast.makeText(getActivity(), "Swipe right for \""+storyCategorySpinner.getSelectedItem().toString()+"\" tips.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });

            //When the user selects a stage
            audienceStageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerStageValid = true;
                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    EventBus.getDefault().post(new EventBusStageSelected(audienceStageSpinner.getSelectedItem().toString()));
                    if (audienceStageSpinner.getSelectedItemPosition()!=0){
                        switchStageForToast();
                        Toast.makeText(getActivity(), "Swipe right for \""+stageToast+"\" tips.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });

            buttonGenerate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                        AlertDialog.Builder generatePopUp = new AlertDialog.Builder(getActivity());
                        generatePopUp.setTitle("Are you sure you want to generate again?");
                        generatePopUp.setMessage("Since you are updating this story lets be certain! If you made changes on the fields above and you want to see how this makes a new story that is fine.\n\nIf you have made changes only in the story it is best NOT to generate again since any changes will be lost!");
                        generatePopUp.setNegativeButton("Oops! No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        generatePopUp.setPositiveButton("Generate story", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                imageUrlTxt.getEditText().setText(Html.fromHtml((
                                        "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>").replace("\n", "<br>")));

                                EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                                editCounter = 1;
                                imageUrlTxt.requestFocus();
                                dialogInterface.dismiss();
                                hideKeyboard(getActivity());
                            }
                        });
                        AlertDialog alertDialogGenerate = generatePopUp.create();
                        alertDialogGenerate.show();

                    final Button positiveButton = alertDialogGenerate.getButton(AlertDialog.BUTTON_POSITIVE);
                    final Button negativeButton = alertDialogGenerate.getButton(AlertDialog.BUTTON_NEGATIVE);

                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                    layoutParams.weight = 10;
                    positiveButton.setLayoutParams(layoutParams);
                    negativeButton.setLayoutParams(layoutParams);
                }
            });

            //Handle events UPDATE POPUP
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    checkSpinnerValidity();

                    if (spinnerCategoryValid && spinnerStageValid) {

                        //Get values
                        audienceStage = audienceStageSpinner.getSelectedItem().toString();
                        storyCategory = storyCategorySpinner.getSelectedItem().toString();
                        storyTitle = storyTitleTxt.getEditText().getText().toString();
                        ifOtherSpecify = ifOtherSpecifyTxt.getEditText().getText().toString();
                        storyDescription = storyDescriptionTxt.getEditText().getText().toString();
                        orientation = orientationTxt.getEditText().getText().toString();
                        complicatedAction = complicatedActionTxt.getEditText().getText().toString();
                        evaluation = evaluationTxt.getEditText().getText().toString();
                        resolution = resolutionTxt.getEditText().getText().toString();
                        message = messgageTxt.getEditText().getText().toString();
                        stageRelated = stageRelatedTxt.getEditText().getText().toString();
                        contextRelated = contextRelatedTxt.getEditText().getText().toString();

                        storyEvents = orientation + "\n" + complicatedAction + "\n" + evaluation + "\n" + resolution + "\n" + message;
                        storyMeta = stageRelated + "\n" + contextRelated;


                        //Client side validation
                        easyForm.validate();

                        if (easyForm.isValid()) {
                            Toast.makeText(getActivity(), "All the fields are valid.", Toast.LENGTH_SHORT).show();

                            //To appoint label from category selected in stages
                            stage = "Stage 1: The audience is unaware of the problem or issue you are describing.";

                            switchStage();

                            if ((imageUrlTxt.getEditText().getText().toString().equals(imageUrl))) {
                                editCounter = 0;
                            } else {
                                editCounter = 1;
                            }

                            if (editCounter == 0) {
                                AlertDialog.Builder popUpUpdate = new AlertDialog.Builder(getActivity());
                                popUpUpdate.setTitle("Are you sure you are ready to update?");
                                popUpUpdate.setMessage("It appears that you have not made any changes to your main story!\n\nIf it is the case that you wanted to change only the title, description and topic/stage that is fine.");
                                popUpUpdate.setNegativeButton("Oops! No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                popUpUpdate.setPositiveButton("Preview", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialogInterface, int i) {

                                        imageUrl = imageUrlTxt.getEditText().getText().toString();

                                        final AlertDialog.Builder popUpDialog = new AlertDialog.Builder(getActivity());
                                        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                                        View customPopupView = layoutInflater.inflate(R.layout.custom_story_preview_dialog, null);

                                        popUpDialog.setView(customPopupView);

                                        TextView popupTitle = (TextView) customPopupView.findViewById(R.id.popupTitle);
                                        TextView popupStoryTitle = (TextView) customPopupView.findViewById(R.id.storyTitle);
                                        TextView popupStoryDescription = (TextView) customPopupView.findViewById(R.id.storyDescription);
                                        final TextView popupStoryMain = (TextView) customPopupView.findViewById(R.id.storyMain);

                                        popupTitle.setText("Preview and Update");
                                        popupStoryTitle.setText(storyTitle);
                                        popupStoryDescription.setText(storyDescription);
                                        popupStoryMain.setText(imageUrl);

                                        storyFull = "Title: " + storyTitle + "\n\n" + "Description: " + storyDescription + "\n\n" + "Story:" + imageUrl;

                                        popUpDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterfaceN, int i) {
                                                dialogInterfaceN.dismiss();
                                            }
                                        });

                                        popUpDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterfaceN, int i) {

                                                if ((actionString != null) && actionString.equals("update")) {

                                                    //Save data
                                                    StoryCRUD storyCRUD = new StoryCRUD();
                                                    storyCRUD.setStoryId(storyIdInt);
                                                    storyCRUD.setStoryTitle(storyTitle);
                                                    storyCRUD.setStoryCategory(storyCategory);
                                                    storyCRUD.setIfOtherSpecify(ifOtherSpecify);
                                                    storyCRUD.setAuthorId(authorIdInt);
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
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                } else {

                                                    //Save data
                                                    StoryCRUD storyCRUD = new StoryCRUD();
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
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                }

                                            }
                                        });
                                        AlertDialog alertDialogNested = popUpDialog.create();
                                        alertDialogNested.show();

                                        final Button nestedPositiveButton = alertDialogNested.getButton(AlertDialog.BUTTON_POSITIVE);
                                        final Button nestedNegativeButton = alertDialogNested.getButton(AlertDialog.BUTTON_NEGATIVE);

                                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nestedPositiveButton.getLayoutParams();
                                        layoutParams.weight = 10;
                                        nestedPositiveButton.setLayoutParams(layoutParams);
                                        nestedNegativeButton.setLayoutParams(layoutParams);
                                    }
                                });
                                AlertDialog alertDialog = popUpUpdate.create();
                                alertDialog.show();

                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                layoutParams.weight = 10;
                                positiveButton.setLayoutParams(layoutParams);
                                negativeButton.setLayoutParams(layoutParams);

                            } else {
                                AlertDialog.Builder popUpUpdate = new AlertDialog.Builder(getActivity());
                                popUpUpdate.setTitle("Are you sure you are ready to update?");
                                popUpUpdate.setMessage("You have made changes to your main story!\n\nHave you considered changing your title, description and/or topic/stage to reflect these?");
                                popUpUpdate.setNegativeButton("Oops! No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                popUpUpdate.setPositiveButton("Preview", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialogInterface, int i) {

                                        imageUrl = imageUrlTxt.getEditText().getText().toString();

                                        final AlertDialog.Builder popUpDialog = new AlertDialog.Builder(getActivity());
                                        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                                        View customPopupView = layoutInflater.inflate(R.layout.custom_story_preview_dialog, null);

                                        popUpDialog.setView(customPopupView);

                                        TextView popupTitle = (TextView) customPopupView.findViewById(R.id.popupTitle);
                                        TextView popupStoryTitle = (TextView) customPopupView.findViewById(R.id.storyTitle);
                                        TextView popupStoryDescription = (TextView) customPopupView.findViewById(R.id.storyDescription);
                                        final TextView popupStoryMain = (TextView) customPopupView.findViewById(R.id.storyMain);

                                        popupTitle.setText("Preview and Update");
                                        popupStoryTitle.setText(storyTitle);
                                        popupStoryDescription.setText(storyDescription);
                                        popupStoryMain.setText(imageUrl);

                                        storyFull = "Title: " + storyTitle + "\n\n" + "Description: " + storyDescription + "\n\n" + "Story:" + imageUrl;

                                        popUpDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterfaceN, int i) {
                                                dialogInterfaceN.dismiss();
                                            }
                                        });

                                        popUpDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterfaceN, int i) {

                                                if ((actionString != null) && actionString.equals("update")) {

                                                    //Save data
                                                    StoryCRUD storyCRUD = new StoryCRUD();
                                                    storyCRUD.setStoryId(storyIdInt);
                                                    storyCRUD.setStoryTitle(storyTitle);
                                                    storyCRUD.setStoryCategory(storyCategory);
                                                    storyCRUD.setIfOtherSpecify(ifOtherSpecify);
                                                    storyCRUD.setAuthorId(authorIdInt);
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
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                } else {

                                                    //Save data
                                                    StoryCRUD storyCRUD = new StoryCRUD();
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
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                }

                                            }
                                        });

                                        AlertDialog alertDialogNested = popUpDialog.create();
                                        alertDialogNested.show();

                                        final Button nestedPositiveButton = alertDialogNested.getButton(AlertDialog.BUTTON_POSITIVE);
                                        final Button nestedNegativeButton = alertDialogNested.getButton(AlertDialog.BUTTON_NEGATIVE);

                                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nestedPositiveButton.getLayoutParams();
                                        layoutParams.weight = 10;
                                        nestedPositiveButton.setLayoutParams(layoutParams);
                                        nestedNegativeButton.setLayoutParams(layoutParams);
                                    }
                                });
                                AlertDialog alertDialog = popUpUpdate.create();
                                alertDialog.show();

                                final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                                final Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                                layoutParams.weight = 10;
                                positiveButton.setLayoutParams(layoutParams);
                                negativeButton.setLayoutParams(layoutParams);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Oops! Some field is not filled.\nPlease fill all the fields to proceed.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        } else {

            final EasyForm easyFormAdd = getView().findViewById(R.id.meta_first_form_add);

            initialiseExpandButtonsAdd();

            //Reference views
            storyCategorySpinner = getView().findViewById(R.id.categorySpinner);
            final EasyTextInputLayout storyTitleTxt = getView().findViewById(R.id.storyTitle_add);
            final EasyTextInputLayout ifOtherSpecifyTxt = getView().findViewById(R.id.ifOtherSpecify_add);
            final EasyTextInputLayout storyDescriptionTxt = getView().findViewById(R.id.storyDescription_add);
            final EasyTextInputLayout orientationTxt = getView().findViewById(R.id.orientation_add);
            final EasyTextInputLayout complicatedActionTxt = getView().findViewById(R.id.complicatedAction_add);
            final EasyTextInputLayout evaluationTxt = getView().findViewById(R.id.evaluation_add);
            final EasyTextInputLayout resolutionTxt = getView().findViewById(R.id.resolution_add);
            final EasyTextInputLayout messgageTxt = getView().findViewById(R.id.message_add);
            final EasyTextInputLayout stageRelatedTxt = getView().findViewById(R.id.stageRelated_add);
            final EasyTextInputLayout contextRelatedTxt = getView().findViewById(R.id.contextRelated_add);
            final EasyTextInputLayout imageUrlTxt = getView().findViewById(R.id.imageUrl_add);
            audienceStageSpinner = getView().findViewById(R.id.stageSpinner);
            final Button buttonAdd = getView().findViewById(R.id.addButton);
            final Button buttonGenerateAdd = getView().findViewById(R.id.generateButton_add);

            //imageUrlTxt.getEditText().setText("Generate to edit and post online!");
            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

            //This is to populate our spinners
            populateSpinners();

            storyCategorySpinner.setSelection(0);
            audienceStageSpinner.setSelection(0);

            storyCategorySpinner.setFocusable(true);
            audienceStageSpinner.setFocusable(true);
            storyCategorySpinner.setFocusableInTouchMode(true);
            audienceStageSpinner.setFocusableInTouchMode(true);

            //When the user selects a category
            storyCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerCategoryValid = true;
                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    EventBus.getDefault().post(new EventBusCategorySelected(storyCategorySpinner.getSelectedItem().toString()));
                    if (storyCategorySpinner.getSelectedItemPosition()!=0){
                        Toast.makeText(getActivity(), "Swipe right for \""+storyCategorySpinner.getSelectedItem().toString()+"\" tips.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });

            //When the user selects a stage
            audienceStageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinnerStageValid = true;
                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    EventBus.getDefault().post(new EventBusStageSelected(audienceStageSpinner.getSelectedItem().toString()));
                    if (audienceStageSpinner.getSelectedItemPosition()!=0){
                        switchStageForToast();
                        Toast.makeText(getActivity(), "Swipe right for \""+stageToast+"\" tips.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });


            buttonGenerateAdd.setOnClickListener(new View.OnClickListener(){
                int generated = 0;
                @Override
                public void onClick(View view){
                    if (generated == 0){

                        imageUrlTxt.getEditText().setText(Html.fromHtml((
                                "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>").replace("\n", "<br>")));

                        EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                        generated = 1;
                        editCounter = 1;
                        imageUrlTxt.requestFocus();
                        hideKeyboard(getActivity());

                    } else {
                        AlertDialog.Builder generatePopUp = new AlertDialog.Builder(getActivity());
                        generatePopUp.setTitle("Are you sure you want to generate again?");
                        generatePopUp.setMessage("Since you are updating this story lets be certain! If you made changes on the fields above and you want to see how this makes a new story that is fine.\n\nIf you have made changes only in the story it is best NOT to generate again since any changes will be lost!");
                        generatePopUp.setNegativeButton("Oops! No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        generatePopUp.setPositiveButton("Generate story", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                imageUrlTxt.getEditText().setText(Html.fromHtml((
                                        "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>").replace("\n", "<br>")));

                                EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                                editCounter = 1;
                                imageUrlTxt.requestFocus();
                                dialogInterface.dismiss();
                                hideKeyboard(getActivity());
                            }
                        });
                        AlertDialog alertDialogGenerate = generatePopUp.create();
                        alertDialogGenerate.show();

                        final Button positiveButton = alertDialogGenerate.getButton(AlertDialog.BUTTON_POSITIVE);
                        final Button negativeButton = alertDialogGenerate.getButton(AlertDialog.BUTTON_NEGATIVE);

                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
                        layoutParams.weight = 10;
                        positiveButton.setLayoutParams(layoutParams);
                        negativeButton.setLayoutParams(layoutParams);
                    }

                }
            });

            //Handle events ADD POPUP
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    checkSpinnerValidity();

                    if (spinnerCategoryValid && spinnerStageValid) {

                        //Get values
                        audienceStage = audienceStageSpinner.getSelectedItem().toString();
                        storyCategory = storyCategorySpinner.getSelectedItem().toString();
                        storyTitle = storyTitleTxt.getEditText().getText().toString();
                        ifOtherSpecify = ifOtherSpecifyTxt.getEditText().getText().toString();
                        storyDescription = storyDescriptionTxt.getEditText().getText().toString();
                        orientation = orientationTxt.getEditText().getText().toString();
                        complicatedAction = complicatedActionTxt.getEditText().getText().toString();
                        evaluation = evaluationTxt.getEditText().getText().toString();
                        resolution = resolutionTxt.getEditText().getText().toString();
                        message = messgageTxt.getEditText().getText().toString();
                        stageRelated = stageRelatedTxt.getEditText().getText().toString();
                        contextRelated = contextRelatedTxt.getEditText().getText().toString();

                        authorId = Integer.parseInt(userIdString);
                        storyEvents = orientation + "\n" + complicatedAction + "\n" + evaluation + "\n" + resolution + "\n" + message;
                        storyMeta = stageRelated + "\n" + contextRelated;

                        if (editCounter == 0) {
                            imageUrlTxt.getEditText().setText(Html.fromHtml((
                                    "<font color=\"#B2182D\">" + contextRelatedTxt.getEditText().getText().toString() + "</font>" + "<br>"
                                            + "<font color=\"#128E4A\">" + stageRelatedTxt.getEditText().getText().toString() + "</font>" + "<br>" + "<br>"
                                            + orientationTxt.getEditText().getText().toString() + "<br>"
                                            + complicatedActionTxt.getEditText().getText().toString() + "<br>"
                                            + evaluationTxt.getEditText().getText().toString() + "<br>"
                                            + resolutionTxt.getEditText().getText().toString() + "<br>" + "<br>"
                                            + "<font color=\"#8E44AD\">" + messgageTxt.getEditText().getText().toString() + "</font>").replace("\n", "<br>")));
                            imageUrl = imageUrlTxt.getEditText().getText().toString();

                            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrl));

                        } else {
                            imageUrl = imageUrlTxt.getEditText().getText().toString();

                            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrl));
                        }

                        //Client side validation
                        easyFormAdd.validate();

                        if (easyFormAdd.isValid()) {
                            //Toast.makeText(getActivity(), "All the fields are valid.", Toast.LENGTH_SHORT).show();

                            //To appoint label from category selected in stages
                            stage = "Stage 1: The audience is unaware of the problem or issue you are describing.";
                            switchStage();

                            imageUrl = imageUrlTxt.getEditText().getText().toString();

                            final AlertDialog.Builder popUpDialog = new AlertDialog.Builder(getActivity());
                            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                            View customPopupView = layoutInflater.inflate(R.layout.custom_story_preview_dialog, null);

                            popUpDialog.setView(customPopupView);

                            TextView popupTitle = (TextView) customPopupView.findViewById(R.id.popupTitle);
                            TextView popupStoryTitle = (TextView) customPopupView.findViewById(R.id.storyTitle);
                            TextView popupStoryDescription = (TextView) customPopupView.findViewById(R.id.storyDescription);
                            final TextView popupStoryMain = (TextView) customPopupView.findViewById(R.id.storyMain);

                            popupTitle.setText("Preview and Post Online");
                            popupStoryTitle.setText(storyTitle);
                            popupStoryDescription.setText(storyDescription);
                            popupStoryMain.setText(imageUrl);

                            storyFull = "Title: " + storyTitle + "\n\n" + "Description: " + storyDescription + "\n\n" + "Story:" + imageUrl;

                            popUpDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterfaceN, int i) {
                                    dialogInterfaceN.dismiss();
                                }
                            });

                            popUpDialog.setPositiveButton("Post Online", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterfaceN, int i) {

                                    if ((actionString != null) && actionString.equals("update")) {

                                        //Save data
                                        StoryCRUD storyCRUD = new StoryCRUD();
                                        storyCRUD.setStoryId(storyIdInt);
                                        storyCRUD.setStoryTitle(storyTitle);
                                        storyCRUD.setStoryCategory(storyCategory);
                                        storyCRUD.setIfOtherSpecify(ifOtherSpecify);
                                        storyCRUD.setAuthorId(authorIdInt);
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
                                        storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                        storyCRUD.setImageUrl(imageUrl);
                                        storyCRUD.setAudienceStage(stage);

                                        new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);


                                    } else {

                                        //Save data
                                        StoryCRUD storyCRUD = new StoryCRUD();
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
                                        storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                        storyCRUD.setImageUrl(imageUrl);
                                        storyCRUD.setAudienceStage(stage);

                                        new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                    }

                                }
                            });

                            AlertDialog alertDialogNested = popUpDialog.create();
                            alertDialogNested.show();

                            final Button nestedPositiveButton = alertDialogNested.getButton(AlertDialog.BUTTON_POSITIVE);
                            final Button nestedNegativeButton = alertDialogNested.getButton(AlertDialog.BUTTON_NEGATIVE);

                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nestedPositiveButton.getLayoutParams();
                            layoutParams.weight = 10;
                            nestedPositiveButton.setLayoutParams(layoutParams);
                            nestedNegativeButton.setLayoutParams(layoutParams);

                        } else {
                            Toast.makeText(getActivity(), "Oops! Some field is not filled.\nPlease fill all the fields to proceed.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    /*
    METHODS
     */

    public static void hideKeyboard(Context context) {
        try {
            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            if ((((Activity) context).getCurrentFocus() != null) && (((Activity) context).getCurrentFocus().getWindowToken() != null)) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public static void showKeyboard(Context context) {
        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    */

    void initialiseExpandButtonsAdd() {
        final ExpandableTextView expandableTextView1 = (ExpandableTextView) getActivity().findViewById(R.id.tvWelcomeMessageMetaFirst);
        final ImageButton buttonToggle1 = (ImageButton) getActivity().findViewById(R.id.button_tvWelcomeMessageMetaFirst);
        buttonToggle1.setBackgroundResource(R.drawable.expand_more);
        expandableTextView1.setAnimationDuration(750L);
        expandableTextView1.setInterpolator(new OvershootInterpolator());
        buttonToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle1.setBackgroundResource(expandableTextView1.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView1.toggle();
            }
        });

        final ExpandableTextView expandableTextView2 = (ExpandableTextView) getActivity().findViewById(R.id.tvSwipeRight);
        final ImageButton buttonToggle2 = (ImageButton) getActivity().findViewById(R.id.button_tvSwipeRight);
        buttonToggle2.setBackgroundResource(R.drawable.expand_more);
        expandableTextView2.setAnimationDuration(750L);
        expandableTextView2.setInterpolator(new OvershootInterpolator());
        buttonToggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle2.setBackgroundResource(expandableTextView2.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView2.toggle();
            }
        });

        final ExpandableTextView expandableTextView3 = (ExpandableTextView) getActivity().findViewById(R.id.tvContextRelated_add);
        final ImageButton buttonToggle3 = (ImageButton) getActivity().findViewById(R.id.button_tvContextRelated_add);
        buttonToggle3.setBackgroundResource(R.drawable.expand_more);
        expandableTextView3.setAnimationDuration(750L);
        expandableTextView3.setInterpolator(new OvershootInterpolator());
        buttonToggle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle3.setBackgroundResource(expandableTextView3.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView3.toggle();
            }
        });

        final ExpandableTextView expandableTextView4 = (ExpandableTextView) getActivity().findViewById(R.id.tvStageRelated_add);
        final ImageButton buttonToggle4 = (ImageButton) getActivity().findViewById(R.id.button_tvStageRelated_add);
        buttonToggle4.setBackgroundResource(R.drawable.expand_more);
        expandableTextView4.setAnimationDuration(750L);
        expandableTextView4.setInterpolator(new OvershootInterpolator());
        buttonToggle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle4.setBackgroundResource(expandableTextView4.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView4.toggle();
            }
        });

        final ExpandableTextView expandableTextView5 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeOrientation_add);
        final ImageButton buttonToggle5 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeOrientation_add);
        buttonToggle5.setBackgroundResource(R.drawable.expand_more);
        expandableTextView5.setAnimationDuration(750L);
        expandableTextView5.setInterpolator(new OvershootInterpolator());
        buttonToggle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle5.setBackgroundResource(expandableTextView5.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView5.toggle();
            }
        });

        final ExpandableTextView expandableTextView6 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeComplicatedAction_add);
        final ImageButton buttonToggle6 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeComplicatedAction_add);
        buttonToggle6.setBackgroundResource(R.drawable.expand_more);
        expandableTextView6.setAnimationDuration(750L);
        expandableTextView6.setInterpolator(new OvershootInterpolator());
        buttonToggle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle6.setBackgroundResource(expandableTextView6.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView6.toggle();
            }
        });

        final ExpandableTextView expandableTextView7 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeEvaluation_add);
        final ImageButton buttonToggle7 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeEvaluation_add);
        buttonToggle7.setBackgroundResource(R.drawable.expand_more);
        expandableTextView7.setAnimationDuration(750L);
        expandableTextView7.setInterpolator(new OvershootInterpolator());
        buttonToggle7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle7.setBackgroundResource(expandableTextView7.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView7.toggle();
            }
        });

        final ExpandableTextView expandableTextView8 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeResolution_add);
        final ImageButton buttonToggle8 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeResolution_add);
        buttonToggle8.setBackgroundResource(R.drawable.expand_more);
        expandableTextView8.setAnimationDuration(750L);
        expandableTextView8.setInterpolator(new OvershootInterpolator());
        buttonToggle8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle8.setBackgroundResource(expandableTextView8.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView8.toggle();
            }
        });

        final ExpandableTextView expandableTextView9 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeMessage_add);
        final ImageButton buttonToggle9 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeMessage_add);
        buttonToggle9.setBackgroundResource(R.drawable.expand_more);
        expandableTextView9.setAnimationDuration(750L);
        expandableTextView9.setInterpolator(new OvershootInterpolator());
        buttonToggle9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle9.setBackgroundResource(expandableTextView9.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView9.toggle();
            }
        });

        final ExpandableTextView expandableTextView10 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeURL_add);
        final ImageButton buttonToggle10 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeURL_add);
        buttonToggle10.setBackgroundResource(R.drawable.expand_more);
        expandableTextView10.setAnimationDuration(750L);
        expandableTextView10.setInterpolator(new OvershootInterpolator());
        buttonToggle10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle10.setBackgroundResource(expandableTextView10.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView10.toggle();
            }
        });
    }

    void initialiseExpandButtonsUpdate() {
        final ExpandableTextView expandableTextView1 = (ExpandableTextView) getActivity().findViewById(R.id.tvWelcomeMessageMetaFirst);
        final ImageButton buttonToggle1 = (ImageButton) getActivity().findViewById(R.id.button_tvWelcomeMessageMetaFirst);
        buttonToggle1.setBackgroundResource(R.drawable.expand_more);
        expandableTextView1.setAnimationDuration(750L);
        expandableTextView1.setInterpolator(new OvershootInterpolator());
        buttonToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle1.setBackgroundResource(expandableTextView1.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView1.toggle();
            }
        });

        final ExpandableTextView expandableTextView2 = (ExpandableTextView) getActivity().findViewById(R.id.tvSwipeRight);
        final ImageButton buttonToggle2 = (ImageButton) getActivity().findViewById(R.id.button_tvSwipeRight);
        buttonToggle2.setBackgroundResource(R.drawable.expand_more);
        expandableTextView2.setAnimationDuration(750L);
        expandableTextView2.setInterpolator(new OvershootInterpolator());
        buttonToggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle2.setBackgroundResource(expandableTextView2.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView2.toggle();
            }
        });

        final ExpandableTextView expandableTextView3 = (ExpandableTextView) getActivity().findViewById(R.id.tvContextRelated);
        final ImageButton buttonToggle3 = (ImageButton) getActivity().findViewById(R.id.button_tvContextRelated);
        buttonToggle3.setBackgroundResource(R.drawable.expand_more);
        expandableTextView3.setAnimationDuration(750L);
        expandableTextView3.setInterpolator(new OvershootInterpolator());
        buttonToggle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle3.setBackgroundResource(expandableTextView3.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView3.toggle();
            }
        });

        final ExpandableTextView expandableTextView4 = (ExpandableTextView) getActivity().findViewById(R.id.tvStageRelated);
        final ImageButton buttonToggle4 = (ImageButton) getActivity().findViewById(R.id.button_tvStageRelated);
        buttonToggle4.setBackgroundResource(R.drawable.expand_more);
        expandableTextView4.setAnimationDuration(750L);
        expandableTextView4.setInterpolator(new OvershootInterpolator());
        buttonToggle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle4.setBackgroundResource(expandableTextView4.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView4.toggle();
            }
        });

        final ExpandableTextView expandableTextView5 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeOrientation);
        final ImageButton buttonToggle5 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeOrientation);
        buttonToggle5.setBackgroundResource(R.drawable.expand_more);
        expandableTextView5.setAnimationDuration(750L);
        expandableTextView5.setInterpolator(new OvershootInterpolator());
        buttonToggle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle5.setBackgroundResource(expandableTextView5.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView5.toggle();
            }
        });

        final ExpandableTextView expandableTextView6 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeComplicatedAction);
        final ImageButton buttonToggle6 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeComplicatedAction);
        buttonToggle6.setBackgroundResource(R.drawable.expand_more);
        expandableTextView6.setAnimationDuration(750L);
        expandableTextView6.setInterpolator(new OvershootInterpolator());
        buttonToggle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle6.setBackgroundResource(expandableTextView6.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView6.toggle();
            }
        });

        final ExpandableTextView expandableTextView7 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeEvaluation);
        final ImageButton buttonToggle7 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeEvaluation);
        buttonToggle7.setBackgroundResource(R.drawable.expand_more);
        expandableTextView7.setAnimationDuration(750L);
        expandableTextView7.setInterpolator(new OvershootInterpolator());
        buttonToggle7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle7.setBackgroundResource(expandableTextView7.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView7.toggle();
            }
        });

        final ExpandableTextView expandableTextView8 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeResolution);
        final ImageButton buttonToggle8 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeResolution);
        buttonToggle8.setBackgroundResource(R.drawable.expand_more);
        expandableTextView8.setAnimationDuration(750L);
        expandableTextView8.setInterpolator(new OvershootInterpolator());
        buttonToggle8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle8.setBackgroundResource(expandableTextView8.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView8.toggle();
            }
        });

        final ExpandableTextView expandableTextView9 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeMessage);
        final ImageButton buttonToggle9 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeMessage);
        buttonToggle9.setBackgroundResource(R.drawable.expand_more);
        expandableTextView9.setAnimationDuration(750L);
        expandableTextView9.setInterpolator(new OvershootInterpolator());
        buttonToggle9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle9.setBackgroundResource(expandableTextView9.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView9.toggle();
            }
        });

        final ExpandableTextView expandableTextView10 = (ExpandableTextView) getActivity().findViewById(R.id.tvBeforeURL);
        final ImageButton buttonToggle10 = (ImageButton) getActivity().findViewById(R.id.button_tvBeforeURL);
        buttonToggle10.setBackgroundResource(R.drawable.expand_more);
        expandableTextView10.setAnimationDuration(750L);
        expandableTextView10.setInterpolator(new OvershootInterpolator());
        buttonToggle10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonToggle10.setBackgroundResource(expandableTextView10.isExpanded() ? R.drawable.expand_more : R.drawable.expand_less);
                expandableTextView10.toggle();
            }
        });
    }

    void switchStage(){
        switch (audienceStage) {
            case "Stage 1: The audience is unaware of the problem or issue you are describing.":
                stage = "Precontemplation";
                break;
            case "Stage 2: The audience is aware of the problem or issue you are describing.":
                stage = "Contemplation";
                break;
            case "Stage 3: The audience wants to act soon regarding the problem or issue.":
                stage = "Preparation";
                break;
            case "Stage 4: The audience is already taking action to overcome the problem or issue.":
                stage = "Action";
                break;
            default:
                stage = "Precontemplation";
        }
    }

    void switchStageForToast(){
        switch (audienceStageSpinner.getSelectedItem().toString()) {
            case "Stage 1: The audience is unaware of the problem or issue you are describing.":
                stageToast = "Stage 1";
                break;
            case "Stage 2: The audience is aware of the problem or issue you are describing.":
                stageToast = "Stage 2";
                break;
            case "Stage 3: The audience wants to act soon regarding the problem or issue.":
                stageToast = "Stage 3";
                break;
            case "Stage 4: The audience is already taking action to overcome the problem or issue.":
                stageToast = "Stage 4";
                break;
            default:
                stageToast = "No stage selected";
        }
    }

    void switchStoryCategory(){
        switch (storyCategory){
            case "Other":
                storyCategorySpinner.setSelection(1);
                spinnerCategoryValid = true;
                break;
            case "Art":
                storyCategorySpinner.setSelection(2);
                spinnerCategoryValid = true;
                break;
            case "Causes":
                storyCategorySpinner.setSelection(3);
                spinnerCategoryValid = true;
                break;
            case "Education":
                storyCategorySpinner.setSelection(4);
                spinnerCategoryValid = true;
                break;
            case "Food":
                storyCategorySpinner.setSelection(5);
                spinnerCategoryValid = true;
                break;
            case "Lifestyle":
                storyCategorySpinner.setSelection(6);
                spinnerCategoryValid = true;
                break;
            case "Business":
                storyCategorySpinner.setSelection(7);
                break;
            case "Sports":
                storyCategorySpinner.setSelection(8);
                spinnerCategoryValid = true;
                break;
            case "Travel":
                storyCategorySpinner.setSelection(9);
                spinnerCategoryValid = true;
                break;
            case "Security":
                storyCategorySpinner.setSelection(10);
                spinnerCategoryValid = true;
                break;
            default:
                storyCategorySpinner.setSelection(0);
                spinnerCategoryValid = false;
        }
    }

    void switchAudienceStage(){
        switch (audienceStage){
            case "Precontemplation":
                audienceStageSpinner.setSelection(1);
                spinnerStageValid = true;
                break;
            case "Contemplation":
                audienceStageSpinner.setSelection(2);
                spinnerStageValid = true;
                break;
            case "Preparation":
                audienceStageSpinner.setSelection(3);
                spinnerStageValid = true;
                break;
            case "Action":
                audienceStageSpinner.setSelection(4);
                spinnerStageValid = true;
                break;
            default:
                audienceStageSpinner.setSelection(0);
                spinnerStageValid = false;
        }
    }

    void populateSpinners(){
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

        categoryAdapter.add("Select a topic");
        categoryAdapter.add("Other");
        categoryAdapter.add("Art");
        categoryAdapter.add("Causes");
        categoryAdapter.add("Education");
        categoryAdapter.add("Food");
        categoryAdapter.add("Lifestyle");
        categoryAdapter.add("Business");
        categoryAdapter.add("Sports");
        categoryAdapter.add("Travel");
        categoryAdapter.add("Security");

        storyCategorySpinner.setAdapter(categoryAdapter);
        //storyCategorySpinner.setSelection(0);

        ArrayAdapter<String> stageAdapter = new ArrayAdapter<String>(getActivity(), R.layout.multiline_spinner);

        stageAdapter.add("Select a stage");
        stageAdapter.add("Stage 1: The audience is unaware of the problem or issue you are describing.");
        stageAdapter.add("Stage 2: The audience is aware of the problem or issue you are describing.");
        stageAdapter.add("Stage 3: The audience wants to act soon regarding the problem or issue.");
        stageAdapter.add("Stage 4: The audience is already taking action to overcome the problem or issue.");

        audienceStageSpinner.setAdapter(stageAdapter);
        //audienceStageSpinner.setSelection(0);
    }

    void checkSpinnerValidity(){
        if (storyCategorySpinner.getSelectedItemPosition()==0){
            AlertDialog.Builder generatePopUpCategory = new AlertDialog.Builder(getActivity());
            generatePopUpCategory.setTitle("Oops! Story topic was not selected!");
            generatePopUpCategory.setMessage("Please select a relevant topic for your story to be categorised in.");
            generatePopUpCategory.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    spinnerCategoryValid = false;
                    storyCategorySpinner.requestFocus();
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialogGenerate = generatePopUpCategory.create();
            alertDialogGenerate.show();
        } else if (audienceStageSpinner.getSelectedItemPosition()==0){
            AlertDialog.Builder generatePopUpStage = new AlertDialog.Builder(getActivity());
            generatePopUpStage.setTitle("Oops! Audience stage was not selected!");
            generatePopUpStage.setMessage("Please select the relevant stage your audience is in, regarding the problem or issue you are describing.");
            generatePopUpStage.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    spinnerStageValid = false;
                    audienceStageSpinner.requestFocus();
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialogGenerate = generatePopUpStage.create();
            alertDialogGenerate.show();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i("onActivityCreated", ": FORM");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //Log.i("onViewStateRestored", ": FORM");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerSuccess(EventBusOnServerSuccess eventBusOnServerSuccess) {
        success = eventBusOnServerSuccess.message;
        Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
        switch (success){
            case "success_add":
                intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);
                break;
            case "success_update":
                intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);
                break;
            default:
                Log.i("MetaFirstFormFragment", "Event Bus Error");
        }
    }
}