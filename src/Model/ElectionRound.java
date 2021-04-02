package Model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Kind_Of_Persons.PartyMember;
import Kind_Of_Persons.Person;
import Kind_Of_Persons.RegularCitizen;
import Kind_Of_Persons.Solider;
import Kind_Of_Persons.SoliderSickCorona;
import Model.Party.eType;
import Kind_Of_Persons.SickCoronaPerson;

public class ElectionRound {
	// messages the user need to get--->
	public static enum eChangesUserNeedToKnow {
		CitizenOnList, IdBallotNotExsistNotSucceeded, PartyOnList, PartyIndexNotExsist, MemberNotExsistInListVoters,
		MemberExsistInParty, StatusNumberUncorrect, TheCitizenAlreadyMember, Succeeded, KindInputNotOk, ClassNameNotOk,
		SoliderCantBePartyMember, SickCoronaMustDressOk, inputMountYearNotOk, InpoutNotOK
	};

	public static enum etypeOfBallot {
		regular, corona, army
	};

	public static final int YEAR_MIN = 2020;
	public static final int YEAR_MAX = 2025;
	private MySet<Person> votList; // set is not java set,he is created
	private List<Party> partyList;
	private YearMonth yearMonth;
	private List<Ballot<RegularCitizen>> ballotRegularCitizenList;
	private List<Ballot<SickCoronaPerson>> ballotsickCoronaList;
	private List<Ballot<Solider>> ballotSoliderList;
	private List<Ballot<SoliderSickCorona>> ballotSoliderSickCoronaList;
	private int numbersOfPartys;
	private List<Integer> typeResult;
	public ElectionRound(int year, int month) {
		setYearMonth(year, month);
		votList = new MySet<Person>();
		partyList = new ArrayList<Party>();
		ballotRegularCitizenList = new ArrayList<Ballot<RegularCitizen>>();
		ballotsickCoronaList = new ArrayList<Ballot<SickCoronaPerson>>();
		ballotSoliderList = new ArrayList<Ballot<Solider>>();
		ballotSoliderSickCoronaList = new ArrayList<Ballot<SoliderSickCorona>>();
		typeResult=new ArrayList<Integer>();
		typeResult.add(0);
		typeResult.add(0);
		typeResult.add(0);
		numbersOfPartys = 0;
	}

	public ElectionRound() {
		this(0, 0);
	}

	public boolean setYearMonth(int year, int month) {
		if (month >= 1 && month <= 12 && year > YEAR_MIN && year < YEAR_MAX) {
			this.yearMonth = YearMonth.of(year, month);
			return true;
		}
		this.yearMonth = YearMonth.of(YEAR_MIN, 1);
		return false;
	}

	public int getCounterListVote() {
		return votList.size();
	}

	public eChangesUserNeedToKnow addCitizenTOList(Person citizenToAdd, int indexballot) {
		if (!(votList.add(citizenToAdd))) {
			
			return eChangesUserNeedToKnow.CitizenOnList;
		}
		return addCitizenToBallot(citizenToAdd, indexballot);
	}

