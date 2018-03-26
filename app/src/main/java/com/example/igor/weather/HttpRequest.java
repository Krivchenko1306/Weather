package com.example.igor.weather;


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
    private static String WEATHER_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=Kharkiv,Ukr&Appid=a0613a0a4fc49bd71446e8de5039cd56";
    private static String PNG = ".png";

    public static String getWeatherData() {
        String url = WEATHER_DATE_URL;
        String inputLine;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
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
        String png = PNG;
        InputStream is = null;
        try {
            URL obj = new URL(url + code + png);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");

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

    public static String getForecastData(){
        String url = WEATHER_FORECAST_URL;
        String inputLine;

        try{
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer responce = new StringBuffer();
            while((inputLine = in.readLine()) != null){
                responce.append(inputLine);
            }
            in.close();
            return responce.toString();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (ProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
