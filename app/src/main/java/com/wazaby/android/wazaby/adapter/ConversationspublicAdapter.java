package com.wazaby.android.wazaby.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.appviews.AfficheCommentairePublic;
import com.wazaby.android.wazaby.model.data.ConversationItem;
import com.wazaby.android.wazaby.model.data.ConversationPublicItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by bossmaleo on 09/11/17.
 */

public class ConversationspublicAdapter extends RecyclerView.Adapter<ConversationspublicAdapter.MyViewHolder> {


    List<ConversationPublicItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public ConversationspublicAdapter(Context context,List<ConversationPublicItem> data)
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
    public ConversationspublicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapterconversationspublic,parent,false);
        ConversationspublicAdapter.MyViewHolder holder = new ConversationspublicAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConversationspublicAdapter.MyViewHolder holder, int position) {
        final ConversationPublicItem current = data.get(position);
        holder.title.setText(current.getNameMembreProb());
        holder.title1.setText(current.getDatetime());
        holder.contenu.setText(current.getContenu());
        holder.commentnumber.setText(current.getCommentnumber());
        if(!current.getImageID().equals("null")) {
            Glide.with(current.getContext1())
                    .load("https://wazaby939393.000webhostapp.com/Images/" + current.getImageID())
                    .into(holder.picture);
        }else
        {
            holder.picture.setImageResource(R.drawable.ic_profile_colorier);
        }
        holder.commenticon.setImageResource(current.getIconComment());
        //holder.icononline.setColorFilter(context.getResources().getColor(current.getColor1()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView title1;
        TextView contenu;
        TextView commentnumber;
        ImageView picture;
        ImageView commenticon;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            title1 = (TextView)itemView.findViewById(R.id.title1);
            contenu = (TextView) itemView.findViewById(R.id.contenu);
            commentnumber = (TextView) itemView.findViewById(R.id.contenucomment);
            picture = (ImageView) itemView.findViewById(R.id.icon);
            commenticon = (ImageView) itemView.findViewById(R.id.commenticon);
        }
    }


}
