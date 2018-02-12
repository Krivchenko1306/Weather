package com.example.igor.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.weather.model.WeatherModel;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private TextView cityView;
    private TextView tempView;
    private ImageView condIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = (TextView) findViewById(R.id.cityView);
        tempView = (TextView) findViewById(R.id.tempView);
        condIcon = (ImageView) findViewById(R.id.condIcon);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{});

    }
    private class JSONWeatherTask extends AsyncTask<String,Void,WeatherModel>{

        @Override
        protected WeatherModel doInBackground(String...params){
            WeatherModel weather = new WeatherModel();
            String data = ((new HttpRequest()).getWeatherData(params[0]));

            try{
                weather = Parser.getWeather(data);

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
            cityView.setText("Kharkov");
            tempView.setText("temp: " + weather.main.getTemp());
        }
    }
}
