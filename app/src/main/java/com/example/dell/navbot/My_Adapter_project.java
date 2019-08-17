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

public class My_Adapter_project extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    public itemdata_project[] itemdata_projects;

    public My_Adapter_project(itemdata_project[] itemdata_projects, Context context) {

        this.context = context;
        this.itemdata_projects = itemdata_projects;
        this.itemdata_projects[0]=null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==255)
        {
            View craeteproject = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_project, null);
            MyViewHolder_createproject crate_projecr = new MyViewHolder_createproject(craeteproject);
            return crate_projecr;
        }
        else {
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_project, null);
            My_Adapter_project.MyViewHolderProject myViewHolderProject = new My_Adapter_project.MyViewHolderProject(itemLayoutView);
            return myViewHolderProject;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(position==0)
        {
            MyViewHolder_createproject createproject = (MyViewHolder_createproject) holder;
            createproject.create_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // create post here
                    Intent intent=new Intent(context,create_project.class);
                    context.startActivity(intent);
                }
            });
        }
        else {
            My_Adapter_project.MyViewHolderProject myViewHolderProject = (My_Adapter_project.MyViewHolderProject) holder;
            myViewHolderProject.name.setText(itemdata_projects[position].name + " " + itemdata_projects[position].namelast);
            myViewHolderProject.money.setText(itemdata_projects[position].money + "$");
            myViewHolderProject.desc.setText(itemdata_projects[position].desc);
            myViewHolderProject.time.setText(itemdata_projects[position].time + " Months");
            myViewHolderProject.image_project.setImageResource(itemdata_projects[position].image);
            myViewHolderProject.card_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "you click on " + itemdata_projects[position].name.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return itemdata_projects.length;
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0) return 255;
        return super.getItemViewType(position);
    }


    public class MyViewHolderProject extends RecyclerView.ViewHolder {
        public TextView name, money, desc,time;
        public CardView card_project;
        public ImageView image_project;

        public MyViewHolderProject(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_project);
            money = (TextView) itemView.findViewById(R.id.money_project);
            desc = (TextView) itemView.findViewById(R.id.descreption_project);
            card_project = (CardView) itemView.findViewById(R.id.card_project);
            time = (TextView) itemView.findViewById(R.id.time_project);
            image_project = (ImageView) itemView.findViewById(R.id.image_project);
        }
    }
    public class MyViewHolder_createproject extends RecyclerView.ViewHolder
    {
        public Button create_project;

        public MyViewHolder_createproject(View itemView) {
            super(itemView);
            create_project=(Button)itemView.findViewById(R.id.create_project);

        }
    }
}