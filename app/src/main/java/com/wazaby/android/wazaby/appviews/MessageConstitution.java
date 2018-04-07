package com.wazaby.android.wazaby.appviews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.adapter.ConversationspriveeAdapter;
import com.wazaby.android.wazaby.model.Config;
import com.wazaby.android.wazaby.model.Const;
import com.wazaby.android.wazaby.model.Database.SessionManager;
import com.wazaby.android.wazaby.model.dao.DatabaseHandler;
import com.wazaby.android.wazaby.model.data.Conversation;
import com.wazaby.android.wazaby.model.data.Conversationprivateitem;
import com.wazaby.android.wazaby.model.data.Profil;
import com.wazaby.android.wazaby.utils.NotificationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bossmaleo on 19/11/17.
 */

public class MessageConstitution extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent;
    private Resources res;
    private de.hdodenhof.circleimageview.CircleImageView profilimage;
    //private ImageView arrow_back;
    private TextView Profiltextname;
    private String maleodrawable;
    private RecyclerView recyclerView;
    private ConversationspriveeAdapter allUsersAdapter;
    private String KEYPUSH;
    private int ID;
    private String Nom;
    private String PHOTO;
    private ImageView sender;
    private EditText editvalue;
    private List<Conversationprivateitem> data = new ArrayList<>();
    private Context context;
    private DatabaseHandler database;
    private Profil user;
    private SessionManager session;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String Libelle = null;

    private List<Conversation> conversationList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messageconstitution);
        res = getResources();
        profilimage = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.imageToolBar);
        //arrow_back = (ImageView)findViewById(R.id.arrow_back);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        intent = getIntent();
        session = new SessionManager(this);
        database = new DatabaseHandler(this);
        user = database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID)));
        Profiltextname = (TextView)findViewById(R.id.toolbarText);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Profiltextname.setText(intent.getStringExtra("name"));
        ID = intent.getIntExtra("ID",0);
        conversationList = database.getAllConversation(String.valueOf(ID));
        KEYPUSH=intent.getStringExtra("KEYPUSH");
        PHOTO=intent.getStringExtra("PHOTO");
        Nom = intent.getStringExtra("name");
        sender = (ImageView)findViewById(R.id.sender);
        editvalue = (EditText)findViewById(R.id.editvalue);
        maleodrawable= intent.getStringExtra("imageview");
        context = this;
        Glide.with(this)
                .load("https://wazaby939393.000webhostapp.com/Images/"+maleodrawable)
                .into(profilimage);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsersAdapter = new ConversationspriveeAdapter(this,data);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(allUsersAdapter);
        /*arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });*/

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    Drawable bossdraw = getResources().getDrawable(R.drawable.rounded_corner);
                    Drawable bossdraw2 = getResources().getDrawable(R.drawable.rounded_corner1);
                    database.addConversation(new Conversation(String.valueOf(ID),message,"2"));
                    data.add(new Conversationprivateitem(user.getPHOTO(),R.drawable.arrow_bg1,
                            editvalue.getText().toString(),bossdraw,context,R.drawable.arrow_bg2,
                            PHOTO,message,bossdraw2,false,true));
                    allUsersAdapter.notifyDataSetChanged();
                }
            }
        };

        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Drawable bossdraw = getResources().getDrawable(R.drawable.rounded_corner);
                Drawable bossdraw2 = getResources().getDrawable(R.drawable.rounded_corner1);
                Libelle = editvalue.getText().toString();
                database.addConversation(new Conversation(String.valueOf(ID),Libelle,"1"));
                data.add(new Conversationprivateitem(user.getPHOTO(),R.drawable.arrow_bg1,
                        editvalue.getText().toString(),bossdraw,context,R.drawable.arrow_bg2,
                        PHOTO,"",bossdraw2,true,false));
                allUsersAdapter.notifyDataSetChanged();
                EnvoyerMessage();
                editvalue.setText("");
            }
        });

        for (int i = 0;i<conversationList.size();i++)
        {
            Drawable bossdraw = getResources().getDrawable(R.drawable.rounded_corner);
            Drawable bossdraw2 = getResources().getDrawable(R.drawable.rounded_corner1);
            if(conversationList.get(i).getEtat().equals("2"))
            {
                data.add(new Conversationprivateitem(user.getPHOTO(),R.drawable.arrow_bg1,
                        editvalue.getText().toString(),bossdraw,context,R.drawable.arrow_bg2,
                        PHOTO,conversationList.get(i).getLibelle(),bossdraw2,false,true));
            }else
            {
                data.add(new Conversationprivateitem(user.getPHOTO(),R.drawable.arrow_bg1,
                        conversationList.get(i).getLibelle(),bossdraw,context,R.drawable.arrow_bg2,
                        PHOTO,"",bossdraw2,true,false));
            }

        }
    }

    private void EnvoyerMessage()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Const.URL_SENDMESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MessageConstitution.this,"message envoyé !",Toast.LENGTH_LONG).show();
                        editvalue.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("title",Nom);
                params.put("message",Libelle);
                params.put("push_type","individual");
                params.put("ID_EME",String.valueOf(ID));
                params.put("ID_RECEP",String.valueOf(user.getID()));
                params.put("ID_Prob",String.valueOf(user.getIDPROB()));
                params.put("regId",KEYPUSH);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // User chose the "Settings" item, show the app settings UI...
                /*Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();*/
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                return true;



            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
