package com.example.chat;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<>();
    private MessageAdapter adapter = new MessageAdapter(messageList);
    private MessageRetriever retriever;
    private static final String SERVER = "chat.plade.org";
    private String user = "Moi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        RecyclerView rv = findViewById(R.id.RecyclerView);

        retriever = new MessageRetriever(this, SERVER);
        retriever.execute();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


    void clearChatList(){
        messageList.clear();
        adapter.notifyDataSetChanged();
    }

    void addReceivedMessage(Message m){
        messageList.add(m);
        adapter.notifyDataSetChanged();
    }

    //Envoyer un message sur la liste
    void sendMessage(Message m){
        messageList.add(m);
        adapter.notifyItemInserted(messageList.size()-1);
        Log.i("MESSAGE", Arrays.toString(messageList.toArray()));

        MessageSender ms = new MessageSender(this, SERVER, m.getQueue(), m.getAuthor());
        ms.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, m);
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
        if (message != null) {
            sendMessage(new Message("android", user, System.currentTimeMillis()/1000, message));
//            Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
//            t.show();
            RecyclerView rv = findViewById(R.id.RecyclerView);
            rv.smoothScrollToPosition(messageList.size()-1);
        }
        text.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (retriever.isCancelled()) {
            clearChatList();
            retriever = new MessageRetriever(this, SERVER);
            retriever.execute();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        retriever.cancel(true);
    }
}
