package com.wazaby.android.wazaby.connInscript;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.appviews.Home;
import com.wazaby.android.wazaby.model.Database.SessionManager;

public class MainActivity extends AppCompatActivity {
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        Thread background = new Thread() {
            public void run() {


                try {

                    sleep(3*1000);
                    if(!session.IsLoggedIn()) {
                        Intent i = new Intent(getApplicationContext(), Connexion.class);
                        startActivity(i);
                    }else
                    {
                        Intent i = new Intent(getApplicationContext(), Home.class);
                        startActivity(i);
                    }
                    finish();

                } catch (Exception e) {

                }
            }
        };

        background.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
