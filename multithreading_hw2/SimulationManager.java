import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;


public class SimulationManager implements Runnable{
	private int timeLimit; //maximum processing time
	private int maxProcessingTime;
	private int minProcessingTime;
	private int numberOfServers;
	private int numberOfTasks;
	private SelectionPolicy selectionPolicy;	
	
	private String msgSim1;
	private String msgSim2;
	
	private View viewSimulationManager;
	
	//entity responsible with queue management and client distribution
	private Scheduler scheduler;
	
	//pool of tasks 
	private List<Task> generatedTasks = new ArrayList<Task>();
	
	public SimulationManager(int timeLimit,int maxProcessingTime,int minProcessingTime,int numberOfServers, int numberOfTasks, SelectionPolicy selectionPolicy,View viewSimulationManager) {
		this.viewSimulationManager = viewSimulationManager;
		this.timeLimit = timeLimit;
		this.maxProcessingTime = maxProcessingTime;
		this.minProcessingTime = minProcessingTime;
		this.numberOfServers = numberOfServers;
		this.numberOfTasks = numberOfTasks;
		this.selectionPolicy = selectionPolicy;
		
		this.msgSim1 = timeLimit + " " + maxProcessingTime + " " + minProcessingTime + " " + numberOfServers + " " + numberOfTasks;
		this.viewSimulationManager.textArea.append(this.msgSim1+"\n");
		
		System.out.println(timeLimit + " " + maxProcessingTime + " " + minProcessingTime + " " + numberOfServers + " " + numberOfTasks);
		scheduler = new Scheduler(this.numberOfServers,this.numberOfTasks,this.viewSimulationManager);			
		scheduler.changeStrategy(selectionPolicy);
		int count = 0;
		this.generateNRandomTasks(this.numberOfTasks);
		for (Task newTask : generatedTasks) {
			
			this.msgSim2 = count + " " +newTask.getArrivalTime() + " " + newTask.getProcessingTime();
			this.viewSimulationManager.textArea.append(this.msgSim2+"\n");
			
			System.out.println(count + " " +newTask.getArrivalTime() + " " + newTask.getProcessingTime());
			count++;
		}
	}
	
	private void generateNRandomTasks(int numberOfTasks) {
		for (int i=0;i<numberOfTasks;i++) {
			int randomProcessingTime = ThreadLocalRandom.current().nextInt(this.minProcessingTime,this.maxProcessingTime + 1);
			int randomArrivalTime = ThreadLocalRandom.current().nextInt(0,this.timeLimit + 1);
			Task newTask = new Task(randomArrivalTime,randomProcessingTime);
			//System.out.println("Generated!");
			generatedTasks.add(newTask);			
		}
		
		Collections.sort(generatedTasks,new TaskComparator());
		
	}
	


	
	public void run() {
		int currentTime=0;
		//this.generateNRandomTasks(this.numberOfTasks);
		while (currentTime < timeLimit) {
			try {
				for (int i = 0;i<generatedTasks.size();i++) {
					if (generatedTasks.get(i).getArrivalTime() == currentTime) {
						scheduler.dispatchTask(generatedTasks.get(i));
						this.viewSimulationManager.tfAverageTime.setText(Double.toString(generatedTasks.get(i).getFinishTime()/numberOfServers));						
						generatedTasks.remove(i);
					}
					
				}
				
				Thread.sleep(1000);
				currentTime++;
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			
			this.viewSimulationManager.textArea.append("Current time " + currentTime + "\n");
			this.viewSimulationManager.tfCurrentTime.setText(Integer.toString(currentTime));
			System.out.println("Current time " + currentTime);
			
			
			
		}
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getMaxProcessingTime() {
		return maxProcessingTime;
	}

	public void setMaxProcessingTime(int maxProcessingTime) {
		this.maxProcessingTime = maxProcessingTime;
	}

	public int getMinProcessingTime() {
		return minProcessingTime;
	}

	public void setMinProcessingTime(int minProcessingTime) {
		this.minProcessingTime = minProcessingTime;
	}

	public int getNumberOfServers() {
		return numberOfServers;
	}

	public void setNumberOfServers(int numberOfServers) {
		this.numberOfServers = numberOfServers;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public SelectionPolicy getSelectionPolicy() {
		return selectionPolicy;
	}

	public void setSelectionPolicy(String selectionPolicy) {
		if (selectionPolicy.equals("SHORTEST_QUEUE")) {
			this.selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
			System.out.println(selectionPolicy);
		}
		if (selectionPolicy.equals("SHORTEST_TIME")) {
			this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
			System.out.println(selectionPolicy);
		}
	}
	
	
	
	/*
	public static void main(String[] args) {
		SimulationManager gen = new SimulationManager();
		Thread t = new Thread(gen);
		t.start();
		
		
	}
	*/
	
}


