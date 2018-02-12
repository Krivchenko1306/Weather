package com.example.igor.weather.model;

/**
 * Created by Igor on 1/23/2018.
 */

public class WeatherModel {
    public Coord coord = new Coord();
    public CurrentCondition currentCondition = new CurrentCondition();
    public Base base = new Base();
    public Main main = new Main();
    public Visibility visibility = new Visibility();
    public Wind wind = new Wind();
    public Clouds clouds = new Clouds();
    public Dt dt = new Dt();
    public Sys sys = new Sys();
    public Id id = new Id();
    public Name name = new Name();
    public Cod cod = new Cod();

    public byte[] iconData;

    public class Coord{
        private Float longitude;
        private Float latitude;

        public Float getLongitude() {
            return longitude;
        }

        public void setLongitude(Float longitude) {
            this.longitude = longitude;
        }

        public Float getLatitude() {
            return latitude;
        }

        public void setLatitude(Float latitude) {
            this.latitude = latitude;
        }
    }

    public class CurrentCondition {
        private String icon;
        private String description;
        private String main;
        private int id;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class Base{
        private String stations;

        public String getStations() {
            return stations;
        }

        public void setStations(String stations) {
            this.stations = stations;
        }
    }

    public class Main{
        private float temp;
        private float tempMin;
        private float humidity;
        private float pressure;
        private float tempMax;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getTempMin() {
            return tempMin;
        }

        public void setTempMin(float tempMin) {
            this.tempMin = tempMin;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getTempMax() {
            return tempMax;
        }

        public void setTempMax(float tempMax) {
            this.tempMax = tempMax;
        }
    }

    public class Visibility{
        private float visibilityValue;

        public float getVisibilityValue() {
            return visibilityValue;
        }

        public void setVisibilityValue(float visibilityValue) {
            this.visibilityValue = visibilityValue;
        }
    }

    public class Wind{
        private float degrees;
        private float speed;

        public float getDegrees() {
            return degrees;
        }

        public void setDegrees(float degrees) {
            this.degrees = degrees;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }
    }

    public class Clouds{
        private int perc;

        public int getPerc() {
            return perc;
        }

        public void setPerc(int perc) {
            this.perc = perc;
        }
    }

    public class Dt{
        private float dtValue;

        public float getDtValue() {
            return dtValue;
        }

        public void setDtValue(float dtValue) {
            this.dtValue = dtValue;
        }
    }

    public class Sys{
        private String counrty;
        private int sunrise;
        private int sunset;
        private int id;
        private int type;
        private float message;

        public String getCounrty() {
            return counrty;
        }

        public void setCounrty(String counrty) {
            this.counrty = counrty;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public float getMessage() {
            return message;
        }

        public void setMessage(float message) {
            this.message = message;
        }
    }

    public class Id{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class Name{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Cod{
        private int codValue;

        public int getCodValue() {
            return codValue;
        }

        public void setCodValue(int codValue) {
            this.codValue = codValue;
        }
    }
}
