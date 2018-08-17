package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.Client;
import model.Comanda;
import model.Produs;
import model.Stoc;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBll;
import bll.ComandaBll;
import bll.ProdusBll;
import bll.StocBll;



public class View extends JPanel{

		private Reflection reflection;
	
		private JButton addBtn = new JButton("Add");
		private JButton updateBtn = new JButton("Update");
		private JButton deleteBtn = new JButton("Delete");
		private JButton seeAllBtn = new JButton("See all");
		private JButton findByIdBtn = new JButton("Find by id");
		private JButton buyBtn = new JButton("Buy");
		private JButton showOrderBtn = new JButton("Show orders");
		private static JButton makeOrderBtn = new JButton("Order");
		private static JButton refreshBtn = new JButton("Refresh");
		
		private JTextField idClientTxt = new JTextField("");
		private JTextField idProdusTxt = new JTextField("");
		private JTextField idComandaTxt = new JTextField("");
		private JTextField idStocTxt = new JTextField("");	
		private JTextField numeTxt = new JTextField("");
		private JTextField prenumeTxt = new JTextField("");
		private JTextField cantitateTxt = new JTextField("");
		private JTextField pretTxt = new JTextField("");
		private JTextField stocTxt = new JTextField("");
		private static JTextField cantitateOrderTxt = new JTextField("");
		
		private JLabel idClientLabel = new JLabel("Id client:");
		private JLabel idProdusLabel = new JLabel("Id produs:");
		private JLabel idComandaLabel = new JLabel("Id comanda:");
		private JLabel idStocLabel = new JLabel("Id stoc:");
		private JLabel numeLabel = new JLabel ("Nume:");
		private JLabel prenumeLabel = new JLabel("Prenume:");
		private JLabel cantitateLabel = new JLabel("Cantitate:");
		private JLabel pretLabel = new JLabel("Pret");

		private Client client = new Client();
		private Comanda comanda = new Comanda();
		private Produs produs = new Produs();
		private Stoc stoc = new Stoc();
		
		private ClientBll clientBll = new ClientBll();
		private ComandaBll comandaBll = new ComandaBll();
		private ProdusBll produsBll = new ProdusBll();
		private StocBll stocBll = new StocBll();
	
		private Object types[] = {client,comanda,produs,stoc};
		private JComboBox comboBox = new JComboBox(types);
	
	
	  	private static final int GAP = 5;
	    private static final String[] COL_NAMES = {};
	    private DefaultTableModel model = new DefaultTableModel(COL_NAMES, 0);
	    private JTable table= new JTable(model);
	    private JScrollPane paneTable = new JScrollPane();
	    
	    private static DefaultTableModel modelClient = new DefaultTableModel(COL_NAMES, 0);
	    private static JTable tableClient = new JTable(modelClient);
	
	    private static DefaultTableModel modelProduct = new DefaultTableModel(COL_NAMES,0);
	    private static JTable tableProduct = new JTable(modelProduct);

	    
	
	
	
	
	
	public View() {
		 JPanel buttonPanel = new JPanel(new GridLayout(3, 0, GAP, GAP));
         JPanel fieldPanel = new JPanel (new GridLayout(0,1));     
        
    
         buttonPanel.add(seeAllBtn);
         buttonPanel.add(buyBtn);
	     buttonPanel.add(addBtn);
	     buttonPanel.add(updateBtn);	        
	     buttonPanel.add(findByIdBtn);
	     buttonPanel.add(deleteBtn);	
	     buttonPanel.add(showOrderBtn);
	   
	     
	     idClientTxt.setPreferredSize(new Dimension(400,25));
	     idProdusTxt.setPreferredSize(new Dimension(400,25));
	     idComandaTxt.setPreferredSize(new Dimension(400,25));
	     idStocTxt.setPreferredSize(new Dimension(400,25));
	     numeTxt.setPreferredSize(new Dimension(400,25));
	     prenumeTxt.setPreferredSize(new Dimension(400,25));
	     cantitateTxt.setPreferredSize(new Dimension(400,25));
	     pretTxt.setPreferredSize(new Dimension(400,25));
	     stocTxt.setPreferredSize(new Dimension(400,25));
	     
	     fieldPanel.add(comboBox);
	     
	     fieldPanel.add(idClientLabel);
	     fieldPanel.add(idClientTxt);
	     fieldPanel.add(idProdusLabel);
	     fieldPanel.add(idProdusTxt);
	     fieldPanel.add(idComandaLabel);
	     fieldPanel.add(idComandaTxt);
	     fieldPanel.add(idStocLabel);
	     fieldPanel.add(stocTxt);
	     fieldPanel.add(numeLabel);
	     fieldPanel.add(numeTxt);
	     fieldPanel.add(prenumeLabel);
	     fieldPanel.add(prenumeTxt);
	     fieldPanel.add(cantitateLabel);	     
	     fieldPanel.add(cantitateTxt);
	     fieldPanel.add(pretLabel);
	     fieldPanel.add(pretTxt);
        
        
       

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buttonPanel);
        
