package com.devjurnal.aplikasichat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DevJurnal on 11/3/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    JSONArray jsonArray;
    public ChatAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat,parent,false);
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        // Get Data
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);

            // Set Data
            holder.tvItemNama.setText(jsonObject.getString("Nama"));
            holder.tvItemIsi.setText(jsonObject.getString("Pesan"));
            holder.tvItemDate.setText(jsonObject.getString("Waktu"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemNama,tvItemIsi,tvItemDate;
        ImageView ivItemPoster;
        public ChatViewHolder(View itemView) {
            super(itemView);
            tvItemNama = itemView.findViewById(R.id.tvItemNama);
            tvItemIsi =  itemView.findViewById(R.id.tvItemIsi);
            tvItemDate =  itemView.findViewById(R.id.tvItemDate);
            ivItemPoster =  itemView.findViewById(R.id.ivItemPoster);
        }
    }
}
