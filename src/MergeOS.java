import java.util.ArrayList;

public class MergeOS{
	public static ArrayList<MemoryBlock> freeBlocks;
	public static ArrayList<MemoryBlock> busyBlocks;
	public static ArrayList<Job> hold;	
	public static ArrayList<Job> ready;
	public static ArrayList<Job> running;
	public static ArrayList<Job> waiting;
	public static ArrayList<Job> finished;

	public static void main(String[] args){
		
		freeBlocks = new ArrayList<>();
		busyBlocks = new ArrayList<>();
		hold = new ArrayList<>();
		ready = new ArrayList<>();
		running = new ArrayList<>();
		waiting = new ArrayList<>();
		finished = new ArrayList<>();
		double size = 25.0;
		
		long start;
		long duration;
		double throughput;
		
		int[] rounds = new int[4];
		long[] durations = new long[4];
		int[] throughputs = new int[4];
	
		double[] turnarounds = new double[4];
		long[] processDurations = new long[4];
		int[] processThroughputs = new int[4];
		
		System.out.println("Survey of memory allocation schemes begins here\n");
		
		Startup("0");
		System.out.println("First-Fit");
		start = System.nanoTime();
		rounds[0] = FirstFit();
		duration = System.nanoTime();	
		duration -= start;
		durations[0] = duration;
		throughput = ((double)(finished.size()))/(size);
		throughputs[0] = (int)(throughput*100);		
		
		Startup("0");
		System.out.println("Best-Fit");
		start = System.nanoTime();
		rounds[1] = BestFit();
		duration = System.nanoTime();
		duration -= start;
		durations[1] = duration;
		throughput = ((double)(finished.size()))/(size);
		throughputs[1] = (int)(throughput*100);
			
		Startup("0");
		System.out.println("Next-Fit");
		start = System.nanoTime();
		rounds[2] = NextFit();
		duration = System.nanoTime();
		duration -= start;
		durations[2] = duration;
		throughput = ((double)(finished.size()))/(size);
		throughputs[2] = (int)(throughput*100);
		
		Startup("0");
		System.out.println("Worst-Fit");
		start = System.nanoTime();
		rounds[3] = WorstFit();
		duration = System.nanoTime();
		duration -= start;
		durations[3] = duration;
		throughput = ((double)(finished.size()))/(size);
		throughputs[3] = (int)(throughput*100);

		System.out.println("\n*********************** Summary ***********************");
		System.out.println("Scheme\t\tRounds\tTime(ns)\tThroughput(%)");
		System.out.println("First-Fit\t"+rounds[0]+"\t"+durations[0]+"\t\t\t"+throughputs[0]);
		System.out.println("Best-Fit\t"+rounds[1]+"\t"+durations[1]+"\t\t\t"+throughputs[1]);
		System.out.println("Next-Fit\t"+rounds[2]+"\t"+durations[2]+"\t\t\t"+throughputs[2]);
		System.out.println("Worst-Fit\t"+rounds[3]+"\t"+durations[3]+"\t\t\t"+throughputs[3]+"\n\n");
		
		
		System.out.println("Survey of Process Management begins here");
		
		Startup("1");
		JobScheduler(0);
		System.out.println("FCFS");
		start = System.nanoTime();
		FCFS();
		duration = System.nanoTime();
		duration -= start;
		processDurations[0] = duration;
		turnarounds[0] = calcAvgTurnaround();
        throughput = ((double)(finished.size()))/(size);
        processThroughputs[0] = (int)(throughput*100);; 
		
		Startup("1");
		JobScheduler(0);
		System.out.println("SJN");
		start = System.nanoTime();
		SJN();
		duration = System.nanoTime();
		duration -= start;
		processDurations[1] = duration;
		turnarounds[1] = calcAvgTurnaround();
        throughput = ((double)(finished.size()))/(size);
        processThroughputs[1] = (int)(throughput*100);; 
		
		Startup("1");
		JobScheduler(0);
		System.out.println("Round Robin");
		start = System.nanoTime();
		RoundRobin(9);
		duration = System.nanoTime();
		duration -= start;
		processDurations[2] = duration;
		turnarounds[2] = calcAvgTurnaround();
        throughput = ((double)(finished.size()))/(size);
        processThroughputs[2] = (int)(throughput*100);; 

	
		Startup("1");
		JobScheduler(0);
		System.out.println("SRT");
		start = System.nanoTime();
		SRT();
		duration = System.nanoTime();
		duration -= start;
		processDurations[3] = duration;
		turnarounds[3] = calcAvgTurnaround();
        throughput = ((double)(finished.size()))/(size);
        processThroughputs[3] = (int)(throughput*100);; 

		
		System.out.println("\n************************Summary************************");
		System.out.println("Algorithm\tAvg Turnaround(cycles)\tTime(ns)\tThroughput(%)");
		System.out.println("FCFS\t\t"+turnarounds[0]+"\t\t\t"+processDurations[0]+"\t\t"+processThroughputs[0]);
		System.out.println("SJN\t\t"+turnarounds[1]+"\t\t\t"+processDurations[1]+"\t\t"+processThroughputs[1]);
		System.out.println("Round Robin\t"+turnarounds[2]+"\t\t\t"+processDurations[2]+"\t\t"+processThroughputs[2]);
		System.out.println("SRT\t\t"+turnarounds[3]+"\t\t\t"+processDurations[3]+"\t\t"+processThroughputs[3]+"\n\n");
		
	}

