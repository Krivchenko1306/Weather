package com.example.igor.weather.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Igor on 3/8/2018.
 */

public class Time {

  public Calendar c = Calendar.getInstance();

    public String getDateAndTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String result = df.format(this.c.getTime());
        return result;
    }

    public String getDate(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String result = date.format(c.getTime());
        return result;
    }

    public String getTime(){
        SimpleDateFormat time = new SimpleDateFormat("HH:ss");
        String result = time.format(c.getTime());
        return result;
    }

    public int getSec(){
        int second = c.get(Calendar.SECOND);
        return second;
    }

    public int getMinutes(){
        int minutes = c.get(Calendar.MINUTE);
        return minutes;
    }

    public int getDay(){
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public int getMonth(){
        int month = c.get(Calendar.MONTH);
        return month;
    }

    public int getYear(){
        int year = c.get(Calendar.YEAR);
        return year;
    }

}
