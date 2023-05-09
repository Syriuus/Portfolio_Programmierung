package main;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import akteure.Konsument;
import akteure.Marketplace;
import akteure.Produzent;
import akteure.Rohstofflieferant;
import util.SimulatedResource;
import util.Variables;

public class Main {
	
	private static ArrayList<Produzent> producerList = new ArrayList<>();
	private static ArrayList<Rohstofflieferant> supplierList = new ArrayList<>();
	private static ArrayList<Konsument> consumerList = new ArrayList<>();
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		Variables.initRecipe();
		
		addProducer();
		addSupplier();
		addConsumer();
		int numberOfThreads = producerList.size() + supplierList.size() + consumerList.size();
		
		for(int k = 0; k < 10; k++) {
			ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
			
			Produzent producerPool[] = new Produzent[producerList.size()];
			for(int i = 0; i < producerList.size(); i++) {
				producerPool[i] = producerList.get(i);
				threadPool.execute(producerPool[i]);
			}	
			
			Rohstofflieferant supplierPool[] = new Rohstofflieferant[supplierList.size()];
			for(int i = 0; i < supplierList.size(); i++) {
				supplierPool[i] = supplierList.get(i);
				threadPool.execute(supplierPool[i]);
			}
			
			Konsument consumerPool[] = new Konsument[consumerList.size()];
			for(int i = 0; i < consumerList.size(); i++) {
				consumerPool[i] = consumerList.get(i);
				threadPool.execute(consumerPool[i]);
			}
			threadPool.shutdown();
			threadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
			
			Marketplace m = Marketplace.getMarketplace();
			m.updatePrices();
			
			System.out.println("-------------------");
			System.out.println("Marketplace inventory");
			for(SimulatedResource r : m.getContents()) {
				System.out.println(r);
			}
			System.out.println("-------------------");
		}
		
		
	
		
		
		
		
		
		
		
		
	}
	
	private static void addProducer() {
		producerList.add(new Produzent("Charlie", "axe", "oven"));
		producerList.add(new Produzent("Oli", "axe", "oven"));
		producerList.add(new Produzent("Gregor", "axe", "oven"));
	}
	
	private static void addSupplier() {
		supplierList.add(new Rohstofflieferant("Uwe"));
		supplierList.add(new Rohstofflieferant("Michelle"));
		supplierList.add(new Rohstofflieferant("Peter"));
	}
	
	private static void addConsumer() {
		consumerList.add(new Konsument("Karin", "axe"));
		consumerList.add(new Konsument("Dennis", "oven"));
		consumerList.add(new Konsument("Bruno", "oven"));
	}
}
