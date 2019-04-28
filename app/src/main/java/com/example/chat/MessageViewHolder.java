package com.example.chat;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimeZone;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView authorTextView;
    private TextView bodyTextView;
    private TextView timestampTextView;

    public MessageViewHolder(View v) {
        super(v);
        authorTextView = v.findViewById(R.id.authorTextView);
        bodyTextView = v.findViewById(R.id.bodyTextView);
        timestampTextView = v.findViewById(R.id.timestampTextView);
    }

    public void update(Message m){
        authorTextView.setText(m.getAuthor());
        bodyTextView.setText(m.getBody());
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        calendar.setTimeInMillis(m.getTimestamp() * 1000L);
        timestampTextView.setText(DateFormat.format("dd/MM/yyyy HH:mm:ss", calendar).toString());
    }
}
