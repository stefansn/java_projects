import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {
	private int coef;
	private int exp;
	private double doubleCoef;
	
	public Monom() {
		
	}
	
	public Monom(int coef,int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	public Monom(double doubleCoef,int exp) {
		this.doubleCoef = doubleCoef;
		this.exp = exp;
	}
	
	public Monom(String strMon) {
		parseMonom(strMon);		
	}
	
	public void parseMonom(String mon) {
		if (mon.contentEquals("-x")) {
			this.coef=-1;
			this.exp=1;		
		}
		else if (mon.contentEquals("x") || mon.contentEquals("+x")) {
			this.coef=1;
			this.exp=1;
		}		
		else if (mon.contains("x^")) {
			if (mon.charAt(0)=='-' && mon.indexOf("x^")==1) {
				this.coef=-1;
				this.exp = Integer.parseInt(mon.substring(mon.indexOf("x^")+2, mon.length()));
			}
			else if ((mon.charAt(0)=='+' && mon.indexOf("x^")==1) || mon.indexOf("x^")==0) {
				this.coef=1;
				this.exp=Integer.parseInt(mon.substring(mon.indexOf("x^")+2, mon.length()));
			}
			else if (mon.charAt(0)=='-') {
				this.coef = Integer.parseInt(mon.substring(0,mon.indexOf("x^")));
				this.exp = Integer.parseInt(mon.substring(mon.indexOf("x^")+2, mon.length()));
			}
			else if ( (mon.charAt(0)=='+' && mon.indexOf("x^")!=1)) {
				this.coef=Integer.parseInt(mon.substring(0,mon.indexOf("x^")));
				this.exp = Integer.parseInt(mon.substring(mon.indexOf("x^")+2, mon.length()));
			}
			else if ( (mon.charAt(0)!='+' && mon.charAt(0)!='-')) {
				this.coef = Integer.parseInt(mon.substring(0,mon.indexOf("x^")));
				this.exp = Integer.parseInt(mon.substring(mon.indexOf("x^")+2, mon.length()));
			}		
		}
		else if (mon.contains("x")) {
			this.coef = Integer.parseInt(mon.substring(0,mon.indexOf("x")));			
			this.exp=1;			
		}
		else {
			this.coef = Integer.parseInt(mon);
			this.exp =0;
		}
		
		
	}
	
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public double getDoubleCoef() {
		return doubleCoef;
	}
	public void setDoubleCoef(double doubleCoef) {
		this.doubleCoef = doubleCoef;
	}
	
	public Monom divideMonom (Monom mon2) {
		int coef =(int)(this.getCoef() / mon2.getCoef());
		int exp = this.getExp()-mon2.getExp();
		Monom rez = new Monom(coef,exp);
		return rez;
	}
	
	public String getStringMonom() {
		if (this.exp==0) {
			return Integer.toString(this.coef);
		}
		else
			return Integer.toString(this.coef)+"x^"+Integer.toString(this.exp);
	}
	
	
}
