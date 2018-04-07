package com.wazaby.android.wazaby.appviews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.connInscript.Connexion;
import com.wazaby.android.wazaby.model.Const;
import com.wazaby.android.wazaby.model.Database.SessionManager;
import com.wazaby.android.wazaby.model.dao.DatabaseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bossmaleo on 19/11/17.
 */

public class Sharepublicconversation extends AppCompatActivity {

    private Toolbar toolbar;
    private Resources res;
    private ProgressDialog pDialog;
    private DatabaseHandler database;
    private SessionManager session;
    private String LIBELLE;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharepublicconversation);
        res = getResources();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editText = (EditText)findViewById(R.id.textArea_information);
        setSupportActionBar(toolbar);
        Drawable maleoIcon = res.getDrawable(R.drawable.ic_close_black_24dp);
        maleoIcon.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        getSupportActionBar().setTitle("");
        this.getSupportActionBar().setHomeAsUpIndicator(maleoIcon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = new DatabaseHandler(this);
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send, menu);
        MenuItem favoriteItem = menu.findItem(R.id.send);
        MenuItem favoriteItem1 = menu.findItem(R.id.add_picture);
        MenuItem favoriteItem2 = menu.findItem(R.id.insert_picture);
        Drawable newIcon = (Drawable)favoriteItem.getIcon();
        Drawable newIcon1 = (Drawable)favoriteItem1.getIcon();
        Drawable newIcon2 = (Drawable)favoriteItem2.getIcon();
        newIcon.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        newIcon1.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        newIcon2.mutate().setColorFilter(Color.rgb(255, 255, 255), PorterDuff.Mode.SRC_IN);
        favoriteItem.setIcon(newIcon);
        favoriteItem1.setIcon(newIcon1);
        favoriteItem2.setIcon(newIcon2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User chose the "Settings" item, show the app settings UI...
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
                return true;
            case R.id.send :
                LIBELLE = editText.getText().toString();
                pDialog = new ProgressDialog(Sharepublicconversation.this);
                pDialog.setMessage(res.getString(R.string.chargement));
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Connexion();
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void Connexion()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.URL_SHARE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        Toast.makeText(Sharepublicconversation.this,res.getString(R.string.comment_share),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Sharepublicconversation.this,Home.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        if(error instanceof ServerError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_servererror),Toast.LENGTH_LONG).show();

                        }else if(error instanceof NetworkError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }else if(error instanceof AuthFailureError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }else if(error instanceof ParseError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }else if(error instanceof NoConnectionError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }else if(error instanceof TimeoutError)
                        {
                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }else
                        {

                            Toast.makeText(Sharepublicconversation.this,res.getString(R.string.error_volley_noconnectionerror),Toast.LENGTH_LONG).show();

                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("ID_EME",String.valueOf(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getID()));
                params.put("IDProb",String.valueOf(database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID))).getIDPROB()));
                params.put("LIBELLE",LIBELLE);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
