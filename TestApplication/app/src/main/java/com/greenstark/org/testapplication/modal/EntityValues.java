package com.greenstark.org.testapplication.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntityValues {

    @SerializedName("panicId")
    private int panicId;
    @SerializedName("userId")
    private UserDetails userId;

    public int getPanicId() {
        return panicId;
    }

    public void setPanicId(int panicId) {
        this.panicId = panicId;
    }

    public UserDetails getUserId() {
        return userId;
    }

    public void setUserId(UserDetails userId) {
        this.userId = userId;
    }
}
