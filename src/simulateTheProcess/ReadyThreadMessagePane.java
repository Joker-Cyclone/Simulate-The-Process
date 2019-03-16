package simulateTheProcess;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.util.*;
import javafx.geometry.Insets;

public class ReadyThreadMessagePane extends VBox{

	int MAXSIZE=7;
	
	public ReadyThreadMessagePane() {
		super(15);
		this.setPadding(new Insets(15,15,15,15));
		this.setStyle("-fx-border-corlor:black;"
				+ "-fx-background-color:grey");
		Label a = new Label("就绪队列");
		a.setStyle("-fx-border-corlor:black;"
				+ "-fx-font-size:20px");
		this.getChildren().add(a);
		for(int i=0; i<=MAXSIZE; i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
		this.setStyle("-fx-background-color:lightgrey");
	}
	
	/**展示就绪队列信息*/
	public void addMessages(List<Process> ReadyList) {
		this.getChildren().remove(1, MAXSIZE+1);
		int size = ReadyList.size();
		int i;
		for(i=0; i<size; i++) {
			String id = ""+ReadyList.get(i).getId();
			Label l  = new Label(id);
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			//this.getChildren().remove(size);
			this.getChildren().add(i+1, l);
		}
		for(;i<MAXSIZE;i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
	}
	
	/**移除所有信息*/
	public void removeTheList() {
		this.getChildren().remove(1,MAXSIZE+1);
		
		for(int i=0; i<MAXSIZE; i++) {
			Label l  = new Label("  ");
			l.setStyle("-fx-background-radius: 25;" 
				     + "-fx-border-radius: 25;"
				     + "-fx-font-size:20px;");
			this.getChildren().add(l);
		}
	}
}
