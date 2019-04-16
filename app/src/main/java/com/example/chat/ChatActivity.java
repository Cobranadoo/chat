package com.example.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        adapter.notifyItemInserted(messageList.size()-1);
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

    // Extrait le texte du champ e
    protected String extract(EditText e) {
        if (!e.getText().toString().matches(""))
            return e.getText().toString();
        return null;
    }

    public void onSendButton(View v) {
        EditText text = findViewById(R.id.EditText);
        String message = extract(text);
        addMessage(new Message(message));
        Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        t.show();
    }
}
