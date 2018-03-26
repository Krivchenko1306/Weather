package com.example.igor.weather.parser;

/**
 * Created by Igor on 3/24/2018.
 */

public class ParserForString {

   /* private String fullString; //contains dtTxt of ObjectFromlist

    private String[] partWithFullData = fullString.split(" "); // string with full data
    private String[] partWithParseData = partWithFullData[0].split("(-)"); // array with parse date (year,month,day)

    private String[] partWithFullTime = fullString.split("(?= )"); //get array with time and date
    private String partWithTime = partWithFullTime[1]; //contains full time, [0] - it's full date
    private String[] partWithFullTime1 = partWithTime.split(":");//contains parse time*/

    // String fullString = forecast.list.getObjectModelFromList().get(2).dtTxt.getDt_txt();

    public int[] getArrayWithDate(String[] partWithParseData) {
        String part1 = partWithParseData[0];
        String part2 = partWithParseData[1];
        String part3 = partWithParseData[2];

        int part1_1 = Integer.parseInt(part1);
        int part2_1 = Integer.parseInt(part2);
        int part3_1 = Integer.parseInt(part3);

        int result[] = {part1_1,part2_1,part3_1};
        return result;
    }

    public int[] getArrayWithTime(String[] partWithFullTime1) {
        String part1 = partWithFullTime1[0];
        String part2 = partWithFullTime1[1];
       // String part3 = partWithFullTime1[2];

        int part1_1 = Integer.parseInt(part1.trim());
        int part2_1 = Integer.parseInt(part2);
        //int part3_1 = Integer.parseInt(part3);

        int result[] = {part1_1,part2_1};
        return result;
    }

}
