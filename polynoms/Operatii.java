import java.util.Map.Entry;

public class Operatii {
	public Operatii() {
		
	}
	
	public Polinom adunare (Polinom pol1, Polinom pol2) {		
		
		for (Entry<Integer, Integer> entry  : pol2.hm.entrySet()) {
			if (pol1.hm.containsKey(entry.getKey())) {
				int aux = entry.getValue();
				aux+=pol1.hm.get(entry.getKey());
				pol1.hm.put(entry.getKey(), aux);
				if (aux==0) {
					pol1.hm.remove(entry.getKey());
				}
			}
			else
			{
				pol1.hm.put(entry.getKey(), entry.getValue());
			}
		}	
		
		return pol1;
	}
	
	public Polinom scadere (Polinom pol1, Polinom pol2) {		
		Polinom rez = new Polinom();
		for (Entry<Integer, Integer> entry  : pol2.hm.entrySet()) {
			pol2.hm.put(entry.getKey(), entry.getValue()*(-1));
		}
		Operatii op = new Operatii();
		rez=op.adunare(pol1, pol2);
		
			
		return rez;
	}
	
	public Polinom inmultire (Polinom pol1,Polinom pol2) {
		
		Polinom rez = new Polinom();
		int coef=0;
		int exp=0;
		for (Entry<Integer, Integer> entry_pol1  : pol1.hm.entrySet()) {
			for (Entry<Integer, Integer> entry_pol2  : pol2.hm.entrySet()) {
				exp = entry_pol1.getKey()+entry_pol2.getKey();
				coef =entry_pol1.getValue()*entry_pol2.getValue();
				
				if (rez.hm.containsKey(exp)==true) {
					int aux = rez.hm.get(exp);
					aux+=coef;;
					
					rez.hm.put(exp, aux);
					if (aux==0)
						rez.hm.remove(exp);
				}
				else {
					rez.hm.put(exp, coef);
				}
				
				
			}
		}
		
		return rez;
	}
	
	public Polinom derivare (Polinom pol) {
		Polinom rez = new Polinom();
		int coef;
		int exp;
		for (Entry<Integer, Integer> entry_pol  : pol.hm.entrySet()) {
			coef = entry_pol.getValue() * entry_pol.getKey();
			exp = entry_pol.getKey()-1;
			
			if (exp>=0)
				rez.hm.put(exp, coef);		
			
		}
		
		return rez;	
	}
	
	public Polinom integrare(Polinom pol) {
		Polinom rez = new Polinom();
		int coef;
		int exp;
		for (Entry<Integer, Integer> entry_pol  : pol.hm.entrySet()) {
			coef=entry_pol.getValue()/(entry_pol.getKey()+1);
			exp=entry_pol.getKey()+1;
			if (exp>=0)
				rez.hm.put(exp, coef);
		}
		return rez;
	}
	
	public Polinom impartire(Polinom d,Polinom i) {
		Polinom c = new Polinom("");
		Polinom r = new Polinom("");
		Operatii op = new Operatii();
		Polinom aux;
		while (i.gradMaxim()<d.gradMaxim()) {
			Monom m1 = d.getMonomMax();
			Monom m2 = i.getMonomMax();
			Monom m3 = m1.divideMonom(m2);
			
			c.hm.put(m3.getExp(), m3.getCoef());
			
			aux = new Polinom();
			aux.hm.put(m3.getExp(), m3.getCoef());
			
			Polinom pow = new Polinom();
			pow = op.inmultire(i, aux);
			
			d=op.scadere(d, pow);
		}			
		
		return c;
	}
	
	
	
	
}
