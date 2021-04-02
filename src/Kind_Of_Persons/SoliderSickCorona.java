package Kind_Of_Persons;

import Exseptions.AgeException;
import Exseptions.IdException;
import Interface.Armyable;
import Interface.Coronable;

public class SoliderSickCorona extends Solider implements Coronable {
	private boolean dressSaftey;
	private int daysOfSicknes;
	private boolean isCarryWeapon;

	public SoliderSickCorona(String name, int id, int birthday, int myBallotId) throws IdException, AgeException {
		super(name, id, birthday, myBallotId);
	}

	public boolean getDressSaftey() {
		return dressSaftey;
	}

	@Override
	public void carryWeapon(boolean ans) {
		isCarryWeapon = ans;
	}

	@Override
	public void isHeProtected(boolean ans) {
		dressSaftey = ans;
	}

	@Override
	public void numberDaysOfSicknes(int days) {
		daysOfSicknes = days;
	}

	@Override
	public SoliderSickCorona clone() throws CloneNotSupportedException {
		return (SoliderSickCorona) super.clone();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof SoliderSickCorona)) {
			return false;
		}
		return super.equals(other);
	}

	public String toString() {
		StringBuffer back = new StringBuffer(
				getClass().getSimpleName() + " " + "Name: " + name + ", id: " + id + ", birthday: " + birthday
						+ ", ballot: " + myBallotId + ",number days of sicknes--->" + daysOfSicknes + ", ");
		if (isCarryWeapon) {
			back.append("He is carry weapon \n");
		} else {
			back.append("He is not carry weapon \n");
		}
		return back.toString();
	}
}
