package com.swe.lsm.auth.api.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil 
{
	public static String getCurrentDateTime(String pattern) 
	{
        SimpleDateFormat objDateFormat = new SimpleDateFormat(pattern);

        return objDateFormat.format(Calendar.getInstance().getTime());
    }
	
	public static String formatDateTime(String pattern, long timeMilisec) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);    
		Date resultdate = new Date(timeMilisec);
		return sdf.format(resultdate);
	}
	
	public static String getFirstDayOfThisMonth() 
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		Date objDate = new Date();
		return df.format(objDate);
	}
}
