package simulateTheProcess;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.util.*;
import javafx.geometry.Insets;

public class NewWaitMessagePane extends VBox{
	int MAXSIZE=7;
	
	public NewWaitMessagePane() {
		super(15);
		this.setPadding(new Insets(15,15,15,15));
		this.setStyle("-fx-background-radius:0;"
				+ "-fx-border-corlor:black;"
				+ "-fx-background-color:lightgrey");
		Label a = new Label("新建等待队列");
		this.getChildren().add(a);
		a.setStyle("-fx-background-radius:0;"
				+ "-fx-border-corlor:black;"
				+ "-fx-font-size:20px");
		for(int i=0; i<=MAXSIZE; i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
	}
	
	/**添加当前新建等待函数*/
	public void addMessage(List<Process> NewWaitList) {
		this.getChildren().remove(1,MAXSIZE+1);
		int size = NewWaitList.size();
		if(size>MAXSIZE) {
			size=MAXSIZE;
		}
		int i;
		for(i=0; i<size; i++) {
			String id = ""+NewWaitList.get(i).getId();
			Label l  = new Label(id);
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(i+1, l);
		}
		for(;i<MAXSIZE; i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
	}
	
	public void removeTheList() {
		this.getChildren().remove(1,MAXSIZE+1);
		for(int i=1; i<=MAXSIZE; i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
	}

}
