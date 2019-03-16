package simulateTheProcess;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.util.*;
import javafx.geometry.Insets;

public class RunningMessagePane extends VBox{
	int size;
	
	public RunningMessagePane() {
		super();
		this.setPadding(new Insets(15,15,15,15));
		this.size=-1;
	}
	
	public void addMessage(String message) {
		if(size==2) {
			this.getChildren().remove(size);
			this.size--;
		}
		Label l  = new Label(message);
		l.setStyle("-fx-background-radius: 25;" 
			     + "-fx-border-radius: 25;"
			     + "-fx-font-size:20px;");
		this.getChildren().add(0,l);
		this.size++;
	}
	
	public void removeMessage() {
		if(size==-1)
			return;
		this.getChildren().remove(0, size+1);
		size=-1;
		
	}

}
