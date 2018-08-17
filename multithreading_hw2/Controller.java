import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Controller {
	
	
	
	
	public static void main(String[] args) {
		
		
		View gui = new View();		
		
		gui.startSimBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				int a = Integer.parseInt(gui.fieldTimeLimit.getText());
				int b = Integer.parseInt(gui.fieldMaxProc.getText());
				int c = Integer.parseInt(gui.fieldMinProc.getText());
				int d = Integer.parseInt(gui.fieldNrServers.getText());
				int e = Integer.parseInt(gui.fieldNrTasks.getText());
				SelectionPolicy selectionPolicy = (SelectionPolicy) gui.strategyCombo.getSelectedItem();
				
				gui.n = d;
				
				gui.textFieldArray = new JTextField[gui.n];
				for(int i=0;i<gui.n;i++) {					
					gui.textFieldArray[i] = new JTextField(40);					
					gui.textFieldArray[i].setEditable(false);
					gui.textFieldContainer.add(gui.textFieldArray[i]);
				}
				gui.textFieldScrollPane.validate();
				
			
				
				SimulationManager gen = new SimulationManager(a,b,c,d,e,selectionPolicy,gui);
				Thread t = new Thread(gen);				
				t.start();
				
			}
			
		});
		
		
	}
}
	

	

	
