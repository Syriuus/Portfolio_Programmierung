package akteure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;

import main.Main;
import util.SimulatedResource;

public class Marketplace {
	
	private static Marketplace INSTANCE;
	private static ArrayList<SimulatedResource> contents;
	private static Logger logger;
	
	
	private Marketplace() {
		contents = new ArrayList<>();
		logger = Main.getLogger();
	}
	
	public static Marketplace getMarketplace() { // Singletoninstanz erstellung
		if(INSTANCE == null) {
			INSTANCE = new Marketplace();
			logger.info("Marketplace has been created");
		}
		return INSTANCE;
	}
	
	public synchronized void put(String productName, String producer, Integer count) {  //Bearbeitet das Objekt mit dem Eintrag key und addet den Count
		ArrayList<SimulatedResource> productList = new ArrayList<>();
		for(SimulatedResource resource : contents) {
			if(resource.getName() == productName) {
				productList.add(resource);
			}
		}
		for(SimulatedResource resource : productList) {
			if(resource.getProducer() == producer) {
				resource.addCount(count);
				logger.info(producer + " added " + count + " " + productName + " to the marketplace");
			}
		}
		productList.clear();
	}
	
	public synchronized Integer get(String producer, String product, Double price, Integer count) {
		
		ArrayList<SimulatedResource> possibleMatchesUnsorted = new ArrayList<>();
		ArrayList<SimulatedResource> possibleMatchesSorted = new ArrayList<>();
		ArrayList<Double> priceSortingList = new ArrayList<>();
		Integer counter = 0;
		Integer returnResourcesCounter = 0;
		
		for(SimulatedResource r : contents) {
			if(r.getName() == product && r.getValue() <= price) {
				possibleMatchesUnsorted.add(r);
			}
		}
		for(SimulatedResource r : possibleMatchesUnsorted) {
			priceSortingList.add(r.getValue());
		}
		Collections.sort(priceSortingList);
		for(SimulatedResource r : possibleMatchesUnsorted) {
			if(r.getValue() == priceSortingList.get(counter)) {
				counter++;
				possibleMatchesSorted.add(r);
			}
		}
		for(SimulatedResource r : possibleMatchesSorted) {
			int neededResourceCount = count;
			if(returnResourcesCounter != count) {
				if(r.getCount() >= neededResourceCount) {
					returnResourcesCounter += neededResourceCount;
					neededResourceCount -= neededResourceCount;
					r.subtractCount(neededResourceCount);
				} else {
					returnResourcesCounter += r.getCount();
					neededResourceCount -= r.getCount();
					r.subtractCount(r.getCount());
				}
			}
		}
		return returnResourcesCounter;
	}
	
	public void initResources(SimulatedResource r) {
		contents.add(r);
	}
	
	public HashMap<Double, Integer> getPrice(String product) {
		HashMap<Double, Integer> prices = new HashMap<>();
		for(SimulatedResource r : contents) {
			if(r.getName() == product) {
				prices.put(r.getValue(), r.getCount());
			}
		}
		return prices;
	}
	
	public void print() {
		System.out.println(contents);
	}
}
