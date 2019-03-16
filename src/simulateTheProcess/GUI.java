package simulateTheProcess;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.*;

public class GUI extends Application{

	public void start(Stage MainStage) {
		MainPane Main = new MainPane();
		Scene scene = new Scene(Main,1000,650);
		MainStage.setTitle("进程调度");
		MainStage.setScene(scene);
		MainStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
