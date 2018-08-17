package homework4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Bank implements BankProc,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Person,ArrayList<Account>> hm;

	public Bank() {
		hm = new HashMap<Person,ArrayList<Account>>();	
	}
	
	@Override
	public void addPerson(Person person) {
		assert person!=null : "person == null";
		int preSize = hm.size();
		
		hm.put(person, new ArrayList<Account>());
		this.writeAccount();
		
		int postSize = hm.size();
		assert preSize != postSize + 1 : "postSize != preSize + 1";
		assert hm.containsKey(person) : "hm does not contain person";
	}

	@Override
	public void removePerson(Person person) {	
		assert person!=null : "person == null";
		assert hm.containsKey(person) : "hm does not contain person";
		int preSize = hm.size();
		
		hm.remove(person);		
		
		int postSize = hm.size();
		assert hm.containsKey(person)==false : "hm does contain person";
		assert postSize == preSize - 1: "preSize == postSize - 1";
	}

	@Override
	public void addAccount(Person person,Account account) {	
		assert person!=null : "person == null";
		assert account!=null : "account == null";
		assert hm.containsKey(person) : "hm does not contain person";
		int preSize = hm.get(person).size();
		
		hm.get(person).add(account); //adaug contul in lista de conturi a persoanei
		this.writeAccount();
		account.addObserver(person);
		
		int postSize = hm.get(person).size();
		assert hm.get(person).contains(account) : "hm.get(person) does not contain account";
		assert postSize == preSize + 1 : "postSize != preSize + 1";
	}

	@Override
	public void removeAccount(Person person,Account account) {
		assert person!=null : "person == null";
		assert account!=null : "account == null";
		assert hm.containsKey(person) : "hm does not contain person";
		assert hm.get(person).contains(account) : "hm.get(person) does not contain account";
		int preSize = hm.get(person).size();
		
		hm.get(person).remove(account);	
		this.writeAccount();
		
		int postSize = hm.get(person).size();
		assert postSize == preSize - 1 : "postSize != preSize -1";
		assert hm.get(person).contains(account)==false : "hm.get(person) does contains account";
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Person,ArrayList<Account>> readAccount() {
		HashMap<Person,ArrayList<Account>> aux_hm = new HashMap<Person,ArrayList<Account>>();	
		
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\whos\\Desktop\\write.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);			
			
			aux_hm = (HashMap<Person, ArrayList<Account>>) in.readObject();
			int maxStaticId = 0;
			int maxStaticIdAccount = 0;
			for (Map.Entry<Person, ArrayList<Account>> entry: aux_hm.entrySet()) {
				
				Person p = entry.getKey();		
				
				if (p.getIdPerson() >= maxStaticId) {
					maxStaticId = p.getIdPerson();					
				}	
				
				
				ArrayList<Account> acc = entry.getValue();
				
				for (Account ac : acc) {
					if (ac.getId() >= maxStaticIdAccount) {
						maxStaticIdAccount = ac.getId();
					}
				}
				
			}
			Person.setStaticId(maxStaticId+1);
			Account.setStaticId(maxStaticIdAccount+1);
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return aux_hm;
	}

	@Override
	public void writeAccount() {
		try {
			FileOutputStream fout = new FileOutputStream("C:\\Users\\whos\\Desktop\\write.ser");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fout);
						
			oos.writeObject(hm);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeEverything() {
		HashMap<Person,ArrayList<Account>> hm_clean = new HashMap<Person,ArrayList<Account>>();
		hm = hm_clean;
		writeAccount();
		
	}	
	
	public HashMap<Person, ArrayList<Account>> getHm() {
		return hm;
	}

	public void setHm(HashMap<Person, ArrayList<Account>> hm) {
		this.hm = hm;
	}

	public List<Account> seeAllAccounts(Person person) {
		return hm.get(person);
	}
	
	public void interest () {
		for (Map.Entry<Person, ArrayList<Account>> entry: hm.entrySet()) {			
	
			ArrayList<Account> acc = entry.getValue();
			
			for (Account ac : acc) {
				if (ac instanceof SavingAccount) {
					ac.setBalance(ac.getBalance() + (ac.getBalance()/100 * 0.3));
				}
			}
			
		}
	}

}
