package View;

import Model.Party;
import Model.ElectionRound.eChangesUserNeedToKnow;
import Model.Party.eType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ViewOption3AddParty extends ViewOption {

	public ViewOption3AddParty(Pane pane, MainView mainViewListener) {
		super(pane, "Add Party", mainViewListener);
		Label nameMsg = new Label("Name:");
		TextField nameField = new TextField();
		Label typeOfParty = new Label("Type:");
		RadioButton btnLeft = new RadioButton("Left");
		RadioButton btnCenter = new RadioButton("Center");
		RadioButton btnRight = new RadioButton("Right");
		ToggleGroup typeToggle = new ToggleGroup();
		btnLeft.setToggleGroup(typeToggle);
		btnCenter.setToggleGroup(typeToggle);
		btnRight.setToggleGroup(typeToggle);
		HBox radioBtnOrder = new HBox();
		radioBtnOrder.setPadding(new Insets(10));
		radioBtnOrder.setSpacing(10);
		Label yearMsg = new Label("Year:(" + Party.DATE_MIN + " Between " + Party.DATE_MAX + ")");
		TextField yearField = new TextField();
		proprtay.add(nameMsg, 0, 0);
		proprtay.add(nameField, 1, 0);
		proprtay.add(yearMsg, 0, 1);
		proprtay.add(yearField, 1, 1);
		proprtay.add(typeOfParty, 0, 2);
		radioBtnOrder.getChildren().addAll(btnLeft, btnCenter, btnRight);
		proprtay.add(radioBtnOrder, 0, 3, 15, 1);
		proprtay.add(btnAdd, 0, 4);
		proprtay.add(msgUser, 0, 5);
		EventHandler<ActionEvent> pressAdd = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				msgUser.setText("");
				if (nameField.getText().isEmpty()) {
					msgUser.setText("Enter Text");
				} else if (!(btnLeft.isSelected()) && !(btnCenter.isSelected()) && !(btnRight.isSelected())) {
					msgUser.setText("Pick Type");
				} else if (!(MainView.checkInputCorrect(yearField.getText()))) {
					msgUser.setText("uncorrect input in year");
				} else if (Integer.parseInt(yearField.getText()) < Party.DATE_MIN
						|| Integer.parseInt(yearField.getText()) > Party.DATE_MAX) {
					msgUser.setText("uncorrect input in year");
				} else {
					eType type;
					if (btnLeft.isSelected()) {
						type = eType.LEFT;
					} else if (btnCenter.isSelected()) {
						type = eType.CENTER;
					} else {
						type = eType.RIGHT;
					}
					int year = Integer.parseInt(yearField.getText());
					mainViewListener.addPartyOption3SendDetailsToMainView(nameField.getText(), type, year);
				}

			}
		};
		btnAdd.setOnAction(pressAdd);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
		msgUser.setText(ans.toString());
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
		} else {
			msgUser.setTextFill(Color.DARKRED);
		}
	}

}
