package com.swe.lms.admin.api.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ExceptionUtil {
	public static String getStackTrace(Throwable e)
	{
		String strResult;
		try {
			ByteArrayOutputStream byteArrOutStream = new ByteArrayOutputStream();
			PrintWriter writer = new PrintWriter(byteArrOutStream);
	
			e.printStackTrace(writer);
			writer.flush();
			
			strResult = byteArrOutStream.toString();
			
			byteArrOutStream.close();
			
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return strResult;
	}
	
	public static String getStackTrace(Throwable exception, Boolean isOnlyMessage) {
	    if (isOnlyMessage) {
	      return ExceptionUtils.getMessage(exception);
	    } else {
	      return ExceptionUtils.getStackTrace(exception);
	    }
	  }
}
