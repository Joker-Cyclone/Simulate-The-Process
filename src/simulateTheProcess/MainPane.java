package simulateTheProcess;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.animation.*;
import javafx.event.*;
import java.util.*;

public class MainPane extends Pane{
	Button TimeRotate = new Button("时间轮转调度");
	Button PrioritySchedul = new Button("优先级调度");
	Button ShortProcess = new Button("最短进程优先");
	Button ShortRemain = new Button("最短剩余时间");
    Button DynamicCreate = new Button("动态创建进程");
    Button ManualCreate = new Button("手动创建进程");
    Button DynamicSchedul = new Button("动态调度进程");
    Button ManualSchedul = new Button("手动调度进程");
    
    Timeline animation; //设置动态显示的动作
    
    ButtonsPane B ;//按钮面板
    ReadyThreadMessagePane R ;//就绪队列信息面板
    NewWaitMessagePane N;//新建等待队列信息面板
    RunningMessagePane Run;//运行时信息面板
    
    TimeRotation T;//时间轮转调度对象
    PriorityScheduling P;//优先级调度对象
    ShortProcessScheduling S;//最短进程优先调度对象
    ShortRemainScheduling SR;//最短剩余时间调度
    
    // 1 为选择时间轮转调度， 2为选择优先级调度， 3为最短进程优先调度， 4为最短剩余时间优先调度
    int SELECT=0; 
    int FIRST=0;
    
