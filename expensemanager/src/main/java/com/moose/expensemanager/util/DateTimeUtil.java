package com.moose.expensemanager.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// let's just format the date to be shown as dd/MM/yyyy
public class DateTimeUtil {
	
	public static String convertDateToString(Date date) {
		// define the format of a date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(date);
	}

	public static Date convertStringToDate(String dateStringFormat) throws ParseException {

		// define the format of a date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// parse string date format to Sql type of date
		java.util.Date utilDate = sdf.parse(dateStringFormat);

		return new Date(utilDate.getTime());
	}

}
