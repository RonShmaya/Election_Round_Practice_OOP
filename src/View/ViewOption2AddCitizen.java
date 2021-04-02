package View;

import java.time.LocalDate;
import java.util.List;

import Exseptions.AgeException;
import Exseptions.IdException;
import Kind_Of_Persons.Person;
import Kind_Of_Persons.RegularCitizen;
import Kind_Of_Persons.SickCoronaPerson;
import Kind_Of_Persons.Solider;
import Kind_Of_Persons.SoliderSickCorona;
import Model.Ballot;
import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewOption2AddCitizen extends ViewOption {
	private VBox sickGroup;
	private RadioButton yes;
	private RadioButton no;
	private TextField birthField;
	private VBox soliderDetails;
	private FlowPane ballotsDetails;
	private TextField nameField;
	private TextField IdField;
	private Label pressMsg;
	private TextField numDaysSicknesField;
	private RadioButton yesDressOk;
	private RadioButton noDressOk;
	private RadioButton yesWeapon;
	private RadioButton noWeapon;
	private Label emptyMsg;
	private Person citizenToAdd;
	private Label exseptionMsg;
	private Label msgFinal;
	private Label lastBallotPressing;

	public ViewOption2AddCitizen(Pane pane, MainView mainViewListener) {
		super(pane, "Add Citizen", mainViewListener);
		Label nameMsg = new Label("Name:");
		nameField = new TextField();
		Label idMsg = new Label("Id:");
		IdField = new TextField();
		Label birthMsg = new Label(
				"Birth:(" + Person.MIN_BIRTHDAY_INPUT + " between " + (LocalDate.now().getYear() - 18) + ")");
		birthField = new TextField();
		Label sick = new Label("Sick:");
		yes = new RadioButton("Yes");
		no = new RadioButton("No");
		ToggleGroup radioGroup = new ToggleGroup();
		yes.setToggleGroup(radioGroup);
		no.setToggleGroup(radioGroup);
		HBox radioShow = new HBox();
		radioShow.setSpacing(15);
		radioShow.getChildren().addAll(sick, yes, no);
		proprtay.add(nameMsg, 0, 0);
		proprtay.add(nameField, 1, 0);
		proprtay.add(idMsg, 0, 1);
		proprtay.add(IdField, 1, 1);
		proprtay.add(birthMsg, 0, 2);
		proprtay.add(birthField, 1, 2);
		proprtay.add(new Label("(Solider 1999+)"), 2, 2);
		proprtay.add(radioShow, 0, 3, 15, 1);
		// solider option
		Label solider = new Label("Solider:");
		Label weaponMsg = new Label("Carry Weapon:");
		yesWeapon = new RadioButton("Yes");
		noWeapon = new RadioButton("No");
		ToggleGroup radioGroupWeapon = new ToggleGroup();
		yesWeapon.setToggleGroup(radioGroupWeapon);
		noWeapon.setToggleGroup(radioGroupWeapon);
		HBox radioWeaponYesNo = new HBox();
		radioWeaponYesNo.setSpacing(15);
		radioWeaponYesNo.getChildren().addAll(weaponMsg, yesWeapon, noWeapon);
		soliderDetails = new VBox();
		soliderDetails.setSpacing(10);
		soliderDetails.getChildren().addAll(solider, radioWeaponYesNo);
		soliderDetails.setVisible(false);
		// sick option
		Label numDaysSicknesMsg = new Label("Days of Sicknes:");
		numDaysSicknesField = new TextField();
		Label dressOk = new Label("Dress saftey:");
		yesDressOk = new RadioButton("Yes");
		noDressOk = new RadioButton("No");
		ToggleGroup radioGroupDress = new ToggleGroup();
		yesDressOk.setToggleGroup(radioGroupDress);
		noDressOk.setToggleGroup(radioGroupDress);
		HBox radioYesNo = new HBox();
		radioYesNo.setSpacing(10);
		radioYesNo.getChildren().addAll(dressOk, yesDressOk, noDressOk);
		HBox dressDatials = new HBox();
		dressDatials.setSpacing(10);
		dressDatials.getChildren().addAll(numDaysSicknesMsg, numDaysSicknesField);
		sickGroup = new VBox();
		sickGroup.getChildren().addAll(dressDatials, radioYesNo);
		sickGroup.setVisible(false);
		// solider and sick place
		HBox soliderAndSickProperties = new HBox();
		soliderAndSickProperties.setSpacing(15);
		soliderAndSickProperties.getChildren().addAll(sickGroup, soliderDetails);
		proprtay.add(soliderAndSickProperties, 0, 4, 15, 1);
		proprtay.add(btnAdd, 0, 5);
		proprtay.add(msgUser, 0, 6, 15, 1);
		pressMsg = new Label("Saving Detailes... , Press Ballot To ADD:");
		pressMsg.setVisible(false);
		emptyMsg = new Label();
		proprtay.add(pressMsg, 0, 7, 15, 1);
		exseptionMsg = new Label();
		exseptionMsg.setTextFill(Color.RED);
		exseptionMsg.setVisible(false);
		proprtay.add(exseptionMsg, 0, 9, 15, 1);
		ballotsDetails = new FlowPane();
		ballotsDetails.setVgap(10);
		ballotsDetails.setHgap(10);
		msgFinal = new Label();
		proprtay.add(msgFinal, 1, 6, 15, 1);
		lastBallotPressing = new Label();
		events();

	}

	private void events() {
		EventHandler<Event> pressSickYes = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				sickGroup.setVisible(true);

			}
		};
		EventHandler<Event> pressSickNo = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				sickGroup.setVisible(false);
			}
		};
		EventHandler<ActionEvent> pressAddBtn = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				msgUser.setText("");
				msgFinal.setText("");
				lastBallotPressing.setTextFill(Color.BLACK);
				exseptionMsg.setVisible(false);
				msgUser.setTextFill(Color.DARKRED);
				if (nameField.getText().isEmpty()) {
					msgUser.setText("name cannot be empty");
				} else if (!(MainView.checkInputCorrect(IdField.getText()))) {
					msgUser.setText("Id must be only digit");
				} else if (!(yes.isSelected()) && !(no.isSelected())) {
					msgUser.setText("Must select If Sick Or Not");
				} else {
					if (!(MainView.checkInputCorrect(birthField.getText()))) {
						msgUser.setText("Birth must be only digit");
					} else {
						int birth = Integer.parseInt(birthField.getText());
						if (LocalDate.now().getYear() - birth <= Solider.ARMY_AGE_MAX) {// if he solider
							soliderDetails.setVisible(true);
						} else {
							soliderDetails.setVisible(false);
						}
						nextAddingDatails();
					}
				}

			}

		};
		yes.setOnMouseClicked(pressSickYes);
		no.setOnMouseClicked(pressSickNo);
		btnAdd.setOnAction(pressAddBtn);
	}

	private void nextAddingDatails() {
		try {
			if (!(sickGroup.isVisible()) && !(soliderDetails.isVisible())) {
				msgUser.setText("");
				citizenToAdd = new RegularCitizen(nameField.getText(), Integer.parseInt(IdField.getText()),
						Integer.parseInt(birthField.getText()), -1);
				showTheBallotTypes(mainViewListener.getBallotListReg(), RegularCitizen.class.getSimpleName());

			} else if (sickGroup.isVisible() && !(soliderDetails.isVisible())) {
				if (sickDetailsOk()) {
					msgUser.setText("");
					citizenToAdd = new SickCoronaPerson(nameField.getText(), Integer.parseInt(IdField.getText()),
							Integer.parseInt(birthField.getText()), -1);
					((SickCoronaPerson) citizenToAdd).isHeProtected(true);
					((SickCoronaPerson) citizenToAdd)
							.numberDaysOfSicknes(Integer.parseInt(numDaysSicknesField.getText()));
					showTheBallotTypes(mainViewListener.getBallotListSick(), SickCoronaPerson.class.getSimpleName());
				}
			} else if (!(sickGroup.isVisible()) && soliderDetails.isVisible()) {
				if (soliderDetailsOk()) {
					msgUser.setText("");
					citizenToAdd = new Solider(nameField.getText(), Integer.parseInt(IdField.getText()),
							Integer.parseInt(birthField.getText()), -1);
					((Solider) citizenToAdd).carryWeapon(false);
					showTheBallotTypes(mainViewListener.getBallotListSolider(), Solider.class.getSimpleName());
				}
			} else {
				if (soliderDetailsOk() && sickDetailsOk()) {
					msgUser.setText("");
					citizenToAdd = new SoliderSickCorona(nameField.getText(), Integer.parseInt(IdField.getText()),
							Integer.parseInt(birthField.getText()), -1);
					((SoliderSickCorona) citizenToAdd)
							.numberDaysOfSicknes(Integer.parseInt(numDaysSicknesField.getText()));
					((SoliderSickCorona) citizenToAdd).isHeProtected(true);
					((SoliderSickCorona) citizenToAdd).carryWeapon(false);
					showTheBallotTypes(mainViewListener.getBallotListSoliderSick(),
							SoliderSickCorona.class.getSimpleName());
				}
			}
		} catch (IdException e) {
			exseptionMsg.setText("Exseption ---> " + e.getMessage());
			exseptionMsg.setVisible(true);
		} catch (AgeException e) {
			exseptionMsg.setText("Exseption ---> " + e.getMessage());
			exseptionMsg.setVisible(true);
		}
	}

	private boolean sickDetailsOk() {
		if (!(MainView.checkInputCorrect(numDaysSicknesField.getText()))) {
			msgUser.setText("days of sicknes must be only digit");
			return false;
		} else if (!(noDressOk.isSelected()) && !(yesDressOk.isSelected())) {
			msgUser.setText("pick if dress ok or not ");
			return false;
		} else if (noDressOk.isSelected()) {
			msgUser.setText("sick person must dress ok");
			return false;
		}
		return true;
	}

	private boolean soliderDetailsOk() {
		if (!(noWeapon.isSelected()) && !(yesWeapon.isSelected())) {
			Label soliPick = new Label("Pick if weapon or not");
			soliPick.setTextFill(Color.GREEN);
			soliderDetails.getChildren().add(soliPick);
			return false;
		} else if (yesWeapon.isSelected()) {
			msgUser.setText("solider with weapon cant vote");
			return false;
		}
		return true;
	}

	private void showTheBallotTypes(List<Label> ballotList, String kind) {
		ballotsDetails.getChildren().clear();
		emptyMsg.setText("");
		pressMsg.setVisible(false);
		if (ballotList.isEmpty()) {
			emptyMsg = new Label("there is no Ballot on type " + kind + " , go Adding");
			emptyMsg.setTextFill(Color.DARKRED);
			proprtay.add(emptyMsg, 0, 8, 15, 1);
		} else {
			for (Label l : ballotList) {
				ballotsDetails.getChildren().add(l);
				addEventOnPressBallot(ballotList, ballotList.indexOf(l));
			}
			pressMsg.setVisible(true);
			borderpane.setBottom(ballotsDetails);
		}
	}

	private void addEventOnPressBallot(List<Label> ballotList, int index) {
		EventHandler<Event> pickBallot = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				String[] getBallotId = ballotList.get(index).getText().split(": ");
				int ballotId = Integer.parseInt(getBallotId[3]);
				citizenToAdd.setMyBallotId(ballotId);
				lastBallotPressing.setTextFill(Color.BLACK);
				lastBallotPressing = ballotList.get(index);
				mainViewListener.addCitizenOption2SendDetailsToMainView(citizenToAdd);
			}
		};
		ballotList.get(index).setOnMouseClicked(pickBallot);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			msgFinal.setTextFill(Color.GREEN);
			lastBallotPressing.setTextFill(Color.GREEN);
			msgFinal.setText("Succeeded to Add");
		} else {

			msgFinal.setTextFill(Color.RED);
			lastBallotPressing.setTextFill(Color.RED);
			msgFinal.setText(ans.toString());
		}
	}

	@Override
	public void clearPane() {
		lastBallotPressing.setTextFill(Color.BLACK);
		super.clearPane();
	}
}
