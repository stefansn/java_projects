package homework4;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Observer,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idPerson = 0;
	private int id;
	private String nume;
	private String updateMessage;
	
	
	public Person(String nume) {		
		this.id = idPerson++;
		this.nume = nume;
	}
	
	public int getIdPerson() {
		return this.id;
	}


	public String getNume() {
		return nume;
	}
	
	public static void setStaticId(int id) {
		idPerson = id;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Change observed in " + this.getNume() + "'s" + " account: " + arg);
		
		this.updateMessage = "Change observed in " + this.getNume() + "'s" + " account: " + arg;
		
	}
	

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public String getUpdateMessage() {
		return this.updateMessage;
	}
	
}
