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
			
			//clb.insertIntoClient(new Client(6,"Stefan","Stanescu"));
			//clb.insertIntoClient(new Client(7,"Jake","Jake"));
			//clb.deleteByIdClient(7);
			
			ComandaBll comb = new ComandaBll();
//			comb.insertIntoComanda(new Comanda(1,1,1,100));
			
			//clb.insertIntoClient(new Client("Coltan","Gheorghe"));
			//clb.updateClient(new Client("Aron","Oana"), 0);
			//clb.insertIntoClient(new Client("Marcel","99"));\
			//clb.insertIntoClient(new Client("Cristian","Puscas"));
			//clb.insertIntoClient(new Client("Viorica","Dancila"));
			
			//clb.insertIntoClient(new Client("Bianca","Ioana"));
			//clb.insertIntoClient(new Client("Oancea","Raul"));
			//clb.deleteByIdClient(0);
			//clb.orderByIdClient();
			
		//	comb.insertIntoComanda(new Comanda(2,2,500));
			
			View.createAndShowGui();
	}
	
	
	
	

}
