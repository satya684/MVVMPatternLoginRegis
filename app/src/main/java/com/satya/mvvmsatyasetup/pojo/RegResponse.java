package com.satya.mvvmsatyasetup.pojo;

import java.io.Serializable;

public class RegResponse implements Serializable {
    private String message;

    public RegResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
