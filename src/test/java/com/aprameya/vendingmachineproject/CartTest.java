package com.aprameya.vendingmachineproject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import validation.InvalidDataException;
import validation.ProductUnavailableException;


public class CartTest {
	@Test
	public void testRemoveProduct() throws InvalidDataException, ProductUnavailableException {
		Cart cart = Cart.init();
		Product p = new Product(1, "Kurkure", 10);
		cart.addProduct(p, 10);
		cart.removeProduct(p, 1);
		assertEquals(9, cart.getProductList().get(p).intValue());
	}
	
	@Test(expected = ProductUnavailableException.class)
	public void testRemoveProduct_WithUnavailableRemoveProductException() throws InvalidDataException, ProductUnavailableException {
		Cart cart = Cart.init();
		Product p = new Product(1, "Kurkure", 10);
		cart.addProduct(p, 10);
		Product p1 = new Product(2, "Doritos", 10);
		cart.removeProduct(p1, 1);
	}
	
	@Test(expected = InvalidDataException.class)
	public void testRemoveProduct_WithInvalidDataException() throws InvalidDataException, ProductUnavailableException {
		Cart cart = Cart.init();
		Product p = new Product(1, "Kurkure", 10);
		cart.addProduct(p, 10);
		cart.removeProduct(p, -1);
	}
}
