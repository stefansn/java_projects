package start;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import bll.ClientBll;
import bll.ComandaBll;
import bll.ProdusBll;
import connection.ConnectionFactory;
import model.Client;
import model.Comanda;
import model.Produs;
import presentation.Controller;
import presentation.View;

public class Start {
	
	public static void main(String[] args) {	
			ClientBll clb = new ClientBll();
			
			
			
			ComandaBll comb = new ComandaBll();

			
			View.createAndShowGui();
	}
	
	
	
	

}
