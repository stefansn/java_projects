package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import bll.ClientBll;
import bll.ComandaBll;
import bll.ProdusBll;
import bll.StocBll;
import model.Client;

public class Controller {
	//private GUI view;
	
	private ClientBll clientBll;
	private ComandaBll comandaBll;
	private ProdusBll produsBll;
	private StocBll stocBll;	
	
	
	
	public Controller() {
		
		
		clientBll = new ClientBll();
		comandaBll = new ComandaBll();
		produsBll = new ProdusBll();
		stocBll = new StocBll();
		
		clientBll.deleteByIdClient(5);
		
		
		
	}
	
	
}
