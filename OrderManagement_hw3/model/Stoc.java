package model;

public class Stoc {
	private int idStoc;
	private int idProdus;
	private int cantitate;
	
	public Stoc() {
		
	}
	
	public Stoc(int idProdus,int cantitate) {
		this.idProdus = idProdus;
		this.cantitate = cantitate;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public int getIdStoc() {
		return idStoc;
	}

	public void setIdStoc(int idStoc) {
		this.idStoc = idStoc;
	}
	
	
}
