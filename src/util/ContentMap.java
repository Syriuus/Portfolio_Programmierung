package util;

import java.util.HashMap;

public class ContentMap {
	
	public static HashMap<String, Integer> getContentMap() {
		
		HashMap<String, Integer> contentMap = new HashMap<>();
		String[] contents = Variables.getContents();
		
		for(String s : contents) {
			contentMap.put(s, 0);
		}
		return contentMap;
	}
}
