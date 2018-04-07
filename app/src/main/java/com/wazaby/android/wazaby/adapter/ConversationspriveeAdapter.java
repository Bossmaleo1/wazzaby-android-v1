package com.wazaby.android.wazaby.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.model.data.Conversationprivateitem;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bossmaleo on 22/11/17.
 */

public class ConversationspriveeAdapter extends RecyclerView.Adapter<ConversationspriveeAdapter.MyViewHolder> {

    List<Conversationprivateitem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public ConversationspriveeAdapter(Context context,List<Conversationprivateitem> data)
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
    public ConversationspriveeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_privateconversation,parent,false);
        ConversationspriveeAdapter.MyViewHolder holder = new ConversationspriveeAdapter.MyViewHolder(view);
        return holder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(ConversationspriveeAdapter.MyViewHolder holder, int position) {
        Conversationprivateitem current = data.get(position);
        holder.message1.setText(current.getMessage());
        holder.icon1.setImageResource(current.getIcon());
        holder.message1.setBackground(current.getCouleur());
        Glide.with(current.getContext1())
                .load("https://wazaby939393.000webhostapp.com/Images/"+current.getPhoto())
                .into(holder.photo1);
        //messages recues
        if(current.isEnvoie()) {
            holder.layout1.setVisibility(View.VISIBLE);
        }else
        {
            holder.layout1.setVisibility(View.GONE);
        }
        //message envoyés
        if(current.isReception()) {
            holder.layout2.setVisibility(View.VISIBLE);
        }else
        {
            holder.layout2.setVisibility(View.GONE);
        }

        holder.icon2.setImageResource(current.getIcon2());
        holder.message2.setText(current.getMessage2());
        Glide.with(current.getContext1())
                .load("https://wazaby939393.000webhostapp.com/Images/"+current.getPhoto2())
                .into(holder.photo2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layout1;
        TextView message1;
        ImageView photo1;
        ImageView icon1;
        LinearLayout layout2;
        TextView message2;
        ImageView photo2;
        ImageView icon2;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            layout1 = (LinearLayout)itemView.findViewById(R.id.layout1);
            message1 = (TextView)itemView.findViewById(R.id.textView);
            photo1 = (CircleImageView)itemView.findViewById(R.id.imageView2);
            icon1 = (ImageView)itemView.findViewById(R.id.imageView);

            layout2 = (LinearLayout)itemView.findViewById(R.id.layout2);
            message2 = (TextView)itemView.findViewById(R.id.textView1);
            photo2 = (CircleImageView)itemView.findViewById(R.id.imageView3);
            icon2 = (ImageView)itemView.findViewById(R.id.imageView1);
        }
    }

}
