package main;

import java.util.HashMap;

import akteure.Marketplace;
import util.ContentMap;

public class Main {
	public static void main(String[] args) {
		Marketplace m = Marketplace.getMarketplace();
		HashMap<String, Integer> test = new HashMap<>();
		test.put("Holz", 5);
		m.print();
		m.put(test);
		m.print();
		m.put(test);
		m.print();
	}

}
