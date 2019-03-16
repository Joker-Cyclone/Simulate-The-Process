package simulateTheProcess;

public class PCB {
	private int ID;
	private int Priority;
	private int State;
	private int RunTime;
	private int RemainTime;
	
	private int STATE_NEW=1;
	private int STATE_RUNNING=2;
	private int STATE_BLOCKING=3;
	private int STATE_END=0;
    
    public PCB(int ID, int Priority, int State,int RunTime) {
		this.ID=ID;
		this.Priority=Priority;
		this.RunTime=RunTime;
		this.RemainTime=RunTime;
		this.RemainTime=RunTime;
    }
}
