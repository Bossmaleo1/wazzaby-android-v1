package com.wazaby.android.wazaby.adapter;

import android.content.Context;
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
import com.wazaby.android.wazaby.model.data.displaycommentary;
import com.wazaby.android.wazaby.model.data.friendProbItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by bossmaleo on 02/02/18.
 */

public class displaycommentaryadapter extends RecyclerView.Adapter<displaycommentaryadapter.MyViewHolder> {

    List<displaycommentary> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private DatabaseHandler database;
    private SessionManager session;
    private Profil user;

    public displaycommentaryadapter(Context context,List<displaycommentary> data)
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
    public displaycommentaryadapter .MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapterdisplaycommentary,parent,false);
        displaycommentaryadapter.MyViewHolder holder = new displaycommentaryadapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(displaycommentaryadapter.MyViewHolder holder, int position) {
        displaycommentary current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.title1.setText(current.getNOM());
        holder.date.setText(current.getDATETIME());
        Glide.with(current.getContext1())
                .load("https://wazaby939393.000webhostapp.com/Images/"+current.getPHOTO())
                .into(holder.icon);
        holder.icononline.setImageResource(current.getIcononline());
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
        TextView date;
        TextView title1;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            title1 = (TextView)itemView.findViewById(R.id.title1);
            icon = (ImageView)itemView.findViewById(R.id.icon);
            icononline = (ImageView)itemView.findViewById(R.id.icononline);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }


}

