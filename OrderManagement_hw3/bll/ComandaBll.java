package bll;

import java.util.List;

import DAO.AbstractDAO;
import DAO.ClientDAO;
import DAO.ComandaDAO;
import model.Client;
import model.Comanda;

public class ComandaBll extends AbstractDAO<Comanda>{
	public List<Comanda> findComandaById (int id) {
		ComandaDAO comandaD = new ComandaDAO();
		List<Comanda> list = comandaD.findById(id);
		
		return list;
	}
	
	public List<Comanda> findAllOrders () {
		ComandaDAO comandaD = new ComandaDAO();
		
		List<Comanda> list = comandaD.findAll();
		
		return list;
	}
	
	public void insertIntoComanda(Comanda newComanda) {
		ComandaDAO comandaD = new ComandaDAO();
		
		List<Comanda> existentOrders = this.findAllOrders();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Comanda ord: existentOrders) {
			number[ord.getIdComanda()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		newComanda.setIdComanda(freeId);
		
		
		comandaD.insertIntoT(newComanda);
	}
	
	public void updateComanda(Comanda newComanda,int id) {
		ComandaDAO comandaD = new ComandaDAO();
		
		List<Comanda> existentOrders = this.findAllOrders();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Comanda ord: existentOrders) {
			number[ord.getIdComanda()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		
		
		
		comandaD.updateT(newComanda,id);
	}
	
	public void deleteByIdComanda(int id) {
		ComandaDAO comandaD = new ComandaDAO();
		comandaD.deleteByIdT(id);
	}
}
