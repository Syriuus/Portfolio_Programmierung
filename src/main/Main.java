package main;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import akteure.Marketplace;

public class Main {
	
	private static Logger logger;
	
	public static void main(String[] args) {
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		
		
		Marketplace m = Marketplace.getMarketplace();
		HashMap<String, Integer> test = new HashMap<>();
		test.put("Holz", 5);
		m.print();
		m.put(test);
		m.print();
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
