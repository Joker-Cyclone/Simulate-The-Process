package simulateTheProcess;

import java.util.*;

import simulateTheProcess.Process;

public class ShortRemainScheduling {
	private List<Process> ReadyList ;//��������
	private List<Process> NewWaitList;//�½��ȴ�����
	private int MAXSIZE=7;//��������
	private int IDS;//����ID
	private int FIRST=0;
	private int DY=0;
	
	/**���캯��*/
	public ShortRemainScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**��ȡ��ǰ�ľ�������*/
	public List<Process> getReadyList(){
		return this.ReadyList;
	}
	
	/**��ȡ��ǰ���½��ȴ�����*/
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
	
	/**��̬��������*/
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
	
	/**�ֶ���������*/
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
	
	/**���ʣ��ʱ�����*/
	public void ShortRemainSchedul() {
		this.ReadyList.get(FIRST).deleteRemainTime();;
	}
	
	/**��δ������Ľ��̷��ص�����������*/
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
