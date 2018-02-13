package com.example.igor.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.weather.model.WeatherModel;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private TextView cityView;
    private TextView tempView;
    private ImageView condIcon;
    private Button button;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityView = findViewById(R.id.cityView);
        tempView = findViewById(R.id.tempView);
        condIcon = findViewById(R.id.condIcon);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONWeatherTask task  = new JSONWeatherTask();
                task.execute();
            }
        });

    }
    private class JSONWeatherTask extends AsyncTask<String,Void,WeatherModel>{

        @Override
        protected WeatherModel doInBackground(String...params){
            WeatherModel weather = new WeatherModel();
            String data = ((new HttpRequest()).getWeatherData());

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
            tempView.setText("temp: " + Math.round(weather.main.getTemp() - 273.15));
        }
    }
}
