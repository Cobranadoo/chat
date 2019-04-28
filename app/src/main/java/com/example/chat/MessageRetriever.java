package com.example.chat;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;

public class MessageRetriever extends AsyncTask<String, Message, Void> {

    private final ChatActivity activity;
    private final ChatIO chatIO;
    private final String server;

    public MessageRetriever(ChatActivity activity, String server) {
        this.activity = activity;
        this.chatIO = new ChatIO();
        this.server = server;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(String[] params) {
        int id = 0;
        while (true) {
            if (isCancelled())
                return null;
            try {
                Message m = chatIO.fetchMessage(server, "android", id);
                publishProgress(m);
                id++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void o) {
        super.onPostExecute(o);
    }

    @Override
    protected  void onProgressUpdate(Message... values) {
        super.onProgressUpdate(values);
        for (Message message: values)
            activity.addReceivedMessage(message);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
