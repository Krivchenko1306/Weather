package com.example.igor.weather.parser;

/**
 * Created by Igor on 3/24/2018.
 */

public class ParserForString {

    public int[] getArrayWithDate(String[] partWithParseData) {
        String part1 = partWithParseData[0];
        String part2 = partWithParseData[1];
        String part3 = partWithParseData[2];

        int part1_1 = Integer.parseInt(part1);
        int part2_1 = Integer.parseInt(part2);
        int part3_1 = Integer.parseInt(part3);

        int result[] = {part1_1, part2_1, part3_1};
        return result;
    }

    public int[] getArrayWithTime(String[] partWithFullTime1) {
        String part1 = partWithFullTime1[0];
        String part2 = partWithFullTime1[1];
        // String part3 = partWithFullTime1[2];

        int part1_1 = Integer.parseInt(part1.trim());
        int part2_1 = Integer.parseInt(part2);
        //int part3_1 = Integer.parseInt(part3);

        int result[] = {part1_1, part2_1};
        return result;
    }

}
