package akteure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import main.Main;
import util.SimulatedResource;
import util.Variables;

public class Produzent implements Runnable {
	
	private HashMap<String, Double> prices = new HashMap<>();
	private HashMap<String, Integer> inventory = new HashMap<>();
	private ArrayList<String> desiredResources = new ArrayList<>();
	
	private Random random = new Random();
	
	private Marketplace marketplace;
	private Logger logger;
	
	private String name;
	private String product1;
	private String product2;
	private Integer wantedProductCount = 3000;
	private String[] recipe1;
	private String[] recipe2;
	
	public Produzent(String name, String product1, String product2) {
		this.name = name;
		this.product1 = product1;
		this.product2 = product2;
		
		logger = Main.getLogger();
		marketplace = Marketplace.getMarketplace();
		initPrices();
		
		recipe1 = Variables.getRecipe(product1);
		recipe2 = Variables.getRecipe(product2);
		for(String s : recipe1) {
			if(!desiredResources.contains(s)) {
				desiredResources.add(s);
			}
		}
		for(String s : recipe2) {
			if(!desiredResources.contains(s)) {
				desiredResources.add(s);
			}
		}
		
		
	
		for(String s : Variables.getContents()) {
			int i = 0;
			inventory.put(s, i);
		}

		int count = 0;
		Double price = 0.0;
		for(String s : Variables.getContents()) {
			if(s==product1) {
				price = Variables.getValues()[count];
			}
			count++;
		}
		Marketplace.getMarketplace().initResources(new SimulatedResource(product1, name, 0, price));
		
		count = 0;
		price = 0.0;
		for(String s : Variables.getContents()) {
			if(s==product2) {
				price = Variables.getValues()[count];
			}
			count++;
		}
		Marketplace.getMarketplace().initResources(new SimulatedResource(product2, name, 0, price));
	}
	
	@Override
	public void run() {
		tryToGetResources(desiredResources);
		produce(product1, recipe1);
		produce(product2, recipe2);
		updatePrices();
	}
	
	
	
	private void tryToGetResources(ArrayList<String> resources) {
		for(String resource : resources) {
			int count = marketplace.get(name, resource, prices.get(resource), wantedProductCount);
			if(count != 0) {
				logger.info(name + " put " + count + " " + resource + " in their inventory");
				inventory.put(resource, inventory.get(resource) + count);
			}
		}
	}
	
	private void produce(String product, String[] recipe) {
		int smallestNumberOfResource = 1000000;
		for(String s : recipe) {
			int numberOfResource = inventory.get(s);
			if(smallestNumberOfResource > numberOfResource) {
				smallestNumberOfResource = numberOfResource;
			}
		}
		
		if(smallestNumberOfResource != 0) {
			
			for(String s: recipe) {
				inventory.put(s, inventory.get(s) - smallestNumberOfResource);
			}
			marketplace.put(product, name, smallestNumberOfResource);
		}
		
	}
	
	private void initPrices() {
		int counter = 0;
		for(String s : Variables.getContents()) {
			prices.put(s, Variables.getValues()[counter]);
		}
	}
	private void updatePrices() {
		for(String s : prices.keySet()) {
			Double percentage = random.nextDouble(-1, 1)/100;
			Double value = prices.get(s);
			value += (value*percentage);
			prices.put(s, value);
		}
	}
}
