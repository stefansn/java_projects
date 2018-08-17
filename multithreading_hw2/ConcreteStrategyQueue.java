import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
	
	public ConcreteStrategyQueue() {
		
	}

	public void addTask(List<Server> servers, Task workTask) {
		//adaugam task-ul pe serverul cel mai putin ocupat
		int min = servers.get(0).getQueueLength();
		int queue = 0;
		
		for (int i = 0;i < servers.size();i++) {
			if (servers.get(i).getQueueLength() < min) {
				min = servers.get(i).getQueueLength();
				queue = i;
			}
		}
		servers.get(queue).addTask(workTask);
	}

}
