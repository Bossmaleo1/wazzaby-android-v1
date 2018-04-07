package com.wazaby.android.wazaby.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.model.Database.SessionManager;
import com.wazaby.android.wazaby.model.dao.DatabaseHandler;
import com.wazaby.android.wazaby.model.data.Profil;
import com.wazaby.android.wazaby.model.data.friendProbItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by bossmaleo on 03/11/17.
 */

public class friendonlineAdapter  extends RecyclerView.Adapter<friendonlineAdapter.MyViewHolder> {


    List<friendProbItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private DatabaseHandler database;
    private SessionManager session;
    private Profil user;

    public friendonlineAdapter(Context context,List<friendProbItem> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public friendonlineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_friendonline,parent,false);
        friendonlineAdapter.MyViewHolder holder = new friendonlineAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(friendonlineAdapter.MyViewHolder holder, int position) {
        friendProbItem current = data.get(position);
        holder.title.setText(current.getFriendLibelle());
        //holder.icon.setImageResource(current.getImageID());
        //database = new DatabaseHandler(current.getContext1());
        //session = new SessionManager(current.getContext1());
       // user = database.getUSER(Integer.valueOf(session.getUserDetail().get(SessionManager.Key_ID)));
        if(!current.getImageID().equals("null")) {
            Glide.with(current.getContext1())
                    .load("https://wazaby939393.000webhostapp.com/Images/" + current.getImageID())
                    .into(holder.icon);
        }else
        {
            holder.icon.setImageResource(R.drawable.ic_profile_colorier);
        }
        holder.icononline.setImageResource(current.getOnlinestatus());
        holder.icononline.setColorFilter(context.getResources().getColor(current.getColor1()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView icon;
        ImageView icononline;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            icon = (ImageView)itemView.findViewById(R.id.icon);
            icononline = (ImageView)itemView.findViewById(R.id.icononline);
        }
    }


}