	private eChangesUserNeedToKnow addCitizenToBallot(Person citizenToAdd, int idballot) {
		int theIndexOfBallotId;
		if (citizenToAdd.getClass() == SickCoronaPerson.class) {
			if (ballotsickCoronaList.isEmpty()) {
				ballotsickCoronaList.add(new Ballot<SickCoronaPerson>());
				idballot = ballotsickCoronaList.get(0).getIdBallot();
			}
			if (checkIfIndexOk(ballotsickCoronaList, idballot) != -1) {
				theIndexOfBallotId = checkIfIndexOk(ballotsickCoronaList, idballot);
				ballotsickCoronaList.get(theIndexOfBallotId).addCitizenTOList((SickCoronaPerson) citizenToAdd);
				updateIdBallotToPerson(citizenToAdd, ballotsickCoronaList.get(theIndexOfBallotId));
				return eChangesUserNeedToKnow.Succeeded;
			}
			deleteCitizen(citizenToAdd);
			return eChangesUserNeedToKnow.IdBallotNotExsistNotSucceeded;
		} else if (citizenToAdd.getClass() == RegularCitizen.class) {
			if (ballotRegularCitizenList.isEmpty()) {
				ballotRegularCitizenList.add(new Ballot<RegularCitizen>());
				idballot = ballotRegularCitizenList.get(0).getIdBallot();
			}
			if (checkIfIndexOk(ballotRegularCitizenList, idballot) != -1) {
				theIndexOfBallotId = checkIfIndexOk(ballotRegularCitizenList, idballot);
				ballotRegularCitizenList.get(theIndexOfBallotId).addCitizenTOList((RegularCitizen) citizenToAdd);
				updateIdBallotToPerson(citizenToAdd, ballotRegularCitizenList.get(theIndexOfBallotId));
				return eChangesUserNeedToKnow.Succeeded;
			}
			deleteCitizen(citizenToAdd);
			return eChangesUserNeedToKnow.IdBallotNotExsistNotSucceeded;
		} else if (citizenToAdd.getClass() == Solider.class) {
			if (ballotSoliderList.isEmpty()) {
				ballotSoliderList.add(new Ballot<Solider>());
				idballot = ballotSoliderList.get(0).getIdBallot();
			}
			if (checkIfIndexOk(ballotSoliderList, idballot) != -1) {
				theIndexOfBallotId = checkIfIndexOk(ballotSoliderList, idballot);
				ballotSoliderList.get(theIndexOfBallotId).addCitizenTOList((Solider) citizenToAdd);
				updateIdBallotToPerson(citizenToAdd, ballotSoliderList.get(theIndexOfBallotId));
				return eChangesUserNeedToKnow.Succeeded;
			}
			deleteCitizen(citizenToAdd);
			return eChangesUserNeedToKnow.IdBallotNotExsistNotSucceeded;
		} else if (citizenToAdd.getClass() == SoliderSickCorona.class) {
			if (ballotSoliderSickCoronaList.isEmpty()) {
				ballotSoliderSickCoronaList.add(new Ballot<SoliderSickCorona>());
				idballot = ballotSoliderSickCoronaList.get(0).getIdBallot();
			}
			if (checkIfIndexOk(ballotSoliderSickCoronaList, idballot) != -1) {
				theIndexOfBallotId = checkIfIndexOk(ballotSoliderSickCoronaList, idballot);
				ballotSoliderSickCoronaList.get(theIndexOfBallotId).addCitizenTOList((SoliderSickCorona) citizenToAdd);
				updateIdBallotToPerson(citizenToAdd, ballotSoliderSickCoronaList.get(theIndexOfBallotId));
				return eChangesUserNeedToKnow.Succeeded;
			}
			deleteCitizen(citizenToAdd);
			return eChangesUserNeedToKnow.IdBallotNotExsistNotSucceeded;
		}
		return eChangesUserNeedToKnow.KindInputNotOk;
	}

