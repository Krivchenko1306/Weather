package com.example.igor.weather.parser;

import com.example.igor.weather.model.ForecastModel;
import com.example.igor.weather.model.ObjectModelFromList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Igor on 3/6/2018.
 */

public class ParserForForecast {

    public static ForecastModel getForecast(String data) throws JSONException {

        ForecastModel forecastModel = new ForecastModel();

        JSONObject jObj = new JSONObject(data);

        forecastModel.cod.setCodeValue(getString("cod", jObj));
        forecastModel.message.setMessage(getDouble("message", jObj));
        forecastModel.cnt.setCnt(getInt("cnt", jObj));

        JSONArray jArr = jObj.getJSONArray("list");

        for (int i = 0;i < jArr.length(); i++) {

            ObjectModelFromList objectModelFromList = new ObjectModelFromList();

            JSONObject currentModel = jArr.getJSONObject(i);

            forecastModel.objectModelFromList.dt.setDt(getInt("dt", currentModel));

            JSONObject JSONMain = getObject("main", currentModel);
            objectModelFromList.main.setTemp(getDouble("temp", JSONMain));
            objectModelFromList.main.setTemp_min(getDouble("temp_min", JSONMain));
            objectModelFromList.main.setTemp_max(getDouble("temp_max", JSONMain));
            objectModelFromList.main.setPressure(getDouble("pressure", JSONMain));
            objectModelFromList.main.setSea_level(getDouble("sea_level", JSONMain));
            objectModelFromList.main.setGrnd_level(getDouble("grnd_level", JSONMain));
            objectModelFromList.main.setHumidity(getInt("humidity", JSONMain));
            objectModelFromList.main.setTemp_kf(getDouble("temp_kf", JSONMain));

           JSONArray jsonWeatherArr = currentModel.getJSONArray("weather");

            JSONObject JSONWeather = jsonWeatherArr.getJSONObject(0);
            objectModelFromList.weather.setId(getInt("id", JSONWeather));
            objectModelFromList.weather.setMain(getString("main", JSONWeather));
            objectModelFromList.weather.setDescription(getString("description", JSONWeather));
            objectModelFromList.weather.setIcon(getString("icon", JSONWeather));

            JSONObject JSONClouds = currentModel.getJSONObject("clouds");
            objectModelFromList.clouds.setAll(getInt("all", JSONClouds));

            JSONObject JSONWind = currentModel.getJSONObject("wind");
            objectModelFromList.wind.setSpeed(getDouble("speed", JSONWind));
            objectModelFromList.wind.setDeg(getDouble("deg", JSONWind));

            JSONObject JSONSys = currentModel.getJSONObject("sys");
            objectModelFromList.sys.setPod(getString("pod", JSONSys));

            objectModelFromList.dtTxt.setDt_txt(getString("dt_txt", currentModel));

            forecastModel.list.getObjectModelFromList().add(objectModelFromList);

            }

            JSONObject JSONCity = jObj.getJSONObject("city");
            forecastModel.city.setId(getInt("id",JSONCity));
            forecastModel.city.setName(getString("name",JSONCity));

            JSONObject JSONCoor = JSONCity.getJSONObject("coord");
            forecastModel.city.coord.setLat(getDouble("lat",JSONCoor));
            forecastModel.city.coord.setLon(getDouble("lon",JSONCoor));

            forecastModel.city.setCountry(getString("country",JSONCity));
            forecastModel.city.setPopulation(getInt("population",JSONCity));

        return forecastModel;

    }


    private static JSONObject getObject(String tagName,JSONObject jObj) throws JSONException{
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException{
        return jObj.getString(tagName);
    }

    private static int getInt(String tagName,JSONObject jObj) throws JSONException{
        return jObj.getInt(tagName);
    }

    private static double getDouble(String tagName,JSONObject jObj) throws JSONException{
        return jObj.getDouble(tagName);
    }


}
