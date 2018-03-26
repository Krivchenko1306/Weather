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
import com.example.igor.weather.parser.ParserForString;
import com.example.igor.weather.parser.ParserForWeather;
import com.example.igor.weather.time.Time;

import org.json.JSONException;




public class MainActivity extends AppCompatActivity {

    private TextView cityView;
    private TextView tempView;
    private ImageView condIcon;
    private TextView mainView;
    private TextView descriptionView;

    private TextView textMessage;

    private ListView listWithForecast;

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

        textMessage = findViewById(R.id.textViewMessage);

        listWithForecast = findViewById(R.id.listWithForecast);

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

            for(int i = 0; i < forecast.list.getObjectModelFromList().size(); i++){
                String fullString = forecast.list.getObjectModelFromList().get(i).dtTxt.getDt_txt(); //contains dtTxt of ObjectFromlist

                String[] partWithFullData = fullString.split(" "); // string with full data
                String[] partWithParseData = partWithFullData[0].split("(-)"); // array with parse date (year,month,day)

                String[] partWithFullTime = fullString.split("(?= )"); //get array with time and date
                String partWithTime = partWithFullTime[1]; //contains full time, [0] - it's full date
                String[] partWithFullTime1 = partWithTime.split(":");//contains parse time
              // String[] test = partWithFullTime1[0].split(" ");

               res = parserForString.getArrayWithDate(partWithParseData);
               resTime = parserForString.getArrayWithTime(partWithFullTime1);

               if(res[2] != time.getDay() && resTime[0] == 9) {
                   System.out.println("Date: " + res[2]);
                   System.out.println("hours: " + resTime[0]);
               }



            }



            // parserForString.setFullString(forecast.list.getObjectModelFromList().get(2).dtTxt.getDt_txt());
           // System.out.println(parserForString.getArrayWithDate());

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