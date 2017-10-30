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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;

import org.greenrobot.eventbus.EventBus;


public class MetaFirstFormFragment extends Fragment {

    String userIdString, actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl, storyCategory, audienceStage, storyEvents, storyMeta, storyFull, stage, storyFullEdited;
    int authorId;
    int editCounter = 0;

    //CategoryFragment categoryFragment = new CategoryFragment();
    //StageFragment stageFragment = new StageFragment();
    Spinner storyCategorySpinner, audienceStageSpinner;

    //Instance fields
    int storyIdInt, authorIdInt;

    //TEST
    int thisVariable = 0;

    //POPUP WINDOW STUFF
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentThis", Integer.parseInt(userIdString));
        Log.i("onSaveInstanceState", ": FORM");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("onAttach", ": FORM");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", ": FORM");

        if (savedInstanceState != null){
            thisVariable = savedInstanceState.getInt("currentThis", 0);
            Log.i("On Create FORM: ", Integer.toString(thisVariable));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", ": FORM");

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        userIdString = getArguments().getString("USERID_KEY");
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
        }
        return inflater.inflate(R.layout.meta_first_main,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("onViewCreated", ": FORM");
        String userIdStringSIS, actionStringSIS, storyIdStringSIS, storyTitleSIS, ifOtherSpecifySIS, authorIdStringSIS, storyDescriptionSIS, orientationSIS, complicatedActionSIS, evaluationSIS, resolutionSIS, messageSIS, stageRelatedSIS, contextRelatedSIS, imageUrlSIS;

        final EasyForm easyForm = getView().findViewById(R.id.meta_first_form);
        final EditText editText = getView().findViewById(R.id.uniquetest);


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
            final EasyTextInputLayout messgageTxt = getView().findViewById(R.id.messgage);
            final EasyTextInputLayout stageRelatedTxt = getView().findViewById(R.id.stageRelated);
            final EasyTextInputLayout contextRelatedTxt = getView().findViewById(R.id.contextRelated);
            final EasyTextInputLayout imageUrlTxt = getView().findViewById(R.id.imageUrl);
            audienceStageSpinner = getView().findViewById(R.id.stageSpinner);
            final Button buttonUpdate = getView().findViewById(R.id.updateButton);

            buttonUpdate.setEnabled(true);
            buttonUpdate.setAlpha(1);

            //This is to populate our spinners
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

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

            stageAdapter.add("Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)");
            stageAdapter.add("Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)");
            stageAdapter.add("Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)");
            stageAdapter.add("Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)");

            audienceStageSpinner.setAdapter(stageAdapter);
            audienceStageSpinner.setSelection(0);

            //Put the data from the update
            storyCategorySpinner.setSelection(0);
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
            audienceStageSpinner.setSelection(0);

            storyIdInt = Integer.parseInt(storyIdString);
            authorIdInt = Integer.parseInt(authorIdString);

            //When the user selects a category
            storyCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /*PACK DATA IN A BUNDLE
                    Bundle forCategoryFragment = new Bundle();
                    forCategoryFragment.putString("CATEGORY_KEY", storyCategorySpinner.getSelectedItem().toString());
                    Toast.makeText(getActivity(), storyCategorySpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    categoryFragment.setArguments(forCategoryFragment); */
                    //TODO EVENTBUS
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
                    /* PACK DATA IN A BUNDLE
                    Bundle forStageFragment = new Bundle();
                    forStageFragment.putString("STAGE_KEY", audienceStageSpinner.getSelectedItem().toString());
                    //Toast.makeText(getActivity(), audienceStageSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    stageFragment.setArguments(forStageFragment); */
                    EventBus.getDefault().post(new EventBusStageSelected(audienceStageSpinner.getSelectedItem().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });

            //Handle events UPDATE //TODO POPUP
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Get values
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
                    imageUrl = imageUrlTxt.getEditText().getText().toString();
                    audienceStage = audienceStageSpinner.getSelectedItem().toString();

                    storyEvents = orientation + "\n" + complicatedAction + "\n" + evaluation + "\n" + resolution + "\n" + message;
                    storyMeta = stageRelated + "\n" + contextRelated;

                    if (editCounter==0){
                        storyFull = storyTitle + "\n" + storyDescription + "\n" + storyEvents + "\n" + storyMeta;
                    } else {
                        storyFull = storyFullEdited;
                    }


                    //Client side validation
                    easyForm.validate();

                    if (easyForm.isValid()) {
                        Toast.makeText(getActivity(), "All the fields are valid.", Toast.LENGTH_SHORT).show();

                        //To appoint label from category selected in stages
                        stage= "The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)";
                        switch (audienceStage){
                            case "Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)":
                                stage = "Precontemplation";
                                break;
                            case "Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)":
                                stage = "Contemplation";
                                break;
                            case "Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)":
                                stage = "Preparation";
                                break;
                            case "Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)":
                                stage = "Action";
                                break;
                            default:
                                stage = "Precontemplation";
                                return;
                        }

                        //TODO DIALOG FRAGMENT
                        FragmentManager manager = getFragmentManager();
                        Fragment fragment = manager.findFragmentByTag("fragment_edit_story");
                        if (fragment != null){
                            manager.beginTransaction().remove(fragment).commit();
                        }

                        PopupAlertDialogFragment popupAlertDialogFragment = new PopupAlertDialogFragment();
                        popupAlertDialogFragment.show(manager, "fragment_edit_name");


                        /*/TODO POPUP WINDOW


                        popupWindow = new PopupWindow(container, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                        popupWindow.setFocusable(true);
                        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 300, 300);
                        //popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                        */

                        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                        final EditText editTextPopup;
                        editTextPopup = (EditText) container.findViewById(R.id.popup_edit_text);

                        final Button buttonBack, buttonConfirm;
                        buttonBack = (Button) container.findViewById(R.id.popup_button_back);
                        buttonConfirm = (Button) container.findViewById(R.id.popup_button_confirm);

                        editTextPopup.setText(storyFull);


                        //WHEN BACK IS PRESSED ON POPUP
                        buttonBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                storyFullEdited = editTextPopup.getText().toString();
                                editCounter = 1;
                                popupWindow.dismiss();
                            }
                        });

                        //WHEN CONFIRM IS PRESSED ON POPUP
                        buttonConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if ((actionString != null) && actionString.equals("update")){

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
                                    storyCRUD.setStageRelated(stageRelated);;
                                    storyCRUD.setContextRelated(contextRelated);
                                    storyCRUD.setStoryFull(editTextPopup.getText().toString());
                                    storyCRUD.setImageUrl(imageUrl);
                                    storyCRUD.setAudienceStage(stage);

                                    new MySQLClientCRUD(getActivity()).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
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
                                    storyCRUD.setStageRelated(stageRelated);;
                                    storyCRUD.setContextRelated(contextRelated);
                                    storyCRUD.setStoryFull(storyFull);
                                    storyCRUD.setImageUrl(imageUrl);
                                    storyCRUD.setAudienceStage(stage);

                                    new MySQLClientCRUD(getActivity()).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                    //TODO CHECK IF CORRECT
                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

                                }

                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "The last input was invalid.", Toast.LENGTH_SHORT).show();
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
            final EasyTextInputLayout messgageTxt = getView().findViewById(R.id.messgage);
            final EasyTextInputLayout stageRelatedTxt = getView().findViewById(R.id.stageRelated);
            final EasyTextInputLayout contextRelatedTxt = getView().findViewById(R.id.contextRelated);
            final EasyTextInputLayout imageUrlTxt = getView().findViewById(R.id.imageUrl);
            audienceStageSpinner = getView().findViewById(R.id.stageSpinner);
            final Button buttonAdd = getView().findViewById(R.id.addButton);

            buttonAdd.setEnabled(true);
            buttonAdd.setAlpha(1);

            //This is to populate our spinners
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

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

            stageAdapter.add("Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)");
            stageAdapter.add("Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)");
            stageAdapter.add("Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)");
            stageAdapter.add("Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)");

            audienceStageSpinner.setAdapter(stageAdapter);
            audienceStageSpinner.setSelection(0);

            //When the user selects a category
            storyCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    /*PACK DATA IN A BUNDLE
                    Bundle forCategoryFragment = new Bundle();
                    forCategoryFragment.putString("CATEGORY_KEY", storyCategorySpinner.getSelectedItem().toString());
                    Toast.makeText(getActivity(), storyCategorySpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    categoryFragment.setArguments(forCategoryFragment); */
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
                    /*PACK DATA IN A BUNDLE
                    Bundle forStageFragment = new Bundle();
                    forStageFragment.putString("STAGE_KEY", audienceStageSpinner.getSelectedItem().toString());
                    Toast.makeText(getActivity(), audienceStageSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    stageFragment.setArguments(forStageFragment); */
                    EventBus.getDefault().post(new EventBusStageSelected(audienceStageSpinner.getSelectedItem().toString()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Do nothing
                }
            });

            //Handle events
            //ADD
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Get values
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
                    imageUrl = imageUrlTxt.getEditText().getText().toString();
                    audienceStage = audienceStageSpinner.getSelectedItem().toString();

                    //TODO get from user activity for create
                    authorId = Integer.parseInt(userIdString);
                    storyEvents = orientation + "\n" + complicatedAction + "\n" + evaluation + "\n" + resolution + "\n" + message;
                    storyMeta = stageRelated + "\n" + contextRelated;

                    if (editCounter==0){
                        storyFull = storyTitle + "\n" + storyDescription + "\n" + storyEvents + "\n" + storyMeta;
                    } else {
                        storyFull = storyFullEdited;
                    }

                    //Client side validation
                    easyForm.validate();

                    if (easyForm.isValid()) {
                        Toast.makeText(getActivity(), "All the fields are valid.", Toast.LENGTH_SHORT).show();

                        //To appoint label from category selected in stages
                        stage= "The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)";
                        switch (audienceStage){
                            case "Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)":
                                stage = "Precontemplation";
                                break;
                            case "Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)":
                                stage = "Contemplation";
                                break;
                            case "Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)":
                                stage = "Preparation";
                                break;
                            case "Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)":
                                stage = "Action";
                                break;
                            default:
                                stage = "Precontemplation";
                                return;
                        }

                        //TODO POPUP WINDOW
                        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popup_window, null);

                        popupWindow = new PopupWindow(container, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                        final EditText editTextPopup;
                        editTextPopup = (EditText) container.findViewById(R.id.popup_edit_text);

                        final Button buttonBack, buttonConfirm;
                        buttonBack = (Button) container.findViewById(R.id.popup_button_back);
                        buttonConfirm = (Button) container.findViewById(R.id.popup_button_confirm);

                        popupWindow.setFocusable(true);
                        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 300, 300);
                        //popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                        editTextPopup.setText(storyFull);

                        //WHEN BACK IS PRESSED ON POPUP
                        buttonBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                storyFullEdited = editTextPopup.getText().toString();
                                editCounter = 1;
                                popupWindow.dismiss();
                            }
                        });

                        //WHEN CONFIRM IS PRESSED ON POPUP
                        buttonConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if ((actionString != null) && actionString.equals("update")){

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
                                    storyCRUD.setStageRelated(stageRelated);;
                                    storyCRUD.setContextRelated(contextRelated);
                                    storyCRUD.setStoryFull(editTextPopup.getText().toString());
                                    storyCRUD.setImageUrl(imageUrl);
                                    storyCRUD.setAudienceStage(stage);

                                    new MySQLClientCRUD(getActivity()).update(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
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
                                    storyCRUD.setStageRelated(stageRelated);;
                                    storyCRUD.setContextRelated(contextRelated);
                                    storyCRUD.setStoryFull(storyFull);
                                    storyCRUD.setImageUrl(imageUrl);
                                    storyCRUD.setAudienceStage(stage);

                                    new MySQLClientCRUD(getActivity()).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);

                                    //TODO CHECK IF CORRECT
                                    Intent intentGoToRetrieveStoriesCRUDActivity = new Intent(getActivity(), RetrieveStoriesCRUDActivity.class);
                                    intentGoToRetrieveStoriesCRUDActivity.putExtra("CATEGORY_KEY", storyCategory);
                                    getActivity().startActivity(intentGoToRetrieveStoriesCRUDActivity);

                                }

                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "The last input was invalid.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated", ": FORM");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("onViewStateRestored", ": FORM");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", ": FORM");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", ": FORM");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", ": FORM");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i("onStop", ": FORM");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView", ": FORM");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", ": FORM");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("onDetach", ": FORM");
    }
}