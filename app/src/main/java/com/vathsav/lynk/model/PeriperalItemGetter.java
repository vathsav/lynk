package com.vathsav.lynk.model;

/**
 * Created by vathsav on 16/08/16.
 */
public class PeriperalItemGetter {
    public String user;
    public String light;
    public String fan;

    public PeriperalItemGetter(String user, String light, String fan) {
        this.user = user;
        this.light = light;
        this.fan = fan;
    }

    public String getUser() {
        return user;
    }

    public String getLight() {
        return light;
    }

    public String getFan() {
        return fan;
    }
}
