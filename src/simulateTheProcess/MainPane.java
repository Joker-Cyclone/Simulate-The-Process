package simulateTheProcess;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.animation.*;
import javafx.event.*;
import java.util.*;

public class MainPane extends Pane{
	Button TimeRotate = new Button("ʱ����ת����");
	Button PrioritySchedul = new Button("���ȼ�����");
	Button ShortProcess = new Button("��̽�������");
	Button ShortRemain = new Button("���ʣ��ʱ��");
    Button DynamicCreate = new Button("��̬��������");
    Button ManualCreate = new Button("�ֶ���������");
    Button DynamicSchedul = new Button("��̬���Ƚ���");
    Button ManualSchedul = new Button("�ֶ����Ƚ���");
    
    Timeline animation; //���ö�̬��ʾ�Ķ���
    
    ButtonsPane B ;//��ť���
    ReadyThreadMessagePane R ;//����������Ϣ���
    NewWaitMessagePane N;//�½��ȴ�������Ϣ���
    RunningMessagePane Run;//����ʱ��Ϣ���
    
    TimeRotation T;//ʱ����ת���ȶ���
    PriorityScheduling P;//���ȼ����ȶ���
    ShortProcessScheduling S;//��̽������ȵ��ȶ���
    ShortRemainScheduling SR;//���ʣ��ʱ�����
    
    // 1 Ϊѡ��ʱ����ת���ȣ� 2Ϊѡ�����ȼ����ȣ� 3Ϊ��̽������ȵ��ȣ� 4Ϊ���ʣ��ʱ�����ȵ���
    int SELECT=0; 
    int FIRST=0;
    
    /**���캯��*/
    public MainPane() {
    	super();
    	this.SELECT=0;
    	EventHandler<ActionEvent> eventHandler = e->nullRoll();
		KeyFrame k = new KeyFrame(Duration.millis(1000),eventHandler); 
    	this.animation=new Timeline(k);
    	addElements();
    	setButtonAction();
    	
    }
    
    /**����animation�Ŀ�ת����*/
    public void nullRoll() {
    	
    }
    
    /**�������Ԫ��*/
    public void addElements() {
    	/**������ť��岢���*/
    	B=new ButtonsPane(TimeRotate,PrioritySchedul,ShortProcess,ShortRemain,
    			DynamicCreate,ManualCreate,DynamicSchedul,ManualSchedul);
    	this.getChildren().add(B);
    	B.setLayoutX(75);
    	B.setLayoutY(50);
    	
    	/**����������Ϣ��岢���*/
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
    
    /**���ð�ť�¼�*/
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
    
    /**׼������ʱ����ת����*/
    public void Time() {
    	//��ֹ�����Ҷ������춯���Ƿ��������У��������ж����
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	
    	this.SELECT=1;
    	this.T=new TimeRotation();
    	removeMessages();
    }
    
    /**׼���������ȼ�����*/
    public void Priority() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	
    	this.SELECT=2;
    	this.P=new PriorityScheduling();
    	removeMessages();
    }
    
    /**׼�������������ʱ�����*/
    public void ShortTime() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	this.SELECT=3;
    	this.S=new ShortProcessScheduling();
    	removeMessages();
    }
    
    /**׼���������ʣ��ʱ�����*/
    public void ShortRemainTime() {
    	if(animation.getStatus()==Animation.Status.RUNNING) {
    		animation.pause();
    	}
    	this.SELECT=4;
    	this.SR=new ShortRemainScheduling();
    	removeMessages();
    }
    
    /**���������Ϣ������һ��ģ�����*/
    public void removeMessages() {
    	R.removeTheList();
    	N.removeTheList();
    	Run.removeMessage();
    }
    
    /**��̬���ɽ��̰�ť�¼�*/
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
    
    /**�ֶ����ɽ��̰�ť�¼�*/
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
    
    /**���е�����Ϣ���ǵ�ǰ��һλ��������������Ϣ��
     * ����Ϊ��ǰδ�޸Ĺ��Ķ���
     * �����ڽ�����������޸�
     * ��������������Ϣ�б��л���ʣ��һ�����̵���Ϣ
     * */
    
    /**ʱ����ת����*/
    public void TSchedul() {
    	T.timeRotate();
		R.addMessages(T.getReadyList());
		N.addMessage(T.getNewWaitList());
		Run.addMessage(T.getReadyList().get(FIRST).run());
		T.moveBackToList();
    }
    
    /**���ö�̬�¼���ת�����¼�*/
    public void TSchedulAction() {
    	if(T.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	TSchedul();
    }
    
    /**���ȼ�����*/
    public void PSchedul() {
    	P.prioritySchedul();
		R.addMessages(P.getReadyList());
		N.addMessage(P.getNewWaitList());
		Run.addMessage(P.getReadyList().get(FIRST).run());
		P.moveBackToList();
    }
    
    /**���ö�̬���ȼ������¼�*/
    public void PSchedulAction() {
    	if(P.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	PSchedul();
    }
    
    /**��̽������ȵ���*/
    public void SSchedul() {
    	S.shortProcessSchedul();
		R.addMessages(S.getReadyList());
		N.addMessage(S.getNewWaitList());
		Run.addMessage(S.getReadyList().get(FIRST).run());
		S.removeProcess();
    }
    
    /**���ö�̬��̽������ȵ���*/
    public void SSchedulAction() {
    	if(S.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	SSchedul();
    }
    
    /**���ʣ��ʱ�����*/
    public void SRSchedul() {
    	SR.ShortRemainSchedul();
		R.addMessages(SR.getReadyList());
		N.addMessage(SR.getNewWaitList());
		Run.addMessage(SR.getReadyList().get(FIRST).run());
		SR.moveBackToList();
    }
    
    /**���ö�̬���ʣ��ʱ���¼�*/
    public void SRSchedulAction() {
    	if(SR.getReadyList().isEmpty()) {
    		this.animation.pause();
    		return;
    	}
    	SRSchedul();
    }
    
    /**��̬���Ƚ��̰�ť�¼�*/
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
    
    /**�ֶ����Ƚ����¼�*/
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
