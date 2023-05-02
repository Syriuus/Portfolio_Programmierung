package akteure;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import main.Main;
import util.Variables;

public class Konsument implements Runnable {
	
	private HashMap<String, Double> prices = new HashMap<>();
	private Random random = new Random();
	
	private Marketplace marketplace;
	private Logger logger;
	
	private String name;
	private String product;
	private Integer wantedProductCount;

	public Konsument(String name, String product) {
		this.name = name;
		this.product = product;
		
		marketplace = Marketplace.getMarketplace();
		this.logger = Main.getLogger();
		initPrices();
	}
	
	
	@Override
	public void run() {
		buy();
		updatePrices();
	}
	
	private void buy() {
		wantedProductCount = random.nextInt(3, 5);
		Integer count = marketplace.get(name, product, prices.get(product), wantedProductCount);
		if(count != 0) {
			logger.info(name + " put " + count + " " + product + " in their inventory");
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
