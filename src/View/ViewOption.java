package View;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class ViewOption {
	protected Pane pane;
	protected Label titleLabel;
	protected BorderPane borderpane;
	protected GridPane proprtay;
	protected MainView mainViewListener;
	protected Label msgUser;
	protected Button btnAdd;

	public ViewOption(Pane pane, String tital, MainView mainViewListener) {
		this.pane = pane;
		this.mainViewListener = mainViewListener;
		titleLabel = new Label(tital);
		borderpane = new BorderPane();
		proprtay = new GridPane();
		proprtay.setHgap(10);
		proprtay.setVgap(10);
		titleLabel.setFont(new Font(35));
		borderpane.setTop(titleLabel);
		borderpane.setLeft(proprtay);
		pane.getChildren().add(borderpane);
		msgUser = new Label();
		msgUser.setTextFill(Color.DARKRED);
		btnAdd = new Button("Add");
	}

	public abstract void mainViewUpdateOption(eChangesUserNeedToKnow ans);

	public void clearPane() {
		pane.getChildren().remove(borderpane);
	}
}
