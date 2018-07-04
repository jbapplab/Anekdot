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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusCategorySelected;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusShareStoryMetaFirstActivity;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusStageSelected;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;

import org.greenrobot.eventbus.EventBus;

public class MetaFirstFormFragment extends Fragment {

    String userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString;
    String actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl, storyCategory, audienceStage, storyEvents, storyMeta, storyFull, stage, storyFullEdited;
    int authorId;
    int editCounter = 0;
    Boolean spinnerCategoryValid;
    Boolean spinnerStageValid;

    //CategoryFragment categoryFragment = new CategoryFragment();
    //StageFragment stageFragment = new StageFragment();
    Spinner storyCategorySpinner, audienceStageSpinner;


    //Instance fields
    int storyIdInt, authorIdInt;

    //TEST
    //int thisVariable = 0;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("currentThis", Integer.parseInt(userIdString));
        //Log.i("onSaveInstanceState", ": FORM");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreate", ": FORM");

        if (savedInstanceState != null){
            //thisVariable = savedInstanceState.getInt("currentThis", 0);
            //Log.i("On Create FORM: ", Integer.toString(thisVariable));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.i("onCreateView", ": FORM");

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        userIdString = getArguments().getString("USERID_KEY");
        firstNameString = getArguments().getString("FIRSTNAME_KEY");
        lastNameString = getArguments().getString("LASTNAME_KEY");
        usernameString = getArguments().getString("USERNAME_KEY");
        passwordString = getArguments().getString("PASSWORD_KEY");
        emailString = getArguments().getString("EMAIL_KEY");

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

        return inflater.inflate(R.layout.meta_first_main,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Log.i("onViewCreated", ": FORM");
        //final String userIdStringSIS, actionStringSIS, storyIdStringSIS, storyTitleSIS, ifOtherSpecifySIS, authorIdStringSIS, storyDescriptionSIS, orientationSIS, complicatedActionSIS, evaluationSIS, resolutionSIS, messageSIS, stageRelatedSIS, contextRelatedSIS, imageUrlSIS;

        final EasyForm easyForm = getView().findViewById(R.id.meta_first_form);
        //final EditText editText = getView().findViewById(R.id.uniquetest);

        //This is to populate the views with the update info
        if ((actionString != null) && actionString.equals("update")){

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

            buttonUpdate.setEnabled(true);
            buttonUpdate.setAlpha(1);

            //This is to populate our spinners
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

            storyCategorySpinner.setFocusable(true);
            audienceStageSpinner.setFocusable(true);
            storyCategorySpinner.setFocusableInTouchMode(true);
            audienceStageSpinner.setFocusableInTouchMode(true);

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
                                imageUrlTxt.getEditText().setText(Html.fromHtml(
                                        "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>"));

                                EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                                editCounter = 1;
                                dialogInterface.dismiss();
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

                    if ((!spinnerCategoryValid) || (!spinnerStageValid)) {

                        //Toast.makeText(getActivity(),"The spinner selection is not valid.", Toast.LENGTH_SHORT).show();

                    } else {

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
                                    return;
                            }

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
                                                    ;
                                                    storyCRUD.setContextRelated(contextRelated);
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                                                    ;
                                                    storyCRUD.setContextRelated(contextRelated);
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                    //TODO CHECK IF CORRECT
                                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                                                    ;
                                                    storyCRUD.setContextRelated(contextRelated);
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                                                    ;
                                                    storyCRUD.setContextRelated(contextRelated);
                                                    storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                                    storyCRUD.setImageUrl(imageUrl);
                                                    storyCRUD.setAudienceStage(stage);

                                                    new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                                    //TODO CHECK IF CORRECT
                                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                            Toast.makeText(getActivity(), "The last input was invalid.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        } else {

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
            final Button buttonAdd = getView().findViewById(R.id.addButton);
            final Button buttonGenerate = getView().findViewById(R.id.generateButton);

            imageUrlTxt.getEditText().setText("Try generating to share and post online!");
            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

            buttonAdd.setEnabled(true);
            buttonAdd.setAlpha(1);

            //This is to populate our spinners
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
            storyCategorySpinner.setSelection(0);

            ArrayAdapter<String> stageAdapter = new ArrayAdapter<String>(getActivity(), R.layout.multiline_spinner);

            stageAdapter.add("Select a stage");
            stageAdapter.add("Stage 1: The audience is unaware of the problem or issue you are describing.");
            stageAdapter.add("Stage 2: The audience is aware of the problem or issue you are describing.");
            stageAdapter.add("Stage 3: The audience wants to act soon regarding the problem or issue.");
            stageAdapter.add("Stage 4: The audience is already taking action to overcome the problem or issue.");

            audienceStageSpinner.setAdapter(stageAdapter);
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
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });


            buttonGenerate.setOnClickListener(new View.OnClickListener(){
                int generated = 0;
                @Override
                public void onClick(View view){
                    if (generated == 0){

                        imageUrlTxt.getEditText().setText(Html.fromHtml(
                                "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>"));

                        EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                        generated = 1;
                        editCounter = 1;
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
                                imageUrlTxt.getEditText().setText(Html.fromHtml(
                                        "<font color=\"#B2182D\">"+contextRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"
                                                +"<font color=\"#128E4A\">"+stageRelatedTxt.getEditText().getText().toString()+"</font>"+"<br>"+"<br>"
                                                +orientationTxt.getEditText().getText().toString()+"<br>"
                                                +complicatedActionTxt.getEditText().getText().toString()+"<br>"
                                                +evaluationTxt.getEditText().getText().toString()+"<br>"
                                                +resolutionTxt.getEditText().getText().toString()+"<br>"+"<br>"
                                                +"<font color=\"#8E44AD\">"+messgageTxt.getEditText().getText().toString()+"</font>"));

                                EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrlTxt.getEditText().getText().toString()));

                                editCounter = 1;
                                dialogInterface.dismiss();
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

                    if ((!spinnerCategoryValid) || (!spinnerStageValid)) {

                        Toast.makeText(getActivity(),"The spinner selection is not valid.", Toast.LENGTH_SHORT).show();

                    } else {

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
                            imageUrlTxt.getEditText().setText(Html.fromHtml(
                                    "<font color=\"#B2182D\">" + contextRelatedTxt.getEditText().getText().toString() + "</font>" + "<br>"
                                            + "<font color=\"#128E4A\">" + stageRelatedTxt.getEditText().getText().toString() + "</font>" + "<br>" + "<br>"
                                            + orientationTxt.getEditText().getText().toString() + "<br>"
                                            + complicatedActionTxt.getEditText().getText().toString() + "<br>"
                                            + evaluationTxt.getEditText().getText().toString() + "<br>"
                                            + resolutionTxt.getEditText().getText().toString() + "<br>" + "<br>"
                                            + "<font color=\"#8E44AD\">" + messgageTxt.getEditText().getText().toString() + "</font>"));
                            imageUrl = imageUrlTxt.getEditText().getText().toString();

                            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrl));

                        } else {
                            imageUrl = imageUrlTxt.getEditText().getText().toString();

                            EventBus.getDefault().post(new EventBusShareStoryMetaFirstActivity(imageUrl));
                        }

                        //Client side validation
                        easyForm.validate();

                        if (easyForm.isValid()) {
                            //Toast.makeText(getActivity(), "All the fields are valid.", Toast.LENGTH_SHORT).show();

                            //To appoint label from category selected in stages
                            stage = "Stage 1: The audience is unaware of the problem or issue you are describing.";
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
                                    return;
                            }

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
                                        ;
                                        storyCRUD.setContextRelated(contextRelated);
                                        storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                        storyCRUD.setImageUrl(imageUrl);
                                        storyCRUD.setAudienceStage(stage);

                                        new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                        Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                        getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                                        ;
                                        storyCRUD.setContextRelated(contextRelated);
                                        storyCRUD.setStoryFull(popupStoryMain.getText().toString());
                                        storyCRUD.setImageUrl(imageUrl);
                                        storyCRUD.setAudienceStage(stage);

                                        new MySQLClientCRUD(getActivity(), userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                        //TODO CHECK IF CORRECT
                                        Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("userId_KEY", userIdString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("firstName_KEY", firstNameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("lastName_KEY", lastNameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("username_KEY", usernameString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("password_KEY", passwordString);
                                        intentGoToRetrieveStoriesCRUDActivity.putExtra("email_KEY", emailString);
                                        getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

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
                            Toast.makeText(getActivity(), "The last input was invalid.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
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

}