	public static void Startup(String a){    
        freeBlocks.clear();
        busyBlocks.clear();
        ready.clear();
        running.clear();
        waiting.clear();
        finished.clear();
        hold.clear();
        
        if(a.equals("0")){
	        hold.add(new Job(1, 5760, 0, 5));
			hold.add(new Job(2, 4190, 0, 4));
			hold.add(new Job(3, 3290, 0, 8));
			hold.add(new Job(4, 2030, 0, 2));
			hold.add(new Job(5, 2550, 0, 2));
			hold.add(new Job(6, 6990, 0, 6));
			hold.add(new Job(7, 8940, 0, 8));
			hold.add(new Job(8, 740, 0, 10));
			hold.add(new Job(9, 3930, 0, 7));
			hold.add(new Job(10, 6890, 0, 6));
			hold.add(new Job(11, 6580, 0, 5));
			hold.add(new Job(12, 3820, 0, 8));
			hold.add(new Job(13, 9140, 0, 9));
			hold.add(new Job(14, 420, 0, 10));
			hold.add(new Job(15, 220, 0, 10));
			hold.add(new Job(16, 7540, 0, 7));
			hold.add(new Job(17, 3210, 0, 3));
			hold.add(new Job(18, 1380, 0, 1));
			hold.add(new Job(19, 9850, 0, 9));
			hold.add(new Job(20, 3610, 0, 3));
			hold.add(new Job(21, 7540, 0, 7));
			hold.add(new Job(22, 2710, 0, 2));
			hold.add(new Job(23, 8390, 0, 8));
			hold.add(new Job(24, 5950, 0, 5));
			hold.add(new Job(25, 760, 0, 10));
       	
			freeBlocks.add(new MemoryBlock(1, 9500));
			freeBlocks.add(new MemoryBlock(2, 7000));
			freeBlocks.add(new MemoryBlock(3, 4500));
			freeBlocks.add(new MemoryBlock(4, 8500));
			freeBlocks.add(new MemoryBlock(5, 3000));
			freeBlocks.add(new MemoryBlock(6, 9000));
			freeBlocks.add(new MemoryBlock(7, 1000));
			freeBlocks.add(new MemoryBlock(8, 5500));
			freeBlocks.add(new MemoryBlock(9, 1500));
			freeBlocks.add(new MemoryBlock(10, 500));
		}
		else{
	        hold.add(new Job(1, 5760, 0, 5));
			hold.add(new Job(2, 4190, 0, 4));
			hold.add(new Job(3, 3290, 0, 8));
			hold.add(new Job(4, 2030, 0, 2));
			hold.add(new Job(5, 2550, 0, 2));
			hold.add(new Job(6, 6990, 1, 6));
			hold.add(new Job(7, 8940, 2, 8));
			hold.add(new Job(8, 740, 2, 10));
			hold.add(new Job(9, 3930, 3, 7));
			hold.add(new Job(10, 6890, 3, 6));
			hold.add(new Job(11, 6580, 3, 5));
			hold.add(new Job(12, 3820, 4, 8));
			hold.add(new Job(13, 9140, 4, 9));
			hold.add(new Job(14, 420, 4, 10));
			hold.add(new Job(15, 220, 4, 10));
			hold.add(new Job(16, 7540, 5, 7));
			hold.add(new Job(17, 3210, 5, 3));
			hold.add(new Job(18, 1380, 5, 1));
			hold.add(new Job(19, 9850, 5, 9));
			hold.add(new Job(20, 3610, 5, 3));
			hold.add(new Job(21, 7540, 6, 7));
			hold.add(new Job(22, 2710, 6, 2));
			hold.add(new Job(23, 8390, 6, 8));
			hold.add(new Job(24, 5950, 6, 5));
			hold.add(new Job(25, 760, 6, 10));
			
			freeBlocks.add(new MemoryBlock(1, 50000));
		}
	}

