package View;

import java.util.List;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewOption8ElectionRound extends ViewOption {
	private boolean isElectionWas;
	private List<Label> listPersons;
	private FlowPane placeToShowCitizen;
	private VBox citizenBlock;
	private List<Label> listPartys;
	private FlowPane placeToShowPartys;
	private VBox PartysBlock;
	private int indexOfCitizen;
	private int indexOfParty;

	public ViewOption8ElectionRound(Pane pane, MainView mainViewListener) {
		super(pane, "Election Round", mainViewListener);
		isElectionWas = mainViewListener.getIsWasElection();
		borderpane.setCenter(msgUser);
		placeToShowCitizen = new FlowPane();
		placeToShowPartys = new FlowPane();
		citizenBlock = new VBox();
		PartysBlock = new VBox();
		listPersons = mainViewListener.getCitizenList();
		listPartys = mainViewListener.getPartyList();
		if (isElectionWas) {
			msgUser.setText("Eleaction Already Finished");

		} else {
			if (listPersons.isEmpty()) {
				msgUser.setText("there is not have citizens, go adding ");
			} else if (listPartys.isEmpty()) {
				msgUser.setText("there is not have Partys, go adding ");
			} else {
				btnAdd.setText("Finish");
				placeToShowPartys.setDisable(true);
				Label msg = new Label(
						"press Citizen to vote,after that on a Party, press Finish---> All Citizen that want to vote,vote");
				citizenBlock.getChildren().addAll(msg, placeToShowCitizen, btnAdd, msgUser);
				PartysBlock.getChildren().add(placeToShowPartys);
				borderpane.setCenter(citizenBlock);
				borderpane.setBottom(PartysBlock);
				eventsAndAdding();
			}
		}
	}

	private void eventsAndAdding() {
		for (Label l : listPersons) {
			placeToShowCitizen.getChildren().add(l);
			EventHandler<Event> onTouchCitizen = new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					if (l.getTextFill() == Color.GREEN) {
						msgUser.setText("This Citizen Already Vote");
					} else {
						mainViewListener.setInProsessVoting(true);
						msgUser.setText("");
						l.setTextFill(Color.GREEN);
						placeToShowCitizen.setDisable(true);
						placeToShowPartys.setDisable(false);
						indexOfCitizen = listPersons.indexOf(l);
						btnAdd.setDisable(true);
					}
				}
			};
			l.setOnMouseClicked(onTouchCitizen);
		}
		for (Label l : listPartys) {
			placeToShowPartys.getChildren().add(l);
			EventHandler<Event> onTouchParty = new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					placeToShowCitizen.setDisable(false);
					placeToShowPartys.setDisable(true);
					btnAdd.setDisable(false);
					mainViewListener.updateMainViewWantToVote(indexOfCitizen, true);
					mainViewListener.updateMainViewSelection(indexOfCitizen, listPartys.indexOf(l));
				}
			};
			l.setOnMouseClicked(onTouchParty);
		}
		EventHandler<ActionEvent> pressFinish = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				btnAdd.setDisable(true);
				placeToShowCitizen.setDisable(true);
				placeToShowPartys.setDisable(true);
				mainViewListener.updateMainViewIsWasElections();
				mainViewListener.setInProsessVoting(false);
				for (Label l : listPersons) {
					if (l.getTextFill() != Color.GREEN) {
						mainViewListener.updateMainViewWantToVote(listPersons.indexOf(l), false);
						l.setTextFill(Color.RED);
					}
				}
			}
		};
		btnAdd.setOnAction(pressFinish);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
	}

	@Override
	public void clearPane() {
		if (!(placeToShowPartys.isDisable())) {
			if (!(listPersons.isEmpty())) {
				listPersons.get(indexOfCitizen).setTextFill(Color.BLACK);
			}
		}
		if (mainViewListener.getIsWasElection()) {
			for (Label l : listPersons) {
				l.setTextFill(Color.BLACK);
			}
		}
		super.clearPane();
	}
}
