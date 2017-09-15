package com.jbapplab.navigationdrawertabs.m_DataObject;

/**
 * Created by JohnB on 15/09/2017.
 */

public class StoryCRUD {
    //Instance fields
    private int storyId;
    private String storyTitle;
    private int storyCategory;
    private String ifOtherSpecify;
    private int authorId;
    private String storyDescription;
    private String storyEvents;
    private String orientation;
    private String complicatedAction;
    private String evaluation;
    private String resolution;
    private String message;
    private String storyMeta;
    private String stageRelated;
    private String contextRelated;
    private String storyFull;
    private String imageUrl;
    private String audienceStage;

    //Getters and Setters
    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public int getStoryCategory() {
        return storyCategory;
    }

    public void setStoryCategory(int storyCategory) {
        this.storyCategory = storyCategory;
    }

    public String getIfOtherSpecify() {
        return ifOtherSpecify;
    }

    public void setIfOtherSpecify(String ifOtherSpecify) {
        this.ifOtherSpecify = ifOtherSpecify;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public String getStoryEvents() {
        return storyEvents;
    }

    public void setStoryEvents(String storyEvents) {
        this.storyEvents = storyEvents;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getComplicatedAction() {
        return complicatedAction;
    }

    public void setComplicatedAction(String complicatedAction) {
        this.complicatedAction = complicatedAction;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStoryMeta() {
        return storyMeta;
    }

    public void setStoryMeta(String storyMeta) {
        this.storyMeta = storyMeta;
    }

    public String getStageRelated() {
        return stageRelated;
    }

    public void setStageRelated(String stageRelated) {
        this.stageRelated = stageRelated;
    }

    public String getContextRelated() {
        return contextRelated;
    }

    public void setContextRelated(String contextRelated) {
        this.contextRelated = contextRelated;
    }

    public String getStoryFull() {
        return storyFull;
    }

    public void setStoryFull(String storyFull) {
        this.storyFull = storyFull;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudienceStage() {
        return audienceStage;
    }

    public void setAudienceStage(String audienceStage) {
        this.audienceStage = audienceStage;
    }

    //To String
    @Override
    public String toString(){
        return storyTitle;
    }
}
