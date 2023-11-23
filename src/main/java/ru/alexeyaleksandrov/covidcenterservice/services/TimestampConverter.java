package ru.alexeyaleksandrov.covidcenterservice.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class TimestampConverter
{
    public static long convertMMddyyyyToTimestamp(String date_str)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        long timestamp = -1;
        try
        {
            timestamp = dateFormat.parse(date_str).getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static long getLocalTimestamp()
    {
        return Instant.now().toEpochMilli();
    }
}
