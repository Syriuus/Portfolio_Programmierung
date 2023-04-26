package util;

import java.util.HashMap;

public class ContentMap {
	
	private static HashMap<String, SimulatedResource> contentMap = new HashMap<>();
	
	public static HashMap<String, SimulatedResource> getContentMap() {
		String[] contents = Variables.getContents();
		Double[] values = Variables.getValues();
		int i = 0;
		for(String s : contents) {
			contentMap.put(s, new SimulatedResource(0, values[i]));
			i++;
		}
		return contentMap;
	}
}
