package com.seproject.healthqa.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class CalculateUtility {

	private static Logger log = Logger.getLogger("InfoLogging");
    
	
	public int calculateAge(String birthDate) {            
	    // validate inputs ...    
		Date currentDate = new Date();
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(birthDate));                            
	    int d2 = Integer.parseInt(formatter.format(currentDate));                          
	    int age = (d2 - d1) / 10000;                                                       
	    return age;                                                                        
	}
}