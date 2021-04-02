package View;

import Kind_Of_Persons.RegularCitizen;
import Kind_Of_Persons.SickCoronaPerson;
import Kind_Of_Persons.Solider;
import Kind_Of_Persons.SoliderSickCorona;
import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class ViewOption1AddBallot extends ViewOption {

	public ViewOption1AddBallot(Pane pane, MainView mainViewListener) {
		super(pane, "Add Ballot", mainViewListener);
		Label addressMsg = new Label("Ballot Address:");
		TextField addressField = new TextField();
		Label type = new Label("Type:");
		RadioButton typeRegularBallot = new RadioButton("Regular Ballot");
		RadioButton typeCoronaBallot = new RadioButton("Corona Ballot");
		RadioButton typeSolidersBallot = new RadioButton("Soliders Ballot");
		RadioButton typeSolidersCoronaBallot = new RadioButton("Soliders Corona Ballot");
		ToggleGroup radioGroup = new ToggleGroup();
		typeRegularBallot.setToggleGroup(radioGroup);
		typeCoronaBallot.setToggleGroup(radioGroup);
		typeSolidersBallot.setToggleGroup(radioGroup);
		typeSolidersCoronaBallot.setToggleGroup(radioGroup);
		proprtay.add(addressMsg, 0, 0);
		proprtay.add(addressField, 1, 0);
		proprtay.add(type, 0, 1);
		HBox radioOrder = new HBox();
		radioOrder.setPadding(new Insets(10));
		radioOrder.setSpacing(10);
		radioOrder.getChildren().addAll(typeRegularBallot, typeCoronaBallot, typeSolidersBallot,
				typeSolidersCoronaBallot);
		proprtay.add(radioOrder, 0, 2, 15, 1);
		proprtay.add(btnAdd, 0, 3);
		proprtay.add(msgUser, 0, 4);

		EventHandler<ActionEvent> pressBtnAdd = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (typeRegularBallot.isSelected() || typeCoronaBallot.isSelected() || typeSolidersBallot.isSelected()
						|| typeSolidersCoronaBallot.isSelected()) {
					if ((addressField.getText().isEmpty())) {
						msgUser.setText("Enter Text");
					} else {
						String kind;
						if (typeRegularBallot.isSelected()) {
							kind = RegularCitizen.class.getSimpleName();
						} else if (typeCoronaBallot.isSelected()) {
							kind = SickCoronaPerson.class.getSimpleName();
						} else if (typeSolidersBallot.isSelected()) {
							kind = Solider.class.getSimpleName();
						} else {
							kind = SoliderSickCorona.class.getSimpleName();
						}
						mainViewListener.addBallotOption1SendDetailsToMainView(addressField.getText(), kind);
					}
				} else {
					msgUser.setText("Pick Type Of Ballot");
				}
			}
		};
		btnAdd.setOnAction(pressBtnAdd);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgUser.setTextFill(Color.GREEN);
		} else {
			msgUser.setTextFill(Color.DARKRED);
		}
		msgUser.setText(ans.toString());
	}
}
