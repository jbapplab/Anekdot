package com.jbapplab.navigationdrawertabs.m_DataObject;

/**
 * Created by JohnB on 12/09/2017.
 */

public class Story {

    String storyId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
    String imageUrl;

    public String getId() {
        return storyId;
    }

    public void setId(String id) {
        this.storyId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
