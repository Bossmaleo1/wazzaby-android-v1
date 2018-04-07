package com.wazaby.android.wazaby.connInscript;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.wazaby.android.wazaby.R;

import java.util.Calendar;

/**
 * Created by bossmaleo on 25/08/17.
 */

public class forminscript2 extends AppCompatActivity{

    private Toolbar toolbar;
    private Resources res;
    private Button send;
    private EditText email;
    private Intent intent;
    private Button naissance;


    private int day;
    private int month;
    private int year;

    private int heure;
    private int minute;
    private int seconde;


    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forminscript2);
        res = getResources();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        send = (Button)findViewById(R.id.send);
        email = (EditText)findViewById(R.id.email);
        intent = getIntent();
        naissance = (Button) findViewById(R.id.naissance2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res.getString(R.string.inscript1_1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setCurrentDateOnView();
        addListenerOnButton();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),forminscript3.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User chose the "Settings" item, show the app settings UI...
                Intent _result = new Intent(getApplicationContext(),Connexion.class);

                startActivity(_result);
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    public void setCurrentDateOnView() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
/*
        heure = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);*/
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth+1;
            day = selectedDay;

            if(month>9 && day>9) {
                naissance.setText(day + "/" + month + "/" + year);
            }else if(day<9 && month>9)
            {
                naissance.setText("0"+day + "/" + month + "/" + year);
            }else if(month<9 && day>9)
            {
                naissance.setText(day + "/0" + month + "/" + year);
            }
            else if(month<9 && day<9)
            {
                naissance.setText("0"+day + "/0" + month + "/" + year);
            }
        }
    };

    //ajouter la boîte de dialogue Calendrier
    public void addListenerOnButton() {


        naissance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });
    }


    @Override
    public void onBackPressed() {
        Intent _result = new Intent(getApplicationContext(),Connexion.class);
        startActivity(_result);
    }

}