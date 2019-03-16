package simulateTheProcess;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ButtonsPane extends HBox { 
	
	public ButtonsPane(Button b1, Button b2, Button b3, Button b4 ,Button b5, Button b6, Button b7, Button b8) {
		super(15);
		this.getChildren().add(b1);
		this.getChildren().add(b2);
		this.getChildren().add(b3);
		this.getChildren().add(b4);
		this.getChildren().add(b5);
		this.getChildren().add(b6);
		this.getChildren().add(b7);
		this.getChildren().add(b8);
	}

}
