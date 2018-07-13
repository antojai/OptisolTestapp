package com.greenstark.org.testapplication.modal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 102525 on 8/28/2017.
 */

public class StatusResoponse {

    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
