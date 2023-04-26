package akteure;

import java.util.HashMap;

import util.ContentMap;

public class Produzent implements Runnable {
	
	private static HashMap<String, Integer> recievedResources = new HashMap<>();
	
	private static HashMap<String, Integer> inventory = new HashMap<>();
	
	@Override
	public void run() {
		inventory = ContentMap.getContentMap();
	}
	
	private void tryToGetResources(HashMap<String, Integer> desiredResources) {
		Marketplace marketplace = Marketplace.getMarketplace();
		recievedResources = marketplace.get(desiredResources);
		for(String s : recievedResources.keySet()) {
			inventory.put(s, inventory.get(s) + recievedResources.get(s));
		}
	}
	
	private void checkPrice(HashMap<String, Integer> desiredResources) {
		
	}
}
