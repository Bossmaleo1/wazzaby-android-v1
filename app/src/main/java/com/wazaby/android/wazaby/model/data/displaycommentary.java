package com.wazaby.android.wazaby.model.data;

import android.content.Context;

/**
 * Created by bossmaleo on 25/01/18.
 */

public class displaycommentary {

    private String PHOTO;
    private Context context1;
    private int icononline;
    private String title;
    private int color1;
    private int ID;
    private String NOM;
    private String DATETIME;

    public displaycommentary(String PHOTO, Context context1, int icononline, String title,int color1,int id,String datetime,String nom) {
        this.PHOTO = PHOTO;
        this.context1 = context1;
        this.icononline = icononline;
        this.title = title;
        this.color1 = color1;
        this.ID = id;
        this.DATETIME = datetime;
        this.NOM = nom;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }

    public Context getContext1() {
        return context1;
    }

    public void setContext1(Context context1) {
        this.context1 = context1;
    }

    public int getIcononline() {
        return icononline;
    }

    public void setIcononline(int icononline) {
        this.icononline = icononline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }
}
