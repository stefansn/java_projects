package model;

public class Produs {
	private int idProdus;
	private String nume;
	private int pret;
	
	public Produs() {
		
	}

	public Produs(String string, int j) {
		// TODO Auto-generated constructor stub		
		this.nume = string;
		this.pret = j;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}
	
	
}
