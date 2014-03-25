
public class Job {
	private int id;
	private int size;
	private int arrivalTime;
	private int remainingTime;
	private int finishTime;
	private String status;
	
	public Job(int a, int s, int d, int f){
		id = a;
		size = s;
		arrivalTime = d;
		remainingTime = f;
	}
	
	public int getId(){
		return id;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getArrival(){
		return arrivalTime;
	}
	
	public int getRemainingTime(){
		return remainingTime;
	}
	
	public void decreaseRemainingTime(){
		remainingTime--;
	}
	
	public void setFinishTime(int a){
		finishTime = a;
	}
	
	public void setRemainingTime(int a){
		remainingTime = a;
	}
	
	public int getFinishTime(){
		return finishTime;
	}
	
	public void setStatus(String a){
		status = a;
	}
	
	public String getStatus(){
		return status;
	}
}
