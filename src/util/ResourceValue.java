package util;

import java.util.HashMap;
import java.util.Random;

public class ResourceValue {
	
	private static HashMap<String, Double> resourceValues = new HashMap<>();
	
	private static Random random = new Random();
	
	public static void initValues() {
		String[] resources = Variables.getContents();
		Double[] values = Variables.getValues();
		int i = 0;
		for(String s : resources) {
			resourceValues.put(s, values[i]);
			i++;
		}
	}
	
	public Double getValue(String s) {
		return resourceValues.get(s);
	}
	
	public void updateValue() { //verändert den Wert der Resourcen um einen Wert zwischen -1% bis 1%
		for(String s : resourceValues.keySet()) {
			double value = resourceValues.get(s);
			Double percentage = random.nextDouble(-1, 1)/100;
			value += (value*percentage);
			resourceValues.put(s, value);
		}
		
	}
}
