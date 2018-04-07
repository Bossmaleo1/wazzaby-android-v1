package com.wazaby.android.wazaby.model.data;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by bossmaleo on 23/11/17.
 */

public class Conversationprivateitem {

    private String photo;
    private int icon;
    private String message;
    private Drawable couleur;
    private Context context1;
    private boolean envoie = false;
    private boolean reception = false;
    //Les datas de la reception
    private int icon2;
    private String photo2;
    private String message2;

    public Conversationprivateitem(String photo,int icon,String message,Drawable couleur,Context context1
    ,int icon2,String photo2,String message2,Drawable couleur2,boolean envoie,boolean reception)
    {
        this.photo = photo;
        this.icon = icon;
        this.message = message;
        this.couleur = couleur;
        this.context1 = context1;
        this.icon2 = icon2;
        this.photo2 = photo2;
        this.message2 = message2;
        this.envoie = envoie;
        this.reception = reception;
    }


    public Drawable getCouleur() {
        return couleur;
    }


    public int getIcon() {
        return icon;
    }

    public String getPhoto() {
        return photo;
    }

    public String getMessage() {
        return message;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setContext1(Context context1) {
        this.context1 = context1;
    }

    public Context getContext1() {
        return context1;
    }

    public boolean isEnvoie() {
        return envoie;
    }

    public void setEnvoie(boolean envoie) {
        this.envoie = envoie;
    }

    public boolean isReception() {
        return reception;
    }

    public void setReception(boolean reception) {
        this.reception = reception;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
