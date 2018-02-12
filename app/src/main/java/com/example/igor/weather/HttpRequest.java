package com.example.igor.weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Igor on 1/20/2018.
 */

public class HttpRequest {

    private static String WEATHER_DATE_URL = "http://api.openweathermap.org/data/2.5/weather?q=Kharkiv,UA&appid=a0613a0a4fc49bd71446e8de5039cd56";
    private static String IMAGE_URL = "http://openweathermap.org/img/w/";

    public static String getWeatherData(String param) {
        String url = WEATHER_DATE_URL;
        String inputLine;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            System.out.println("\nSending 'GET' request to URL : " + url);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        }catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getImage(String code) {
        String url = IMAGE_URL;
        InputStream is = null;
        try {
            URL obj = new URL(url + code);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Image url: " + url + code);

            //Read the response
            is = connection.getInputStream();
            byte[] buffer = new byte[1024];

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try { is.close(); } catch (Throwable t) {}
        }
        return null;
    }
}
