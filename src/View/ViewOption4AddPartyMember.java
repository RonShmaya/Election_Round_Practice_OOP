package View;

import java.util.List;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewOption4AddPartyMember extends ViewOption {
	private List<Label> listPersons;
	private FlowPane placeToShowCitizen;
	private VBox citizenBlock;
	private List<Label> listPartys;
	private FlowPane placeToShowPartys;
	private VBox PartysBlock;
	private int indexOfCitizen;
	private int indexOfParty;

	public ViewOption4AddPartyMember(Pane pane, MainView mainViewListener) {
		super(pane, "Add PartyMember", mainViewListener);
		listPersons = mainViewListener.getCitizenList();
		borderpane.setBottom(msgUser);
		if (mainViewListener.getInProsessVoting()) {
			borderpane.setDisable(true);
		}
		if (listPersons.isEmpty()) {
			msgUser.setText("there is no citzen, go add please");
		} else {
			listPartys = mainViewListener.getPartyList();
			if (listPartys.isEmpty()) {
				msgUser.setText("there is no Partys, go add please");
			} else {
				citizenBlock = new VBox();
				placeToShowCitizen = new FlowPane();
				PartysBlock = new VBox();
				placeToShowPartys = new FlowPane();
				indexOfCitizen = 0;
				indexOfParty = 0;
				Label msgMiniTitle = new Label("Press on citizen to be party member (solider cannot be party member)");
				citizenBlock.getChildren().add(msgMiniTitle);
				for (Label l : listPersons) {
					placeToShowCitizen.getChildren().add(l);
					pressCitizenEvent(listPersons.indexOf(l));
				}
				citizenBlock.getChildren().add(placeToShowCitizen);
				borderpane.setCenter(citizenBlock);
				Label msgMiniTitleParty = new Label("Pick the party you want to add");
				PartysBlock.getChildren().add(msgMiniTitleParty);
				for (Label l : listPartys) {
					placeToShowPartys.getChildren().add(l);
					pressPartyEvent(listPartys.indexOf(l));
				}
				PartysBlock.getChildren().add(placeToShowPartys);
				PartysBlock.getChildren().add(msgUser);
				borderpane.setBottom(PartysBlock);
				PartysBlock.setVisible(false);
			}
		}
	}

	public void pressCitizenEvent(int index) {
		EventHandler<Event> pressOnCitizen = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				PartysBlock.setVisible(true);
				placeToShowCitizen.setDisable(true);
				placeToShowPartys.setDisable(false);
				indexOfCitizen = index;
				listPersons.get(index).setTextFill(Color.GREEN);
				listPartys.get(indexOfParty).setTextFill(Color.BLACK);
			}
		};
		listPersons.get(index).setOnMouseClicked(pressOnCitizen);
	}

	public void pressPartyEvent(int index) {
		EventHandler<Event> pressOnParty = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				String[] idCitizen = listPersons.get(indexOfCitizen).getText().split(": ");
				int idCitzenCaste = Integer.parseInt(idCitizen[3]);
				mainViewListener.addPartyMemberOption4SendDetailsToMainView(idCitzenCaste, index);
				placeToShowPartys.setDisable(true);
				placeToShowCitizen.setDisable(false);
				listPersons.get(indexOfCitizen).setTextFill(Color.BLACK);
				listPartys.get(index).setTextFill(Color.GREEN);
				indexOfParty = index;
			}
		};
		listPartys.get(index).setOnMouseClicked(pressOnParty);
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

	@Override
	public void clearPane() {
		if (!(listPersons.isEmpty()) && !(listPartys.isEmpty())) {
			listPersons.get(indexOfCitizen).setTextFill(Color.BLACK);
			listPartys.get(indexOfParty).setTextFill(Color.BLACK);
		}
		super.clearPane();
	}
}
