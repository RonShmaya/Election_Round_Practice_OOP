package ControlerAndProgram;

import Model.ElectionModel;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView view = new MainView(primaryStage);
		ElectionModel model = new ElectionModel();
		ElectionController controller = new ElectionController(model, view);
	}
}
