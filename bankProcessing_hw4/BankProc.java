package homework4;

import java.util.ArrayList;
import java.util.HashMap;

public interface BankProc {
	
	/*
	 * @pre person!=null
	 * 
	 * 
	 * @post hm.size==hm.size@pre + 1
	 * @post hm.contains(person)	
	 */
	
	public void addPerson(Person person);
	
	/*
	 * @pre person!=null
	 * @pre hm.contains(person)
	 * 
	 * 
	 * @post !hm.contains(person)
	 * @post hm.size==hm.size@pre - 1
	 * 
	 */
	public void removePerson(Person person);
	
	/*
	 * @pre person!=null
	 * @pre account!=null
	 * @pre hm.contains(person)
	 * 
	 * 
	 * 
	 * @post hm.get(person).contains(account)
	 * @post hm.get(person).size == hm.get(person).size@pre + 1
	 * 
	 */
	
	public void addAccount(Person person, Account account);
	
	/*
	 * @pre person!=null
	 * @pre account!=null
	 * @pre hm.contains(person)
	 * @pre hm.get(person).contains(account)
	 * 
	 * @post !hm.get(person).contains(account)
	 * @post postSize == preSize - 1
	 * 
	 */
	
	public void removeAccount(Person person, Account account);
	
	
	public HashMap<Person,ArrayList<Account>> readAccount();
	public void writeAccount();
	
	
	
}
