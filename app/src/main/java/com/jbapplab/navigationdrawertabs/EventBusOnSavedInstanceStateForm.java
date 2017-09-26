package com.jbapplab.navigationdrawertabs;

/**
 * Created by JohnB on 26/09/2017.
 */

public class EventBusOnSavedInstanceStateForm {

    public final String userIdString, actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl;


    public EventBusOnSavedInstanceStateForm(String userIdString, String actionString, String storyIdString, String storyTitle, String ifOtherSpecify, String authorIdString, String storyDescription, String orientation, String complicatedAction, String evaluation, String resolution, String message, String stageRelated, String contextRelated, String imageUrl) {
        this.userIdString = userIdString;
        this.actionString = actionString;
        this.storyIdString = storyIdString;
        this.storyTitle = storyTitle;
        this.ifOtherSpecify = ifOtherSpecify;
        this.authorIdString = authorIdString;
        this.storyDescription = storyDescription;
        this.orientation = orientation;
        this.complicatedAction = complicatedAction;
        this.evaluation = evaluation;
        this.resolution = resolution;
        this.message = message;
        this.stageRelated = stageRelated;
        this.contextRelated = contextRelated;
        this.imageUrl = imageUrl;
    }
}