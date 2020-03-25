package com.kkgmdevelopments.traveljournalapp;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * Date Converter
 *  This is the recommended class to create Custom TypeConverters
 *  to interact between Room/SQLite Data and The Application
 */
public class DateConverter {
    /**
     * TimeStamp to Date Converter
     *  This is the way for the Application to interpret a Date format
     *
     * @param value Long DateTimestamp
     * @return Date Instance
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Date to Timestmap
     * @param date Date Object
     * @return Long Timestamp data
     */
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
