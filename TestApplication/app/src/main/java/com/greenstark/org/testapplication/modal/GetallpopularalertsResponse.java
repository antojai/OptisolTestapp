package com.greenstark.org.testapplication.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 102525 on 8/28/2017.
 */

public class GetallpopularalertsResponse {


    @SerializedName("status")
    private StatusResoponse statusResoponse;

    @SerializedName("_entity")
    private List<EntityValues> _entity;

    public List<EntityValues> get_entity() {
        return _entity;
    }

    public void set_entity(List<EntityValues> _entity) {
        this._entity = _entity;
    }

    public StatusResoponse getStatusResoponse() {
        return statusResoponse;
    }

    public void setStatusResoponse(StatusResoponse statusResoponse) {
        this.statusResoponse = statusResoponse;
    }
}
