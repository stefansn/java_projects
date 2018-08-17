package model;

public class Client {	
	private int idClient;
	private String nume;
	private String prenume;
	
	
	public Client() {
		this.idClient = 0;
		this.nume = "Unknown";
		this.prenume = "Unknown";
	}
	
	public Client(String nume,String prenume) {		
		this.nume=nume;
		this.prenume = prenume;
	}	

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	
}
