package util;

import java.util.HashMap;
import java.util.Random;

public class Variables {

	private static String[] contents = {"wood", "metal", "plastic", "leather", "glass", "axe", "oven"};
	private static String[] resources = {"wood", "metal", "plastic", "leather", "glass"};
	private static Double[] values = {5.0, 10.0, 1.0, 15.0, 2.0, 60.0, 300.0};
	
	private static Random random = new Random();
	private static boolean output = true;
	
	private static String[] recipe1 = {"wood", "metal", "leather"};
	private static String[] recipe2 = {"metal", "plastic", "glass"};
	private static HashMap<String, String[]> recipeMap = new HashMap<>();
	
	private static int producerWantedProductCount = 3000;
	
	
	public static void initRecipe() {
		recipeMap.put(contents[5], recipe1);
		recipeMap.put(contents[6], recipe2);
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
	
	private static boolean contains(String[] list, String[] otherList) {
		int counter = 0;
		for(String entry: list) {
			for(String otherEntry: otherList) {
				if(entry.equalsIgnoreCase(otherEntry)) {
					counter++;
				}
			}
		}
		if(counter == otherList.length) {
			return true;
		}
		return false;
	}
	
	public static void validateVariables() {
		if(contents.length != values.length || values.length != 7 || resources.length != 5) {
			throw new FalseInputException("The lengths of the lists are not correct");
		}
		
		if(!contains(resources, recipe1) || !contains(resources, recipe2)) {
			throw new FalseInputException("The components of the recipe are not valid resources");
		}
	}
	public static boolean getOutput() {
		return output;
	}
	public static void setOutput(boolean output) {
		Variables.output = output;
	}
}
