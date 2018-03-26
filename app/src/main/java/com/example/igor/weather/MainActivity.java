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
import com.example.igor.weather.model.ObjectModelFromList;
import com.example.igor.weather.model.WeatherModel;
import com.example.igor.weather.parser.ParserForForecast;
import com.example.igor.weather.parser.ParserForString;
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

    private TextView textViewDay1Descr;
    private TextView textViewDay2Descr;
    private TextView textViewDay3Descr;
    private TextView textViewDay4Descr;
    private TextView textViewDay5Descr;


    Time time = new Time();
    ParserForString parserForString = new ParserForString();
    @Override
     protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.cityView);
        tempView = findViewById(R.id.tempView);
        condIcon = findViewById(R.id.condIcon);
        mainView = findViewById(R.id.mainView);
        descriptionView = findViewById(R.id.descriptionView);

        textViewDay1Descr = findViewById(R.id.textViewDay1_4);
        textViewDay2Descr = findViewById(R.id.textViewDay2_4);
        textViewDay3Descr = findViewById(R.id.textViewDay3_4);
        textViewDay4Descr = findViewById(R.id.textViewDay4_4);
        textViewDay5Descr = findViewById(R.id.textViewDay5_4);

        JSONWeatherTask weatherTask  = new JSONWeatherTask();
        weatherTask.execute();

        JSONForecastTask forecastTask = new JSONForecastTask();
        forecastTask.execute();


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

            int res[];
            int resTime[];

            ArrayList<String> listWithDescription = new ArrayList<>();

            for(int i = 0; i < forecast.list.getObjectModelFromList().size(); i++){
                String fullString = forecast.list.getObjectModelFromList().get(i).dtTxt.getDt_txt(); //contains dtTxt of ObjectFromlist

                String[] partWithFullData = fullString.split(" "); // string with full data
                String[] partWithParseData = partWithFullData[0].split("(-)"); // array with parse date (year,month,day)

                String[] partWithFullTime = fullString.split("(?= )"); //get array with time and date
                String partWithTime = partWithFullTime[1]; //contains full time, [0] - it's full date
                String[] partWithFullTime1 = partWithTime.split(":");//contains parse time

               res = parserForString.getArrayWithDate(partWithParseData);
               resTime = parserForString.getArrayWithTime(partWithFullTime1);

                if(res[2] != time.getDay() && resTime[0] == 12){
                    forecast.list.getObjectModelFromList().get(i).weather.getDescription();
                    listWithDescription.add(forecast.list.getObjectModelFromList()
                            .get(i).weather.getDescription());
                }

               /* if(res[2] != time.getDay() && (resTime[0] == 6
                       || resTime[0] == 9 || resTime[0] == 12
                       || resTime[0] == 15 || resTime[0] == 18)) {
                   listWithSortObject.add(forecast.list.getObjectModelFromList().get(i));
               }*/
            }

            textViewDay1Descr.setText(listWithDescription.get(0));
            textViewDay2Descr.setText(listWithDescription.get(1));
            textViewDay3Descr.setText(listWithDescription.get(2));
            textViewDay4Descr.setText(listWithDescription.get(3));
            textViewDay5Descr.setText(listWithDescription.get(4));
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