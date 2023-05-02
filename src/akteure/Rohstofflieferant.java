package akteure;

import java.util.HashMap;
import java.util.Random;

import util.SimulatedResource;
import util.Variables;

public class Rohstofflieferant implements Runnable {
	
	private String name;
	
	public Rohstofflieferant(String name) {
		this.name = name;
		
		int count = 0;
		for(String resource : Variables.getResources()) {
			Double price = Variables.getValues()[count];
			Marketplace.getMarketplace().initResources(new SimulatedResource(resource, name, 0, price));
			count++;
		}
	}

	@Override
	public void run() {
		
		String[] resources = Variables.getResources();
		Random random = new Random();
		Marketplace marketplace = Marketplace.getMarketplace();
		HashMap<String, Integer> returnMap = new HashMap<>();
		
		while(returnMap.size() < 2) { // Sucht 2 Random Resourcen aus und gibt davon 5 bis 10 aus
			int randomResource = random.nextInt(resources.length);
			int randomResourceCount = random.nextInt(5, 10);
			returnMap.put(resources[randomResource], randomResourceCount);
		}
		for(String s : returnMap.keySet()) {
			marketplace.put(s, name, returnMap.get(s));
		}
	}
}
