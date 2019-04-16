package com.example.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView authorTextView;
    private TextView bodyTextView;
    private TextView timestampTextView;

    public MessageViewHolder(@NonNull View v) {

        super(v);
        //authorTextView = v.findViewById("id");
    }

    public void update(Message m){
        authorTextView.setText(m.getAuthor());
        bodyTextView.setText(m.getBody());
        timestampTextView.setText(""+m.getTimestamp()); // --> A faire plus proprement

    }
}
