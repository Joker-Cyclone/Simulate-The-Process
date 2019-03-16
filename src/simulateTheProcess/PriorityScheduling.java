package simulateTheProcess;
import java.util.*;

public class PriorityScheduling {
	private List<Process> ReadyList ;//就绪队列
	private List<Process> NewWaitList;//新建等待队列
	private int MAXSIZE=7;//最大进程数
	private int IDS;//进程ID
	private int FIRST=0;
	private int DY=0;
	
	public PriorityScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**用简单的插入排序来进行排序*/
	private void addProcess(int size, Process NewP) {
		int i;
		if(size==0) {
			ReadyList.add(NewP);
			return;
		}
		for(i=0; i<size; i++) {
			if(NewP.getPriority()>ReadyList.get(i).getPriority()) {
				ReadyList.add(i,NewP);
				break;
			}
		}
		if(i==size) {
			ReadyList.add(NewP);
		}
	}
	
	/**动态生成以优先级为顺序的队列*/
	public void dynamicCreateProcess() {
		if(DY!=0)
			return;
		for(int i=0; i<MAXSIZE; i++) {
			int Priority = (int)(Math.random()*10);
			int RunTime = (int)(Math.random()*10);
			Process NewP = new Process(IDS,Priority,RunTime);
		    NewP.setRunning();
			this.IDS++;
			int j;			
			addProcess(i,NewP);
		}
		DY++;
	}
	
	
	/**手动创建进程*/
	public void manualCreate() {
		int Priority = (int)(Math.random()*10+1);
		int RunTime = (int)(Math.random()*10+1);
		Process NewP = new Process(IDS,Priority,RunTime);
		this.IDS++;
		int size = ReadyList.size();
		if(size<MAXSIZE) {
			NewP.setRunning();
			addProcess(size,NewP);
		}else {
			NewWaitList.add(NewP);
		}
		DY=1;
	}
	
	/**获取当前的就绪队列*/
	public List<Process> getReadyList() {
		return ReadyList;
	}
	
	/**获取当前的新建等待队列*/
	public List<Process> getNewWaitList() {
		return NewWaitList;
	}
	
	/**进行优先级调度*/
	public void prioritySchedul() {
		ReadyList.get(FIRST).deletePriority();
		ReadyList.get(FIRST).deleteRemainTime();
	}
	
	/**将刚运行完的进程重新加入到队列中*/
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
