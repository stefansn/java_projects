
public class Task{
	private int arrivalTime;
	private int processingTime;
	private int finishTime;
	
	public Task (int arrivalTime, int processingTime) {
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
	}

	synchronized public int getArrivalTime() {
		return arrivalTime;
	}

	synchronized public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	synchronized public int getProcessingTime() {
		return processingTime;
	}

	synchronized public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}	


	synchronized public int getFinishTime() {
		return finishTime;
	}

	synchronized public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
	
}	
