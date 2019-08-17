package com.example.dell.navbot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdpter_notify extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    public Itemdata_notify[] itemdata_notify;

    public MyAdpter_notify(Itemdata_notify[] itemdata_notify, Context context) {

        this.context = context;
        this.itemdata_notify = itemdata_notify;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_notify, null);
        MyAdpter_notify.MyViewHolder_notify myViewHoldernoti = new MyAdpter_notify.MyViewHolder_notify(itemLayoutView);
        return myViewHoldernoti;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MyAdpter_notify.MyViewHolder_notify myviewHolder_notify = (MyAdpter_notify.MyViewHolder_notify) holder;
        myviewHolder_notify.title.setText(itemdata_notify[position].title);
        myviewHolder_notify.desc.setText(itemdata_notify[position].desc);
    }

    @Override
    public int getItemCount() {

        return itemdata_notify.length;
    }

    public class MyViewHolder_notify extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public MyViewHolder_notify(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_notify);
            desc = (TextView) itemView.findViewById(R.id.desc_notify);
        }
    }
}

