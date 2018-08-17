import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;

public class View {
	private int peakHourTotal = 0;
	
    private JComponent container = null;
   
    public JTextField tfCurrentTime = new JTextField("Current time");
    public JTextField tfAverageTime = new JTextField("Average time");
    public JTextField tfPeakHour = new JTextField("Peak hour");
    
    private JLabel labelCurrentTime = new JLabel("Current time");
    private JLabel labelAverageTime = new JLabel("Average time");
    private JLabel labelPeakHour = new JLabel("Peak hour");
    
    private static final int LENGTH = 3;
    
    public JTextField fieldNrServers = new JTextField(LENGTH);
	public JTextField fieldNrTasks = new JTextField(LENGTH);
	public JTextField fieldMaxProc = new JTextField(LENGTH);
	public JTextField fieldMinProc = new JTextField(LENGTH);
	public JTextField fieldTimeLimit = new JTextField(LENGTH);
	//private String[] strategies = {"Queue strategy", "Time strategy"};	
	public SelectionPolicy selectionPolicy;
	public JComboBox<SelectionPolicy> strategyCombo = new JComboBox<SelectionPolicy>(SelectionPolicy.values());	
	public JButton startSimBtn = new JButton("Start simulation");
	public int n;
	public JTextField[] textFieldArray;
	public JTextArea textArea = new JTextArea("",30,50);
	public JScrollPane textFieldScrollPane;
	public JFrame f;
	public JComponent[] components = { 
			new JLabel("Number of servers:"), fieldNrServers,
			new JLabel("Number of tasks:"), fieldNrTasks,
			new JLabel("Max processing time:"), fieldMaxProc,
			new JLabel("Min processing time:"), fieldMinProc,
			new JLabel("Time limit:"), fieldTimeLimit,
			new JLabel("Choose strategy:"), strategyCombo,
			startSimBtn
	};
    
    public View() {
        startView();
    }
    
    public JPanel textFieldContainer;
    
    public void startView() {   	
    	fieldNrServers.setText("3");
    	fieldNrTasks.setText("10");
    	fieldMaxProc.setText("10");
    	fieldMinProc.setText("2");
    	fieldTimeLimit.setText("40");
    	
       
        container = new JPanel(new BorderLayout(4,4));
        container.setBorder(new EmptyBorder(4,20,4,20));

        
        textFieldContainer = new JPanel(new GridLayout(0,1,5,5));
     
        //textFieldArray = new JTextField[n];
        
       /* for (int i=0; i<n; i++) {
            textFieldArray[i] = new JTextField("Server " + i, 40);
            textFieldContainer.add(textFieldArray[i]);           
            
        }       
      */
        
        textFieldScrollPane = new JScrollPane(textFieldContainer);
        Dimension d = textFieldContainer.getPreferredSize();
        textFieldScrollPane.getViewport().setPreferredSize(new Dimension(d.width, 200));
        
        
        container.add(textFieldScrollPane, BorderLayout.CENTER);
        
        JPanel textAreaContainer = new JPanel(new FlowLayout());
        textArea.setEditable(false);
        textAreaContainer.setBorder(new EmptyBorder(10, 50, 10, 50));
        textAreaContainer.add(new JScrollPane(textArea));
        container.add(textAreaContainer, BorderLayout.PAGE_START);

        
        
        JPanel lowerContainer = new JPanel(new BorderLayout());
        lowerContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        container.add(lowerContainer, BorderLayout.PAGE_END);
        lowerContainer.setLayout(new FlowLayout());
        lowerContainer.add(labelCurrentTime);
        lowerContainer.add(tfCurrentTime);
        lowerContainer.add(labelAverageTime);
        lowerContainer.add(tfAverageTime);
        lowerContainer.add(labelPeakHour);
        lowerContainer.add(tfPeakHour);
        
        JPanel simPanel = new JPanel(new GridLayout(0,1,5,5));
        for (JComponent comp: components) {
        	simPanel.add(comp);
        }
        textAreaContainer.add(simPanel);
       //container.add(simPanel,BorderLayout.PAGE_START);
        
        
     
        
        f = new JFrame("Simulare");
    
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);

        f.setContentPane(getContainer());
        f.pack();
        f.setMinimumSize(f.getSize());
        //f.setResizable(false);
        f.setVisible(true);
    }

    public JComponent getContainer() {
        return container;
    }

	public int getPeakHourTotal() {
		return peakHourTotal;
	}

	public void setPeakHourTotal(int peakHourTotal) {
		this.peakHourTotal = peakHourTotal;
	}
    
    

    /*
    public static void main(String[] args) {
       
                View view = new View();

                JFrame f = new JFrame("Simulare");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.setContentPane(view.getContainer());
                f.pack();
                f.setMinimumSize(f.getSize());

                f.setVisible(true);
      
    }
    */
    
}