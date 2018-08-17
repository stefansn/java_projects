package homework4;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
	public static void main (String[] args) {
		
		Bank bank = new Bank();
		

		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int count= 0;
			
			public void run() {
				//System.out.println(count);
				count++;
				bank.interest();
				if (count==1) {
					bank.setHm(bank.readAccount());
					GUI.createAndShowGui(bank);
				}
				bank.writeAccount();
			}
		}, new Date(), 1000);
		
		
	}
} 
