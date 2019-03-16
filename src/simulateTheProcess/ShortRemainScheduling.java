package simulateTheProcess;

import java.util.*;

import simulateTheProcess.Process;

public class ShortRemainScheduling {
	private List<Process> ReadyList ;//就绪队列
	private List<Process> NewWaitList;//新建等待队列
	private int MAXSIZE=7;//最大进程数
	private int IDS;//进程ID
	private int FIRST=0;
	private int DY=0;
	
	/**构造函数*/
	public ShortRemainScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**获取当前的就绪队列*/
	public List<Process> getReadyList(){
		return this.ReadyList;
	}
	
	/**获取当前的新建等待队列*/
	public List<Process> getNewWaitList(){
		return this.NewWaitList;
	}
	
	private void addProcess(int size, Process NewP) {
		int i;
		if(size==0) {
			ReadyList.add(NewP);
			return;
		}
		for(i=0; i<size; i++) {
			if(NewP.getRemainTime()<ReadyList.get(i).getRemainTime()) {
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
	
	/**最短剩余时间调度*/
	public void ShortRemainSchedul() {
		this.ReadyList.get(FIRST).deleteRemainTime();;
	}
	
	/**将未运行完的进程返回到就绪队列中*/
	public void moveBackToList() {
		Process p = ReadyList.get(FIRST);
		ReadyList.remove(FIRST);
		int size = ReadyList.size();
		
		/**如果剩余的时间大于零，则返回就绪队列*/
		if(p.getRemainTime()>0) {
		    addProcess(size,p);
		}
		
		size=ReadyList.size();
		/**如果当前的就绪队列中有空位，则从新建等待队列中找一个进程来插入*/
		if(size<MAXSIZE) {
			if(!NewWaitList.isEmpty()) {
				Process NewP = NewWaitList.get(FIRST);
				NewP.setRunning();
				NewWaitList.remove(this.FIRST);
				addProcess(size,NewP);
			}
		}
	} 
	
	
}
