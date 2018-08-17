import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
	
	private JTextField pol1TextField = new JTextField("");
	private JTextField pol2TextField = new JTextField("");
	private JTextField rezTextField = new JTextField("");
	private JTextField rez2TextField = new JTextField("");
	
	
	private JButton adunareBtn = new JButton("Adunare");
	private JButton scadereBtn = new JButton("Scadere");
	private JButton inmultireBtn = new JButton("Inmultire");
	private JButton impartireBtn = new JButton("Impartire");
	private JButton derivareBtn = new JButton("Derivare");
	private JButton integrareBtn = new JButton("Integrare");
	private JPanel panel = new JPanel();
	private JLabel labelPol1 = new JLabel("Introduceti primul polinom");
	private JLabel labelPol2 = new JLabel("Introduceti al doilea polinom");
	private JButton clearBtn = new JButton("Clear");
	
	
	public GUI() {
		super("Procesare polinoame");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        setResizable(false);
       
        panel.setLayout(new FlowLayout());
        
        pol1TextField.setPreferredSize(new Dimension(450,30));
        pol2TextField.setPreferredSize(new Dimension(450,30));
        rezTextField.setPreferredSize(new Dimension(450,30));
        rez2TextField.setPreferredSize(new Dimension(450,30));
        
        panel.add(labelPol1);
        panel.add(pol1TextField);
        panel.add(labelPol2);
        panel.add(pol2TextField);
        panel.add(adunareBtn);
        panel.add(scadereBtn);
        panel.add(inmultireBtn);
        panel.add(impartireBtn);
        panel.add(derivareBtn);
        panel.add(integrareBtn);
        panel.add(clearBtn);
        panel.add(rezTextField);
        panel.add(rez2TextField);
        
       
        
        this.add(panel);
        
        adunareBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());
        		Polinom pol2 = new Polinom(pol2TextField.getText());
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.adunare(pol1, pol2);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        scadereBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());
        		Polinom pol2 = new Polinom(pol2TextField.getText());
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.scadere(pol1, pol2);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        inmultireBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());
        		Polinom pol2 = new Polinom(pol2TextField.getText());
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.inmultire(pol1, pol2);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        impartireBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());
        		Polinom pol2 = new Polinom(pol2TextField.getText());
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.impartire(pol1, pol2);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        derivareBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());        		
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.derivare(pol1);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        integrareBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		Polinom pol1 = new Polinom(pol1TextField.getText());
        		
        		
        		Operatii op = new Operatii();
        		
        		Polinom rez = op.integrare(pol1);
        		
        		rezTextField.setText(rez.showPol(rez));
        	}
         });
        
        clearBtn.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		pol2TextField.setText("");
        		pol1TextField.setText("");
        		rez2TextField.setText("");
        		rezTextField.setText("");
        	}
         });
        
	}
}
