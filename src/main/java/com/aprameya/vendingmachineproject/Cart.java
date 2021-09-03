package com.aprameya.vendingmachineproject;

import java.util.*;

import validation.InvalidDataException;
import validation.ProductUnavailableException;

public class Cart {
	private static Cart single_instance = null;
	private Map<Product, Integer> productList;

	private Cart() {
		productList = new LinkedHashMap<Product, Integer>();
	}
	
	public static Cart init() {
        if (single_instance == null)
            single_instance = new Cart();
        return single_instance;
    }
	
	public Map<Product, Integer> getProductList() {
		return productList;
	}

	public void setProductList(Map<Product, Integer> productList) {
		this.productList = productList;
	}
	
	// Add Product Method
	public void addProduct(Product prod, int count) throws InvalidDataException {
		if(count < 0) {
			throw new InvalidDataException("Enter valid quantity!!");
		} else {
			if(productList.containsKey(prod)) productList.put(prod, productList.get(prod) + count);
			else productList.put(prod, count);
		}
	}
	
	// Remove Product Method
	public void removeProduct(Product prod, int count) throws ProductUnavailableException, InvalidDataException {
		if(count < 0) {
			throw new InvalidDataException("Enter valid quantity!!");
		} else {
			if(!productList.containsKey(prod)) {
				throw new ProductUnavailableException("No such product found!!");
			} else {
				if(productList.get(prod) > count) {
					productList.put(prod, productList.get(prod) - count);
					System.out.println(count + " " + prod.getProductName() + " has been removed.");
				} else if(productList.get(prod) == count) {
					productList.remove(prod);
					System.out.println(prod.getProductName() + " has been removed from cart.");
				}
			}
		} 
	}
}
