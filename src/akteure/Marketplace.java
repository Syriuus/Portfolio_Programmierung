package akteure;

import java.util.HashMap;

import util.ContentMap;
import util.SimulatedResource;

public class Marketplace {
	
	private static Marketplace INSTANCE;
	private static boolean empty;
	private static HashMap<String, SimulatedResource> contents;
	
	
	private Marketplace() {
		empty = true;
		contents = ContentMap.getContentMap();
	}
	
	public static Marketplace getMarketplace() {
		if(INSTANCE == null) {
			INSTANCE = new Marketplace();
		}
		return INSTANCE;
	}
	
	public synchronized void put(HashMap<String, Integer> putMap) {
		for(String key : putMap.keySet()) {
			SimulatedResource resource = contents.get(key);
			resource.addCount(putMap.get(key));
		}
	}
	
	public synchronized HashMap<String, Integer> get(HashMap<String, Integer> desiredMap) {
		return desiredMap;
	}
	public void print() {
		System.out.println(contents);
	}
}
