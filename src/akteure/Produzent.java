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
	private Integer wantedProductCount = 100000;
	
	public Produzent(String name, String product1, String product2) {
		this.name = name;
		this.product1 = product1;
		this.product2 = product2;
		
		logger = Main.getLogger();
		marketplace = Marketplace.getMarketplace();
		initPrices();
		
	
		for(String s : Variables.getContents()) {
			inventory.put(s, 0);
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
		produce();
		updatePrices();
	}
	
	private void produce() {
		String[] recipe1 = Variables.getRecipe(product1);
		String[] recipe2 = Variables.getRecipe(product2);
		for(String s : recipe1) {
			desiredResources.add(s);
		}
		for(String s : recipe2) {
			desiredResources.add(s);
		}
		tryToGetResources(desiredResources);
	}
	
	private void tryToGetResources(ArrayList<String> resources) {
		for(String resource : resources) {
			//check price then get -> doppelte absicherung weil das produkt in der zeit schon weg sein kann
		
			Integer count = marketplace.get(name, resource, prices.get(resource), wantedProductCount);
			inventory.put(resource, inventory.get(resource) + count);
			if(count != 0) {
				logger.info(name + " put " + count + " " + resource + " in their inventory");
			}
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
