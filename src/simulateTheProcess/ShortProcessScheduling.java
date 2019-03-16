package simulateTheProcess;

import java.util.ArrayList;
import java.util.List;

public class ShortProcessScheduling {
	private List<Process> ReadyList ;//��������
	private List<Process> NewWaitList;//�½��ȴ�����
	private int MAXSIZE=7;//��������
	private int IDS;//����ID
	private int FIRST=0;
	private int DY=0;
	
	/**���췽��*/
	public ShortProcessScheduling() {
		this.ReadyList=new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
        this.IDS=0;
	}
	
	/**��ȡ��ǰ�ľ�������*/
	public List<Process> getReadyList(){
		return ReadyList;
	}
	
	/**��ȡ��ǰ���½��ȴ�����*/
	public List<Process> getNewWaitList(){
		return NewWaitList;
	}
	
	/**����̷���ʱ�����Ŷ�*/
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
	
	/**�̽��̵���*/
	public void shortProcessSchedul() {
		int runtime = ReadyList.get(FIRST).getRunTime();
		for(int i=0; i<runtime; i++) {
			ReadyList.get(FIRST).deleteRemainTime();
		}
	}
	
	/**�Ƴ��Ѵ�����Ľ���*/
	public void removeProcess() {
		ReadyList.remove(FIRST);
		if(!NewWaitList.isEmpty()) {
			int size = ReadyList.size();
			Process NewP = NewWaitList.get(FIRST);
			addProcess(size,NewP);
			NewWaitList.remove(FIRST);
		}
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
	
}
