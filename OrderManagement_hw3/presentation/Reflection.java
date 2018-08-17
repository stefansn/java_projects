package presentation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import bll.ClientBll;
import bll.ComandaBll;
import bll.ProdusBll;
import bll.StocBll;
import model.Client;
import model.Comanda;
import model.Produs;
import model.Stoc;

public class Reflection {
	private List<String> listValues;
	private List<String> listFields;
	
	public Reflection() {
		
	}
	
	public Reflection(Object object) {
		
		this.listFields = new ArrayList<String>();
		this.listValues = new ArrayList<>();
	
		
		
		for (Field field: object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(object);
				this.listFields.add(field.getName());			
				
				
				//System.out.println(field.getName() + " = " + value);
			}catch(IllegalArgumentException | IllegalAccessException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Client> getAllClientsValues() {
		List<Client> list = new ArrayList<>();
		ClientBll p = new ClientBll();
		list = p.findAllClients();
		
		return list;
	}
	public List<Produs> getAllProductsValues() {
		List<Produs> list = new ArrayList<>();
		ProdusBll p = new ProdusBll();
		list = p.findAllProducts();
		
		return list;
	}
	public List<Comanda> getAllOrdersValues() {
		List<Comanda> list = new ArrayList<>();
		ComandaBll p = new ComandaBll();
		list = p.findAllOrders();
		
		return list;
	}
	public List<Stoc> getAllStockValues() {
		List<Stoc> list = new ArrayList<>();
		StocBll p = new StocBll();
		list = p.findAllStock();
		
		return list;
	}

	
	public List<String> getListValues() {
		return this.listValues;
	}
	
	public List<String> getListFields() {
		return this.listFields;
	}
	
	

}
