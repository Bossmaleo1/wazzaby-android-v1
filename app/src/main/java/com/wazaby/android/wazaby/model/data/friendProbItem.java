package com.wazaby.android.wazaby.model.data;

import android.content.Context;

/**
 * Created by bossmaleo on 07/11/17.
 */

public class friendProbItem {

    private String FriendLibelle;
    private int onlinestatus;
    private String imageID;
    private int color1;
    private Context context;
    private String Keypush;
    private int ID;

    public friendProbItem(int ID,String libelle,int onlinestatus,String imageID,int color,Context context,String keypush)
    {
        this.ID =ID;
        this.FriendLibelle = libelle;
        this.onlinestatus = onlinestatus;
        this.imageID = imageID;
        this.color1 = color;
        this.context = context;
        this.Keypush = keypush;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color) {
        this.color1 = color;
    }

    public String getImageID() {
        return imageID;
    }

    public int getOnlinestatus() {
        return onlinestatus;
    }

    public String getFriendLibelle() {
        return FriendLibelle;
    }

    public void setFriendLibelle(String friendLibelle) {
        FriendLibelle = friendLibelle;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public void setOnlinestatus(int onlinestatus) {
        this.onlinestatus = onlinestatus;
    }

    public Context getContext1() {
        return context;
    }

    public String getKeypush() {
        return Keypush;
    }

    public void setKeypush(String keypush) {
        Keypush = keypush;
    }

    public void setContext1(Context context) {
        this.context = context;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
