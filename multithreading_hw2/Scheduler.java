import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private List<Server> servers = new ArrayList<Server>();
	private int maxNoServers;
	private View viewScheduler;
	private Strategy strategy;
	
	private String msgScheduler1;
	
	
	public Scheduler(int numberOfServers,int numberOfTasks,View viewScheduler) {
		//for maxNoServers
		//-create server object
		//-create thread with the object
		this.maxNoServers = numberOfServers;
		this.viewScheduler = viewScheduler;		
		
		
		for (int i=0;i<this.maxNoServers;i++) {			
			
			Server workServer = new Server(numberOfTasks,i,this.viewScheduler);			
			servers.add(workServer);						
			Thread t = new Thread(workServer);			
			t.start();
			
			
			this.msgScheduler1="Thread server: " + i +" started!";
			this.viewScheduler.textArea.append(this.msgScheduler1+"\n");
			System.out.println("Thread server: " + i +" started!");
			
		}
		
	}
	
	public void changeStrategy (SelectionPolicy policy) {
		//apply strategy pattern to instantiate the strategy with the concrete 
		//strategy corresponding to policy
		
		if (policy == SelectionPolicy.SHORTEST_QUEUE) {
			strategy = new ConcreteStrategyQueue();
		}
		if (policy == SelectionPolicy.SHORTEST_TIME) {
			strategy = new ConcreteStrategyTime();
		}
	}
	
	public void dispatchTask (Task workTask) {
		strategy.addTask(this.servers, workTask);
	}
	
	
	
}
