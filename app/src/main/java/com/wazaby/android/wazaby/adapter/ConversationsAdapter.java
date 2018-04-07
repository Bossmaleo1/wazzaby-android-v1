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
import com.wazaby.android.wazaby.model.data.ConversationItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by bossmaleo on 08/11/17.
 */

public class ConversationsAdapter extends RecyclerView.Adapter<ConversationsAdapter.MyViewHolder> {


    List<ConversationItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public ConversationsAdapter(Context context,List<ConversationItem> data)
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
    public ConversationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapterlastconversations,parent,false);
        ConversationsAdapter.MyViewHolder holder = new ConversationsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ConversationsAdapter.MyViewHolder holder, int position) {
        ConversationItem current = data.get(position);
        holder.title.setText(current.getFriendLibelle());
        if(!current.getImageID().equals("null")) {
            Glide.with(current.getContext1())
                    .load("https://wazaby939393.000webhostapp.com/Images/" + current.getImageID())
                    .into(holder.icon);
        }else
        {
            holder.icon.setImageResource(R.drawable.ic_profile_colorier);
        }

        holder.icononline.setImageResource(current.getOnlinestatus());
        holder.title1.setText(current.getContenu());
        holder.icononline.setColorFilter(context.getResources().getColor(current.getColor1()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView title1;
        ImageView icon;
        ImageView icononline;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            title1 = (TextView)itemView.findViewById(R.id.title1);
            icon = (ImageView)itemView.findViewById(R.id.icon);
            icononline = (ImageView)itemView.findViewById(R.id.icononline);
        }
    }


}
