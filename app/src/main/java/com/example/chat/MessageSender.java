package com.example.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class MessageSender extends AsyncTask<Message, Void, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private HttpURLConnection connection;

    public MessageSender(Context context, String server, String queue, String author) {
        this.context = context;
        try {
            URL url = new URL("http://" + server + ":2019/" + queue + "?author=" + author);
            this.connection = (HttpURLConnection) url.openConnection();
        } catch (Exception ignored) {}
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Boolean doInBackground(Message... messages) {
        Message message = messages[0];
        try {
            ChatIO.postData(connection, message.getBody());
            return Boolean.TRUE;
        } catch (Exception ignored) {
            return Boolean.FALSE;
        }
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        if (!b) {
            Toast t = Toast.makeText(context, "Impossible d'envoyer le message", Toast.LENGTH_SHORT);
            t.show();
            Log.i("Erreur", "Erreur lors de l'envoi");
        } else {
            Toast t = Toast.makeText(context, "Message envoyé", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
