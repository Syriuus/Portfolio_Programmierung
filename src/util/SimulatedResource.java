package util;

import java.util.Random;

public class SimulatedResource {
	
	private String name;
	private String producer;
	private Integer count;
	private Double value;
	
	private Random random;
	
	public SimulatedResource(String name, String producer, Integer count, Double value) {
		this.name = name;
		this.producer = producer;
		this.count = count;
		this.value = value;
		this.random = new Random();
	}

	public String getName() {
		return name;
	}

	public String getProducer() {
		return producer;
	}

	public Integer getCount() {
		return count;
	}

	public void addCount(Integer i) {
		count += i;
	}
	
	public void subtractCount(Integer i) {
		count -= i;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void updateValue() { //verändert den Wert der Resourcen um einen Wert zwischen -1% bis 1%
		Double percentage = random.nextDouble(-1, 1)/100;
		value += (value*percentage);
	}

	@Override
	public String toString() {
		return "Name=" + name + ", Producer=" + producer + ", Count=" + count + ", Value=" + String.format("%.2f", value);
	}
	
	
}
