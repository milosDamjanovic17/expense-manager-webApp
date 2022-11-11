package com.moose.expensemanager.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

// lets just format the date to be shown as dd/MM/yyyy
public class DateTimeUtil {
	
	public static String convertDateToString(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		return sdf.format(date);
	}

}
