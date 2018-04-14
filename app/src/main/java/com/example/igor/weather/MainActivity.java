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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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

    private TextView textViewDay1TemtMax;
    private TextView textViewDay2TemtMax;
    private TextView textViewDay3TemtMax;
    private TextView textViewDay4TemtMax;

    private TextView textViewDay1TemtMin;
    private TextView textViewDay2TemtMin;
    private TextView textViewDay3TemtMin;
    private TextView textViewDay4TemtMin;

    private TextView textViewDay1OfWeek;
    private TextView textViewDay2OfWeek;
    private TextView textViewDay3OfWeek;
    private TextView textViewDay4OfWeek;

    private ImageView imageView1;

    Time time = new Time();
    ParserForString parserForString = new ParserForString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.cityView);
        tempView = findViewById(R.id.tempView);
        condIcon = findViewById(R.id.condIcon);
        descriptionView = findViewById(R.id.descriptionView);

        textViewDay1Descr = findViewById(R.id.textViewDay1_4);
        textViewDay2Descr = findViewById(R.id.textViewDay2_4);
        textViewDay3Descr = findViewById(R.id.textViewDay3_4);
        textViewDay4Descr = findViewById(R.id.textViewDay4_4);

        textViewDay1TemtMax = findViewById(R.id.textViewDay1_1);
        textViewDay2TemtMax = findViewById(R.id.textViewDay2_1);
        textViewDay3TemtMax = findViewById(R.id.textViewDay3_1);
        textViewDay4TemtMax = findViewById(R.id.textViewDay4_1);

        textViewDay1TemtMin = findViewById(R.id.textViewDay1_2);
        textViewDay2TemtMin = findViewById(R.id.textViewDay2_2);
        textViewDay3TemtMin = findViewById(R.id.textViewDay3_2);
        textViewDay4TemtMin = findViewById(R.id.textViewDay4_2);

        textViewDay1OfWeek = findViewById(R.id.textViewDay1_3);
        textViewDay2OfWeek = findViewById(R.id.textViewDay2_3);
        textViewDay3OfWeek = findViewById(R.id.textViewDay3_3);
        textViewDay4OfWeek = findViewById(R.id.textViewDay4_3);

        imageView1 = findViewById(R.id.imageViewDay1);

        JSONWeatherTask weatherTask = new JSONWeatherTask();
        weatherTask.execute();

        JSONForecastTask forecastTask = new JSONForecastTask();
        forecastTask.execute();

    }

    private class JSONForecastTask extends AsyncTask<String, Void, ForecastModel> {
        @Override
        protected ForecastModel doInBackground(String... params) {
            ForecastModel forecast = new ForecastModel();
            String dataForecast = ((new HttpRequest()).getForecastData());

            try {
                forecast = ParserForForecast.getForecast(dataForecast);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return forecast;
        }

        @Override
        protected void onPostExecute(ForecastModel forecast) {
            super.onPostExecute(forecast);

            int res[];
            int resTime[];

            ArrayList<String> listWithDescription = new ArrayList<>();
            ArrayList<String> listWithDay = new ArrayList<>();
            ArrayList<String> listWithIcon = new ArrayList<>();
            ArrayList<Double> listWithMaxTemp = new ArrayList<>();
            ArrayList<Double> listWithMinTemp = new ArrayList<>();
            ArrayList<String> listWithDayOfWeek = new ArrayList<>();

            for (int i = 0; i < forecast.list.getObjectModelFromList().size(); i++) {
                String fullString = forecast.list.getObjectModelFromList().get(i).dtTxt.getDt_txt(); //contains dtTxt of ObjectFromlist

                String[] partWithFullData = fullString.split(" "); // string with full data
                String[] partWithParseData = partWithFullData[0].split("(-)"); // array with parse date (year,month,day)

                String[] partWithFullTime = fullString.split("(?= )"); //get array with time and date
                String partWithTime = partWithFullTime[1]; //contains full time, [0] - it's full date
                String[] partWithFullTime1 = partWithTime.split(":");//contains parse time

                res = parserForString.getArrayWithDate(partWithParseData);
                resTime = parserForString.getArrayWithTime(partWithFullTime1);

                if (res[2] != time.getDay() && resTime[0] == 9 || resTime[0] == 18) {
                    listWithMaxTemp.add(forecast.list.getObjectModelFromList()
                            .get(i).main.getTemp_max());
                    listWithMinTemp.add(forecast.list.getObjectModelFromList()
                            .get(i).main.getTemp_min());
                }

                // get arraylist with description and icon
                if (res[2] != time.getDay() && resTime[0] == 12) {
                    listWithDescription.add(forecast.list.getObjectModelFromList().get(i).weather.getDescription());
                    listWithDay.add(forecast.list.getObjectModelFromList().get(i).dtTxt.getDt_txt());
                    listWithIcon.add(forecast.list.getObjectModelFromList().get(i).weather.getIcon());
                }

                try {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date dt1 = format1.parse(partWithFullData[0]);
                    DateFormat format2 = new SimpleDateFormat("EEEE");
                    String finalDay = format2.format(dt1);
                    listWithDayOfWeek.add(finalDay);
                } catch (ParseException e) {
                    System.out.println(" Error");
                }
            }

            LinkedHashSet<String> lhs = new LinkedHashSet<>();// HashSet for delete items that repeat from ArrayList
            lhs.addAll(listWithDayOfWeek);
            listWithDayOfWeek.clear();
            listWithDayOfWeek.addAll(lhs);

            textViewDay1OfWeek.setText(listWithDayOfWeek.get(1));
            textViewDay2OfWeek.setText(listWithDayOfWeek.get(2));
            textViewDay3OfWeek.setText(listWithDayOfWeek.get(3));
            textViewDay4OfWeek.setText(listWithDayOfWeek.get(4));


            textViewDay1TemtMax.setText(Math.round(listWithMaxTemp.get(0) - 273.15) + "°");
            textViewDay1TemtMin.setText(Math.round(listWithMinTemp.get(0) - 273.15) + "°");

            textViewDay2TemtMax.setText(Math.round(listWithMaxTemp.get(1) - 273.15) + "°");
            textViewDay2TemtMin.setText(Math.round(listWithMinTemp.get(1) - 273.15) + "°");

            textViewDay3TemtMax.setText(Math.round(listWithMaxTemp.get(2) - 273.15) + "°");
            textViewDay3TemtMin.setText(Math.round(listWithMinTemp.get(2) - 273.15) + "°");

            textViewDay4TemtMax.setText(Math.round(listWithMaxTemp.get(3) - 273.15) + "°");
            textViewDay4TemtMin.setText(Math.round(listWithMinTemp.get(3) - 273.15) + "°");

            textViewDay1Descr.setText(listWithDescription.get(0));
            textViewDay2Descr.setText(listWithDescription.get(1));
            textViewDay3Descr.setText(listWithDescription.get(2));
            textViewDay4Descr.setText(listWithDescription.get(3));
        }
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, WeatherModel> {

        @Override
        protected WeatherModel doInBackground(String... params) {
            WeatherModel weather = new WeatherModel();
            String dataWeather = ((new HttpRequest()).getWeatherData());

            try {
                weather = ParserForWeather.getWeather(dataWeather);
                weather.iconData = ((new HttpRequest()).getImage(weather.currentCondition.getIcon()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(WeatherModel weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                condIcon.setImageBitmap(img);
            }

            cityView.setText("City: " + weather.name.getName());
            tempView.setText(Math.round(weather.main.getTemp() - 273.15) + "°");
            descriptionView.setText("description: " + weather.currentCondition.getDescription());
        }
    }
}