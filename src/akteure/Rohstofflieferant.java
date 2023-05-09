package akteure;

import java.util.HashMap;
import java.util.Random;

import util.SimulatedResource;
import util.Variables;

public class Rohstofflieferant implements Runnable {
	
	private String name;
	private Marketplace marketplace;
	
	public Rohstofflieferant(String name) {
		this.name = name;
		marketplace = Marketplace.getMarketplace();
		
		int count = 0;
		for(String resource : Variables.getResources()) {
			Double price = Variables.getValues()[count];
			marketplace.initResources(new SimulatedResource(resource, name, 0, price));
			count++;
		}
	}

	@Override
	public void run() {
		
		String[] resources = Variables.getResources();
		Random random = new Random();
		
		HashMap<String, Integer> returnMap = new HashMap<>();
		
		while(returnMap.size() < 2) {
			int randomResource = random.nextInt(resources.length);
			int randomResourceCount = Variables.getMaterialSupplyCount();
			returnMap.put(resources[randomResource], randomResourceCount);
		}
		for(String s : returnMap.keySet()) {
			marketplace.put(s, name, returnMap.get(s));
		}
	}
}
