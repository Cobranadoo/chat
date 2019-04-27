package com.example.chat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private int id;
    // Salon de discussion utilisé
    private String queue;
    // Auteur du message
    private String author;
    // Instant de rédaction du message sous la forme d'un timestamp Unix
    private Timestamp timestamp;
    // Contient le corps du message
    private String body;

    public Message(String body) {
        this.timestamp = new Timestamp(new Date().getTime());
        this.body = body;
        this.author = "Florian";
    }

    public String getBody() {
        return body;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "De " + getAuthor() + " à " + getTimestamp() + " : " + getBody();
    }
}
