import java.util.List;

public class ConcreteStrategyTime implements Strategy{

	public ConcreteStrategyTime() {
		
	}
	
	public void addTask(List<Server> servers,Task workTask) {
		//adaugam task-ul pe serverul care are waitingPeriod cel mai mic
		
		int min = servers.get(0).getWaitingPeriod();
		int queue=0;
		
		for (int i = 0; i<servers.size();i++) {
			if (servers.get(i).getWaitingPeriod() < min) {
				min = servers.get(i).getWaitingPeriod();
				queue = i;
			}
		}
		servers.get(queue).addTask(workTask);		
		
	}

}
