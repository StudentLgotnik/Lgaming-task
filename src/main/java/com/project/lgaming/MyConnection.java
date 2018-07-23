package com.project.lgaming;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.security.SignatureException;

public class MyConnection {

    private static final String LGAMING_URL = "https://test.lgaming.net/external/extended";
    private static final String CHARSET = "UTF-8";

    public static void connectLGaming(String xmlPath) throws IOException {

        URL url = new URL(LGAMING_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept-charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/xml");

        String xml = Convertors.XMLToString(xmlPath);
        System.out.println("Request: \n" + xml);
        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), CHARSET)){
            writer.write(new Encrypting().sign(xml)); // Write POST query string (if any needed).
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        InputStream result = connection.getInputStream();
        System.out.println("Response: \n" + Convertors.IStreamToString(result));
    }
}
