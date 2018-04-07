package com.wazaby.android.wazaby.connInscript;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.wazaby.android.wazaby.appviews.Home;
import com.wazaby.android.wazaby.model.Const;
import com.wazaby.android.wazaby.model.Database.SessionManager;
import com.wazaby.android.wazaby.model.dao.DatabaseHandler;
import com.wazaby.android.wazaby.model.data.Profil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//Html.fromHtml("<font color='#ff3824'>Settings</font>")

/**
 * Created by bossmaleo on 14/08/17.
 */

public class Connexion extends AppCompatActivity {

    private TextView about;
    private Resources res;
    private LinearLayout aboutblock;
    private Toolbar toolbar;
    private TextView passwordforget;
    private Button Connexion;
    private EditText email;
    private EditText password;
    private final int REQUEST_LOCATION = 200;
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog pDialog;
    private JSONObject reponse;
    private JSONObject data;
    private int succes;
    private SessionManager session;
    private DatabaseHandler database;
    //private MenuItem Inscript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        res = getResources();
        about = (TextView)findViewById(R.id.about);
        aboutblock = (LinearLayout)findViewById(R.id.aboutblock);
        passwordforget = (TextView)findViewById(R.id.passforget);
        Connexion = (Button)findViewById(R.id.connexion);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        SpannableString content = new SpannableString(res.getString(R.string.about));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        about.setText(content);
        SpannableString content1 = new SpannableString(res.getString(R.string.passforget));
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        passwordforget.setText(content1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res.getString(R.string.app_name));
        session = new SessionManager(this);

        //Instanciata de la classe DatabaseHandler
        database = new DatabaseHandler(this);

        aboutblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),About.class);
                startActivity(intent);
            }
        });

        passwordforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Passforget.class);
                startActivity(intent);
            }
        });

        Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog = new ProgressDialog(Connexion.this);
                pDialog.setMessage("Connexion en cours...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                login();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //je suis entrain de souligner le bouton s'inscrire
        getMenuInflater().inflate(R.menu.menu_inscript, menu);
        /*SpannableString content = new SpannableString(res.getString(R.string.inscript));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        Inscript = menu.findItem(R.id.inscript);
        Inscript.setTitle(content);*/
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.inscript:
                ControleGPS();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login() {

        if (validate()==true) {
            Connexion();
        }


    }

    public boolean validate() {
        boolean valid = true;

        String _email = email.getText().toString();
        String _password = password.getText().toString();

        if (_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            email.setError(res.getString(R.string.email_error));
            valid = false;
        } else {
            email.setError(null);
        }

        if (_password.isEmpty() || _password.length() <= 8) {
            password.setError(res.getString(R.string.password_error1));
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission est garantie
                    Intent intent = new Intent(getApplicationContext(),forminscript1.class);
                    startActivity(intent);
                } else {
                    // La permission est refusée
                    affichageDialog();
                }
                return;
            }
        }
    }


    private void ControleGPS()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Connexion.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else
        {
            Intent intent = new Intent(getApplicationContext(),forminscript1.class);
            startActivity(intent);
        }
    }

    private void affichageDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Connexion.this);
        alertDialog.setTitle("Permission");
        alertDialog.setMessage("Vous devriez autoriser à Wazaby d'accéder à vos coordonnées GPS afin de vous inscrire");
        alertDialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        ControleGPS();
                    }
                });
        alertDialog.show();
    }


    private void Connexion()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.URL_CONNEXION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        showJSON(response);
                        if(succes!=1) {
                            email.setText("");
                            password.setText("");
                            Toast.makeText(Connexion.this, "votre mot de passe ou votre adresse e-mail est incorrecte" , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        if(error instanceof ServerError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur au niveau du serveur viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof NetworkError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof AuthFailureError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur d'authentification réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof ParseError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof NoConnectionError)
                        {
                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir, veuillez revoir votre connexion internet ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else if(error instanceof TimeoutError)
                        {
                            Toast.makeText(Connexion.this,"Le delai d'attente viens d'expirer,veuillez revoir votre connexion internet ! ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }else
                        {

                            Toast.makeText(Connexion.this,"Une erreur  du réseau viens de survenir ",Toast.LENGTH_LONG).show();
                            email.setText("");
                            password.setText("");
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("EMAIL",email.getText().toString());
                params.put("Password",password.getText().toString());
                params.put("Test","0");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response)
    {
        try {
             reponse = new JSONObject(response);
             succes = reponse.getInt("succes");
            if(succes==1)
            {
                session.createLoginSession(reponse.getInt("ID"));
                Profil profil = new Profil(reponse.getInt("ID"),reponse.getString("NOM"),reponse.getString("PRENOM"),reponse.getString("DATE_DE_NAISSANCE")
                        ,reponse.getString("SEXE"),reponse.getString("EMAIL"),reponse.getString("PHOTO"),reponse.getString("KEYPUSH")
                        ,reponse.getString("LANGUE"),reponse.getString("ETAT"),reponse.getString("Pays"),reponse.getString("Ville"),reponse.getString("ID_Prob"));
                database.addUSER(profil);
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
