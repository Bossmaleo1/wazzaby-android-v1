package com.wazaby.android.wazaby.model.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by bossmaleo on 01/01/18.
 */

public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Wazabypref";
    public static final String Key_ID = "ID";
    private static final String IS_LOGIN = "IsLoggedIn";


    public SessionManager(Context context)
    {
        this._context = context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(int id)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(Key_ID, String.valueOf(id));
        editor.commit();
    }

    public HashMap<String,String> getUserDetail()
    {
        HashMap<String,String> user = new HashMap<String,String>();
        user.put(Key_ID,pref.getString(Key_ID,null));
      return user;
    }

    public void logoutUser()
    {
        editor.clear();
        editor.commit();
    }

    public boolean IsLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN,false);
    }

}
