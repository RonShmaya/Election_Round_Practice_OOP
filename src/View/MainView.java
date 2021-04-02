package View;

import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.StrokeBorder;
import javax.swing.event.ChangeEvent;

import Exseptions.IdException;
import Kind_Of_Persons.Person;
import Kind_Of_Persons.RegularCitizen;
import Kind_Of_Persons.SickCoronaPerson;
import Kind_Of_Persons.Solider;
import Kind_Of_Persons.SoliderSickCorona;
import Listeners.ListenersOfUi;
import Model.Ballot;
import Model.ElectionRound;
import Model.ElectionRound.eChangesUserNeedToKnow;
import Model.Party.eType;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView {
	private ListenersOfUi listener;
	private HBox hboxMain;
	private Button btnAdd;
	private Label isSuccededMsg;
	private Button menuButton;
	private GridPane gridPane;
	private TextField enterMountField;
	private TextField enterYearField;
	private Pane placeOption;
	private RadioButton option1;
	private RadioButton option2;
	private RadioButton option3;
	private RadioButton option4;
	private RadioButton option5;
	private RadioButton option6;
	private RadioButton option7;
	private RadioButton option8;
	private RadioButton option9;
	private RadioButton option10;
	private ViewOption optionView;
	private List<Label> listBallotDetailsReg;
	private List<Label> listBallotDetailsSick;
	private List<Label> listBallotDetailsSolider;
	private List<Label> listBallotDetailsSickSolider;
	private List<Label> listPerson;
	private List<Label> listPartys;
	private String[] ballotsShow;
	private String allCitizenShow;
	private String allPartysShow;
	private String[] electionResult;
	private List<Integer> typePartysResult;
	private boolean isElectionWas;
	private boolean inProsessVoting;
	private int counterIfFirstTimeMenu; // we clear menu in changes, first time we cant

	public MainView(Stage stage) {
		stage.setTitle("Election Round");
		counterIfFirstTimeMenu = 0;
		ballotsShow = new String[4];
		listBallotDetailsReg = new ArrayList<Label>();
		listBallotDetailsSick = new ArrayList<Label>();
		listBallotDetailsSolider = new ArrayList<Label>();
		listBallotDetailsSickSolider = new ArrayList<Label>();
		listPartys = new ArrayList<Label>();
		listPerson = new ArrayList<Label>();
		allCitizenShow = new String();
		allPartysShow = new String();
		electionResult = new String[2];
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		// inside gridePane
		Label enterMountMsg = new Label("Enter Mount(1-12):");
		enterMountField = new TextField();
		Label enterYearMsg = new Label("enter year(2020 between 2025):");
		enterYearField = new TextField();
		btnAdd = new Button("Add");
		isSuccededMsg = new Label();
		menuButton = new Button("Menu");
		menuButton.setVisible(false);
		gridPane.add(enterMountMsg, 0, 0);
		gridPane.add(enterMountField, 1, 0);
		gridPane.add(enterYearMsg, 0, 1);
		gridPane.add(enterYearField, 1, 1);
		gridPane.add(btnAdd, 0, 2);
		gridPane.add(isSuccededMsg, 0, 3);
		gridPane.add(menuButton, 1, 3);
		hboxMain = new HBox();
		hboxMain.getChildren().add(gridPane);
		EventHandler<Event> pressAddBtn = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				if (checkInputCorrect(enterMountField.getText()) && checkInputCorrect(enterYearField.getText())) {
					int mount = Integer.parseInt(enterMountField.getText());
					int year = Integer.parseInt(enterYearField.getText());
					listener.uiUpdateModelMountAndYear(mount, year);
				} else {
					isSuccededMsg.setText(ElectionRound.eChangesUserNeedToKnow.inputMountYearNotOk.toString());
				}
			}
		};
		EventHandler<Event> goToMenu = new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				showMenu(stage);
			}
		};
		btnAction(pressAddBtn);
		btnActionGoMenu(goToMenu);
		stage.setScene(new Scene(hboxMain, 1200, 800));
		stage.show();
	}

	public void addListener(ListenersOfUi listener) {
		this.listener = listener;
	}

	private void btnAction(EventHandler<Event> pressAddBtn) {
		btnAdd.setOnMouseClicked(pressAddBtn);

	}

	public void updateResultYearMountInUi(eChangesUserNeedToKnow ans) {
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			isSuccededMsg.setText("Succedeeded ---> ");
			menuButton.setVisible(true);
		} else {
			isSuccededMsg.setText(ans.toString());
		}

	}

	private void showMenu(Stage stage) {
		stage.setTitle("Election Round, Mount: " + enterMountField.getText() + ", Year: " + enterYearField.getText());
		hboxMain.getChildren().remove(gridPane);
		GridPane menu = new GridPane();
		listener.uiAskForHardCoded();
		menu.setAlignment(Pos.TOP_LEFT);
		menu.setPadding(new Insets(10));
		menu.setHgap(10);
		menu.setVgap(10);
		ToggleGroup groupMenu = new ToggleGroup();
		option1 = new RadioButton("Add Ballot");
		option2 = new RadioButton("Add Citizen");
		option3 = new RadioButton("Add Party");
		option4 = new RadioButton("Add Party Member");
		option5 = new RadioButton("Show All Ballots");
		option6 = new RadioButton("Show All Citizen");
		option7 = new RadioButton("Show All Partys");
		option8 = new RadioButton("Start Election Round");
		option9 = new RadioButton("Show Election Result");
		option10 = new RadioButton("Exit");
		option1.setToggleGroup(groupMenu);
		option2.setToggleGroup(groupMenu);
		option3.setToggleGroup(groupMenu);
		option4.setToggleGroup(groupMenu);
		option5.setToggleGroup(groupMenu);
		option6.setToggleGroup(groupMenu);
		option7.setToggleGroup(groupMenu);
		option8.setToggleGroup(groupMenu);
		option9.setToggleGroup(groupMenu);
		option10.setToggleGroup(groupMenu);
		Label menuTitle = new Label("Menu");
		menuTitle.setFont(new Font(25));
		menu.add(menuTitle, 0, 0, 10, 1);
		menu.add(option1, 0, 1);
		menu.add(option2, 0, 2);
		menu.add(option3, 0, 3);
		menu.add(option4, 0, 4);
		menu.add(option5, 0, 5);
		menu.add(option6, 0, 6);
		menu.add(option7, 0, 7);
		menu.add(option8, 0, 8);
		menu.add(option9, 0, 9);
		menu.add(option10, 0, 10);
		placeOption = new Pane();
		hboxMain.getChildren().addAll(menu, placeOption);
		events();
	}

	private void btnActionGoMenu(EventHandler<Event> goToMenu) {
		menuButton.setOnMouseClicked(goToMenu);
	}

	private void events() {
		EventHandler<Event> eventop1 = new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				optionMenu(1);

			}
		};
		option1.setOnMouseClicked(eventop1);
		EventHandler<Event> eventop2 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(2);
			}
		};
		option2.setOnMouseClicked(eventop2);
		EventHandler<Event> eventop3 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(3);
			}
		};
		option3.setOnMouseClicked(eventop3);
		EventHandler<Event> eventop4 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(4);
			}
		};
		option4.setOnMouseClicked(eventop4);
		EventHandler<Event> eventop5 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(5);
			}
		};
		option5.setOnMouseClicked(eventop5);
		EventHandler<Event> eventop6 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(6);
			}
		};
		option6.setOnMouseClicked(eventop6);
		EventHandler<Event> eventop7 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(7);
			}
		};
		option7.setOnMouseClicked(eventop7);
		EventHandler<Event> eventop8 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(8);
			}
		};
		option8.setOnMouseClicked(eventop8);
		EventHandler<Event> eventop9 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(9);
			}
		};
		option9.setOnMouseClicked(eventop9);
		EventHandler<Event> eventop10 = new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				optionMenu(10);
			}
		};
		option10.setOnMouseClicked(eventop10);
	}

	private void optionMenu(int optionIndex) {
		if (counterIfFirstTimeMenu != 0) {
			optionView.clearPane();
		}
		switch (optionIndex) {
		case 1:
			optionView = new ViewOption1AddBallot(placeOption, this);
			break;
		case 2:
			optionView = new ViewOption2AddCitizen(placeOption, this);
			break;
		case 3:
			optionView = new ViewOption3AddParty(placeOption, this);
			break;
		case 4:
			optionView = new ViewOption4AddPartyMember(placeOption, this);
			break;
		case 5:
			optionView = new ViewOption5ShowBallots(placeOption, this);
			break;
		case 6:
			optionView = new ViewOption6ShowAllCitizen(placeOption, this);
			break;
		case 7:
			optionView = new ViewOption7ShowAllPartys(placeOption, this);
			break;
		case 8:
			optionView = new ViewOption8ElectionRound(placeOption, this);
			break;
		case 9:
			optionView = new ViewOption9ShowElectionRound(placeOption, this);
			break;
		case 10:
			hboxMain.setDisable(true);
			break;
		}
		counterIfFirstTimeMenu++;
	}

	public static boolean checkInputCorrect(String strToCheck) {
		if (strToCheck.isEmpty()) {
			return false;
		}
		char[] ch = strToCheck.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (!(Character.isDigit(ch[i]))) {
				return false;
			}
		}
		return true;
	}

	public void addBallotMainView(String address, String kind, int ballotId) {
		Label ballotLabel = new Label("Address: " + address + "\n" + "Kind: " + kind + "\n" + "id: " + ballotId);
		ballotLabel.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		ballotLabel.setPadding(new Insets(5));
		if (kind.equals(RegularCitizen.class.getSimpleName())) {
			listBallotDetailsReg.add(ballotLabel);
		} else if (kind.equals(SickCoronaPerson.class.getSimpleName())) {
			listBallotDetailsSick.add(ballotLabel);
		} else if (kind.equals(Solider.class.getSimpleName())) {
			listBallotDetailsSolider.add(ballotLabel);
		} else {
			listBallotDetailsSickSolider.add(ballotLabel);
		}
	}

	public List<Label> getBallotListReg() {
		return listBallotDetailsReg;
	}

	public List<Label> getBallotListSick() {
		return listBallotDetailsSick;
	}

	public List<Label> getBallotListSolider() {
		return listBallotDetailsSolider;
	}

	public List<Label> getBallotListSoliderSick() {
		return listBallotDetailsSickSolider;
	}

	public void addVoterMainView(String name, int id, String kind) {
		Label citizenLabel = new Label("Kind: " + kind + "\n" + "Name: " + name + "\n" + "id: " + id);
		citizenLabel.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		citizenLabel.setPadding(new Insets(5));
		listPerson.add(citizenLabel);
	}

	public void addPartyMainView(String name, eType type) {
		Label PartyLabel = new Label("Type: " + type.toString() + "\n" + "Name: " + name);
		PartyLabel.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		PartyLabel.setPadding(new Insets(5));
		listPartys.add(PartyLabel);
	}

	public List<Label> getPartyList() {
		return listPartys;
	}

	public List<Label> getCitizenList() {
		return listPerson;
	}

	public void addBallotOption1SendDetailsToMainView(String address, String kind) {
		listener.uiUpdateModelBallotDetails(address, kind);
	}

	public void updateResultMsgFromModel(eChangesUserNeedToKnow ans) {
		optionView.mainViewUpdateOption(ans);
	}

	public void addCitizenOption2SendDetailsToMainView(Person citizenToAdd) {
		listener.uiUpdateModelCitizenDetails(citizenToAdd);
	}

	public void addPartyOption3SendDetailsToMainView(String name, eType type, int year) {
		listener.uiUpdateModelPartyDetails(name, type, year);
	}

	public void addPartyMemberOption4SendDetailsToMainView(int idCitzen, int PartyIndex) {
		listener.uiUpdateModelPartyMemberDetails(idCitzen, PartyIndex);
	}

	public void citizenFullShow(String citizenString) {
		allCitizenShow = citizenString;
	}

	public void ballotFullShow(String[] ballot) {
		ballotsShow = ballot;
	}

	public void partysFullShow(String partys) {
		allPartysShow = partys;
	}

	public void electionResult(String[] result) {
		electionResult = result;
	}

	public String[] getBallotShow() {
		return ballotsShow;
	}

	public String getShowAllCitizen() {
		return allCitizenShow;
	}

	public String getShowAllParyts() {
		return allPartysShow;
	}

	public void isWasElection(boolean ans) {
		isElectionWas = ans;
	}

	public boolean getIsWasElection() {
		return isElectionWas;
	}

	public void updateMainViewIsWasElections() {
		isElectionWas = true;
		listener.uiUpdateModelElectionWas();
	}

	public void updateMainViewWantToVote(int indexOfCitizen, boolean ans) {
		listener.uiUpdateModelWantToVote(indexOfCitizen, ans);

	}

	public void updateMainViewSelection(int indexOfCitizen, int indexOfParty) {
		listener.uiUpdateModelSelection(indexOfCitizen, indexOfParty);

	}

	public String[] getElectionResult() {
		return electionResult;
	}

	public void uiAskForResultElection() {
		listener.uiAskForResultSelection();
	}
	public void setInProsessVoting(boolean ans) {
		inProsessVoting=ans;
	}
	public boolean getInProsessVoting() {
		return inProsessVoting;
	}

	public void getTypePartyResult() {
		listener.uiAskFromModelTypesPartysResult();
	}
	public void modelSendPartysTypeResult(List<Integer> typeNumbers) {
		typePartysResult=typeNumbers;
	}
	public List<Integer> getTypePartyResultToOption(){
		return typePartysResult;
	}
}
