package com.example.igor.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.igor.weather.model.ForecastModel;
import com.example.igor.weather.model.WeatherModel;
import com.example.igor.weather.parser.ParserForForecast;
import com.example.igor.weather.parser.ParserForWeather;
import com.example.igor.weather.time.Time;

import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView cityView;
    private TextView tempView;
    private ImageView condIcon;
    private TextView mainView;
    private TextView descriptionView;

    private TextView textMessage;
    private TextView forecastTempView;
    private TextView forecastMainView;

    private ListView listWithForecast;

    private TextView textDate;

    @Override
     protected void onCreate(Bundle savedInstanceState) {

        Time time = new Time();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.cityView);
        tempView = findViewById(R.id.tempView);
        condIcon = findViewById(R.id.condIcon);
        mainView = findViewById(R.id.mainView);
        descriptionView = findViewById(R.id.descriptionView);

        textMessage = findViewById(R.id.textViewMessage);
        forecastTempView = findViewById(R.id.forecastTempView);
        forecastMainView = findViewById(R.id.forecastMainView);

        listWithForecast = findViewById(R.id.listWithForecast);

        textDate = findViewById(R.id.textDate);

        JSONWeatherTask weatherTask  = new JSONWeatherTask();
        weatherTask.execute();

        JSONForecastTask forecastTask = new JSONForecastTask();
        forecastTask.execute();


        textDate.setText(time.getDateAndTime());


    }
    private class JSONForecastTask extends AsyncTask<String,Void,ForecastModel>{
        @Override
        protected ForecastModel doInBackground(String...params){
            ForecastModel forecast = new ForecastModel();
            String dataForecast = ((new HttpRequest()).getForecastData());

            try{
                forecast = ParserForForecast.getForecast(dataForecast);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return forecast;
        }

        @Override
        protected  void onPostExecute(ForecastModel forecast){
            super.onPostExecute(forecast);

            String s = forecast.list.getObjectModelFromList().get(8).dtTxt.getDt_txt();
            textMessage.setText(s);

            for(int i =0; i < forecast.list.getObjectModelFromList().size();i++){
                System.out.println(forecast.list.getObjectModelFromList().get(i).main.getTemp() + '\n');
            }
        }
    }

        private class JSONWeatherTask extends AsyncTask<String,Void,WeatherModel>{

        @Override
        protected WeatherModel doInBackground(String...params){
            WeatherModel weather = new WeatherModel();
            String dataWeather = ((new HttpRequest()).getWeatherData());

            try{
                weather = ParserForWeather.getWeather(dataWeather);
                weather.iconData = ((new HttpRequest()).getImage(weather.currentCondition.getIcon()));
            }catch (JSONException e){
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(WeatherModel weather){
            super.onPostExecute(weather);

            if(weather.iconData != null && weather.iconData.length > 0){
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData,0,weather.iconData.length);
                condIcon.setImageBitmap(img);
            }

            cityView.setText("City: " + weather.name.getName());
            tempView.setText(Math.round(weather.main.getTemp() - 273.15)+ "°");
            mainView.setText("main: " + weather.currentCondition.getMain());
            descriptionView.setText("description: " + weather.currentCondition.getDescription());
        }
    }
}