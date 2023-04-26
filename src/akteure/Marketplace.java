package akteure;

import java.util.HashMap;

import util.ContentMap;

public class Marketplace {
	
	private static Marketplace INSTANCE;
	private static HashMap<String, Integer> contents;
	
	
	private Marketplace() {
		contents = ContentMap.getContentMap();
	}
	
	public static Marketplace getMarketplace() { // Singletoninstanz erstellung
		if(INSTANCE == null) {
			INSTANCE = new Marketplace();
		}
		return INSTANCE;
	}
	
	public synchronized void put(HashMap<String, Integer> putMap) {  //Bearbeitet das Objekt mit dem Eintrag key und addet den Count
		for(String key : putMap.keySet()) {
			contents.put(key, contents.get(key) + putMap.get(key));
		}
	}
	
	public synchronized HashMap<String, Integer> get(HashMap<String, Integer> desiredResources) {
		HashMap<String, Integer> desiredMap = new HashMap<>();
		for(String s : desiredResources.keySet()) { 
			Integer resourceCount = contents.get(s);
			Integer requestedResourceCount = desiredResources.get(s); //holt sich für jeden Eintrag in desiredResources den Requested Count
			if(resourceCount - requestedResourceCount < 0) { // Wenn genug Resourcen verfügbar sind gibt er diese aus
				desiredMap.put(s, 0);
			}
			else {
				contents.put(s, contents.get(s) - desiredResources.get(s));
				desiredMap.put(s, requestedResourceCount);
			}
		}
		return desiredMap;
	}
	
	public void print() {
		System.out.println(contents);
	}
}
