package util;

public class Variables {

	private static String[] contents = {"Holz", "Metall", "Plastik", "Leder", "Sand", "Axt", "Ofen"};
	private static String[] resources = {"Holz", "Metall", "Plastik", "Leder", "Sand"};
	private static Double[] values = {5.0, 10.0, 1.0, 15.0, 2.0, 60.0, 300.0};
	
	public static String[] getContents() {
		return contents;
	}
	public static String[] getResources() {
		return resources;
	}
	public static Double[] getValues() {
		return values;
	}
}
