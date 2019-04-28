package com.example.chat;

import android.os.SystemClock;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private static int initId = 0;
    private int id;
    // Salon de discussion utilisé
    private String queue;
    // Auteur du message
    private String author;
    // Instant de rédaction du message sous la forme d'un timestamp Unix
    private long timestamp;
    // Contient le corps du message
    private String body;

    public Message(String queue, String author, long timestamp, String body) {
        this.id = initId++;
        this.timestamp = timestamp;
        this.body = body;
        this.author = author;
        this.queue = queue;
    }

    // Créer un Message depuis un objet JSON
    public static Message messageFromJSON(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        Log.i("JSON", jsonObject.toString());
        return new Message(
                "",
                jsonObject.getString("author"),
                jsonObject.getLong("timestamp"),
                jsonObject.getString("message")
        );
    }

    public String getBody() {
        return body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return "(" + id + ")De " + author + " à " + timestamp + " : " + body;
    }
}
