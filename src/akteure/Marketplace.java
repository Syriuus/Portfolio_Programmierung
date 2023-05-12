package akteure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import util.SimulatedResource;
import util.Variables;

public class Marketplace {
	
	private static Marketplace INSTANCE;
	private static ArrayList<SimulatedResource> contents;
	
	
	private Marketplace() {
		contents = new ArrayList<>();
	}
	
	public static Marketplace getMarketplace() { // Singletoninstanz erstellung
		if(INSTANCE == null) {
			INSTANCE = new Marketplace();
			System.out.println("Marketplace has been created");
		}
		return INSTANCE;
	}
	
	// vllt noch zu int return typ damit man sysout produzent und rohstofflieferant unterscheiden kann
	public synchronized void put(String productName, String producer, Integer count) {  //Bearbeitet das Objekt mit dem Eintrag key und addet den Count
		ArrayList<SimulatedResource> productList = new ArrayList<>();
		for(SimulatedResource resource : contents) {
			if(resource.getName().equalsIgnoreCase(productName)) {
				productList.add(resource);
			}
		}
		for(SimulatedResource resource : productList) {
			if(resource.getProducer() == producer) {
				resource.addCount(count);
				System.out.println("\u001B[32m" + producer + " added " + count + " " + productName + " to the marketplace" + "\u001B[0m");
			}
		}
		productList.clear();
	}
	
	public synchronized ArrayList<SimulatedResource> get(String producer, String product, Double price, Integer count) {
		
		ArrayList<SimulatedResource> possibleMatchesUnsorted = new ArrayList<>();
		ArrayList<SimulatedResource> possibleMatchesSorted = new ArrayList<>();
		ArrayList<Double> priceSortingList = new ArrayList<>();
		ArrayList<SimulatedResource> returnList = new ArrayList<>();
		int counter = 0;
		
		for(SimulatedResource r : contents) {
			if(r.getName() == product && r.getValue() <= price && r.getCount() > 0) {
				
				possibleMatchesUnsorted.add(r);
			}
			if(r.getName() == product && r.getValue() > price && Variables.getOutput() && r.getCount() > 0) {
				System.out.println(producer + " wanted to buy " + product + " from " + r.getProducer() + " for " + String.format("%.2f", r.getValue()) + " but the price was too high! Wanted Price: " + String.format("%.2f", price));
			}
		}
		for(SimulatedResource r : possibleMatchesUnsorted) {
			priceSortingList.add(r.getValue());
		}
		Collections.sort(priceSortingList);
		for(SimulatedResource r : possibleMatchesUnsorted) {
			if(r.getValue().equals(priceSortingList.get(counter))) {
				counter++;
				possibleMatchesSorted.add(r);
			}
		}
		int neededResourceCount = count;
		for(SimulatedResource r : possibleMatchesSorted) {
			
			if(neededResourceCount != 0) {
				if(r.getCount() >= neededResourceCount) {
					returnList.add(new SimulatedResource(r.getName(), r.getProducer(), neededResourceCount, r.getValue()));
					neededResourceCount = 0;
					r.subtractCount(neededResourceCount);
				} else {
					returnList.add(new SimulatedResource(r.getName(), r.getProducer(), r.getCount(), r.getValue()));
					neededResourceCount -= r.getCount();
					r.subtractCount(r.getCount());
				}
			}
		}
		return returnList;
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
	
	public void updatePrices() {
		for(SimulatedResource r : contents) {
			r.updateValue();
		}
	}
	
	public ArrayList<SimulatedResource> getContents() {
		return contents;
	}
}
