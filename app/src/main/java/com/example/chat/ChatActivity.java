package com.example.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<Message>();
    private MessageAdapter adapter = new MessageAdapter(messageList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        RecyclerView rv = findViewById(R.id.RecyclerView);
        RecyclerView.LayoutManager lw = new LinearLayoutManager(this);
        rv.setLayoutManager(lw);

        rv.setAdapter(adapter);
    }

    public void addMessage(Message m) {
        messageList.add(m);
        adapter.notify();
    }

    void clearChatList(){
        messageList.clear();
    }

    void addReceivedMessage(Message m){
        messageList.add(m);
    }

    void sendMessage(Message m){
        //Envoyer un message sur la liste
    }
}
