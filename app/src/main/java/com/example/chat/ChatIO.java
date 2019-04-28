package com.example.chat;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class ChatIO {

    // Récupère le message d'identifiant id sur le salon queue
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Message fetchMessage(String server, String queue, int id) throws JSONException {
        String data = "";
        try {
            String connexionString = "http://" + server + ":2019/" + queue + "/" + id;
            URL url = new URL(connexionString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setAllowUserInteraction(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                data = readStream(connection.getInputStream());

        } catch (Exception e) { }
        return Message.messageFromJSON(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readStream(InputStream in) throws IOException {
        if (in != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            catch (Exception e) { }
            finally {
                in.close();
                reader.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void postData(HttpURLConnection conn, String message) throws IOException
    {
        byte[] data = JSONObject.quote(message).getBytes(StandardCharsets.UTF_8); // encodes the string in JSON and converts to byte using UTF-8
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.connect();
        try (OutputStream out = conn.getOutputStream()) {
            out.write(data);
        }
        conn.disconnect();
    }
}
