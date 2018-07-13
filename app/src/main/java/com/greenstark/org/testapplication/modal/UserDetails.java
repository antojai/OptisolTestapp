package com.greenstark.org.testapplication.modal;

import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("userId")
    private int userId;
    @SerializedName("firstName")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
