package bll;

import java.util.List;

import DAO.AbstractDAO;
import DAO.ClientDAO;
import model.Client;

public class ClientBll{	

	public List<Client> findClientById (int id) {
		ClientDAO clD = new ClientDAO();
		List<Client> list = clD.findById(id);
		
		return list;
	}
	
	public List<Client> findAllClients () {
		ClientDAO clD = new ClientDAO();
		
		List<Client> list = clD.findAll();
		
		return list;
	}
	
	public void insertIntoClient(Client newClient) {
		ClientDAO clD = new ClientDAO();
		
		List<Client> existentClients = this.findAllClients();
		
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Client cl: existentClients) {
			number[cl.getIdClient()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		newClient.setIdClient(freeId);
		
		clD.insertIntoT(newClient);
	}
	
	public void updateClient(Client newClient,int id) {
		ClientDAO clD = new ClientDAO();
		
		List<Client> existentClients = this.findAllClients();
		

		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Client cl: existentClients) {
			number[cl.getIdClient()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		
		clD.updateT(newClient,id);
	}
	
	public void deleteByIdClient(int id) {
		ClientDAO clD = new ClientDAO();
		clD.deleteByIdT(id);
	}
	
	public void orderByIdClient() {
		ClientDAO clD = new ClientDAO();
		clD.orderByIdT();
	}
	
}
