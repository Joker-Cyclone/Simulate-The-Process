package simulateTheProcess;

public class Process {

	private int STATE_NEW=1;
	private int STATE_RUNNING=2;
	private int STATE_BLOCKING=3;
	private int STATE_END=0;
	
	private int ID;
	private int Priority;
	private int State;
	private int RunTime;
	private int RemainTime;
	
	/**���캯��*/
	public Process(int ID, int Priority, int RunTime) {
		this.State=STATE_NEW;
		this.ID=ID;
		this.Priority=Priority;
		this.RunTime=RunTime;
		this.RemainTime=RunTime;
	}
	
	/**���еĽ������*/
	public String run() {
		String s1="��ǰ���н���\n"
				+ "���Ϊ:"+this.ID+"\n";
		String s2="��Ҫ����ʱ��Ϊ:"+this.RunTime+"\n";
		String s3="ʣ��ʱ��Ϊ��"+this.RemainTime+"\n";
		String s4="���ȼ�Ϊ"+this.Priority+"\n\n";
		String s5=s1+s2+s3+s4;
		return s5;
		
	}
	
	/**��ȡʣ������ʱ��*/
	public int getRemainTime() {
		return this.RemainTime;
	}
	
	/**��ȡID*/
	public int getId() {
		return this.ID;
	}
	
	/**��ȡ״̬*/
	public int getState() {
		return this.State;
	}
	
	/**��ȡ��Ҫ����ʱ��*/
	public int getRunTime() {
		return this.RunTime;
	}
	
	/**��ȡ���ȼ�*/
	public int getPriority() {
		return this.Priority;
	}
	
	/**��Ϊ�ȴ�����*/
	public void setBlock() {
		this.State=STATE_BLOCKING;
	}
	
	/**��Ϊ����*/
	public void setEnd() {
		this.State=STATE_END;
	}
	
	/**��Ϊ����״̬*/
	public void setRunning(){
		this.State=STATE_RUNNING;
	}
	
	public void deleteRemainTime() {
		if(this.RemainTime>0)
		    this.RemainTime--;
	}
	
	public void deletePriority() {
		if(this.Priority>1) {
		    this.Priority--;
		}
	}
	
}
