package com.jbapplab.navigationdrawertabs;
<<<<<<< HEAD
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.emmasuzuki.easyform.EasyForm;
        import com.emmasuzuki.easyform.EasyFormEditText;
        import com.emmasuzuki.easyform.EasyTextInputLayout;
        import com.jbapplab.navigationdrawertabs.m_DataObject.StoryCRUD;
        import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
=======

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyFormEditText;
import com.emmasuzuki.easyform.EasyTextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

//import butterknife.Bind;
//import butterknife.ButterKnife;
>>>>>>> parent of c95f220... Problem with easy form null. Will attempt fix


public class MetaFirstActivity extends AppCompatActivity {

    //@Bind(R.id.registration_form)
    //EasyForm easyForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_first);

<<<<<<< HEAD

                                         Toolbar toolbar = findViewById(R.id.toolbar);
                                         setSupportActionBar(toolbar);

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
        ArrayAdapter<String> stageAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);

        stageAdapter.add("The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)");
        stageAdapter.add("The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking");
        stageAdapter.add("The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym");
        stageAdapter.add("The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight");

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
                                case "The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)":
                                    stage = "Precontemplation";
                                    break;
                                case "The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking":
                                    stage = "Contemplation";
                                    break;
                                case "The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym":
                                    stage = "Preparation";
                                    break;
                                case "The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight":
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
=======
        final EasyForm easyForm = findViewById(R.id.registration_form);
        final EasyTextInputLayout etFirstName = findViewById(R.id.etFirstName);
        final EasyTextInputLayout etLastName = findViewById(R.id.etLastName);
        final EasyTextInputLayout etUsername = findViewById(R.id.etUsername);
        final EasyTextInputLayout etEmail = findViewById(R.id.etEmail);
        final EasyTextInputLayout etPassword = findViewById(R.id.etPassword);
        final EasyTextInputLayout etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        final Button bRegister = findViewById(R.id.bGoToEventsSecond);


        /**
         * When the user presses the register button we will get information from the fields pass it
         * to com.jbapplab.navigationdrawertabs.RegisterRequest and then execute the request.
         */
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets the text from the name field, converts it to a string and saves it to the name variable
                final String firstName = etFirstName.getEditText().getText().toString();
                final String lastName = etLastName.getEditText().getText().toString();
                final String username = etUsername.getEditText().getText().toString();
                final String email = etEmail.getEditText().getText().toString();
                final String password = etPassword.getEditText().getText().toString();
                final String passwordConfirm = etPasswordConfirm.getEditText().getText().toString();
                //This gets the age. converts it to an int and saves it
                //final int age = Integer.parseInt(etAge.getText().toString());

                if (passwordConfirm.equals(password)) {
                    easyForm.validate();

                    if (easyForm.isValid()) {
                        Log.e(getClass().getSimpleName(), "All values valid");

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                /**
                                 * This happens when the response has been executed and volley here gives
                                 * us the response from register.php. Now we need to convert this to a
                                 * JSONObject because we encoded it like that in the PHP file.
                                 * It takes the response that volley has given us and converts it into a JSONObject
                                 */
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    //When we register we get a success response from the PHP file. We need to get that.
                                    boolean success = jsonResponse.getBoolean("success");


                                    if (success) {
                                        //If successful it will take them to the login page
                                        Intent intent = new Intent(MetaFirstActivity.this, LoginActivity.class);
                                        MetaFirstActivity.this.startActivity(intent);
                                    } else {
                                        //If not successful we want to display an error and user can select to retry
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MetaFirstActivity.this);
                                        builder.setMessage("Registration Failed: The username already exists.")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MetaFirstActivity.this);
                                builder.setMessage("Registration Failed: There is a problem with the server connection.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        };

                        //Now we have to create the request. It needs the 4 fields and a response listener
                        RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, username, email, password, responseListener, errorListener);
                        /**
                         * Finally we need to add our request to a request queue.
                         * Then we ask to get the queue from volley, because volley hold all of the requests.
                         */
                        RequestQueue queue = Volley.newRequestQueue(MetaFirstActivity.this);
                        queue.add(registerRequest);
                        //Log.d("REGISTER ACTIVITY2", "Value: " + (username));
                    } else {
                    Log.e(getClass().getSimpleName(), "The last input was invalid");
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MetaFirstActivity.this);
                    builder.setMessage("The passwords do not match, please make sure they are identical.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });

    }
}
>>>>>>> parent of c95f220... Problem with easy form null. Will attempt fix
