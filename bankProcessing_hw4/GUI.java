package homework4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JPanel {
    private static final int GAP = 5;
    private static final String[] COL_NAMES = {};
    private DefaultTableModel model = new DefaultTableModel(COL_NAMES, 0);
    private JTable table= new JTable(model);
    private JScrollPane paneTable = new JScrollPane();
    
    private JButton btnAddAccount = new JButton("Add account");
	private JButton btnRemoveAccount = new JButton("Remove account");
	private JButton btnAddPerson = new JButton("Add person");
	private JButton btnRemovePerson = new JButton("Remove person");
	private JButton btnDeposit = new JButton("Deposit");
	private JButton btnWithdraw = new JButton("Withdraw");
	private JButton btnClearEverything = new JButton("Clear");
	private JButton btnShowPersons = new JButton("Show persons");
	private JButton btnShowAccounts = new JButton("Show accounts");
	
	private JLabel labelIdPerson = new JLabel("Person's id:");
	private JTextField idPersonField = new JTextField("");
	private JLabel labelNumePerson = new JLabel("Person's name:");
	private JTextField numePersonField = new JTextField("");
	private JLabel labelTypeAccount = new JLabel("Account type:");
	private JTextField accountTypeField = new JTextField("");
	private JLabel labelSum = new JLabel("Sum:");
	private JTextField sumField = new JTextField("");
	private JLabel updateObserverLabel = new JLabel("Update message:");
	private JLabel accountIdLabel = new JLabel("Account id:");
	private JTextField accountIdField = new JTextField("");
	private JTextField updateObserverField = new JTextField("");
	
	

    public GUI(Bank bankTaken) {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 0, GAP, GAP));
        JPanel fieldPanel = new JPanel (new GridLayout(0,1));     
        
        Bank bank = bankTaken;
        
        
        buttonPanel.add(btnShowPersons);
        buttonPanel.add(btnShowAccounts);
        buttonPanel.add(btnAddPerson);
        buttonPanel.add(btnAddAccount);
        buttonPanel.add(btnRemoveAccount);       
        buttonPanel.add(btnRemovePerson);
        buttonPanel.add(btnDeposit);
        buttonPanel.add(btnWithdraw);
        buttonPanel.add(btnClearEverything);
        
        idPersonField.setPreferredSize(new Dimension(400,25));        
        numePersonField.setPreferredSize(new Dimension(400,25));
        accountTypeField.setPreferredSize(new Dimension(400,25));
        sumField.setPreferredSize(new Dimension(400,25));      
        accountIdField.setPreferredSize(new Dimension(400,25));
       
        updateObserverField.setPreferredSize(new Dimension(200,10));
        updateObserverField.setEditable(false);
        String comboBoxAccounts[] = {"Saving account","Spending account"};
        JComboBox typeAccountsCombo = new JComboBox(comboBoxAccounts);
        
        fieldPanel.add(labelIdPerson);
        fieldPanel.add(idPersonField);
        fieldPanel.add(labelNumePerson);
        fieldPanel.add(numePersonField);
        fieldPanel.add(labelTypeAccount);
        fieldPanel.add(typeAccountsCombo);
        fieldPanel.add(labelSum);
        fieldPanel.add(sumField);
        fieldPanel.add(accountIdLabel);
        fieldPanel.add(accountIdField);
        fieldPanel.add(updateObserverLabel);
        fieldPanel.add(updateObserverField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(buttonPanel);
        
        JPanel moreBottomPanel = new JPanel();
        moreBottomPanel.add(fieldPanel);


        
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BorderLayout(GAP, GAP));
        add(new JScrollPane(table), BorderLayout.PAGE_START);
        add(bottomPanel, BorderLayout.CENTER);
        add(moreBottomPanel, BorderLayout.PAGE_END);
        
        btnShowPersons.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				model.setColumnCount(0);
				model.setRowCount(0);				
				
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				model.addColumn("Id");
				model.addColumn("Nume");
				model.addColumn("Saving accounts");
				model.addColumn("Spending accounts");
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();
					ArrayList<Account> listAcc = entry.getValue();
					
					int idProprietar = p.getIdPerson();
					String nume = p.getNume();
					int count_saveAcc = 0;
					int count_spendAcc =0;
					
					
					
					for (Account ac: listAcc) {
						if (ac instanceof SpendingAccount) {
							count_spendAcc++;
						}
						else
							count_saveAcc++;					
					}
					model.addRow(new Object[] {Integer.toString(idProprietar),nume,Integer.toString(count_saveAcc),Integer.toString(count_spendAcc)});
					
				}
			

			}
        	
        });
        
        btnShowAccounts.addActionListener(new ActionListener() {

   			@Override
   			public void actionPerformed(ActionEvent arg0) {
   				// TODO Auto-generated method stub
   				model.setColumnCount(0);
				model.setRowCount(0);	
				
				
   				HashMap<Person,ArrayList<Account>> detailsAccounts = bank.getHm();
   				model.addColumn("Nume");
				model.addColumn("Account id");
				model.addColumn("Account type");
				model.addColumn("Balance");
   				
   				for (Map.Entry<Person, ArrayList<Account>> entry: detailsAccounts.entrySet()) {
   					Person p = entry.getKey();
   					ArrayList<Account> listAcc = entry.getValue();
   					
   					for (Account ac : listAcc) {
   						
   							ac.addObserver(p);
   							int accountId = ac.getId();
   							String accountType;
   							
   							if (ac instanceof SavingAccount) {
   								accountType = "Saving account";
   							}
   							else {
   								accountType = "Spending account";
   							}
   							
   							double balance = ac.getBalance();
   							String clientName = p.getNume();
   							
   							model.addRow(new Object[] {clientName,Integer.toString(accountId),accountType,Double.toString(balance)});
   										
   					}			
   					
   				}
   				
   			}
           	
           });        
      
       
        btnAddPerson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Person p = new Person(numePersonField.getText());
				bank.addPerson(p);
				
			}
        	
        });  
       
        btnAddAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Account acc;
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				String name = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				int sum = Integer.parseInt(sumField.getText());
				
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();				
				
					
					if (p.getIdPerson() == id && p.getNume().equals(name)) {
						if (typeAccountsCombo.getSelectedItem().toString().equals("Saving account")) {
							acc = new SavingAccount(sum);
							bank.addAccount(p, acc);
						}
						else 
							if (typeAccountsCombo.getSelectedItem().toString().equals("Spending account")) {
								acc = new SpendingAccount(sum);
								acc.addObserver(p);
								bank.addAccount(p, acc);
							}
					}
					
				}
				
			}
        	
        });
        	
        btnDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				String nume = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().toString();
				int sum = Integer.parseInt(sumField.getText());
				
				Account acc;
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();
					ArrayList<Account> listAcc = entry.getValue();
				
					
					if (p.getNume().equals(nume)) {
						for (Account ac : listAcc) {
							if (ac.getId() == id ) {
								ac.deposit(sum);
								updateObserverField.setText(p.getUpdateMessage());
								bank.writeAccount();
								
							}
						}
					}
					
				}
				
				
			}
        	
        });
        
        
        btnWithdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				String nume = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().toString();
				int sum = Integer.parseInt(sumField.getText());
				
				Account acc;
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();
					ArrayList<Account> listAcc = entry.getValue();
				
					
					if (p.getNume().equals(nume)) {
						for (Account ac : listAcc) {
							if (ac.getId() == id ) {
								ac.withdraw(sum);
								updateObserverField.setText(p.getUpdateMessage());
								bank.writeAccount();
								
							}
						}
					}
					
				}
				
				
			}
        	
        });
        
        btnRemovePerson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				String nume = table.getModel().getValueAt(table.getSelectedRow(), 1).toString().toString();
				
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();
					ArrayList<Account> listAcc = entry.getValue();
				
					if (p.getIdPerson() == id && p.getNume().equals(nume)) {
						bank.removePerson(p);
						bank.writeAccount();
						break;
					}
					
				}
				
			}
        	
        });
        
        btnRemoveAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
				String nume = table.getModel().getValueAt(table.getSelectedRow(), 0).toString().toString();
				
				HashMap<Person,ArrayList<Account>> bankDetails = bank.getHm();
				
				for (Map.Entry<Person, ArrayList<Account>> entry: bankDetails.entrySet()) {
					Person p = entry.getKey();
					ArrayList<Account> listAcc = entry.getValue();
				
					for (Account acc : listAcc) {
						if (acc.getId() == id && p.getNume().equals(nume)) {
							bank.removeAccount(p,acc);
							break;
							//bank.writeAccount();
						}
					}
					
				}
			}
        	
        });
        
        btnClearEverything.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bank.removeEverything();
			}
        	
        });
    
    }
    
    	

    public static void createAndShowGui(Bank bank) {
        GUI mainPanel = new GUI(bank);
        
        

        JFrame frame = new JFrame("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {            	
//                createAndShowGui();
//            }
//        });
//    }
}