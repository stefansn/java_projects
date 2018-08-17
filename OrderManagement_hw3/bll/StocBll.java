package bll;

import java.util.List;

import DAO.AbstractDAO;
import DAO.StocDAO;
import model.Produs;
import model.Stoc;
import model.Stoc;

public class StocBll extends AbstractDAO<Stoc>{
	public List<Stoc> findStocById (int id) {
		StocDAO stocD = new StocDAO();
		List<Stoc> list = stocD.findById(id);
		
		return list;
	}
	
	public List<Stoc> findAllStock () {
		StocDAO stocD = new StocDAO();
		
		List<Stoc> list = stocD.findAll();
		
		return list;
	}
	
	public void insertIntoStoc(Stoc newStoc) {
		StocDAO stocD = new StocDAO();
		
		List<Stoc> existentStocks = this.findAllStock();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Stoc st: existentStocks) {
			number[st.getIdStoc()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
		
		newStoc.setIdStoc(freeId);
		
		
		stocD.insertIntoT(newStoc);
	}
	
	public void updateStoc(Stoc newStoc,int id) {
		StocDAO stocD = new StocDAO();
		
		List<Stoc> existentStocks = this.findAllStock();
		
		Integer[] number = new Integer[10000];
		int freeId = 0;
		
		for (int i = 0 ; i<10000; i++) {
			number[i] = 0;
		}
		
		for (Stoc st: existentStocks) {
			number[st.getIdStoc()] = 1;
		}
		
		for (int i = 0 ;i<10000;i++) {
			if (number[i] == 0) {
				freeId = i;
				break;
			}
		}
	
		
		
		stocD.updateT(newStoc,id);
	}
	
	public void deleteByIdStoc(int id) {
		StocDAO stocD = new StocDAO();
		stocD.deleteByIdT(id);
	}
}	
