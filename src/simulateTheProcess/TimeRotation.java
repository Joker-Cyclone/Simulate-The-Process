package simulateTheProcess;
import java.util.*;

public class TimeRotation {

	private List<Process> ReadyList ;//��������
	private List<Process> NewWaitList;//�½��ȴ�����
	private int MAXSIZE=7;//��������
	private int IDS;//����ID
	private int FIRST=0;
	private int DY=0;
	
	public TimeRotation() {
		this.ReadyList= new ArrayList<Process>();
		this.NewWaitList = new ArrayList<Process>();
		this.IDS=0;
	}
	
	/**��̬��������(�ú���ֻ���ڸտ�ʼ����һ�Σ���ֹ���ִ���)*/
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
	
	/**��ȡ��ǰ�ľ������̶���*/
	public List<Process> getReadyList(){
		return ReadyList;
	}
	
	/**��ȡ��ǰ�½��ȴ�����*/
	public List<Process> getNewWaitList(){
		return NewWaitList;
	}
	
	/**ʱ����ת����*/
	public void timeRotate() {
		ReadyList.get(FIRST).deleteRemainTime();
	}
	
	/**��δ��ɵĽ��̷ŵ����е�ĩβ*/
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
	
	/**�ֶ���ӽ���*/
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
	
	/**ʵ�ֶ�̬������GUI��ʵ�֣���ѭ����sleep������ʵ�֣��ٲ��ϸ��¿��е�����*/
}
