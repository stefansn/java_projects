package model;

public class UnderStockException extends Exception{
	public UnderStockException() {
		super("Produsul nu mai exista pe stoc.");
	}
}