	public static int FirstFit(){
		Job a;
		MemoryBlock s;
		boolean moved;
		boolean someMoved;
		boolean done = false;
		int rounds = 1;
		
		while(!done){
			Reset();
			JobScheduler(rounds-1);
			someMoved = false;
			System.out.println("Round "+ rounds);
			
			while(!ready.isEmpty()){
				a = ready.get(0);
				moved = false;
				for(int d = 0; d <= 9; d++){
					s = freeBlocks.get(d);
					if((a.getSize() <= s.getSize()) && s.isFree()){
						s.markBusy();
						s.setJobId((""+a.getId()));
						running.add(a);
						ready.remove(0);
						moved = true;
						someMoved = true;
						break;
					}
					if((d == 9) && (!moved)){
						waiting.add(a);
						ready.remove(0);
					}
				}
				
				if(ready.isEmpty()){
					
					MemoryBlock temp;
					for(int d = 0; d < freeBlocks.size(); d++){
						temp = freeBlocks.get(d);
						if(!temp.isFree()){
							busyBlocks.add(temp);
							freeBlocks.remove(d);
							d--;
						}
					}
					Status();
				}
			}
			
			Reset();
			JobScheduler(rounds);
				
			if(!someMoved){
				if(hold.isEmpty())
					done = true;
			}
			
			if(hold.isEmpty() && ((ready.isEmpty() && waiting.isEmpty())))
				done = true;
			if(!done)
				rounds++;
		}
		return rounds;
	}
	
	public static int BestFit(){
		Job a;
		MemoryBlock s;
		MemoryBlock temp;
		int index;
		boolean someMoved;
		boolean done = false;
		int rounds = 1;
		
		while(!done){
			Reset();
			JobScheduler(rounds-1);
			someMoved = false;
			System.out.println("Round "+rounds);
			
			while(!ready.isEmpty()){
				a = ready.get(0);
				index = 0;
				
				s = freeBlocks.get(0);
				while((s.getSize() < a.getSize() || !s.isFree()) && (index < 9)){
					index++;
					s = freeBlocks.get(index);
				}
				if((index == 10) || ((index ==9) && (s.getSize() < a.getSize()))){
					waiting.add(a);
					ready.remove(0);
				}
				else{
					if(index < 9){
						for(int d = index; d < 10; d++){
							temp = freeBlocks.get(d);
							if((a.getSize() <= temp.getSize()) && (temp.isFree()) && (temp.getSize() < s.getSize())){
								s = temp;
								index = d;
							}
						}
					}
					s.markBusy();
					s.setJobId(""+a.getId());
					running.add(a);
					ready.remove(0);
					someMoved = true;
				}
				
				if(ready.isEmpty()){
					for(int d = 0; d < freeBlocks.size(); d++){
						temp = freeBlocks.get(d);
						if(!temp.isFree()){
							busyBlocks.add(temp);
							freeBlocks.remove(d);
							d--;
						}
					}
					Status();
				}
			}
			
			Reset();
			JobScheduler(rounds);

			if(!someMoved){
				if(hold.isEmpty())
					done = true;
			}
			
			if(hold.isEmpty() && ((ready.isEmpty() && waiting.isEmpty())))
				done = true;
			if(!done)
				rounds++;			
		}
		return rounds;
	}
	
