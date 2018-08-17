public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Polinom pol1 = new Polinom("6x^3");
		Polinom pol2 = new Polinom("2x");
		Operatii contr = new Operatii();*/

		/*
		//Polinom pol = new Polinom();
		//pol = contr.inmultire(pol1, pol2);
		for (Entry<Integer, Integer> entry  : pol1.hm.entrySet()) 
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		
		System.out.println("==============================");
		
		for (Entry<Integer, Integer> entry  : pol2.hm.entrySet()) 
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		
		System.out.println("==============================");
		
		Polinom pol = new Polinom();
		pol = contr.impartire(pol1, pol2);
		for (Entry<Integer, Integer> entry  : pol.hm.entrySet()) 
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		
		System.out.println(pol.showPol(pol));
		
		//System.out.println(pol1.gradMaxim());*/
		
		GUI gui = new GUI();
		gui.setVisible(true);
		
		/*Monom mon = pol1.getMonomMax();
		System.out.println(mon.getCoef() + "^ " +mon.getExp());*/
	}

}
