package main;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import akteure.Konsument;
import akteure.Marketplace;
import akteure.Produzent;
import akteure.Rohstofflieferant;
import util.Variables;

public class Main {
	
	private static Logger logger;
	private static ArrayList<Produzent> producerList = new ArrayList<>();
	private static ArrayList<Rohstofflieferant> supplierList = new ArrayList<>();
	private static ArrayList<Konsument> consumerList = new ArrayList<>();
	
	
	public static void main(String[] args) throws InterruptedException {
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		logger.addHandler(new ConsoleHandler());
		
		Variables.initRecipe();
		
		addProducer();
		addSupplier();
		addConsumer();
		int numberOfThreads = producerList.size() + supplierList.size() + consumerList.size();
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
		m.print();
	}
	
	private static void addProducer() {
		producerList.add(new Produzent("Charlie", "Axt", "Ofen"));
		producerList.add(new Produzent("Oli", "Axt", "Ofen"));
		producerList.add(new Produzent("Gregor", "Axt", "Ofen"));
	}
	
	private static void addSupplier() {
		supplierList.add(new Rohstofflieferant("Uwe"));
		supplierList.add(new Rohstofflieferant("Michelle"));
		supplierList.add(new Rohstofflieferant("Peter"));
	}
	
	private static void addConsumer() {
		consumerList.add(new Konsument("Karin", "Axt"));
		consumerList.add(new Konsument("Dennis", "Ofen"));
		consumerList.add(new Konsument("Bruno", "Ofen"));
	}
	public static Logger getLogger() {
		return logger;
	}
}
