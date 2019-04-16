package com.example.chat;

public class Message {
    private int id;
    // Salon de discussion utilisé
    private String queue;
    // Auteur du message
    private String author;
    // Instant de rédaction du message sous la forme d'un timestamp Unix
    private long timestamp;
    // Contient le corps du message
    private String body;

    public Message(String body) {
        this.body = body;
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
}
