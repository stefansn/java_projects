package homework4;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
	public static void main (String[] args) {
		
		Bank bank = new Bank();
		
//		Person p1 = new Person("Stefan");
//		Person p2 = new Person("Stefan2");
//		Person p3 = new Person("Stefan3");
//		
//		bank.addPerson(p1);
//		bank.addPerson(p2);
//		bank.addPerson(p3);
//		
//		bank.addAccount(p1, new SavingAccount(0));
//		bank.addAccount(p2, new SpendingAccount(0));
//		bank.addAccount(p2, new SavingAccount(100));
//		bank.addAccount(p1, new SpendingAccount(5000));
//		
//		
//		
//		
//		for (Map.Entry<Person, ArrayList<Account>> entry: bank.getHm().entrySet()) {
//			Person p = entry.getKey();
//			ArrayList<Account> accounts = entry.getValue();
//			
//			System.out.println("Client nr: " + p.getIdPerson() + " " + p.getNume() + " has: ");
//			
//			for (Account acc : accounts ) {
//				//acc.deposit(10);
//				
//				System.out.print("Account id: " + acc.getId() + " with balance: " + acc.getBalance());
//				System.out.println("");
//			}
//			System.out.println("#####");							
//			
//		}				
//		
//	
//		
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int count= 0;
			
			public void run() {
				//System.out.println(count);
				count++;
				bank.interest();
				if (count==1) {
					bank.setHm(bank.readAccount());
					GUI.createAndShowGui(bank);
				}
				bank.writeAccount();
			}
		}, new Date(), 1000);
		
		
	}
} 