	private <T extends Ballot<?>> int checkIfIndexOk(List<T> arr, int index) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getIdBallot() == index) {
				return i;
			}
		}
		return -1;
	}

	private void deleteCitizen(Person citizen) {
		votList.clear(citizen);
	}

	private <T extends Person> void updateIdBallotToPerson(Person citizen, Ballot<T> ballot) {
		citizen.setMyBallotId(ballot.getIdBallot());
	}

	public eChangesUserNeedToKnow addBallot(String address, String KindOfBallot) {
		boolean inputOk = false;
		if (KindOfBallot.equals(SickCoronaPerson.class.getSimpleName())) {
			ballotsickCoronaList.add(new Ballot<SickCoronaPerson>(address));
			inputOk = true;
		} else if (KindOfBallot.equals(RegularCitizen.class.getSimpleName())) {
			ballotRegularCitizenList.add(new Ballot<RegularCitizen>(address));
			inputOk = true;
		} else if (KindOfBallot.equals(Solider.class.getSimpleName())) {
			ballotSoliderList.add(new Ballot<Solider>(address));
			inputOk = true;
		} else if (KindOfBallot.equals(SoliderSickCorona.class.getSimpleName())) {
			ballotSoliderSickCoronaList.add(new Ballot<SoliderSickCorona>(address));
			inputOk = true;
		}
		if (inputOk) {
			return eChangesUserNeedToKnow.Succeeded;
		}
		return eChangesUserNeedToKnow.ClassNameNotOk;
	}

	public eChangesUserNeedToKnow addPartTOList(Party partyToAdd) {
		if (partyList.contains(partyToAdd)) {
			return eChangesUserNeedToKnow.PartyOnList;
		}
		partyList.add((Party) partyToAdd);
		numbersOfPartys++;
		updateResultSizeInAllBallots();
		return eChangesUserNeedToKnow.Succeeded;
	}

	private void updateResultSizeInAllBallots() {
		updateGeneri(ballotRegularCitizenList);
		updateGeneri(ballotsickCoronaList);
		updateGeneri(ballotSoliderList);
		updateGeneri(ballotSoliderSickCoronaList);
	}

	private <T extends Ballot<?>> void updateGeneri(List<T> arr) {
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).setResultSize();
		}
	}

	public int getCounterPartys() {
		return numbersOfPartys;
	}

	public eChangesUserNeedToKnow addMemberExsist(int indexParty, int idCitizen, int statusMember) {
		try {
			if (indexParty >= numbersOfPartys || indexParty < 0) {
				return eChangesUserNeedToKnow.PartyIndexNotExsist;
			}
			int index = votList.getIndexPersonById(idCitizen);
			if (index != -1) {
				if (votList.get(index) instanceof Solider) {
					return eChangesUserNeedToKnow.SoliderCantBePartyMember;
				}
				PartyMember partyMemberToAdd = new PartyMember(votList.get(index), partyList.get(indexParty),
						statusMember);
				if (checkInAllPartyIfMemberExsist(partyMemberToAdd)) {
					return eChangesUserNeedToKnow.TheCitizenAlreadyMember;
				}
				return partyList.get(indexParty).addMemberTOList(partyMemberToAdd.clone());
			} else {
				return eChangesUserNeedToKnow.MemberNotExsistInListVoters;
			}
		} catch (Exception e) {
			return eChangesUserNeedToKnow.InpoutNotOK;
		}
	}

	private boolean checkInAllPartyIfMemberExsist(PartyMember partyMemberToAdd) {
		for (int i = 0; i < partyList.size(); i++) {
			if (partyList.get(i).checkIfMemberExsistInParty(partyMemberToAdd)) {
				return true;
			}
		}
		return false;
	}

	public void wantToVote(boolean wantToVote, int citizenToVoteIndex) {
		if (wantToVote) {
			if (votList.get(citizenToVoteIndex).getClass() == RegularCitizen.class) {
				ballotRegularCitizenList.get(selectionHelper(ballotRegularCitizenList, citizenToVoteIndex))
						.AddCitizenThatVotForSure();
			} else if (votList.get(citizenToVoteIndex).getClass() == SickCoronaPerson.class) {
				ballotsickCoronaList.get(selectionHelper(ballotsickCoronaList, citizenToVoteIndex))
						.AddCitizenThatVotForSure();
			} else if (votList.get(citizenToVoteIndex).getClass() == Solider.class) {
				ballotSoliderList.get(selectionHelper(ballotSoliderList, citizenToVoteIndex))
						.AddCitizenThatVotForSure();
			} else if (votList.get(citizenToVoteIndex).getClass() == SoliderSickCorona.class) {
				ballotSoliderSickCoronaList.get(selectionHelper(ballotSoliderSickCoronaList, citizenToVoteIndex)).AddCitizenThatVotForSure();
			}
		}
	}

	public <T extends Person> int selectionHelper(List<Ballot<T>> ballot, int citizenToVoteIndex) {
		for (int i = 0; i < ballot.size(); i++) {
			if (votList.get(citizenToVoteIndex).getBallotId() == ballot.get(i).getIdBallot()) {
				return i;
			}
		}
		return -1;
	}

	public void addVoteToParty(int indexParty, int citizenToVoteIndex) {
		if (votList.get(citizenToVoteIndex).getClass() == RegularCitizen.class) {
			ballotRegularCitizenList.get(selectionHelper(ballotRegularCitizenList, citizenToVoteIndex))
					.addResultVot(indexParty);
		} else if (votList.get(citizenToVoteIndex).getClass() == SickCoronaPerson.class) {
			ballotsickCoronaList.get(selectionHelper(ballotsickCoronaList, citizenToVoteIndex))
					.addResultVot(indexParty);
		} else if (votList.get(citizenToVoteIndex).getClass() == Solider.class) {
			ballotSoliderList.get(selectionHelper(ballotSoliderList, citizenToVoteIndex)).addResultVot(indexParty);
		} else if (votList.get(citizenToVoteIndex).getClass() == SoliderSickCorona.class) {
			ballotSoliderSickCoronaList.get(selectionHelper(ballotSoliderSickCoronaList, citizenToVoteIndex))
					.addResultVot(indexParty);
		}
		updateTypeNumbers(partyList.get(indexParty).getType());
	}

	// all string option use
	public String[] electionResult() {
		updateResultSizeInAllBallots();
		Vector<Integer> result = new Vector<Integer>();
		resultSize(result);
		StringBuffer back = new StringBuffer(toString() + "the result in ballots--->\n");
		StringBuffer back1 = new StringBuffer();
		back.append("Regular ballots--->\n");
		generiHelp(ballotRegularCitizenList, result, back);
		back.append("sickPersons ballots--->\n");
		generiHelp(ballotsickCoronaList, result, back);
		back.append("Solider ballots--->\n");
		generiHelp(ballotSoliderList, result, back);
		back.append("Sick Solider ballots--->\n");
		generiHelp(ballotsickCoronaList, result, back);
		back1.append("\nthe final result--->\n");
		for (int i = 0; i < numbersOfPartys; i++) {
			back1.append(partyList.get(i).getName() + "--->" + result.get(i) + "\n");
		}
		String[] ans=new String[2];
		ans[0]=back.toString();
		ans[1]=back1.toString();
		return ans;
	}

	public <T extends Ballot<?>> String generiHelp(List<T> arr, Vector<Integer> result, StringBuffer back) {
		for (int i = 0; i < arr.size(); i++) {
			back.append(arr.get(i).showIdAndType() + "selections:\n");
			for (int j = 0; j < numbersOfPartys; j++) {
				back.append(partyList.get(j).getName() + "--->" + arr.get(i).showResultInOneIndexArray(j) + "\n");
				result.set(j, result.get(j) + arr.get(i).showResultInOneIndexArray(j));
			}
			arr.get(i).updateVotingPresnt();
			back.append("voting present: " + arr.get(i).getVotingPresent() + "\n\n");
		}
		return back.toString();
	}

	private void resultSize(Vector<Integer> result) {
		for (int i = 0; i < numbersOfPartys; i++) {
			result.add(0);
		}
	}

	public String[] showAllBallots() {
		String[] msg = new String[4];
		StringBuffer back1 = new StringBuffer("Ballots list---> \n");
		StringBuffer back2 = new StringBuffer();
		StringBuffer back3 = new StringBuffer();
		StringBuffer back4 = new StringBuffer();
		showAllBallotsGeneriHelp(ballotRegularCitizenList, back1, RegularCitizen.class.getSimpleName());
		showAllBallotsGeneriHelp(ballotsickCoronaList, back2, SickCoronaPerson.class.getSimpleName());
		showAllBallotsGeneriHelp(ballotSoliderList, back3, Solider.class.getSimpleName());
		showAllBallotsGeneriHelp(ballotSoliderSickCoronaList, back4, SoliderSickCorona.class.getSimpleName());
		msg[0] = back1.toString();
		msg[1] = back2.toString();
		msg[2] = back3.toString();
		msg[3] = back4.toString();
		return msg;
	}

	public <T extends Ballot<?>> void showAllBallotsGeneriHelp(List<T> arr, StringBuffer back, String className) {
		back.append("Ballots Kind---> " + className + "\n");
		for (int i = 0; i < arr.size(); i++) {
			back.append(arr.get(i).toString());
		}
	}

	public String showAllCitizen() {
		StringBuffer back = new StringBuffer("Citizen list--->: \n");
		for (int i = 0; i < votList.size(); i++) {
			back.append(votList.get(i).toString());
		}
		return back.toString();
	}

	public String showAllPartys() {
		StringBuffer back = new StringBuffer("Party list--->: \n");
		for (int i = 0; i < partyList.size(); i++) {
			back.append(partyList.get(i).toString());
		}
		return back.toString();
	}

	// override methods
	public String toString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");
		return "the election at ---> " + this.yearMonth.format(format) + "\n";
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof ElectionRound)) {
			return false;
		}
		ElectionRound other = (ElectionRound) obj;
		if (other.yearMonth.getYear() == this.yearMonth.getYear()
				&& other.yearMonth.getMonthValue() == this.yearMonth.getMonthValue()) {
			return true;
		}
		return false;
	}
	public List<Integer> updateTypeNumbers(eType party) {
			if (party == eType.LEFT) {
				typeResult.set(0, (typeResult.get(0)+1));
			}
			else if (party == eType.CENTER) {
				typeResult.set(1, (typeResult.get(1)+1));
			}
			else {
				typeResult.set(2, (typeResult.get(2)+1));
			}
		
		return typeResult;
	}

	public List<Integer> getTypeNumbers() {
		return typeResult;
	}
	
}