	public static int NextFit(){
		Job a;
		MemoryBlock s;
		boolean someMoved = false;
		boolean done = false;
		int rounds = 1;
		int index = 0;
		boolean looped;
		
		while(!done){
			Reset();
			JobScheduler(rounds-1);
			someMoved = false;
			System.out.println("Round "+rounds);
			
			while(!ready.isEmpty()){
				a = ready.get(0);
				looped = false;
				for(int d = index; d < 10; d++){
					if((d == index) && looped){
							waiting.add(a);
							ready.remove(0);
							break;
					}
					else{
						s = freeBlocks.get(d);
						if((a.getSize() <= s.getSize()) && s.isFree()){
							s.markBusy();
							s.setJobId(""+a.getId());
							running.add(a);
							ready.remove(0);
							index = d;
							someMoved = true;
							break;
						}
						if(d == 9){
							d = -1;
							looped = true;
						}
					}
				}	
				if(ready.isEmpty()){
					MemoryBlock temp;
					for(int d = 0; d < freeBlocks.size(); d++){
						temp = freeBlocks.get(d);
						if(!temp.isFree()){
							busyBlocks.add(temp);
							freeBlocks.remove(d);
							d--;
						}
					}
					Status();
				}
			}
			
			Reset();
			JobScheduler(rounds);
			
			if(!someMoved){
				if(hold.isEmpty())
					done = true;
			}
			
			if(hold.isEmpty() && ((ready.isEmpty() && waiting.isEmpty())))
				done = true;
			if(!done)
				rounds++;
		}
		return rounds;
	}
		
	public static int WorstFit(){
		Job a;
		MemoryBlock s;
		MemoryBlock temp;
		int index;
		boolean someMoved;
		boolean done = false;
		int rounds = 1;
		
		while(!done){
			Reset();
			JobScheduler(rounds-1);
			someMoved = false;
			System.out.println("Round "+rounds);
			
			while(!ready.isEmpty()){
				a = ready.get(0);
				index = 0;
				
				s = freeBlocks.get(0);
				while((s.getSize() < a.getSize() || !s.isFree()) && (index < 9)){
					index++;
					s = freeBlocks.get(index);
				}
				if((index == 10) || ((index ==9) && (s.getSize() < a.getSize()))){
					waiting.add(a);
					ready.remove(0);
				}
				else{
					if(index < 9){
						for(int d = index; d < 10; d++){
							temp = freeBlocks.get(d);
							if((temp.isFree()) && (temp.getSize() > s.getSize())){
								s = temp;
								index = d;
							}
						}
					}
					s.markBusy();
					s.setJobId(""+a.getId());
					running.add(a);
					ready.remove(0);
					someMoved = true;
				}
				
				if(ready.isEmpty()){
					for(int d = 0; d < freeBlocks.size(); d++){
						temp = freeBlocks.get(d);
						if(!temp.isFree()){
							busyBlocks.add(temp);
							freeBlocks.remove(d);
							d--;
						}
					}
					Status();
				}	
			}
			
			Reset();
			JobScheduler(rounds);
			
			if(!someMoved){
				if(hold.isEmpty())
					done = true;
			}
			
			if(hold.isEmpty() && ((ready.isEmpty() && waiting.isEmpty())))
				done = true;
			if(!done)
				rounds++;
		}
		return rounds;
	}
	
