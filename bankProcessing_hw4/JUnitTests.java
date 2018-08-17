package homework4;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTests {

	@Test
	public void testAddPerson() {
		Bank bank = new Bank();		
		
		Person person = new Person("Stefan");
		assertNotNull(person);
		
		int preSize = bank.getHm().size();
		
		bank.addPerson(person);
		
		int postSize = bank.getHm().size();
		assertEquals(true,bank.getHm().containsKey(person));
		assertEquals(postSize,preSize+1);
				
	}
	
	@Test 
	public void testRemovePerson() {
		Bank bank = new Bank();
		
		Person person = new Person("Stefan");
		assertNotNull(person);
		bank.addPerson(person);
		assertEquals(true,bank.getHm().containsKey(person));
		
		int preSize = bank.getHm().size();
		
		bank.removePerson(person);
		
		int postSize = bank.getHm().size();
		assertEquals(false,bank.getHm().containsKey(person));
		assertEquals(postSize,preSize-1);
		
	}
	
	@Test 
	public void testAddAccount() {
		Bank bank = new Bank();
		
		Person person = new Person("Stefan");
		SavingAccount account = new SavingAccount(0);
		
		bank.addPerson(person);
		
		int preSize = bank.getHm().get(person).size();
		
		assertNotNull(person);
		assertNotNull(account);
		
		bank.addAccount(person, account);
		
		int postSize = bank.getHm().get(person).size();
		
		assertEquals(postSize,preSize+1);
		assertEquals(true,bank.getHm().get(person).contains(account));
	}
	
	@Test 
	public void testRemoveAccount() {
		Bank bank = new Bank();
		
		Person person = new Person("Stefan");
		SavingAccount account = new SavingAccount(0);
		
		assertNotNull(person);
		assertNotNull(account);
		
		bank.addPerson(person);
		bank.addAccount(person, account);
		
		assertEquals(true,bank.getHm().containsKey(person));
		assertEquals(true,bank.getHm().get(person).contains(account));
		
		
		int preSize = bank.getHm().get(person).size();
		
		bank.removeAccount(person, account);
		
		int postSize = bank.getHm().get(person).size();
		assertEquals(postSize,preSize-1);
		assertEquals(false,bank.getHm().get(person).contains(account));
	}

}
