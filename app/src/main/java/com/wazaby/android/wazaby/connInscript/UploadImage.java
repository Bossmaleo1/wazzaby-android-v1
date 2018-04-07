package com.wazaby.android.wazaby.connInscript;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wazaby.android.wazaby.R;

/**
 * Created by bossmaleo on 31/10/17.
 */

public class UploadImage extends AppCompatActivity {

    private Toolbar toolbar;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadimage);
        res = getResources();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res.getString(R.string.inscript_uploading));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Connexion.class);

        startActivity(intent);
    }
}