	public static void FCFS(){
		Job a;
		MemoryBlock s;
		int totalTime = 0;
		boolean ran = false;
		
		while(ready.size()>0){
			
			a = ready.get(0);
			s = freeBlocks.get(0);
			if(a.getSize() <= s.getSize()){
				busyBlocks.add(s);
				freeBlocks.remove(0);
				running.add(a);
				ran = true;
			}
			else
				waiting.add(a);
			ready.remove(0);
			while(running.size()>0){
				totalTime++;
				DecreaseTime(totalTime);
				JobScheduler(totalTime);
			}
			if(ran){
				s = busyBlocks.get(0);
				freeBlocks.add(s);
				busyBlocks.remove(0);
			}
			while((ready.isEmpty()) && (!hold.isEmpty())){
				totalTime++;
				JobScheduler(totalTime);
			}
		}	
	}
		
	public static void SJN(){
		RemoveImpossibilities();
		Job a;
		int index;
		Job temp;
		MemoryBlock s;
		int totalTime = 0;
		boolean ran = false;
		//find shortest job, if exists
		while(ready.size()>0){
			//initial index of shortest remaining job
			//-1 until a job that can be fit into memory is found
			index = -1;
			//get basis for shortest job
			a = ready.get(0);
			//get memory block
			s = freeBlocks.get(0);
			//move job to waiting if it won't fit into memory
			if(a.getSize() > s.getSize()){
				waiting.add(a);
				ready.remove(0);
			}
			else{
				index = 0;
				for(int d = 1; d < ready.size(); d++){
					temp = ready.get(d);
					if(temp.getSize() > s.getSize()){
						waiting.add(temp);
						ready.remove(d);
						d--;
					}
					else
						if(temp.getRemainingTime() < a.getRemainingTime()){
							a = temp;
							index = d;
						}
				}
			}
			if(index >= 0){
				busyBlocks.add(s);
				freeBlocks.remove(0);
				running.add(a);
				ready.remove(index);
				ran = true;
			}
			while(running.size()>0){
				totalTime++;
				DecreaseTime(totalTime);
				JobScheduler(totalTime);
			}
			if(ran){
				s = busyBlocks.get(0);
				freeBlocks.add(s);
				busyBlocks.remove(0);
			}
			while((ready.isEmpty()) && (!hold.isEmpty())){
				totalTime++;
				JobScheduler(totalTime);
			}
		}		
	}
		
	public static void RoundRobin(int z){
		Job a;
		MemoryBlock s;
		int totalTime = 0;
		int tq = z;
		boolean jobFinished;
		
		while(!ready.isEmpty()){
			for(int d = 0; d < ready.size(); d++){
				a = ready.get(d);
				s = freeBlocks.get(0);
				if(a.getSize() > s.getSize()){
					waiting.add(a);
					ready.remove(d);
				}
				else{
					running.add(a);
					ready.remove(d);
					jobFinished = false;
					for(int f = 0; f < tq; f++){
						totalTime++;
						a = running.get(0);
						a.decreaseRemainingTime();
						if(a.getRemainingTime() == 0){
							a.setFinishTime(totalTime);
							finished.add(a);
							running.remove(0);
							jobFinished = true;
							d--;
							break;
						}
					}
					if(!jobFinished){
						a = running.get(0);
						running.remove(0);
						ready.add(d, a);
					}
				}
				JobScheduler(totalTime);
				while(ready.isEmpty() && !hold.isEmpty()){
					totalTime++;
					JobScheduler(totalTime);
				}
			}
		}
	}
	
	public static void SRT(){
		RemoveImpossibilities();
		boolean done = false;
		int totalTime = 0;
		int a;

		while(!done){
			JobScheduler(totalTime);
			a = GetShortestIndex();
			if(a == -2)
				done = true;
			else{
				if(a >= 0){
					if(running.size() < 1){
						running.add(ready.get(a));
						ready.remove(a);
					}
					else{
						ready.add(running.get(0));
						running.remove(0);
						running.add(ready.get(a));
						ready.remove(a);
					}
				}
				totalTime++;
				DecreaseTime(totalTime);
			}
		}	
	}
	
