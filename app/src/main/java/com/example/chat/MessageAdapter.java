package com.example.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private List<Message> messageList;
    Context context;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //View v = LayoutInflater.from(context).inflate(R.layout.message_layout /*(à créer)*/, parent, false);
        //return new MessageViewHolder(v);
        return null; // --> A enlever
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder vh, int i) {
        vh.update(messageList.get(i));
    }

    @Override
    public int getItemCount(){
        return messageList.size();
    }

}
