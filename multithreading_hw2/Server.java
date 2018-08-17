import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
	private BlockingQueue<Task> tasks;
	private AtomicInteger waitingPeriod;
	private int serverNumber;
	private String msgServer1;
	private String msgServer2;
	private String msgServer3;
	
	private View viewServer;
	
	private int sumTimes = 0;
	private int peakHour =0;
	private int max = 0;
	
	public int getPeakHour() {
		return peakHour;
	}

	public Server(int numberOfTasks,int serverNumber, View viewServer) {
		//initialize queue and waiting period
		this.viewServer = viewServer;
		tasks = new ArrayBlockingQueue<Task>(numberOfTasks);	
		
		waitingPeriod = new AtomicInteger(0);	
		this.serverNumber = serverNumber;		
	
	
	}
	
	synchronized public void addTask(Task newTask) {
		//add new task to queue
		//increment the waitingPeriod	
		newTask.setFinishTime(newTask.getArrivalTime() + waitingPeriod.get() + newTask.getProcessingTime() );
		try {
			tasks.put(newTask);		
			sumTimes += newTask.getFinishTime();
			if (max<tasks.size()) {
				peakHour=0;
				max = tasks.size();
				for (Task task : tasks) {
					peakHour+= task.getFinishTime();
				}
				this.viewServer.setPeakHourTotal(this.viewServer.getPeakHourTotal() + peakHour);
				this.viewServer.setPeakHourTotal(this.viewServer.getPeakHourTotal() + peakHour);
				this.viewServer.tfPeakHour.setText(Integer.toString(this.viewServer.getPeakHourTotal()));
			}
			
			this.viewServer.textFieldArray[serverNumber].setText(this.viewServer.textFieldArray[serverNumber].getText() + " Task");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitingPeriod.addAndGet(newTask.getProcessingTime());
		this.msgServer3 = "Server: " + this.serverNumber + " S-a adaugat task-ul cu arrivingTime: " + newTask.getArrivalTime() + " si processingTime: " + newTask.getProcessingTime(); 
		this.viewServer.textArea.append(msgServer3 + "\n");
		System.out.println("Server: " + this.serverNumber + " S-a adaugat task-ul cu arrivingTime: " + newTask.getArrivalTime() + " si processingTime: " + newTask.getProcessingTime());
	}
		
	public int getSumTimes() {
		return sumTimes;
	}

	public void run() {
		Task workTask;
		
		while (true) {
			//take next task from queue
			//stop the thread for a time equal with the task's processing time
			//decrement the waitingPeriod			
			
			try {				
				while (!tasks.isEmpty()) {						
					
					workTask = tasks.take();							
					
					Thread.sleep(1000 * workTask.getProcessingTime());
					this.viewServer.textFieldArray[serverNumber].setText(this.viewServer.textFieldArray[serverNumber].getText().replaceFirst(" Task"," out "));
					
					
					msgServer1 = "Server: "+ this.serverNumber + " " + "Task out " + workTask.getArrivalTime() + " --- " + workTask.getProcessingTime() + " Waiting period: " + waitingPeriod.get();
					msgServer2 = "Current waiting period is: " + waitingPeriod.get();
					
					this.viewServer.textArea.append("\n" +"###################" + "\n");
					this.viewServer.textArea.append(msgServer2 + "\n");
					this.viewServer.textArea.append(msgServer1 + "\n");					
					
					
					System.out.println("###################");
					System.out.println("Current waiting period is: " + waitingPeriod.get());					
					System.out.println("Server: "+ this.serverNumber + " " + "Task out " + workTask.getArrivalTime() + " --- " + workTask.getProcessingTime() + " Waiting period: " + waitingPeriod.get());
					
					waitingPeriod.set(waitingPeriod.get()-workTask.getProcessingTime());	
					
					this.viewServer.textArea.append(msgServer2 + "\n");

					this.viewServer.textArea.append("###################" + "\n" + "\n");
					
					System.out.println("Current waiting period is: " + waitingPeriod.get());
					System.out.println("###################");
					
					Thread.sleep(1000);
				
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public BlockingQueue<Task> getTasks() {
		return tasks;
	}
	
	public int getQueueLength() {
		return this.tasks.size();
	}
	
	public int getWaitingPeriod() {
		return this.waitingPeriod.get();
	}
	
	
	
	
	
	
	
}
