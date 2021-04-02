package Kind_Of_Persons;

import Exseptions.AgeException;
import Exseptions.IdException;
import Interface.Coronable;

public class SickCoronaPerson extends Person implements Coronable {
	private boolean dressSaftey;
	private int daysOfSicknes;

	public SickCoronaPerson(String name, int id, int birthday, int myBallotId) throws IdException, AgeException {
		super(name, id, birthday, myBallotId);
	}

	public boolean getDressSaftey() {
		return dressSaftey;
	}

	@Override
	public void isHeProtected(boolean dressSaftey) {
		this.dressSaftey = dressSaftey;
	}

	@Override
	public void numberDaysOfSicknes(int days) {
		daysOfSicknes = days;
	}

	public String toString() {
		StringBuffer back = new StringBuffer(getClass().getSimpleName() + " " + super.toString()
				+ ",number days of sicknes--->" + daysOfSicknes + "\n");
		return back.toString();
	}

	@Override
	public SickCoronaPerson clone() throws CloneNotSupportedException {
		return (SickCoronaPerson) super.clone();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof SickCoronaPerson)) {
			return false;
		}
		return super.equals(other);
	}
}
