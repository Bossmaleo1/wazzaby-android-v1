package com.wazaby.android.wazaby.model.data;

/**
 * Created by bossmaleo on 27/08/17.
 */

public class Permission {

    private String Name;

    public Permission()
    {
        this.Name = null;
    }

    public Permission(String nom)
    {
        this.Name = nom;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
