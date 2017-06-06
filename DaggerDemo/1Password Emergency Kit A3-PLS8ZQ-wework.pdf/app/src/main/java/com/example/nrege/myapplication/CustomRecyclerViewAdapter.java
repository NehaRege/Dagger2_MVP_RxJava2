package com.example.nrege.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nrege.myapplication.Models.User;

import java.util.ArrayList;

/**
 * Created by nrege on 6/2/17.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.SampleViewHolder> {

    private ArrayList<User> userData;

    private static OnRecyclerViewItemClickListener onItemClickListener;


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }


    // Default constructor that takes a view as an argument and passes it to super

    public static class SampleViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public SampleViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.rv_list_text_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });

        }

    }

    // Constructor for custom adapter. It takes an ArrayList of data as an argument.

    public CustomRecyclerViewAdapter(ArrayList<User> inComingData, OnRecyclerViewItemClickListener listener) {

        this.onItemClickListener = listener;

        if (inComingData != null){
            // if there is incoming data, use it
//            this.data = inComingData;
            this.userData = inComingData;
        } else {
            // if there is no incoming data, make an empty list to avoid NullPointerExceptions
            this.userData = new ArrayList<User>();
        }
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get context from parent ViewGroup
        Context context = parent.getContext();

        // Get layoutInflater, which will inflate our custom list item layout for us
        LayoutInflater inflater = LayoutInflater.from(context);

        View cardLayout = inflater.inflate(R.layout.rv_card_layout,parent,false);
        return new SampleViewHolder(cardLayout);


    }



    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        User dataItem = userData.get(position);
        TextView textView = holder.textView;
        textView.setText(dataItem.getName());

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
