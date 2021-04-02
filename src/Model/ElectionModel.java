package Model;

import java.util.List;

import Exseptions.AgeException;
import Exseptions.IdException;
import Interface.Coronable;
import Kind_Of_Persons.Person;
import Kind_Of_Persons.RegularCitizen;
import Kind_Of_Persons.SickCoronaPerson;
import Listeners.ListenersOfModel;
import Model.ElectionRound.eChangesUserNeedToKnow;
import Model.Party.eType;

public class ElectionModel {
	public static final int TO_ADD_BALLOT = 1;
	public static final int TO_ADD_CITIZEN = 2;
	public static final int TO_ADD_PARTY = 3;
	public static final int TO_ADD_MEMBER_PARTY = 4;
	public static final int TO_SHOW_ALL_BALLOTS = 5;
	public static final int TO_SHOW_ALL_CITIZEN = 6;
	public static final int TO_SHOW_ALL_PARTY = 7;
	public static final int TO_START_ELECTION_ROUND = 8;
	public static final int TO_SHOW_RESULT_ELECTION_ROUND = 9;
	public static final int TO_EXIT_MENU = 10;

	private ElectionRound electionRound;
	private boolean electionSelectWas;
	private ListenersOfModel listener;

	public ElectionModel() {
		electionRound = new ElectionRound();
		electionSelectWas = false;
	}

	public void addListener(ListenersOfModel listener) {
		this.listener = listener;
	}

	public eChangesUserNeedToKnow setMountAndYear(int mount, int year) {
		if (mount < 1 || mount > 12 || year < ElectionRound.YEAR_MIN || year > ElectionRound.YEAR_MAX) {
			return eChangesUserNeedToKnow.inputMountYearNotOk;
		} else {
			electionRound.setYearMonth(year, mount);
			return eChangesUserNeedToKnow.Succeeded;
		}

	}

	public void setElectionSelectWas(boolean answer) {
		this.electionSelectWas = true;
		listener.modelUpdateUiElectionWas(answer);
	}

	public boolean getElectionSelectWas() {
		return this.electionSelectWas;
	}

	public eChangesUserNeedToKnow addBallot(String address, String className) {// add ballot
		eChangesUserNeedToKnow ans = electionRound.addBallot(address, className);
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			listener.modelSendUiBallotDetails(address, className, (Ballot.idBollat - 1));// return her
			listener.modelUpdateUiBallotFullShow(electionRound.showAllBallots());
		}
		return ans;
	}

	public eChangesUserNeedToKnow addCitizen(Person citizenToAdd, int indexBallot) {// add citizen
		eChangesUserNeedToKnow ans;
		if (citizenToAdd instanceof Coronable && ((Coronable) citizenToAdd).getDressSaftey() == false) {
			return eChangesUserNeedToKnow.SickCoronaMustDressOk;
		}
		ans = electionRound.addCitizenTOList(citizenToAdd, indexBallot);
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			listener.modelSendUiCitizenDetails(citizenToAdd.getName(), citizenToAdd.getClass().getSimpleName(),
					citizenToAdd.getId());
			listener.modelUpdateUiBallotFullShow(electionRound.showAllBallots());
			listener.modelUpdateUiCitizenFullShow(electionRound.showAllCitizen());
		}
		return ans;
	}

	public eChangesUserNeedToKnow addParty(String name, eType type, int date) {// add party
		Party party = new Party(name, type, date);
		eChangesUserNeedToKnow ans = electionRound.addPartTOList(party);
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			listener.modelSendUiPartyDetails(name, type);
			listener.modelUpdateUiPartysFullShow(electionRound.showAllPartys());
		}
		return ans;
	}

	public eChangesUserNeedToKnow addMemberParty(int id, int statusMember, int partyUserPick) {// add party member
		eChangesUserNeedToKnow ans = electionRound.addMemberExsist(partyUserPick, id, statusMember);
		if (ans == eChangesUserNeedToKnow.Succeeded) {
			listener.modelUpdateUiPartysFullShow(electionRound.showAllPartys());
		}
		return ans;
	}

	public void showBallot() {
		listener.modelUpdateUiBallotFullShow(electionRound.showAllBallots());
	}

	public String showCitizen() {
		return electionRound.showAllCitizen();
	}

	public String showParty() {
		return electionRound.showAllPartys();
	}

	public void electionRoundwantToVote(int indexCitizen, boolean wantToVote) {// election--->want to vote or not
		electionRound.wantToVote(wantToVote, indexCitizen);
	}

	public boolean electionRoundSelections(int indexCitizen, int indexParty) {// election---> chooise voter
		if (electionSelectWas == true) {
			return false;
		}
		electionRound.addVoteToParty(indexParty, indexCitizen);
		return true;
	}

	public void electionRoundResult() {
		if (electionSelectWas == false) {
			return;
		} else {
			listener.modelUpdateUiResultElection(electionRound.electionResult());
		}
	}

	public int getCounterVoteList() {
		return electionRound.getCounterListVote();
	}
	public void electionPartysTypeResult() {
		listener.modelUpdateUiTypePartysResult(electionRound.getTypeNumbers());
	}
	public void hardCoded() {
		try {
			this.addBallot("tel aviv-25", RegularCitizen.class.getSimpleName());
			this.addBallot("rishon letzon-5", SickCoronaPerson.class.getSimpleName());
			Person c1 = new RegularCitizen("yoni", 123456789, 1995, 0);
			Person c2 = new RegularCitizen("rami levi", 999999991, 1997, 0);
			Person c3 = new RegularCitizen("mirav ku", 999999992, 1995, 0);
			Person c4 = new RegularCitizen("ali baba", 999999993, 1994, 0);
			SickCoronaPerson c5 = new SickCoronaPerson("tony moni", 999999994, 1993, 0);
			SickCoronaPerson c6 = new SickCoronaPerson("tofi s", 999999995, 1993, 0);
			SickCoronaPerson c7 = new SickCoronaPerson("gilt lt", 999999996, 1993, 0);
			SickCoronaPerson c8 = new SickCoronaPerson("reasd rt", 999999997, 1993, 0);
			c5.isHeProtected(true);
			c6.isHeProtected(true);
			c7.isHeProtected(true);
			c8.isHeProtected(true);
			c5.numberDaysOfSicknes(5);
			c6.numberDaysOfSicknes(7);
			c7.numberDaysOfSicknes(3);
			c8.numberDaysOfSicknes(9);
			this.addCitizen(c1, 0);
			this.addCitizen(c2, 0);
			this.addCitizen(c3, 0);
			this.addCitizen(c4, 0);
			this.addCitizen(c5, 1);
			this.addCitizen(c6, 1);
			this.addCitizen(c7, 1);
			this.addCitizen(c8, 1);
			this.addParty("togeder", eType.LEFT, 1970);
			this.addParty("for you", eType.RIGHT, 1990);
			this.addParty("the go party", eType.CENTER, 1969);
			this.addMemberParty(c1.getId(), 0, 0);
			this.addMemberParty(c2.getId(), 0, 0);
		} catch (IdException e) {
			System.out.println("Problem In Hard-Coded, is--->" + e.getMessage() + " Not All Citizen Success to get in");
		} catch (AgeException e) {
			System.out.println("Problem In Hard-Coded, is--->" + e.getMessage() + " Not All Citizen Success to get in");
		} catch (Exception e) {
			System.out.println("Problem In Hard-Coded, is--->" + e.getMessage());
		}
	}

	

}
