package com.ID0420FF19OWidya.util;

import java.util.ArrayList;

public class listYear
{
    public static ArrayList<Integer> produceYear(final int min, final int max) {
        final ArrayList<Integer> year = new ArrayList<Integer>();
        for (int i = min; i <= max; ++i) {
            year.add(i);
        }
        return year;
    }
}