package com.jbapplab.navigationdrawertabs;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;


public class MetaFirstActivity extends AppCompatActivity {

    //Instance fields
    private Spinner storyCategorySpinner;
    private EasyTextInputLayout storyTitleTxt;
    private EasyTextInputLayout ifOtherSpecifyTxt;
    private EasyTextInputLayout storyDescriptionTxt;
    private EasyTextInputLayout orientationTxt;
    private EasyTextInputLayout complicatedActionTxt;
    private EasyTextInputLayout evaluationTxt;
    private EasyTextInputLayout resolutionTxt;
    private EasyTextInputLayout messgageTxt;
    private EasyTextInputLayout stageRelatedTxt;
    private EasyTextInputLayout contextRelatedTxt;
    private EasyTextInputLayout imageUrlTxt;
    private Spinner audienceStageSpinner;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meta_first_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final EasyForm easyForm = findViewById(R.id.meta_first_form);

        //Reference views
        this.initialiseViews();

        //This is to populate our spinners
        populateCategories();
        populateStages();

        //Handle events
        this.handleClickEvents(easyForm);
    }

    /*
    Reference views
     */
    private void initialiseViews(){

        storyCategorySpinner = findViewById(R.id.categorySpinner);
        storyTitleTxt = findViewById(R.id.storyTitle);
        ifOtherSpecifyTxt = findViewById(R.id.ifOtherSpecify);
        storyDescriptionTxt = findViewById(R.id.storyDescription);
        orientationTxt = findViewById(R.id.orientation);
        complicatedActionTxt = findViewById(R.id.complicatedAction);
        evaluationTxt = findViewById(R.id.evaluation);
        resolutionTxt = findViewById(R.id.resolution);
        messgageTxt = findViewById(R.id.messgage);
        stageRelatedTxt = findViewById(R.id.stageRelated);
        contextRelatedTxt = findViewById(R.id.contextRelated);
        imageUrlTxt = findViewById(R.id.imageUrl);
        audienceStageSpinner = findViewById(R.id.stageSpinner);

        buttonAdd = findViewById(R.id.addButton);

    }

    /*
    Populate our spinners
     */
    private void populateCategories()
    {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);

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

    }

    private void populateStages()
    {
        ArrayAdapter<String> stageAdapter = new ArrayAdapter<String>(this, R.layout.multiline_spinner);

        stageAdapter.add("Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)");
        stageAdapter.add("Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)");
        stageAdapter.add("Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)");
        stageAdapter.add("Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)");

        audienceStageSpinner.setAdapter(stageAdapter);
        audienceStageSpinner.setSelection(0);
    }

    /*
    Handle Click Events
     */
    private void handleClickEvents(final EasyForm easyForm){
        //Events: Add
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get values
                String storyCategory = storyCategorySpinner.getSelectedItem().toString();
                String storyTitle = storyTitleTxt.getEditText().getText().toString();
                String ifOtherSpecify = ifOtherSpecifyTxt.getEditText().getText().toString();
                String storyDescription = storyDescriptionTxt.getEditText().getText().toString();
                String orientation = orientationTxt.getEditText().getText().toString();
                String complicatedAction = complicatedActionTxt.getEditText().getText().toString();
                String evaluation = evaluationTxt.getEditText().getText().toString();
                String resolution = resolutionTxt.getEditText().getText().toString();
                String message = messgageTxt.getEditText().getText().toString();
                String stageRelated = stageRelatedTxt.getEditText().getText().toString();
                String contextRelated = contextRelatedTxt.getEditText().getText().toString();
                String imageUrl = imageUrlTxt.getEditText().getText().toString();
                String audienceStage = audienceStageSpinner.getSelectedItem().toString();

                //TODO get from user activity
                int authorId = 1;
                String storyEvents = orientation + "\n" + complicatedAction + "\n" + evaluation + "\n" + resolution + "\n" + message;
                String storyMeta = stageRelated + "\n" + contextRelated;
                String storyFull = storyTitle + "\n" + storyDescription + "\n" + storyEvents + "\n" + storyMeta;

                //Client side validation
                easyForm.validate();

                if (easyForm.isValid()) {
                    Toast.makeText(MetaFirstActivity.this, "All the fields are valid.", Toast.LENGTH_SHORT).show();

                    //To appoint label from category selected in stages
                    String stage= "The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)";
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

                    new MySQLClientCRUD(MetaFirstActivity.this).add(storyCRUD, storyCategorySpinner, storyTitleTxt, ifOtherSpecifyTxt, storyDescriptionTxt, orientationTxt, complicatedActionTxt, evaluationTxt, resolutionTxt, messgageTxt, stageRelatedTxt, contextRelatedTxt, imageUrlTxt, audienceStageSpinner);




                } else {
                    Toast.makeText(MetaFirstActivity.this, "The last input was invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}