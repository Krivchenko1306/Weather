package com.example.igor.weather.model;

import java.util.ArrayList;

/**
 * Created by Igor on 3/1/2018.
 */

public class ForecastModel {

    public Cod cod = new Cod();
    public Message message = new Message();
    public Cnt cnt = new Cnt();
    public List list = new List();
    public ObjectModelFromList objectModelFromList = new ObjectModelFromList();
    public City city = new City();


    public class Cod{
        private String codeValue ;

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }
    }

    public class Message{
        private double message;

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }
    }

    public class Cnt{
        private int cnt;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }

     public class List{

       private ArrayList<ObjectModelFromList> MFarray = new ArrayList<>();

       public ArrayList<ObjectModelFromList> getObjectModelFromList(){
           return MFarray;
         }

         public void setObjectModelFromList(ObjectModelFromList obj){
           MFarray.add(obj);
         }
     }
     public class ObjectModelFromList {

        public Dt dt = new Dt();
        public Main main = new Main();
        public Weather weather = new Weather();
        public Clouds clouds = new Clouds();
        public Wind wind = new Wind();
        public Sys sys = new Sys();
        public Dt_txt dtTxt = new Dt_txt();

        public class Dt {
            private int dt;

            public int getDt() {
                return dt;
            }

            public void setDt(int dt) {
                this.dt = dt;
            }
        }

       public class Main{
            private double temp;
            private double temp_min;
            private double temp_max;
            private double pressure;
            private double sea_level;
            private double grnd_level;
            private double humidity;
            private double temp_kf;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }

            public double getPressure() {
                return pressure;
            }

            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public double getSea_level() {
                return sea_level;
            }

            public void setSea_level(double sea_level) {
                this.sea_level = sea_level;
            }

            public double getGrnd_level() {
                return grnd_level;
            }

            public void setGrnd_level(double grnd_level) {
                this.grnd_level = grnd_level;
            }

            public double getHumidity() {
                return humidity;
            }

            public void setHumidity(double humidity) {
                this.humidity = humidity;
            }

            public double getTemp_kf() {
                return temp_kf;
            }

            public void setTemp_kf(double temp_kf) {
                this.temp_kf = temp_kf;
            }
        }

       public class Weather{
            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

       public class Clouds{
            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

       public class Wind{
            private double speed;
            private double deg;

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public double getDeg() {
                return deg;
            }

            public void setDeg(double deg) {
                this.deg = deg;
            }
        }

       public class Sys{
            private String pod;

            public String getPod() {
                return pod;
            }

            public void setPod(String pod) {
                this.pod = pod;
            }
        }

       public class Dt_txt{
            private String dt_txt;

            public String getDt_txt() {
                return dt_txt;
            }

            public void setDt_txt(String dt_txt) {
                this.dt_txt = dt_txt;
            }
        }
    }

    public class City{

        public Coord coord = new Coord();

        private int id;
        private String name;

       public class Coord{
            private double lat;
            private double lon;

           public double getLat() {
               return lat;
           }

           public void setLat(double lat) {
               this.lat = lat;
           }

           public double getLon() {
               return lon;
           }

           public void setLon(double lon) {
               this.lon = lon;
           }
       }

        private String country;
        private long population;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getPopulation() {
            return population;
        }

        public void setPopulation(long population) {
            this.population = population;
        }
    }
}

