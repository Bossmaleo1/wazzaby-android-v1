package com.wazaby.android.wazaby.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wazaby.android.wazaby.R;
import com.wazaby.android.wazaby.model.data.Categorie_prob;
import java.util.Collections;
import java.util.List;

/**
 * Created by bossmaleo on 11/01/18.
 */

public class categorieProbAdapter  extends RecyclerView.Adapter<categorieProbAdapter.MyViewHolder> {


    List<Categorie_prob> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public categorieProbAdapter(Context context,List<Categorie_prob> data)
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
    public categorieProbAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.categorieprobadapter,parent,false);
        categorieProbAdapter.MyViewHolder holder = new categorieProbAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(categorieProbAdapter.MyViewHolder holder, int position) {
        Categorie_prob current = data.get(position);
        holder.title.setText(current.getLibelle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }


}
