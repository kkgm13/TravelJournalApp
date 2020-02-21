package com.kkgmdevelopments.traveljournalapp;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

//    @TypeConverter
//    public static Calendar toCalendar(Long l) {
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(l);
//        return c;
//    }
//
//    @TypeConverter
//    public static Long fromCalendar(Calendar c){
//        return c == null ? null : c.getTime().getTime();
//    }
}
