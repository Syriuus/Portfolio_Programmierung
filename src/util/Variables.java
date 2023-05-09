package util;

import java.util.HashMap;
import java.util.Random;

public class Variables {

	private static String[] contents = {"wood", "metal", "plastic", "leather", "glass", "axe", "oven"};
	private static String[] resources = {"wood", "metal", "plastic", "leather", "glass"};
	private static Double[] values = {5.0, 10.0, 1.0, 15.0, 2.0, 60.0, 300.0};
	
	private static Random random = new Random();
	
	private static String[] axeRecipe = {"wood", "metal", "leather"};
	private static String[] ovenRecipe = {"metal", "plastic", "glass"};
	private static HashMap<String, String[]> recipeMap = new HashMap<>();
	
	private static int producerWantedProductCount = 3000;
	
	
	public static void initRecipe() {
		recipeMap.put("axe", axeRecipe);
		recipeMap.put("oven", ovenRecipe);
	}
	
	public static String[] getContents() {
		return contents;
	}
	public static String[] getResources() {
		return resources;
	}
	public static Double[] getValues() {
		return values;
	}
	public static String[] getRecipe(String product) {
		return recipeMap.get(product);
	}

	public static int getProducerWantedProductCount() {
		return producerWantedProductCount;
	}
	public static int getConsumerWantedProductCount() {
		return random.nextInt(3, 5);
	}
	public static int getMaterialSupplyCount() {
		return random.nextInt(5, 10);
	}
}
