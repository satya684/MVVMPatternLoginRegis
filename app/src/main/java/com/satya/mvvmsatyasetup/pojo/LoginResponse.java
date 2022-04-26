package com.satya.mvvmsatyasetup.pojo;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String messgae;

    public LoginResponse(String messgae) {
        this.messgae = messgae;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