        JPanel moreBottomPanel = new JPanel();
        moreBottomPanel.add(fieldPanel);


        
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BorderLayout(GAP, GAP));
        add(new JScrollPane(table), BorderLayout.PAGE_START);
        add(bottomPanel, BorderLayout.CENTER);
        add(moreBottomPanel, BorderLayout.PAGE_END);	
        
        showOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				createAndShowOrderFrame();
				
				
			}
        	
        });
        
        addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object myObj = comboBox.getSelectedItem();
				
				if (myObj instanceof Client) {
					Client newClient = new Client(numeTxt.getText(),prenumeTxt.getText());
					clientBll.insertIntoClient(newClient);
				}
				if (myObj instanceof Produs) {
					Produs newProdus = new Produs(numeTxt.getText(),Integer.parseInt(pretTxt.getText()));
					produsBll.insertIntoProdus(newProdus);
					
				}
				if (myObj instanceof Comanda) {
					Comanda newComanda = new Comanda(Integer.parseInt(idProdusTxt.getText()),Integer.parseInt(idClientTxt.getText()),Integer.parseInt(cantitateTxt.getText()));
					comandaBll.insertIntoComanda(newComanda);
				}
				if (myObj instanceof Stoc) {
					Stoc newStoc = new Stoc(Integer.parseInt(idProdusTxt.getText()),Integer.parseInt(cantitateTxt.getText()));
					stocBll.insertIntoStoc(newStoc);
				}
				
			}
        	
        });
        
        updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object myObj = comboBox.getSelectedItem();
				
				if (myObj instanceof Client) {
					int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					Client newClient = new Client(numeTxt.getText(),prenumeTxt.getText());
					clientBll.updateClient(newClient, id);
				}
				if (myObj instanceof Produs) {
					int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					Produs newProdus = new Produs(numeTxt.getText(),Integer.parseInt(pretTxt.getText()));
					produsBll.updateProdus(newProdus, id);
				}
				if (myObj instanceof Comanda) {
					int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					Comanda newComanda = new Comanda(Integer.parseInt(idProdusTxt.getText()),Integer.parseInt(idClientTxt.getText()),Integer.parseInt(cantitateTxt.getText()));
					comandaBll.updateComanda(newComanda, id);
				}
				if (myObj instanceof Stoc) {
					int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					Stoc newStoc = new Stoc(Integer.parseInt(idProdusTxt.getText()),Integer.parseInt(cantitateTxt.getText()));
					stocBll.updateStoc(newStoc, id);
				}
				
				
			}
        	
        });
        
        deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					Object myObj = comboBox.getSelectedItem();
					
					if (myObj instanceof Client) {
						int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
						clientBll.deleteByIdClient(id);
					}
					if (myObj instanceof Produs) {
						int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
						produsBll.deleteByIdProdus(id);
					}
					if (myObj instanceof Comanda) {
						int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
						comandaBll.deleteByIdComanda(id);
					}
					if (myObj instanceof Stoc) {
						int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
						stocBll.deleteByIdStoc(id);
					}
					
				
			}
        	
        });
        
        seeAllBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					reflection = new Reflection(comboBox.getSelectedItem());
							
					Object  myObj = comboBox.getSelectedItem();
					
					model.setColumnCount(0);
					model.setRowCount(0);
					
					List<String> list = new ArrayList<String>();						
					
					
					list = reflection.getListFields();
					
					    
					for (String str : list) {		
						 model.addColumn(str);
					}				
					
					if (myObj instanceof Client) {
						for (Client cl: reflection.getAllClientsValues()) {
							model.addRow(new Object[] {cl.getIdClient(),cl.getNume(),cl.getPrenume()});
						}						
					}
					if (myObj instanceof Produs) {
						for (Produs pd: reflection.getAllProductsValues()) {
							model.addRow(new Object[] {pd.getIdProdus(),pd.getNume(),pd.getPret()});
						}
					}
					if (myObj instanceof Comanda) {
						for (Comanda c : reflection.getAllOrdersValues()) {
							model.addRow(new Object[] {c.getIdComanda(),c.getIdProdus(),c.getIdClient(),c.getCantitate()});
						}
					}
					if(myObj instanceof Stoc) {
						for(Stoc stc: reflection.getAllStockValues()) {
							model.addRow(new Object[] {stc.getIdStoc(),stc.getIdProdus(),stc.getCantitate()});
						}
					}
				
			}
        	
        });
        
        
	}
	
	
	public static <T> List<T> createList() {
	    return new ArrayList<T>();
	}
	
	
	
	
	
	public static void createAndShowOrderFrame() {
		ClientBll clb = new ClientBll();
		ProdusBll prb = new ProdusBll();
		StocBll stb = new StocBll();
		ComandaBll cmb = new ComandaBll();
		
		View mainOrderPanel = new View();
		
		JPanel buttonPanelOrder = new JPanel(new GridLayout(3, 0, GAP, GAP));
		cantitateOrderTxt.setPreferredSize(new Dimension(400,25));

		buttonPanelOrder.add(makeOrderBtn);
		buttonPanelOrder.add(refreshBtn);
		buttonPanelOrder.add(cantitateOrderTxt);
		
		makeOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idClient = Integer.parseInt(tableClient.getModel().getValueAt(tableClient.getSelectedRow(),0).toString());
				int idProdus = Integer.parseInt(tableProduct.getModel().getValueAt(tableProduct.getSelectedRow(),0).toString());
				int pretProdus = Integer.parseInt(tableProduct.getModel().getValueAt(tableProduct.getSelectedRow(),2).toString());
				String numeProdus = (String) tableProduct.getModel().getValueAt(tableProduct.getSelectedRow(),1);
				String numeClient = (String) tableClient.getModel().getValueAt(tableClient.getSelectedRow(),1);
				
				List<Stoc> stocList = stb.findAllStock();
				for (Stoc s: stocList) {
					if (s.getIdProdus() == idProdus) {
						if (s.getCantitate() < Integer.parseInt(cantitateOrderTxt.getText())) {
							JOptionPane.showMessageDialog(null, "Stocul nu este suficient.", "Eroare", JOptionPane.INFORMATION_MESSAGE);							
						}
						else {
							s.setCantitate(s.getCantitate()-Integer.parseInt(cantitateOrderTxt.getText()));
							stb.updateStoc(s,s.getIdStoc());
							System.out.println("Clientul " + idClient + " a efectuat o comanda pentru produsul " + idProdus);
							
							Comanda comanda = new Comanda(idProdus,idClient,Integer.parseInt(cantitateOrderTxt.getText()));
							cmb.insertIntoComanda(comanda);
							
							StringBuilder sb = new StringBuilder();
							sb.append("\nClientul " + idClient +" "+ numeClient + " a efectuat o comanda pentru ");
							sb.append(cantitateOrderTxt.getText());
							sb.append(" de produse de tipul ");
							sb.append(numeProdus);
							sb.append(" in valoare totala de ");
							sb.append(Integer.parseInt(cantitateOrderTxt.getText()) * pretProdus);
							
							
							String str = sb.toString();
							
							try
							{
							    String filename= "orders.txt";
							    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
							    fw.write(System.getProperty("line.separator"));
							    fw.write(str);//appends the string to the file
							    fw.close();
							}
							catch(IOException ioe)
							{
							    System.err.println("IOException: " + ioe.getMessage());
							}
						}
					}
				}
				
			}
			
		});
		
		refreshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Reflection rf1 = new Reflection(new Client());
				Reflection rf2 = new Reflection(new Produs());
				
				List<String> lc = rf1.getListFields();
				List<String> lp = rf2.getListFields();
				
				
				modelClient.setRowCount(0);
				modelProduct.setRowCount(0);
				modelClient.setColumnCount(0);
				modelProduct.setColumnCount(0);
				
				for (String s: lc)
					modelClient.addColumn(s);
				for(String s: lp)
					modelProduct.addColumn(s);
				
				List<Client> c = clb.findAllClients();
				List<Produs> p = prb.findAllProducts();
				
				for (Client x : c) {
					modelClient.addRow(new Object[] {x.getIdClient(),x.getNume(),x.getPrenume()});
				}
				for(Produs x : p) {
					modelProduct.addRow(new Object[] {x.getIdProdus(),x.getNume(),x.getPret()});
				}
			}
			
		});
		
		mainOrderPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		mainOrderPanel.setLayout(new BorderLayout(GAP, GAP));
		mainOrderPanel.add(new JScrollPane(tableClient), BorderLayout.PAGE_START);
		mainOrderPanel.add(new JScrollPane(tableProduct), BorderLayout.CENTER);
		mainOrderPanel.add(buttonPanelOrder,BorderLayout.PAGE_END);
		
		
        

        JFrame frame = new JFrame("OrderFrame");
        //frame.setPreferredSize(new Dimension(800,800));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        frame.getContentPane().add(mainOrderPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
	
	
	public static void createAndShowGui() {
		
		
		View mainPanel = new View();
        
        

        JFrame frame = new JFrame("Warehouse");
        //frame.setPreferredSize(new Dimension(800,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        

//        JFrame frame = new JFrame("Warehouse");
//        frame.setPreferredSize(new Dimension(1000,1000));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(mainPanel);
//        frame.pack();
//        frame.setLocationByPlatform(true);
//        frame.setVisible(true);
    }
}