    /**构造函数*/
    public MainPane() {
    	super();
    	this.SELECT=0;
    	EventHandler<ActionEvent> eventHandler = e->nullRoll();
		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler); 
    	this.animation=new Timeline(k);
    	addElements();
    	setButtonAction();
    	
    }
    
    /**用于animation的空转函数*/
    public void nullRoll() {
    	
    }
    
    /**添加所有元素*/
    public void addElements() {
    	/**创建按钮面板并添加*/
    	B=new ButtonsPane(TimeRotate,PrioritySchedul,ShortProcess,ShortRemain,
    			DynamicCreate,ManualCreate,DynamicSchedul,ManualSchedul);
    	this.getChildren().add(B);
    	B.setLayoutX(75);
    	B.setLayoutY(50);
    	
    	/**创建三种信息面板并添加*/
    	R = new ReadyThreadMessagePane();
    	N = new NewWaitMessagePane();
    	Run = new RunningMessagePane();
    	this.getChildren().add(R);
    	this.getChildren().add(N);
    	this.getChildren().add(Run);
    	R.setLayoutX(75);
    	R.setLayoutY(150);
    	N.setLayoutX(250);
    	N.setLayoutY(150);
    	Run.setLayoutX(500);
    	Run.setLayoutY(120);    	
    }
    
    /**设置按钮事件*/
    public void setButtonAction() {
    	 this.TimeRotate.setOnAction(e->Time());
    	 this.PrioritySchedul.setOnAction(e->Priority());
    	 this.ShortProcess.setOnAction(e->ShortTime());
    	 this.ShortRemain.setOnAction(e->ShortRemainTime());
    	 this.DynamicCreate.setOnAction(e->DynamicCreateProcess());
    	 this.ManualCreate.setOnAction(e->ManualCreateProcess());
    	 this.DynamicSchedul.setOnAction(e->DynamicSchedulProcess());
    	 this.ManualSchedul.setOnAction(e->manualSchedulProcess());
    }
    
    /**准备进入时间轮转调度*/
    public void Time() {
    	//防止动画乱动，考察动画是否在运行中，下面所有都如此
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	
    	this.SELECT=1;
    	this.T=new TimeRotation();
    	removeMessages();
    }
    
    /**准备进入优先级调度*/
    public void Priority() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	
    	this.SELECT=2;
    	this.P=new PriorityScheduling();
    	removeMessages();
    }
    
    /**准备进入最短运行时间调度*/
    public void ShortTime() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	this.SELECT=3;
    	this.S=new ShortProcessScheduling();
    	removeMessages();
    }
    
    /**准备进入最短剩余时间调度*/
    public void ShortRemainTime() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	this.SELECT=4;
    	this.SR=new ShortRemainScheduling();
    	removeMessages();
    }
    
    /**清空所有信息进入下一次模拟调度*/
    public void removeMessages() {
    	R.removeTheList();
    	N.removeTheList();
    	Run.removeMessage();
    }
    
    /**动态生成进程按钮事件*/
    public void DynamicCreateProcess() {
    	if(this.SELECT==1) {
    		T.dynamicCreateProcess();
    		R.addMessages(T.getReadyList());
    		N.addMessage(T.getNewWaitList());
    		
    	}else if(this.SELECT==2) {
    		P.dynamicCreateProcess();
    		R.addMessages(P.getReadyList());
    		N.addMessage(P.getNewWaitList());
    		
    	}else if(this.SELECT==3) {
    		S.dynamicCreateProcess();
    		R.addMessages(S.getReadyList());
    		N.addMessage(S.getNewWaitList());
    	}else if(this.SELECT==4) {
    		SR.dynamicCreateProcess();
    		R.addMessages(SR.getReadyList());
    		N.addMessage(SR.getNewWaitList());
    	}
    }
    
    /**手动生成进程按钮事件*/
    public void ManualCreateProcess() {
    	if(this.SELECT==1) {
    		T.manualCreate();
    		R.addMessages(T.getReadyList());
    		N.addMessage(T.getNewWaitList());
    		
    	}else if(this.SELECT==2) {
    		P.manualCreate();
    		R.addMessages(P.getReadyList());
    		N.addMessage(P.getNewWaitList());
    		
    	}else if(this.SELECT==3) {
    		S.manualCreate();
    		R.addMessages(S.getReadyList());
    		N.addMessage(S.getNewWaitList());
    	}else if(this.SELECT==4) {
    		SR.manualCreate();
    		R.addMessages(SR.getReadyList());
    		N.addMessage(SR.getNewWaitList());
    	}
    }
    
    /**所有调度信息都是当前第一位进程运行完后的信息，
     * 队列为当前未修改过的队列
     * 队列在进程运行完后修改
     * 故最后就绪队列信息列表中还会剩下一个进程的信息
     * */
    
    /**时间轮转调度*/
    public void TSchedul() {
    	T.timeRotate();
		R.addMessages(T.getReadyList());
		N.addMessage(T.getNewWaitList());
		Run.addMessage(T.getReadyList().get(FIRST).run());
		T.moveBackToList();
    }
    
    /**设置动态事件轮转调度事件*/
    public void TSchedulAction() {
    	if(T.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	TSchedul();
    }
    
    /**优先级调度*/
    public void PSchedul() {
    	P.prioritySchedul();
		R.addMessages(P.getReadyList());
		N.addMessage(P.getNewWaitList());
		Run.addMessage(P.getReadyList().get(FIRST).run());
		P.moveBackToList();
    }
    
    /**设置动态优先级调度事件*/
    public void PSchedulAction() {
    	if(P.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	PSchedul();
    }
    
    /**最短进程优先调度*/
    public void SSchedul() {
    	S.shortProcessSchedul();
		R.addMessages(S.getReadyList());
		N.addMessage(S.getNewWaitList());
		Run.addMessage(S.getReadyList().get(FIRST).run());
		S.removeProcess();
    }
    
    /**设置动态最短进程优先调度*/
    public void SSchedulAction() {
    	if(S.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	SSchedul();
    }
    
    /**最短剩余时间调度*/
    public void SRSchedul() {
    	SR.ShortRemainSchedul();
		R.addMessages(SR.getReadyList());
		N.addMessage(SR.getNewWaitList());
		Run.addMessage(SR.getReadyList().get(FIRST).run());
		SR.moveBackToList();
    }
    
    /**设置动态最短剩余时间事件*/
    public void SRSchedulAction() {
    	if(SR.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	SRSchedul();
    }
    
    /**动态调度进程按钮事件*/
    public void DynamicSchedulProcess() {
    	
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    		return;
    	}
    	if(this.SELECT==1) {
    		EventHandler<ActionEvent> eventHandler = e->TSchedulAction();
    		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler);
            animation = new Timeline(k);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
    	}else if(this.SELECT==2) {
    		EventHandler<ActionEvent> eventHandler = e->PSchedulAction();
    		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler);
            animation = new Timeline(k);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();    		
    	}else if(this.SELECT==3) {
    		EventHandler<ActionEvent> eventHandler = e->SSchedulAction();
    		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler);
            animation = new Timeline(k);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
    	}else if(SELECT ==4){
    		EventHandler<ActionEvent> eventHandler = e->SRSchedulAction();
    		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler);
            animation = new Timeline(k);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
    	}
    }
    
    /**手动调度进程事件*/
    public void manualSchedulProcess() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	if(SELECT ==1) {
    		if(!T.getReadyList().isEmpty())
    		    TSchedul();
    	}else if(SELECT ==2) { 
    		if(!P.getReadyList().isEmpty())
    		    PSchedul();
    	}else if(SELECT ==3) {
    		if(!S.getReadyList().isEmpty())
    		    SSchedul();
    	}else if(SELECT ==4){
    		if(!SR.getReadyList().isEmpty())
    		    SRSchedul();
    	}
    }
}
