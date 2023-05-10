package akteure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import util.SimulatedResource;
import util.Variables;

public class Konsument implements Runnable {
	
	private HashMap<String, Double> prices = new HashMap<>();
	private Random random = new Random();
	
	private Marketplace marketplace;
	
	private String name;
	private String product;
	private Integer wantedProductCount;

	public Konsument(String name, String product) {
		this.name = name;
		this.product = product;
		
		marketplace = Marketplace.getMarketplace();
		initPrices();
	}
	
	
	@Override
	public void run() {
		buy();
		updatePrices();
	}
	
	private void buy() {
		wantedProductCount = Variables.getConsumerWantedProductCount();
		ArrayList<SimulatedResource> resourceList = marketplace.get(name, product, prices.get(product), wantedProductCount);
		for(SimulatedResource r : resourceList) {
			if(r.getCount() != 0) {
				System.out.println("\u001B[36m" + name + " bought " + r.getCount() + " " + r.getName() + " from " + r.getProducer() + " for " +String.format("%.2f", r.getValue()) + " each" + "\u001B[0m");
			}
		}
		
	}

	public void initPrices() {
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
