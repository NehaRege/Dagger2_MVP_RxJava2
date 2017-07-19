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

    static class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewEmail;

        SampleViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.rv_list_text_name);
            textViewEmail = (TextView) itemView.findViewById(R.id.rv_list_text_email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public CustomRecyclerViewAdapter(ArrayList<User> inComingData, OnRecyclerViewItemClickListener listener) {
        this.onItemClickListener = listener;

        if (inComingData != null) {
            this.userData = inComingData;
        } else {
            this.userData = new ArrayList<>();
        }
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardLayout = inflater.inflate(R.layout.rv_card_layout, parent, false);
        return new SampleViewHolder(cardLayout);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        User dataItem = userData.get(position);
        TextView textViewName = holder.textViewName;
        textViewName.setText(dataItem.getName());
        TextView textViewEmail = holder.textViewEmail;
        textViewEmail.setText(dataItem.getEmail());
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}