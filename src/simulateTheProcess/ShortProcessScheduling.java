package simulateTheProcess;

import java.util.ArrayList;
import java.util.List;

public class ShortProcessScheduling {
	private List<Process> ReadyList ;//就绪队列
	private List<Process> NewWaitList;//新建等待队列
	private int MAXSIZE=7;//最大进程数
	private int IDS;//进程ID
	private int FIRST=0;
	private int DY=0;
	
	/**构造方法*/
	public ShortProcessScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**获取当前的就绪队列*/
	public List<Process> getReadyList(){
		return ReadyList;
	}
	
	/**获取当前的新建等待队列*/
	public List<Process> getNewWaitList(){
		return NewWaitList;
	}
	
	/**按最短服务时间来排队*/
	private void addProcess(int size, Process NewP) {
		int i;
		if(size==0) {
			ReadyList.add(NewP);
			return;
		}
		for(i=0; i<size; i++) {
			if(NewP.getRunTime()<ReadyList.get(i).getRunTime()) {
				ReadyList.add(i, NewP);
				break;
			}
		}
		if(i==size) {
			ReadyList.add(NewP);
		}
	}
	
	/**动态创建进程*/
	public void dynamicCreateProcess() {
		if(DY!=0)
			return;
		for(int i=0; i<MAXSIZE; i++) {
			int RunTime = (int)(Math.random()*10+1);
			int Priority = (int)(Math.random()*10+1);
			Process p = new Process(IDS,Priority,RunTime);
			this.IDS++;
			addProcess(i,p);
		}
		DY++;
	}
	
	/**短进程调度*/
	public void shortProcessSchedul() {
		int runtime = ReadyList.get(FIRST).getRunTime();
		for(int i=0; i<runtime; i++) {
			ReadyList.get(FIRST).deleteRemainTime();
		}
	}
	
	/**移除已处理完的进程*/
	public void removeProcess() {
		ReadyList.remove(FIRST);
		if(!NewWaitList.isEmpty()) {
			int size = ReadyList.size();
			Process NewP = NewWaitList.get(FIRST);
			addProcess(size,NewP);
			NewWaitList.remove(FIRST);
		}
	}
	
	/**手动创建进程*/
	public void manualCreate() {
		int RunTime = (int)(Math.random()*10+1);
		int Priority = (int)(Math.random()*10+1);
		Process NewP = new Process(IDS,Priority,RunTime);
		this.IDS++;
		int size = ReadyList.size();
		if(size<MAXSIZE) {
			addProcess(size,NewP);
		}else {
			NewWaitList.add(NewP);
		}
		DY=1;
		
	}
	
}
