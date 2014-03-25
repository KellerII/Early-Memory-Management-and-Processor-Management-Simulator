
public class MemoryBlock{
	private int id;
	private int size;
	private int remainingBusyTime;
	private String jobId;
	private boolean free;
	
	public MemoryBlock(){
		setId(0);
		setSize(0);
		setJobId("-");
		setRemainingBusyTime(0);		
	}
	
	public MemoryBlock(int a, int s){
		setId(a);
		setSize(s);
		setRemainingBusyTime(0);
		markFree();
	}
	
	public int getId(){
		return id;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getRemainingBusyTime(){
		return remainingBusyTime;
	}
	
	public String getJobId(){
		return jobId;
	}
	
	public void decreaseRemainingBusyTime(){
		remainingBusyTime--;
	}
	
	public void setId(int a){
		id = a;
	}
	
	public void setSize(int a){
		size = a;
	}
	
	public void setRemainingBusyTime(int a){
		remainingBusyTime = a;
	}
	
	public void setJobId(String a){
		jobId = a;
	}
	
	public void markBusy(){
		free = false;
	}
	
	public void markFree(){
		free = true;
	}
	
	public boolean isFree(){
		return free;
	}
}