package com.wazaby.android.wazaby.model.data;

import android.content.Context;

/**
 * Created by bossmaleo on 08/11/17.
 */

public class ConversationItem {

    private String Contenu;
    private String FriendLibelle;
    private int onlinestatus;
    private String imageID;
    private int color1;
    private int ID;
    private Context context1;
    private String KEYPUSH;
    private String PHOTO;


    public ConversationItem(Context context1,int id,String contenu,String libelle,int onlinestatus,String imageID,int color
    ,String KEYPUSH,String photo)
    {
        this.FriendLibelle = libelle;
        this.onlinestatus = onlinestatus;
        this.imageID = imageID;
        this.color1 = color;
        this.Contenu = contenu;
        this.ID = id;
        this.context1 = context1;
        this.KEYPUSH = KEYPUSH;
        this.PHOTO = photo;
    }

    public Context getContext1() {
        return context1;
    }

    public void setContext1(Context context1) {
        this.context1 = context1;
    }

    public int getColor1() {
        return color1;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setColor1(int color) {
        this.color1 = color;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
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

    public String getKEYPUSH() {
        return KEYPUSH;
    }

    public void setKEYPUSH(String KEYPUSH) {
        this.KEYPUSH = KEYPUSH;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }
}


