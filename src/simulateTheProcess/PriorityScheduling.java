package simulateTheProcess;
import java.util.*;

public class PriorityScheduling {
	private List<Process> ReadyList ;//��������
	private List<Process> NewWaitList;//�½��ȴ�����
	private int MAXSIZE=7;//��������
	private int IDS;//����ID
	private int FIRST=0;
	private int DY=0;
	
	public PriorityScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**�ü򵥵Ĳ�����������������*/
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
	
	/**��̬���������ȼ�Ϊ˳��Ķ���*/
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
	
	
	/**�ֶ���������*/
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
	
	/**��ȡ��ǰ�ľ�������*/
	public List<Process> getReadyList() {
		return ReadyList;
	}
	
	/**��ȡ��ǰ���½��ȴ�����*/
	public List<Process> getNewWaitList() {
		return NewWaitList;
	}
	
	/**�������ȼ�����*/
	public void prioritySchedul() {
		ReadyList.get(FIRST).deletePriority();
		ReadyList.get(FIRST).deleteRemainTime();
	}
	
	/**����������Ľ������¼��뵽������*/
	public void moveBackToList() {
		Process p = ReadyList.get(FIRST);
		ReadyList.remove(FIRST);
		int size = ReadyList.size();
		
		/**���ʣ���ʱ������㣬�򷵻ؾ�������*/
		if(p.getRemainTime()>0) {
		    addProcess(size,p);
		}
		
		size=ReadyList.size();
		/**�����ǰ�ľ����������п�λ������½��ȴ���������һ������������*/
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
