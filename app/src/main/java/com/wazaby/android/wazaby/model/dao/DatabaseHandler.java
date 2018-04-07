package com.wazaby.android.wazaby.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony;

import com.wazaby.android.wazaby.model.data.Categorie_prob;
import com.wazaby.android.wazaby.model.data.Conversation;
import com.wazaby.android.wazaby.model.data.Profil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bossmaleo on 27/08/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Wazaby";
    private static final int DATABASE_VERSION = 1;
    //La table USER
    private static final String TABLE_USER = "USER";
    private static final String KEY_ID ="ID";
    private static final String KEY_NOM = "NOM";
    private static final String KEY_PRENOM ="PRENOM";
    private static final String KEY_PHOTO ="PHOTO";
    private static final String KEY_KEYPUSH = "KEYPUSH";
    private static final String KEY_SEXE = "SEXE";
    private static final String KEY_VILLE = "VILLE";
    private static final String KEY_DATE_DE_NAISSANCE = "DATE_DE_NAISSANCE";
    private static final String KEY_LANGUE = "LANGUE";
    private static final String KEY_ETAT = "ETAT";
    private static final String KEY_PAYS = "PAYS";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_IDPROB = "IDPROB";
    //La table catégorie problematique
    private static final String TABLE_CATPROB = "CATPROB";
    private static final String KEY_IDCATPROB = "IDCATPROB";
    private static final String KEY_LIBELLECATPROB = "LIBELLECATPROB";
    //La table problématique
    private static final String TABLE_PROB = "PROBLEMATIQUE";
    private static final String KEY_IDPROB1 = "IDPROB";
    private static final String KEY_LIBELLEPROB = "LIBELLEPROB";
    //La table Libelle problématique
    private static final String TABLE_LIBELLE_PROB = "LIBELLEPROB";
    private static final String KEY_TITLELIBELLE ="LIBELLETITLE";
    //La table conversation
    private static final String TABLE_CONVERSATION = "CONVERSATION";
    private static final String KEY_ID_EME_CONV ="ID_EME";
    private static final String KEY_LIBELLE ="LIBELLE";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Création de la table user
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOM + " TEXT,"+KEY_PRENOM+" TEXT,"+KEY_PHOTO+" TEXT," +
                KEY_KEYPUSH+" TEXT,"+KEY_SEXE+" TEXT," +
                KEY_VILLE+" TEXT,"+KEY_DATE_DE_NAISSANCE+" TEXT,"+KEY_LANGUE+" TEXT,"+KEY_ETAT+" TEXT,"+KEY_PAYS+" TEXT," +
                KEY_EMAIL+" TEXT,"+KEY_IDPROB+" TEXT);";
        //création de la table catégorie table
        String CREATE_CATPROB_TABLE = "CREATE TABLE "+ TABLE_CATPROB + "("+
                KEY_IDCATPROB+" INTEGER PRIMARY KEY,"+KEY_LIBELLECATPROB+" TEXT);";
        //Enfin création de la table problématique
        String CREATE_PROB_TABLE = "CREATE TABLE "+ TABLE_PROB + "("+
                KEY_IDPROB1+" INTEGER PRIMARY KEY,"+KEY_LIBELLEPROB+" TEXT);";
        //Enfin création de la table LIBELLE_PROB
        String CREATE_LIBELLETITLE = "CREATE TABLE "+TABLE_LIBELLE_PROB+ "("
                +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+KEY_TITLELIBELLE+" TEXT)";

        String CREATE_CONVERSATION = "CREATE TABLE "+TABLE_CONVERSATION+ "("
                +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+KEY_ID_EME_CONV+" TEXT,"+KEY_LIBELLE+" TEXT,"+KEY_ETAT+" TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CATPROB_TABLE);
        db.execSQL(CREATE_PROB_TABLE);
        db.execSQL(CREATE_LIBELLETITLE);
        db.execSQL(CREATE_CONVERSATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATPROB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBELLE_PROB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONVERSATION);*/
        onCreate(db);
    }

    public void addConversation(Conversation conversation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_EME_CONV, conversation.getID_EME());
        values.put(KEY_LIBELLE, conversation.getLibelle());
        values.put(KEY_ETAT, conversation.getEtat());

        db.insert(TABLE_CONVERSATION, null, values);
        db.close();

        /*

        private static final String TABLE_CONVERSATION = "CONVERSATION";
    private static final String KEY_ID_EME_CONV ="ID_EME";
    private static final String KEY_LIBELLE ="LIBELLE";

         */
    }

    //Modifier id_user
    public void UpdateIDPROB(int IDUSER,int name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDPROB,name);
        db.update(TABLE_USER,values, KEY_ID+"="+IDUSER, null);
    }

    //on ajoute le libelle de la problematique
    public void addLibelleroProblematique(String libelle)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLELIBELLE, libelle);

        db.insert(TABLE_LIBELLE_PROB, null, values);
        db.close();
    }

    //La classe qui affichera le dernier libelle de la problématique
    public String getLastProbLibelle() {
        List<Categorie_prob> catprobList = new ArrayList<Categorie_prob>();
        String selectQuery = "SELECT  * FROM " + TABLE_LIBELLE_PROB;
        String Libelle = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToLast()) {
            Libelle = cursor.getString(1);
        }

        return Libelle;
    }

    //Afficher toute la conversation
    public List<Conversation> getAllConversation(String ID_EME) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONVERSATION+ " WHERE "+KEY_ID_EME_CONV+"= "+ID_EME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do{
                Conversation conversation = new Conversation();
                conversation.setID_EME(cursor.getString(1));
                conversation.setLibelle(cursor.getString(2));
                conversation.setEtat(cursor.getString(3));
                conversations.add(conversation);
            }while (cursor.moveToNext());
        }


        return conversations;
    }

    //La classe qui affichera le dernier libelle de la problématique
   /* public String getLastProbLibelle() {
        List<Categorie_prob> catprobList = new ArrayList<Categorie_prob>();
        String selectQuery = "SELECT  * FROM " + TABLE_LIBELLE_PROB;
        String Libelle = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToLast()) {
            Libelle = cursor.getString(1);
        }

        return Libelle;
    }*/


    public void addUSER(Profil profil) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, profil.getNOM());
        values.put(KEY_PRENOM, profil.getPRENOM());
        values.put(KEY_EMAIL, profil.getEMAIL());
        values.put(KEY_PHOTO, profil.getPHOTO());
        values.put(KEY_ID, profil.getID());
        values.put(KEY_KEYPUSH, profil.getKEYPUSH());
        values.put(KEY_SEXE, profil.getSEXE());
        values.put(KEY_VILLE, profil.getVille());
        values.put(KEY_DATE_DE_NAISSANCE, profil.getDATE_DE_NAISSANCE());
        values.put(KEY_LANGUE, profil.getLANGUE());
        values.put(KEY_ETAT, profil.getETAT());
        values.put(KEY_PAYS, profil.getPays());
        values.put(KEY_IDPROB, profil.getIDPROB());

        db.insert(TABLE_USER, null, values);
        db.close();
    }




    public Profil getUSER(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
                        KEY_NOM,KEY_PRENOM, KEY_DATE_DE_NAISSANCE,KEY_SEXE,
                        KEY_EMAIL,KEY_PHOTO,KEY_KEYPUSH,KEY_LANGUE,KEY_ETAT,
                        KEY_PAYS,KEY_VILLE,KEY_IDPROB}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Profil user = new Profil(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5),
                cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12));
        return user;
    }



}
