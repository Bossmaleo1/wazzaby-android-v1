package com.wazaby.android.wazaby.connInscript;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wazaby.android.wazaby.R;

/**
 * Created by bossmaleo on 16/08/17.
 */

public class Passforget extends AppCompatActivity {

    private Toolbar toolbar;
    private Resources res;
    private Button send;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passforget);

        res = getResources();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        send = (Button)findViewById(R.id.send);
        email = (EditText)findViewById(R.id.email);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res.getString(R.string.passforget));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User chose the "Settings" item, show the app settings UI...
                Intent _result = new Intent();
                setResult(Activity.RESULT_OK, _result);
                finish();
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void login() {

        if (!validate()) {
            return;
        }


    }



    public boolean validate() {
        boolean valid = true;

        String _email = email.getText().toString();

        if (_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            email.setError(res.getString(R.string.email_error));
            valid = false;
        } else {
            email.setError(null);
        }



        return valid;
    }


}