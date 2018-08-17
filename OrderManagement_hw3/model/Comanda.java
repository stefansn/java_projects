package model;

public class Comanda {
	private int idComanda;
	private int idProdus;
	private int idClient;
	private int cantitate;
	
	public Comanda(int idProdus,int idClient,int cantitate) {
		this.idProdus = idProdus;
		this.idClient = idClient;
		this.cantitate = cantitate;
	}
	
	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public Comanda() {
		
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	
	
}
