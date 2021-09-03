package com.aprameya.vendingmachineproject;

public class Account {
	private static Account single_instance = null;
	private double currentBalance;
	
	private Account() {
		currentBalance = 0.0;
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public static Account init() {
        if (single_instance == null)
            single_instance = new Account();
        return single_instance;
    }

	public void addMoney(double amount) {
		this.currentBalance += amount;
	}
	
	public boolean removeMoney(double amount) {
		if(currentBalance >= amount) {
			this.currentBalance -= amount;
			return true;
		} else System.err.println("Not enough balance!!");
		return false;
	}
}
