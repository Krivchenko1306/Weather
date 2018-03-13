package com.example.igor.weather.parser;

import com.example.igor.weather.model.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Igor on 1/23/2018.
 */

public class ParserForWeather {


    public static WeatherModel getWeather(String data) throws JSONException {

        WeatherModel model = new WeatherModel();

        JSONObject jObj = new JSONObject(data);

        JSONObject JSONCoord = getObject("coord",jObj);
        model.coord.setLongitude(getFloat("lon", JSONCoord));
        model.coord.setLatitude(getFloat("lat",JSONCoord));

        JSONArray jArr = jObj.getJSONArray("weather");

        JSONObject JSONWeather = jArr.getJSONObject(0);
        model.currentCondition.setIcon(getString("icon", JSONWeather));
        model.currentCondition.setDescription(getString("description", JSONWeather));
        model.currentCondition.setMain(getString("main", JSONWeather));
        model.currentCondition.setId(getInt("id",JSONWeather));

        //JSONObject JSONBase = getObject("base", jObj);
        model.base.setStations(getString("base", jObj));


        JSONObject JSONMain = getObject("main", jObj);
        model.main.setTemp(getFloat("temp", JSONMain));
        model.main.setTempMin(getFloat("temp_min", JSONMain));
        model.main.setHumidity(getFloat("humidity", JSONMain));
        model.main.setPressure(getFloat("pressure", JSONMain));
        model.main.setTempMax(getFloat("temp_max", JSONMain));

        //JSONObject JSONVisibility = getObject("visibility",jObj);
        model.visibility.setVisibilityValue(getInt("visibility", jObj));

        JSONObject JSONWind = getObject("wind", jObj);
        model.wind.setDegrees(getInt("deg",JSONWind));
        model.wind.setSpeed(getInt("speed",JSONWind));

        JSONObject JSONClouds = getObject("clouds", jObj);
        model.clouds.setPerc(getInt("all",JSONClouds));

        model.dt.setDtValue(getInt("dt", jObj));

        JSONObject JSONSys = getObject("sys", jObj);
        model.sys.setCounrty(getString("country",JSONSys));
        model.sys.setSunrise(getInt("sunrise",JSONSys));
        model.sys.setSunset(getInt("sunset",JSONSys));
        model.sys.setId(getInt("id",JSONSys));
        model.sys.setType(getInt("type",JSONSys));
        model.sys.setMessage(getFloat("message",JSONSys));

        model.id.setId(getInt("id", jObj));

        model.name.setName(getString("name", jObj));

        model.cod.setCodValue(getInt("cod",jObj));

        return model;
    }

    private static JSONObject getObject(String tagName,JSONObject jObj) throws JSONException{
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException{
        return (float) jObj.getDouble(tagName);
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException{
        return jObj.getString(tagName);
    }

    private static int getInt(String tagName,JSONObject jObj) throws JSONException{
        return jObj.getInt(tagName);
    }
}
