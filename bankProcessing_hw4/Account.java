package homework4;

import java.io.Serializable;
import java.util.Observable;


public abstract class Account extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int id = 0;
	private int idPerson;
	private int idAccount;
	private double balance;
	
	public Account(double balance) {
		this.idAccount = id++;		
		this.balance = balance;
	}
	
	public abstract void deposit(int sum);
	public abstract void withdraw(int sum);

	public int getId() {
		return this.idAccount;
	}


	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static void setStaticId(int idAccount) {
		id = idAccount;
	}
}
