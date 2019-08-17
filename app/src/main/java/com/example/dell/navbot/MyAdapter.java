package com.example.dell.navbot;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Itemdata[] itemdata;
    public Context context;
    public int type;

    public MyAdapter(Itemdata[] itemdata,Context context)
    {
        this.context=context;
        this.itemdata=itemdata;
        this.itemdata[0]=null;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==255)
        {
            View craetepost = LayoutInflater.from(parent.getContext()).inflate(R.layout.createpost, null);
            MyViewHoldercreatepost crate_post = new MyViewHoldercreatepost(craetepost);
            return crate_post;
        }
        else
            if (viewType == 1000) {
                View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_witout_image, null);
                MyViewHolderWithoutImg withoutImg = new MyViewHolderWithoutImg(itemLayoutView);
                return withoutImg;
            } else {
                View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler, null);
                MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
                return viewHolder;
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            int viewtype = getItemViewType(position);
        if(position==0)
        {
            MyViewHoldercreatepost createpost = (MyViewHoldercreatepost) holder;
            createpost.create_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // create post here
                    Intent intent=new Intent(context,createpost.class);
                    context.startActivity(intent);
                }
            });
        }
        else {
            if (viewtype == 1000) {
                MyViewHolderWithoutImg withoutimage = (MyViewHolderWithoutImg) holder;
                withoutimage.rec_title.setBackgroundColor(Color.parseColor("#ffffff"));
                withoutimage.rec_detail.setBackgroundColor(Color.parseColor("#ffffff"));
                withoutimage.rec_title.setText(itemdata[position].title);
                withoutimage.rec_detail.setText(itemdata[position].detail);
                withoutimage.rec_detail2.setText(itemdata[position].detail2);
                withoutimage.num_worker.setText(itemdata[position].num_worker);
            } else {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.rec_title.setText(itemdata[position].title);
                myViewHolder.rec_detail.setText(itemdata[position].detail);
                myViewHolder.rec_detail2.setText(itemdata[position].detail2);
                myViewHolder.num_worker.setText(itemdata[position].num_worker);
                myViewHolder.rec_img.setImageResource(itemdata[position].image);
                myViewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(context, "you click on " + itemdata[position].title.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                myViewHolder.massage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                myViewHolder.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
            return itemdata.length;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) return 255;
        else if(itemdata[position].image==1000)
            return 1000;
        return super.getItemViewType(position);
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public  TextView rec_title,rec_detail,rec_detail2,num_worker;
        public ImageView rec_img;
        public CardView card;
        public Button comment, massage;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public MyViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            super(itemView);

            rec_title = (TextView) itemView.findViewById(R.id.title_item);
            rec_detail=(TextView)itemView.findViewById(R.id.detail_item);
            rec_detail2=(TextView)itemView.findViewById(R.id.detail_item2);
            num_worker=(TextView)itemView.findViewById(R.id.num_worker);
            rec_img = (ImageView) itemView.findViewById(R.id.image_item);
            comment=(Button)itemView.findViewById(R.id.comment_item);
            massage=(Button)itemView.findViewById(R.id.massage_item);
            card=(CardView)itemView.findViewById(R.id.card_item);
        }
        public int getItemCount()
        {
                return itemdata.length;
        }
    }
    public class MyViewHolderWithoutImg extends RecyclerView.ViewHolder
    {
        public  TextView rec_title,rec_detail,rec_detail2,num_worker;
        public CardView card;
        public Button comment, send_order;

        public MyViewHolderWithoutImg(View itemView) {
            super(itemView);
            rec_title = (TextView) itemView.findViewById(R.id.title_item);
            rec_detail=(TextView)itemView.findViewById(R.id.detail_item);
            rec_detail2=(TextView)itemView.findViewById(R.id.detail_item2_img);
            num_worker=(TextView)itemView.findViewById(R.id.num_worker_img);
            comment=(Button)itemView.findViewById(R.id.comment_item);
            send_order=(Button)itemView.findViewById(R.id.massage_item);
            card=(CardView)itemView.findViewById(R.id.card_item);
        }
    }
    public class MyViewHoldercreatepost extends RecyclerView.ViewHolder
    {
        public Button create_post;

        public MyViewHoldercreatepost(View itemView) {
            super(itemView);
            create_post=(Button)itemView.findViewById(R.id.create_post);

        }
    }

}
   // Now that we've defined the basic adapter and ViewHolder, we need to begin filling in our adapter. First, let's store a member variable for the list of contacts and pass the list in through our constructor:


