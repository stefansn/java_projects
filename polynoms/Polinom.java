import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {	
	public HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	
	public Polinom() {
		
	}
	
	public Polinom(String pol) {
		Pattern p = Pattern.compile("[+-]?[^-+]+");
		Matcher m = p.matcher(pol);
		
		
		while (m.find()) {
			String s = m.group();		
			//System.out.println(s);
			
			Monom mon = new Monom(s);	
					
			
			if (hm.containsKey(mon.getExp())==true) {
				int aux = hm.get(mon.getExp());
				aux+=mon.getCoef();
				
				hm.put(mon.getExp(), aux);
				if (aux==0)
					hm.remove(mon.getExp());
			}
			else {
				hm.put(mon.getExp(), mon.getCoef());
			}
		}
	}
	
	public String showPol (Polinom pol) {
		Integer v[] = new Integer[hm.size()];
		hm.keySet().toArray(v);
		String strPol=new String("");
		for (int i=hm.size()-1;i>=0;i--) {
			if (hm.get(v[i]).toString().charAt(0)!='-')
				strPol+="+"+hm.get(v[i])+"x^"+v[i];
			else
				strPol+=hm.get(v[i])+"x^"+v[i];
		}
		return strPol;
	}
	
	public int gradMaxim() {
		int max=0;
		for (Entry<Integer, Integer> entry_pol  : this.hm.entrySet()) {
			max=entry_pol.getKey();
		}
		return max;
	}
	
	public Monom getMonomMax() {
		Monom mon = new Monom();
		int exp=0;
		int coef=0;
		for (Entry<Integer, Integer> entry_pol  : this.hm.entrySet()) {
			coef = entry_pol.getValue();
			exp = entry_pol.getKey();
		}
		mon.setCoef(coef);
		mon.setExp(exp);
		return mon;
		
	}
	
	
	
}
