package com.aprameya.vendingmachineproject;

import java.util.Scanner;

import validation.InvalidDataException;
import validation.NotEnoughStockException;
import validation.ProductUnavailableException;

import java.util.*;

public class VendingMachineOperation {
	
	// Method to display available products list in stock
	public static void displayProductAvailable(Map<Product, Integer> productList) {
		System.out.println("ID\tNAME\tPRICE\tQUANTITY");
		System.out.println("------------------------------------------------");
		for(Map.Entry<Product, Integer> p: productList.entrySet()) {
			if(p.getValue() > 0) System.out.println(p.getKey().getProductId()+ "\t" + p.getKey().getProductName() + "\t" 
		+ p.getKey().getProductPrice() + "\t" + p.getValue() + "\n");
		}
		System.out.println("------------------------------------------------");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Account account = Account.init();
		Cart cart = Cart.init();
		VendingMachine vMachine = VendingMachine.init();
		
		
		System.out.println("WELCOME FOLKS, I'M VENDY-THE VENDING MACHINE");
		while(true) {
			System.out.println("------------------------------------------------");
			System.out.println("------------------------------------------------");
//			System.out.println("Current Balance: " + account.getCurrentBalance());
			if(cart.getProductList().isEmpty()) System.out.println("Nothing to show in cart.");
			else {
				System.out.println("CART LIST");
				displayProductAvailable(cart.getProductList());
			}
			
			System.out.println("Choose from given options:");
			System.out.println("0. List of Available Products");
			System.out.println("1. Select Product");
			System.out.println("2. Add Money");
			System.out.println("Press 'q' to Quit");
			char input1 = sc.next().charAt(0);
			
			if(input1 == '0') {
				System.out.println("AVAILABLE PRODUCTS LIST");
				displayProductAvailable(vMachine.stock.getProductList());
			}
			else if(input1 == '1') {
				System.out.println("Enter Product ID:");
				int pid = sc.nextInt();
				System.out.println("Enter Quantity: ");
				int count = sc.nextInt();
				Product foundProd = null;
				for(Map.Entry<Product, Integer> p : vMachine.stock.getProductList().entrySet()) {
					if(pid == p.getKey().getProductId()) {
						foundProd = p.getKey();
						break;
					}
				}
				if(foundProd != null) {
					System.out.println("Found Product: ");
					try {
						vMachine.stock.removeStock(foundProd, count);
						cart.addProduct(foundProd, count);
					} catch (InvalidDataException e) {
						System.err.println("Enter valid quantity");
					} catch (NotEnoughStockException e) {
						System.err.println("Not enough stock!!");
					} catch (ProductUnavailableException e) {
						System.err.println("Regret!! Product is currently unavailable. Kindly, select from some other options.");
					}
				} else System.err.println("Product Not Found");
			} else if(input1 == '2') {
				double grossTotal = 0;
				for(Map.Entry<Product, Integer> e: cart.getProductList().entrySet()) {
					grossTotal += e.getKey().getProductPrice() * e.getValue();
				}
				System.out.println("Gross Total Amount (To be paid): Rs. " + grossTotal + "\nEnter Amount: ");
				double amt = sc.nextDouble();
				if(amt >= grossTotal) {
					account.addMoney(grossTotal);
					System.out.println("Following products has been dispatched. Kindly, collect them..");
					for(Map.Entry<Product, Integer> entry: cart.getProductList().entrySet()) {
						try {
							cart.removeProduct(entry.getKey(), entry.getValue());
						} catch (ProductUnavailableException e1) {
							System.err.println("Regret!! Product is currently unavailable. Kindly, select from some other options.");
						} catch (InvalidDataException e1) {
							System.err.println("Enter valid quantity!!");
						}
						
					}
					double change = amt - grossTotal;
					if(change > 0) System.out.println("Change Returned: Rs. " + change);
					System.out.println("Enjoy your snacks!!");
				} else System.err.println("Please enter enough amount!");
			} else if(input1 == 'q') break;
			  else System.err.println("Please enter a valid input!!");
		}
		System.out.println("Bbye!! \"Happy Meal\"\nCome back again...");
		sc.close();
	}
}
