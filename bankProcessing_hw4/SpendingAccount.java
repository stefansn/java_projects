package homework4;

public class SpendingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpendingAccount(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deposit(int sum) {
		// TODO Auto-generated method stub
		this.setBalance(this.getBalance() + sum);
		this.setChanged();
		notifyObservers("Deposited " + sum);
	}

	@Override
	public void withdraw(int sum) {
		// TODO Auto-generated method stub
		this.setBalance(this.getBalance() - sum);
		this.setChanged();
		notifyObservers("Withdrawed " + sum);
	}
	
}
