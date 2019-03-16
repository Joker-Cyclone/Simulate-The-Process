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
	
	/**构造函数*/
	public Process(int ID, int Priority, int RunTime) {
		this.State=STATE_NEW;
		this.ID=ID;
		this.Priority=Priority;
		this.RunTime=RunTime;
		this.RemainTime=RunTime;
	}
	
	/**运行的进程语句*/
	public String run() {
		String s1="当前运行进程\n"
				+ "编号为:"+this.ID+"\n";
		String s2="需要服务时间为:"+this.RunTime+"\n";
		String s3="剩余时间为："+this.RemainTime+"\n";
		String s4="优先级为"+this.Priority+"\n\n";
		String s5=s1+s2+s3+s4;
		return s5;
		
	}
	
	/**获取剩余运行时间*/
	public int getRemainTime() {
		return this.RemainTime;
	}
	
	/**获取ID*/
	public int getId() {
		return this.ID;
	}
	
	/**获取状态*/
	public int getState() {
		return this.State;
	}
	
	/**获取需要服务时间*/
	public int getRunTime() {
		return this.RunTime;
	}
	
	/**获取优先级*/
	public int getPriority() {
		return this.Priority;
	}
	
	/**设为等待阻塞*/
	public void setBlock() {
		this.State=STATE_BLOCKING;
	}
	
	/**设为结束*/
	public void setEnd() {
		this.State=STATE_END;
	}
	
	/**设为运行状态*/
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
