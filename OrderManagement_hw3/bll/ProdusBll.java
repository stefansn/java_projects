package bll;

import java.util.List;

import DAO.AbstractDAO;
import DAO.ProdusDAO;
import model.Comanda;
import model.Produs;
import model.Produs;

public class ProdusBll extends AbstractDAO<Produs>{
	public List<Produs> findProdusById (int id) {
		ProdusDAO prodD = new ProdusDAO();
		List<Produs> list = prodD.findById(id);
		
		return list;
	}
	
	public List<Produs> findAllProducts () {
		ProdusDAO prodD = new ProdusDAO();
		
		List<Produs> list = prodD.findAll();
		
		return list;
	}
	
	public void insertIntoProdus(Produs newProdus) {
		ProdusDAO prodD = new ProdusDAO();
		
		List<Produs> existentProducts = this.findAllProducts();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Produs prd: existentProducts) {
			number[prd.getIdProdus()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		newProdus.setIdProdus(freeId);
		
		
		
		prodD.insertIntoT(newProdus);
	}
	
	public void updateProdus(Produs newProdus,int id) {
		ProdusDAO prodD = new ProdusDAO();
		
		List<Produs> existentProducts = this.findAllProducts();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Produs prd: existentProducts) {
			number[prd.getIdProdus()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
	
		
		
		prodD.updateT(newProdus,id);
	}
	
	public void deleteByIdProdus(int id) {
		ProdusDAO prodD = new ProdusDAO();
		prodD.deleteByIdT(id);
	}
}
