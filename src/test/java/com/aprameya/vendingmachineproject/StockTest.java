package com.aprameya.vendingmachineproject;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import validation.InvalidDataException;
import validation.NotEnoughStockException;
import validation.ProductUnavailableException;

public class StockTest {
	@Test
	public void testRemoveStock() throws InvalidDataException, NotEnoughStockException, ProductUnavailableException {
		Stock stock = Stock.init();
		Product p = new Product(1, "Kurkure", 10);
		stock.removeStock(p, 1);
		assertEquals(9, stock.getProductList().get(p).intValue());
	}
	
	@Test(expected = ProductUnavailableException.class)
	public void testRemoveStock_WithProductUnavailableException() throws InvalidDataException, NotEnoughStockException, ProductUnavailableException {
		Stock stock = Stock.init();
		Product p = new Product(1, "Kurkure", 10);
		stock.addStock(p, 10);
		Product p1 = new Product(2, "Doritos", 10);
		stock.removeStock(p1, 1);
	}
	
	@Test(expected = InvalidDataException.class)
	public void testRemoveStock_WithInvalidDataException() throws InvalidDataException, NotEnoughStockException, ProductUnavailableException {
		Stock stock = Stock.init();
		Product p = new Product(1, "Kurkure", 10);
		stock.addStock(p, 10);
		stock.removeStock(p, -1);
	}
	
	@Test(expected = NotEnoughStockException.class)
	public void testRemoveStock_WithNotEnoughStockException() throws InvalidDataException, NotEnoughStockException, ProductUnavailableException {
		Stock stock = Stock.init();
		Product p = new Product(1, "Kurkure", 10);
		stock.addStock(p, 10);
		stock.removeStock(p, 20);
	}
}