	public static void Reset(){
		for(int d = 0; d < running.size(); d++){
			finished.add(running.get(d));
			running.remove(0);
			d--;
		}

		for(int d = 0; d < waiting.size(); d++){
			ready.add(waiting.get(d));
			waiting.remove(d);
			d--;
		}
		MemoryBlock a;
		boolean moved;
		for(int d = 0; d < busyBlocks.size(); d++){
			moved = false;
			a = busyBlocks.get(d);
			a.markFree();
			a.setJobId("-");
			for(int f = 0; f < freeBlocks.size(); f++){
				if(a.getId() < freeBlocks.get(f).getId()){
					freeBlocks.add(f, a);
					moved = true;
					break;
				}
			}
			if(!moved){
				freeBlocks.add(a);
			}
		}
		busyBlocks.clear();
	}
	
	public static void DecreaseTime(int s){
		for(int d = 0; d<running.size(); d++){
			Job f = running.get(d);
			f.decreaseRemainingTime();
			if(f.getRemainingTime() <= 0){
				f.setFinishTime(s);
				finished.add(f);
				running.remove(d);
			}
		}
	}
	
	public static int GetShortestIndex(){
		Job a;
		Job temp;
		int index;
		if(ready.size() == 0){
			if(running.size() == 0)
				return -2;
			return -1;
		}
		else{
			a = ready.get(0);
			index = 0;
			for(int d = 1; d < ready.size(); d++){
				temp = ready.get(d);
				if(temp.getRemainingTime() < a.getRemainingTime()){
					a = temp;
					index = d;
				}
			}
			if(running.size() == 1){
				temp = running.get(0);
				if(temp.getRemainingTime() < a.getRemainingTime())
					index = -1;
			}
			return index;
		}
	}
	
	public static void RemoveImpossibilities(){
		for(int a = 0; a < ready.size(); a++)
			if(ready.get(a).getSize() > freeBlocks.get(0).getSize()){
				ready.remove(a);
				a--;
			}
	}
	
	public static double calcAvgTurnaround(){
		double a = 0;
		for(Job s: finished)
			a+=(s.getFinishTime()-s.getArrival());
		a/=(finished.size());
		return ((double)((int)(a*100)))/100;
	}
	
	public static void JobScheduler(int a){
		Job s;
		for(int d = 0; d < hold.size(); d++){
			s = hold.get(d);
			if(s.getArrival() <= a){
				ready.add(s);
				hold.remove(d);
				d--;
			}
		}
	}

	public static void Status(){
		int freeIndex = 0;
		int busyIndex = 0;
		MemoryBlock free;
		MemoryBlock busy;
		System.out.println("\t*** Memory Status***\n\tBlock\tSize\tAccess\tStatus");
		while((freeIndex < freeBlocks.size()) || (busyIndex < busyBlocks.size())){
			if(freeIndex >= freeBlocks.size()){
				busy = busyBlocks.get(busyIndex);
				System.out.println("\t"+busy.getId()+"\t"+busy.getSize()+"\t"+busy.getJobId()+"\tBusy");
				busyIndex++;
			}
			else if(busyIndex >= busyBlocks.size()){
				free = freeBlocks.get(freeIndex);
				System.out.println("\t"+free.getId()+"\t"+free.getSize()+"\t"+free.getJobId()+"\tFree");
				freeIndex++;
			}
			else{
				free = freeBlocks.get(freeIndex);
				busy = busyBlocks.get(busyIndex);
				if(((free.getId()) < (busy.getId()))){
					System.out.println("\t"+free.getId()+"\t"+free.getSize()+"\t"+free.getJobId()+"\tFree");
					freeIndex++;
				}
				else{
					System.out.println("\t"+busy.getId()+"\t"+busy.getSize()+"\t"+busy.getJobId()+"\tBusy");
					busyIndex++;
				}
			}
		}
		System.out.println();
	}
	
}