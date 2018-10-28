package com.seproject.healthqa.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
	public boolean Test(String x) {
		log.info("connected service");
		return true;
	}
	
	public boolean getListTopics() {
		return true;
	}
}