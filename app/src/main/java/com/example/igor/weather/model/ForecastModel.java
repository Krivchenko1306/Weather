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

