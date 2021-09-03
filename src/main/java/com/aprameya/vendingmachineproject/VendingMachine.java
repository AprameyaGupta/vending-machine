package com.aprameya.vendingmachineproject;

import validation.InvalidDataException;

public class VendingMachine {
	private static VendingMachine single_instance = null;
	public Stock stock;
	
	private VendingMachine() {
		stock = Stock.init();
		try {
			stock.addStock(new Product(1, "Kurkure", 10), 15);
			stock.addStock(new Product(2, "Lay's", 20), 10);
			stock.addStock(new Product(3, "Doritos", 25), 25);
			stock.addStock(new Product(4, "Diamond Chips", 25), 0);
		} catch (InvalidDataException e) {
			System.err.println("Enter valid quantity!!");
		}
	}
	
	// Vending Machine needs to be singleton class
	public static VendingMachine init() {
        if (single_instance == null)
            single_instance = new VendingMachine();
        return single_instance;
    }
	
}