package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Exseptions.AgeException;
import Exseptions.IdException;
import Kind_Of_Persons.Person;
import Kind_Of_Persons.Solider;

public class Ballot<T extends Person> {

	public static int idBollat = 0;

	private int myIdBollat;
	private String address;
	private List<T> votList;
	private List<Integer> resultList;
	private double votingPresnt;
	private int citizenThatVotForSure;

	public Ballot(String address) {
		setAddress(address);
		myIdBollat = idBollat++;
		this.votList = new ArrayList<T>();
		resultList = new Vector<Integer>();
		votingPresnt = 0;
		citizenThatVotForSure = 0;
	}

	public Ballot() {
		this("add_address");
	}

	public boolean setAddress(String address) {
		if (address != null && address != "") {
			this.address = address;
			return true;
		}
		this.address = "address_not_ok";
		return false;
	}

	public int getIdBallot() {
		return myIdBollat;
	}

	public String getAddressBallot() {
		return address;
	}

	public void AddCitizenThatVotForSure() {
		citizenThatVotForSure++;
	}

	public void updateVotingPresnt() {
		if (votList.size() != 0) {
			votingPresnt = (citizenThatVotForSure / (double) votList.size()) * 100;
		} else {
			votingPresnt = 0;
		}
	}

	public double getVotingPresent() {
		return votingPresnt;
	}

	public void setResultSize() {
		resultList.add(0);
	}

	public void addCitizenTOList(T citizenToAdd) {
		votList.add(citizenToAdd);
	}

	public void addResultVot(int index) {
		if (index >= 0 && index < resultList.size()) {
			resultList.set(index, (resultList.get(index) + 1));
		}
	}

	public int showResultInOneIndexArray(int index) {
		if (index >= 0 && index < resultList.size()) {
			return resultList.get(index);
		} else {
			return 0;
		}
	}

	public boolean equals(Object other) {
		if (!(other instanceof Ballot)) {
			return false;
		}
		if (this.myIdBollat != ((Ballot) other).myIdBollat) {
			return false;
		}
		return true;
	}

	public String toString() {
		updateVotingPresnt();
		StringBuffer back = new StringBuffer(
				"id ballot: " + myIdBollat + ", the address is: " + address + ", voters list:\n");
		for (int i = 0; i < votList.size(); i++) {
			back.append(votList.get(i).toString());
		}
		back.append("voting present for now is: " + votingPresnt + "\n");
		return back.toString();
	}

	public String showIdAndType() {
		StringBuffer back = new StringBuffer("id ballot: " + myIdBollat + "\n");
		return back.toString();
	}
}
