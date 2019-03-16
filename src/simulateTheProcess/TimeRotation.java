package simulateTheProcess;
import java.util.*;

public class TimeRotation {

	private List<Process> ReadyList ;//就绪队列
	private List<Process> NewWaitList;//新建等待队列
	private int MAXSIZE=7;//最大进程数
	private int IDS;//进程ID
	private int FIRST=0;
	private int DY=0;
	
	public TimeRotation() {
		this.ReadyList= new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
		this.IDS=0;
	}
	
	/**动态创建进程(该函数只能在刚开始调度一次，防止出现错误)*/
	public void dynamicCreateProcess() {
		if(DY!=0)
			return;
		for(int i=0; i<MAXSIZE; i++) {
			int RunTime = (int)(Math.random()*10+1);
			int Priority = (int)(Math.random()*10+1);
			Process p = new Process(IDS,Priority,RunTime);
			p.setRunning();
			ReadyList.add(p);
			this.IDS++;
		}
		DY++;
	}
	
	/**获取当前的就绪进程队列*/
	public List<Process> getReadyList(){
		return ReadyList;
	}
	
	/**获取当前新建等待队列*/
	public List<Process> getNewWaitList(){
		return NewWaitList;
	}
	
	/**时间轮转调度*/
	public void timeRotate() {
		ReadyList.get(FIRST).deleteRemainTime();
	}
	
	/**将未完成的进程放到队列的末尾*/
	public void moveBackToList() {
		Process p = ReadyList.get(FIRST);
		ReadyList.remove(FIRST);
		if(p.getRemainTime()>0) {
		    ReadyList.add(p);
		}
		if(this.ReadyList.size()<this.MAXSIZE) {
			if(!NewWaitList.isEmpty()) {
				Process NewP = NewWaitList.get(FIRST);
				NewWaitList.remove(FIRST);
				NewP.setRunning();
				ReadyList.add(NewP);
			}
		}
	}
	
	/**手动添加进程*/
	public void manualCreate() {
		int RunTime = (int)(Math.random()*10);
		int Priority= (int)(Math.random()*10);
		Process NewP = new Process(this.IDS,Priority,RunTime);
		this.IDS++;
		if(ReadyList.size()<this.MAXSIZE) {
			ReadyList.add(NewP);
		}else {
			NewWaitList.add(NewP);
		}
		DY=1;
	}
	
	/**实现动态调度在GUI中实现，用循环加sleep函数来实现，再不断更新框中的内容*/
}
