package com.cloudify.beans;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

@ApplicationScoped
public class NotificationBean {
    private static final String API_KEY = "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";
    private static final String FROM_EMAIL = "cloudify@noreply.com";

    public void sendEmail(String toEmail, String subject, String content) {
        try {
            URL url = new URL("https://api.sendinblue.com/v3/smtp/email");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("api-key", API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonBody = "{"
                    + "\"sender\": {\"email\": \"" + FROM_EMAIL + "\"},"
                    + "\"to\": [{\"email\": \"" + toEmail + "\"}],"
                    + "\"subject\": \"" + subject + "\","
                    + "\"htmlContent\": \"" + content + "\""
                    + "}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                StringBuilder response = new StringBuilder();
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
