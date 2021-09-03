package com.aprameya.vendingmachineproject;

import java.util.*;

import validation.InvalidDataException;
import validation.NotEnoughStockException;
import validation.ProductUnavailableException;

public class Stock {
	private static Stock single_instance = null;
	private Map<Product, Integer> productList;

	private Stock() {
		productList = new LinkedHashMap<Product, Integer>();
	}
	
	public static Stock init() {
        if (single_instance == null)
            single_instance = new Stock();
        return single_instance;
    }
	
	public Map<Product, Integer> getProductList() {
		return productList;
	}

	public void setProductList(Map<Product, Integer> productList) {
		this.productList = productList;
	}

	// Add Stock Method
	public void addStock(Product prod, int count) throws InvalidDataException {
		if(count < 0) {
			throw new InvalidDataException("Enter valid quantity!!");
		} else {
			if(productList.containsKey(prod)) productList.put(prod, productList.get(prod) + count);
			else {
				if(productList.isEmpty()) {
					prod.setProductId(0);
				} else {
					int cnt = 1;
			        for (Map.Entry<Product, Integer> it : productList.entrySet()) {
			            if (cnt == productList.size()) {
			              prod.setProductId(it.getKey().getProductId() + 1);
			            }
			            cnt++;
			        }
				}
				productList.put(prod, count);
			}
		}
	}
	
	// Remove Stock Method
	public void removeStock(Product prod, int count) throws NotEnoughStockException, ProductUnavailableException, InvalidDataException {
		if(count < 0) {
			throw new InvalidDataException("Enter valid quantity!!");
		} else {
			if(!productList.containsKey(prod)) {
				throw new ProductUnavailableException("Regret!! Product is currently unavailable. Kindly, select from some other options.");
			} else {
				if(productList.get(prod) < count) {
					throw new NotEnoughStockException("Not enough stock!!");
				} else {
					productList.put(prod, productList.get(prod) - count);
					System.out.println(prod.getProductName() + " " + count);
				}
			}
		} 
	}
}
