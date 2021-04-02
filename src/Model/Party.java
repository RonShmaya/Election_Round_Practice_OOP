package Model;

import java.util.Vector;

import Kind_Of_Persons.PartyMember;
import Model.ElectionRound.eChangesUserNeedToKnow;

public class Party implements Cloneable {
	public static enum eType {
		RIGHT, LEFT, CENTER
	};

	public static final int DATE_MAX = 2020;
	public static final int DATE_MIN = 1948;

	private eType type;
	private Vector<PartyMember> members;
	private String name;
	private int date;

	public Party(String name, eType type, int date) {
		setName(name);
		setDate(date);
		this.type = type;
		this.members = new Vector<PartyMember>();
	}

	public Party() {
		this("party", eType.CENTER, 2020);
	}

	public boolean setDate(int date) {
		if (date > DATE_MIN && date <= DATE_MAX) {
			this.date = date;
			return true;
		}
		this.date = DATE_MAX;
		return false;
	}

	public boolean setName(String name) {
		if (name != "" && name != null) {
			this.name = name;
			return true;
		}
		this.name = "input_name_not_ok";
		return false;
	}

	public String getName() {
		return name;
	}
	public eType getType() {
		return this.type;
	}
	public boolean checkIfMemberExsistInParty(PartyMember partyMemberToAdd) {
		return members.contains(partyMemberToAdd);
	}

	public eChangesUserNeedToKnow addMemberTOList(PartyMember partyMemberToAdd) throws CloneNotSupportedException {
		if (members.contains(partyMemberToAdd)) {
			return eChangesUserNeedToKnow.MemberExsistInParty;
		}
		partyMemberToAdd.setStatusNumber(members.size());
		members.add(partyMemberToAdd.clone());
		return eChangesUserNeedToKnow.Succeeded;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		}
		Party otherPartyForShore = (Party) other;
		if (name.equals(otherPartyForShore.name)) {
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuffer back = new StringBuffer("the name of the party is: " + name + ", the type of party is: " + type
				+ ", the party start at " + date + ", the party members is:\n");
		for (int i = 0; i < members.size(); i++) {
			back.append(members.get(i) + "\n");

		}
		return back.toString();
	}

	@Override
	public Party clone() throws CloneNotSupportedException {
		Party temp = (Party) super.clone();
		for (int i = 0; i < temp.members.size(); i++) {
			temp.members.set(i, members.get(i).clone());
		}
		return temp;
	}
}
