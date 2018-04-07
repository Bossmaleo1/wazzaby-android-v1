package com.wazaby.android.wazaby.model.data;

/**
 * Created by bossmaleo on 11/01/18.
 */

public class Categorie_prob {

    private int ID;
    private String Libelle;

    public Categorie_prob()
    {

    }

    public Categorie_prob(int id,String libelle)
    {
        this.ID = id;
        this.Libelle = libelle;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }
}
