package util;

import java.util.Random;

public class SimulatedResource {

	private Integer count;
	private Double value;
	private Random random = new Random();
	
	
	public SimulatedResource(Integer count, Double value) {
		this.count = count;
		this.value = value;
	}


	public Integer getCount() {
		return count;
	}
	
	public void addCount(Integer count) {
		this.count += count;
	}

	public void subtractCount(Integer count) {
		this.count -= count;
	}

	public Double getValue() {
		return value;
	}
	
	public void updateValue() {
		Double percentage = random.nextDouble(-1, 1)/100;
		value += (value*percentage);
	}


	@Override
	public String toString() {
		return "[count=" + count + ", value=" + value + "]";
	}
}
