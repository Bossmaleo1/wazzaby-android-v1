package com.wazaby.android.wazaby.model.data;

/**
 * Created by bossmaleo on 13/02/18.
 */

public class Conversation {

    private String ID_EME;
    private String Libelle;
    private String Etat;

    public Conversation()
    {

    }

    public Conversation(String ID_EME, String libelle, String etat) {
        this.ID_EME = ID_EME;
        Libelle = libelle;
        Etat = etat;
    }

    public String getID_EME() {
        return ID_EME;
    }

    public void setID_EME(String ID_EME) {
        this.ID_EME = ID_EME;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }
}
