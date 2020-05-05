package com.example.htgh.common;

import org.json.JSONObject;

public class ResponsePackage {
    private Boolean success;
    private JSONObject data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
