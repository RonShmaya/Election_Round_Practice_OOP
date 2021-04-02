package Kind_Of_Persons;

import Exseptions.AgeException;
import Exseptions.IdException;
import Model.Party;

public class PartyMember extends Person {
	private Party myParty;
	private int statusNumber;

	public PartyMember(String name, int id, int birthday, int myBallotId, Party myParty, int statusNumber)
			throws IdException, AgeException, CloneNotSupportedException {
		super(name, id, birthday, myBallotId);
		setStatusNumber(statusNumber);
		this.myParty = myParty.clone();
	}

	public PartyMember() throws Exception {// defult
		this("name", 0, 0, 0, new Party(), 0);
	}

	public PartyMember(Person citizen, Party myParty, int statusNumber)
			throws IdException, AgeException, CloneNotSupportedException {// copy citizen---> regular or sick
		this(citizen.name, citizen.id, citizen.birthday, citizen.myBallotId, myParty, statusNumber);
	}

	public void setPartyOfMember(Party myParty) throws CloneNotSupportedException {
		this.myParty = myParty.clone();
	}

	public boolean setStatusNumber(int statusNumber) {
		if (statusNumber > 0) {
			this.statusNumber = statusNumber;
			return true;
		}
		this.statusNumber = 0;
		return false;
	}

	public Party getMyParty() throws CloneNotSupportedException {
		return myParty.clone();
	}

	public int getStatusNumber() {
		return this.statusNumber;
	}

	public String toString() {
		String back = "party member---> " + super.toString() + ", the name of his party: " + myParty.getName()
				+ " the status member in the party is: " + statusNumber + "\n";
		return back;
	}

	public boolean equals(Object other) {
		if (!(other instanceof PartyMember)) {
			return false;
		}
		return super.equals(other); // equals in citizen
	}

	@Override
	public PartyMember clone() throws CloneNotSupportedException {
		return (PartyMember) super.clone();
	}

}
